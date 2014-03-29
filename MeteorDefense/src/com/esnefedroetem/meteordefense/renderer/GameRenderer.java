package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * GameRenderer is responsible for rendering everything in the GameModel.
 * @author Simon Nielsen
 */
public class GameRenderer {

	private float cameraWidth = 100f;
	private float cameraHeight = 200f;
	
	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;
	
	private int width, height;
	private float ppuX, ppuY;
	
	private PropertyChangeSupport pcs;
	
	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(float width, float height){
		cameraWidth = width;
		cameraHeight = height;
		cam = new OrthographicCamera(cameraWidth, cameraHeight);
		cam.position.set(cameraWidth/2, cameraHeight/2, 0);
		cam.update();
		spriteBatch = new SpriteBatch();
		pcs = new PropertyChangeSupport(this);
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
		ppuX = (float) width / cameraWidth;
		ppuY = (float) height / cameraHeight;
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
}
