package com.esnefedroetem.meteordefense.renderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * GameRenderer is responsible for rendering everything in the GameModel.
 */
public class GameRenderer {

	private static final float CAMERA_WIDTH = 100f;
	private static final float CAMERA_HEIGHT = 200f;
	
	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;
	
	private int width, height;
	private float ppuX, ppuY;
	
	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(){
		cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		cam.position.set(CAMERA_WIDTH/2, CAMERA_HEIGHT/2, 0);
		cam.update();
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	/**
	 * Loads all the textures
	 */
	private void loadTextures() {
		
	}

	/**
	 * Renders the view. Draws all the textures to the screen.
	 */
	public void render(){
		spriteBatch.begin();
		//Drawing is done here
		spriteBatch.end();
	}
	
	/**
	 * Sets the size of the view.
	 * @param width The width of the screen.
	 * @param height The height of the screen.
	 */
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}
	
}
