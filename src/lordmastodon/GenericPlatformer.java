package lordmastodon;

import javax.swing.JFrame;

import lordmastodon.display.GenericPlatformerPanel;

public class GenericPlatformer {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Generic Platformer");
		frame.setContentPane(new GenericPlatformerPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

}
