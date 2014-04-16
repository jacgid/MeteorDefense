package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * GameRenderer is responsible for rendering everything in the GameModel.
 * @author Simon Nielsen
 */
public class GameRenderer {

	private float cameraWidth = Constants.LOGIC_SCREEN_WIDTH;
	private float cameraHeight = Constants.LOGIC_SCREEN_HEIGHT;
	
	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;
	
	private int width, height;
	private float ppuX, ppuY;
	
	private GameModel model;
	
	private PropertyChangeSupport pcs;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private boolean done = false;
	
	private String[] textures = {"meteor1.png", "projectile1.png"};
	private Texture meteorTexture, gunbarrel, projectileTexture;
	private Sprite projectileSprite, meteorSprite;
	private Rectangle viewport;
	private float scale;
	
	
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
		spriteBatch.setProjectionMatrix(cam.combined);
		pcs = new PropertyChangeSupport(this);
		loadTextures();
	}
	
	/**
	 * Loads all the textures
	 */
	private void loadTextures() {
		AssetsLoader.loadTextures(textures);
	}
	
	public void unloadTextures(){
		AssetsLoader.unloadTextures(textures);
	}
	
	private void getTextures(){
		meteorTexture = AssetsLoader.getTexture(textures[0]);
		projectileTexture = AssetsLoader.getTexture(textures[1]);
		meteorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		projectileTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		projectileSprite = new Sprite(projectileTexture, 8, 16);
		meteorSprite = new Sprite(meteorTexture, 32, 32);
		meteorSprite.scale(width/(meteorSprite.getWidth()*Constants.LOGIC_SCREEN_WIDTH/Constants.BASE_METEOR_SIZE));
		projectileSprite.scale(width/(projectileSprite.getWidth()*Constants.LOGIC_SCREEN_WIDTH/Constants.DEFAULT_PROJECTILE_SIZE));
	}

	/**
	 * Renders the view. Draws all the textures to the screen.
	 */
	public void render(){
		cam.update();
		cam.apply(Gdx.gl10);
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
		
		if(AssetsLoader.update() && done==false){
			getTextures();
			done = true;
		}
		spriteBatch.begin();
		//Drawing is done here
		for(Projectile projectile : model.getVisibleProjectiles()){
			float x = projectile.getX();
			float y = projectile.getY();
			//TODO: Set rotation
			projectileSprite.setPosition(x, y);
			projectileSprite.setRotation((float) (model.getCannonAngle()*(180/Math.PI))-90);
			projectileSprite.draw(spriteBatch);
		}
		for(Meteor meteor : model.getVisibleMeteors()){
			float x = meteor.getX();
			float y = meteor.getY();
			meteorSprite.setPosition(x, y);
			meteorSprite.draw(spriteBatch);
		}
		spriteBatch.end();
		//drawDebug();
	}
	
	/**
	 * Sets the size of the view.
	 * @param width The width of the screen.
	 * @param height The height of the screen.
	 */
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		ppuX = (float) width / Constants.LOGIC_SCREEN_WIDTH;
		ppuY = (float) height / Constants.LOGIC_SCREEN_HEIGHT;
		
		float aspectRatio = (float)width/(float)height;
        scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        
        if(aspectRatio > Constants.LOGIC_ASPECTRATIO)
        {
            scale = (float)height/Constants.LOGIC_SCREEN_HEIGHT;
            crop.x = (width - Constants.LOGIC_SCREEN_WIDTH*scale)/2f;
        }
        else if(aspectRatio < Constants.LOGIC_ASPECTRATIO)
        {
            scale = (float)width/(float)Constants.LOGIC_SCREEN_WIDTH;
            crop.y = (height - Constants.LOGIC_SCREEN_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)Constants.LOGIC_SCREEN_WIDTH;
        }

        float w = (float)Constants.LOGIC_SCREEN_WIDTH*scale;
        float h = (float)Constants.LOGIC_SCREEN_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	private void drawDebug(){
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Filled);
		float x, y;
		
		// Render meteors
		debugRenderer.setColor(Color.BLUE);
		for(Meteor meteor : model.getVisibleMeteors()){
			Circle circle = meteor.getBounds();
			x = meteor.getX();
			y = meteor.getY();
			switch(meteor.getType()){
			case NONE:
				debugRenderer.setColor(Color.BLUE);
				break;
			case FAST:
				debugRenderer.setColor(Color.YELLOW);
				break;
			case FIRE:
				debugRenderer.setColor(Color.RED);
				break;
			case ICE:
				debugRenderer.setColor(Color.CYAN);
				break;
			case RADIOACTIVE:
				debugRenderer.setColor(Color.GREEN);
				break;
			default:
				break;
			}
			debugRenderer.circle(x, y, circle.radius);
		}
		
		// Render projectiles
		debugRenderer.setColor(Color.RED);
		for(Projectile projectile : model.getVisibleProjectiles()){
			Circle circle = projectile.getBounds();
			x = projectile.getX();
			y = projectile.getY();
			debugRenderer.circle(x, y, circle.radius);
			//System.out.println("ScreenX: " + screenX + " ScreenY: " + screenY + "\n" + "GameX: " + screenX*uppX + " GameY: " + screenY*uppY);
		}
		
		debugRenderer.setColor(Color.MAGENTA);
		Rectangle rect = model.getCity().getBounds();
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		
		debugRenderer.setColor(Color.GREEN);
		debugRenderer.rect(48f, 0f, 4f, Constants.CANNONBARREL_LENGTH, 2f, 0f, (float)(model.getCannonAngle()*(180/Math.PI)-180)+90);
		
		debugRenderer.end();
		
	}
	
	public Vector2 unproject(int x, int y){
		Vector3 temp = new Vector3(x,y,0);
		cam.unproject(temp);
		return new Vector2(temp.x, temp.y);
	}
	
}
