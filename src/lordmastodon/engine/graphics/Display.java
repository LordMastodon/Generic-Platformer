package lordmastodon.engine.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import lordmastodon.engine.Window;

@SuppressWarnings("serial")
public class Display extends Canvas {

	private BufferedImage bufImg;
	private Bitmap screen;
	
	private Bitmap dude;
	
	public Display(Window window) {
		setBounds(0, 0, window._width, window._height);
		
		bufImg = new BufferedImage(window._width, window._height, BufferedImage.TYPE_INT_ARGB);
		screen = new Bitmap(window._width, window._height);
		screen.setPixels(((DataBufferInt) bufImg.getRaster().getDataBuffer()).getData());
	
		dude = Bitmap.getBitmap("/WhatHaveIDone.png");
	}
	
	public void render(Graphics g) {
		screen.fill(Color.CYAN.getRGB());
		screen.renderBitmap(dude, 10, 10);
		
		g.drawImage(bufImg, 0, 0, null);
	}
	
	public void tick() {
		
	}

}
