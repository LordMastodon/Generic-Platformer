package lordmastodon.genericplatformer;

import lordmastodon.engine.Window;

public class GenericPlatformer {
	
	//Main function
	public static void main(String[] args) {
		new GenericPlatformer();
	}
	
	//Constructor
	public GenericPlatformer() {
		//Create the window
		Window window = new Window(800, 600, "Generic Platformer");
		
		//Start the game loop
		window.start();
	}

}
