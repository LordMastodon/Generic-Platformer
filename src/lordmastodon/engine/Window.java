package lordmastodon.engine;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window implements Runnable {
	
	//Actual window
	private JFrame _frame;
	
	//Window size
	public int _width, _height;
	
	//Window title
	public String _title;
	
	//Running variable
	public boolean running;
	
	public Window(int width, int height, String title) {
		//Store variables
		_width = width;
		_height = height;
		_title = title;
		
		//Create window
		_frame = new JFrame(title);
		
		//Set frame variables
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setPreferredSize(new Dimension(width, height));
		_frame.pack();
		_frame.setLocationRelativeTo(null);
		_frame.setResizable(false);
		
		//Set visible
		_frame.setVisible(true);
	}
	
	public void start() {
		//Run the game
		running = true;
		
		//Create the thread
		new Thread(this).start();
	}
	
	public void stop() {
		
	}
	
	@Override
	public void run() {
		System.out.println("Running");
		
		while (running) {
			
		}
	}
	
}