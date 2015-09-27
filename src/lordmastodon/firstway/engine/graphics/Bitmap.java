package lordmastodon.firstway.engine.graphics;

import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Bitmap {
	
	private int[] _pixels;
	private int _width, _height;
	
	public Bitmap(int width, int height) {
		_width = width;
		_height = height;
		_pixels = new int[width * height];
	}
	
	public static Bitmap getBitmap(String path) {
		try {
			BufferedImage image = ImageIO.read(Window.class.getResourceAsStream(path));
			Bitmap bitmap = new Bitmap(image.getWidth(), image.getHeight());
			
			image.getRGB(0, 0, bitmap._width, bitmap._height, bitmap._pixels, 0, bitmap._width);
			
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void renderBitmap(Bitmap bitmap, int x, int y) {
		for (int wx = 0; wx < bitmap._width; wx++) {
			for (int hy = 0; hy < bitmap._height; hy++) {
				int pixel = bitmap._pixels[bitmap.getPixelPosition(wx, hy)];
				_pixels[getPixelPosition(x + wx, y + hy)] = pixel;
			}
		}
	}
	
	private int getPixelPosition(int x, int y) {
		return y * _width + x;
	}
	
	public void setPixels(int[] pixels) {
		_pixels = pixels;
	}
	
	public void fill(int color) {
		Arrays.fill(_pixels, color);
	}
	
	@Deprecated
	public BufferedImage getImage() {
		BufferedImage bufImg = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
		bufImg.setRGB(0, 0, _width, _height, _pixels, 0, _width);
		
		return bufImg;
	}

}
