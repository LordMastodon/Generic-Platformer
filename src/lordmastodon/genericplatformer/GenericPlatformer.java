package lordmastodon.genericplatformer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

public class GenericPlatformer implements Runnable {

	private Thread thread;
	public boolean running = true;
	
	private long window;
	private int width = 1200, height = 800;
	
	public static void main(String[] args) {
		GenericPlatformer game = new GenericPlatformer();
		game.start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "GenericPlatformer");
		thread.start();
	}
	
	@SuppressWarnings("unused")
	public void init() {
		if (glfwInit() != GL_TRUE) {
			System.err.println("GLFW initialization failed!");
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		
		window = glfwCreateWindow(width, height, "Generic Platformer", NULL, NULL);
		if (window == NULL) {
			System.err.println("Could not create window.");
			
			ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, 100, 100);
			glfwMakeContextCurrent(window);
			glfwShowWindow(window);
		}
	}
	
	public void update() {
		glfwPollEvents();
	}
	
	public void render() {
		glfwSwapBuffers(window);
	}
	
	@Override
	public void run() {
		init();
		
		while (running) {
			update();
			render();
			
			if (glfwWindowShouldClose(window) == GL_TRUE) {
				running = false;
			}
		}
	}

}
