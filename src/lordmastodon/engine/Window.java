package lordmastodon.engine;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	//Actual window
	private JFrame _frame;
	
	//Window size
	public int _width, _height;
	
	//Window title
	public String _title;
	
	public Window(int width, int height, String title) {
		//Store variables
		_width = width;
		_height = height;
		_title = title;
		
		//Create window
		_frame = new JFrame(title);
		
		//Set frame variables
		_frame.getContentPane().setPreferredSize(new Dimension(width, height));
		_frame.pack();
		_frame.setLocationRelativeTo(null);
		
		//Set visible
		_frame.setVisible(true);
	}
	
}