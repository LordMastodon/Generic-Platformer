package lordmastodon.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import lordmastodon.display.GenericPlatformerPanel;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		moveScale = ms;
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GenericPlatformerPanel.width;
		this.y = (y * moveScale) % GenericPlatformerPanel.height;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		setPosition(x + dx, x + dy);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, (int) x, (int) y, null);
		
		if (x < 0) {
			g.drawImage(image, (int) x + GenericPlatformerPanel.width, (int) y, null);
		}
		
		if (x > 0) {
			g.drawImage(image, (int) x - GenericPlatformerPanel.height, (int) y, null);
		}
	}

}
