package lordmastodon.genericplatformer;

import lordmastodon.engine.Window;

public class GenericPlatformer {
	
	//Main function
	public static void main(String[] args) {
		new GenericPlatformer();
	}
	
	//Constructor
	public GenericPlatformer() {
		Window window = new Window(800, 600, "Generic Platformer");
		
		window.start();
	}

}
