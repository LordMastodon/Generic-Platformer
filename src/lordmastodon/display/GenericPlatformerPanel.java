package lordmastodon.display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import lordmastodon.gamestate.GameStateManager;

@SuppressWarnings("serial")
public class GenericPlatformerPanel extends JPanel implements Runnable, KeyListener {
	
	public static final int width = 320, height = 240, scale = 2;
	
	private Thread thread;
	private boolean running;
	private int FPS = 120;
	private long targetTime = 1000/FPS;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	public GenericPlatformerPanel() {
		super();
		
		setPreferredSize(new Dimension(width * scale, height * scale));
		setFocusable(true);
		requestFocus();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			
			thread.start();
		}
	}

	@Override
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while (running) {
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		gsm.update();
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	private void init() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) g;
		running = true;
		
		gsm = new GameStateManager();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		gsm.keyTyped(e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

}
