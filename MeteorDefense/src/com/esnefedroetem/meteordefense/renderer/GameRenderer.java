package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile;

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
	
	private GameModel model;
	
	private PropertyChangeSupport pcs;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	
	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(GameModel model){
		this.model = model;
		cameraWidth = GameModel.WIDTH;
		cameraHeight = GameModel.HEIGHT;
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
		drawDebug();
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
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Filled);
		float x, y;
		
		// Render meteors
		debugRenderer.setColor(Color.BLACK);
		for(Meteor meteor : model.getVisibleMeteors()){
			Circle circle = meteor.getBounds();
			x = meteor.getX() + circle.x;
			y = meteor.getY() + circle.y;
			debugRenderer.circle(x, y, circle.radius);
		}
		
		// Render projectiles
		debugRenderer.setColor(Color.RED);
		for(Projectile projectile : model.getVisibleProjectiles()){
			Circle circle = projectile.getBounds();
			x = projectile.getX() + circle.x;
			y = projectile.getY() + circle.y;
			debugRenderer.circle(x, y, circle.radius);
		}
		
		debugRenderer.end();
		
	}
	
}
