package lordmastodon.engine.graphics;

import java.awt.Canvas;
import java.awt.Graphics;

import lordmastodon.engine.Window;

@SuppressWarnings("serial")
public class Display extends Canvas {

	public Display(Window window) {
		setBounds(0, 0, window._width, window._height);
	}
	
	public void render(Graphics g) {
		
	}
	
	public void tick() {
		
	}

}
