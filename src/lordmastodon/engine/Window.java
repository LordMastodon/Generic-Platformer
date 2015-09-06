package lordmastodon.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import lordmastodon.engine.graphics.Display;

public class Window implements Runnable {
	
	//Actual window
	private JFrame _frame;
	
	//Display
	private Display _display;
	
	//Window size
	public int _width, _height;
	
	//Window title
	public String _title;
	
	//The FPS
	public int TARGET_TPS;
	
	//Running variable
	public boolean running;
	
	public Window(int width, int height, String title) {
		//Store variables
		_width = width;
		_height = height;
		_title = title;
		
		//Set the target FPS
		TARGET_TPS = 120;
		
		//Create window
		_frame = new JFrame(title);
		
		//Set frame variables
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setPreferredSize(new Dimension(width, height));
		_frame.setResizable(false);
		_frame.pack();
		_frame.setLocationRelativeTo(null);
		
		//Set visible
		_frame.setVisible(true);
		
		//Create the display
		_display = new Display(this);
		
		//Add display to frame
		_frame.add(_display);
	}
	
	public void start() {
		//Run the game
		running = true;
		
		//Create the thread
		new Thread(this).start();
	}
	
	public void stop() {
		//Stop running the game
		running = false;
	}
	
	public void tick() {
		
	}
	
	public void render() {
		//Buffer strategy
		BufferStrategy bufferStrategy = _display.getBufferStrategy();
		
		if (bufferStrategy == null) {
			_display.createBufferStrategy(2);
			_display.requestFocus();
			return;
		}
		
		//Get Graphics object
		Graphics g = bufferStrategy.getDrawGraphics();
		
		//Clear screen
		g.clearRect(0, 0, _width, _height);
		
		//Draw display
		_display.render(g);
		
		//Dispose of Graphics object
		g.dispose();
		
		//Flip buffers
		bufferStrategy.show();
	}
	
	@Override
	public void run() {
		int fps = 0, tick = 0;
		double fpsTimer = System.currentTimeMillis();
		
		double secondsPerTick = 1D / TARGET_TPS;
		double nanosecondsPerTick = secondsPerTick * 1000000000D;
		double then = System.nanoTime();
		double now;
		double unprocessedFrames = 0;
		
		while (running) {
			now = System.nanoTime();
			unprocessedFrames += (now - then) / nanosecondsPerTick;
			then = now;
			
			while (unprocessedFrames >= 1) {
				tick();
				tick++;
				unprocessedFrames--;
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			render();
			fps++;
			
			if (System.currentTimeMillis() - fpsTimer >= 1000) {
				System.out.printf("FPS: %d, UPS: %d%n", fps, tick);
				
				fps = 0;
				tick = 0;
				fpsTimer += 1000;
			}
		}
		
		_frame.dispatchEvent(new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING));
	}
	
}