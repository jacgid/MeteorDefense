package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	
	private OrthographicCamera gameCam, bgCam;
	private SpriteBatch spriteBatch;
	
	private int width, height;
	private float ppuX, ppuY;
	
	private GameModel model;
	
	private PropertyChangeSupport pcs;
	
	private Stage stage;
	private Table table;
	private Label lifeLabel;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private boolean spritesLoaded = false;
	
	private String[] textures = {"meteor1.png", "projectile1.png", "cannonbarrel.png", "city1.png", "toolbar1.png"};
	private Texture meteorTexture, gunbarrel, projectileTexture, cannonTexture, cityTexture, toolbarTexture;
	private Sprite projectileSprite, meteorSprite, cannonSprite, citySprite, toolbarSprite;
	private Rectangle viewport;
	private float scale;
	
	
	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(GameModel model){
		this.model = model;
		cameraWidth = GameModel.WIDTH;
		cameraHeight = GameModel.HEIGHT;
		gameCam = new OrthographicCamera(cameraWidth, cameraHeight);
		gameCam.position.set(cameraWidth/2, cameraHeight/2, 0);
		gameCam.update();
		spriteBatch = new SpriteBatch();
		pcs = new PropertyChangeSupport(this);
		stage = new Stage();
		loadTextures();
		
		create();
	}

	private void create() {
		table = new Table();
		table.bottom().padLeft(10).left();
		table.setFillParent(true);
		stage.addActor(table);
		
		LabelStyle lifeLabelStyle = new LabelStyle();
		lifeLabelStyle.font = new BitmapFont();
		lifeLabelStyle.font.scale(1);
		lifeLabel = new Label("", lifeLabelStyle);
		table.add(lifeLabel).left();
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
	
	private void loadSprites(){
		meteorTexture = AssetsLoader.getTexture(textures[0]);
		projectileTexture = AssetsLoader.getTexture(textures[1]);
		cannonTexture = AssetsLoader.getTexture(textures[2]);
		cityTexture = AssetsLoader.getTexture(textures[3]);
		toolbarTexture = AssetsLoader.getTexture(textures[4]);
		meteorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		projectileTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cannonTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cityTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		toolbarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		projectileSprite = new Sprite(projectileTexture);
		meteorSprite = new Sprite(meteorTexture);
		cannonSprite = new Sprite(cannonTexture);
		citySprite = new Sprite(cityTexture);
		toolbarSprite = new Sprite(toolbarTexture);
//		meteorSprite.scale(width/(meteorSprite.getWidth()*Constants.LOGIC_SCREEN_WIDTH/Constants.BASE_METEOR_SIZE));
		meteorSprite.setSize(Constants.BASE_METEOR_SIZE, Constants.BASE_METEOR_SIZE);
		projectileSprite.setSize(Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SIZE);
		cannonSprite.setSize(Constants.CANNONBARREL_LENGTH/2, Constants.CANNONBARREL_LENGTH);
		citySprite.setSize(Constants.CITY_BOUNDS.width, Constants.CITY_BOUNDS.height);
		toolbarSprite.setSize(Constants.CITY_BOUNDS.width, Constants.CITY_BOUNDS.height);
	}

	/**
	 * Renders the view. Draws all the textures to the screen.
	 */
	public void render(){
		
		bgCam.update();
		bgCam.apply(Gdx.gl10);
		Gdx.gl.glViewport(0, 0, width, height);
		
		spriteBatch.setProjectionMatrix(bgCam.combined);
		spriteBatch.begin();
		debugRenderer.setProjectionMatrix(bgCam.combined);
		debugRenderer.begin(ShapeType.Filled);
		debugRenderer.setColor(Color.LIGHT_GRAY);
		debugRenderer.rect(0, 0, width, height);
		debugRenderer.end();
		spriteBatch.end();
		
		spriteBatch.setProjectionMatrix(gameCam.combined);
		
		gameCam.update();
		gameCam.apply(Gdx.gl10);
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
		
		if(AssetsLoader.update() && spritesLoaded==false){
			loadSprites();
			spritesLoaded = true;
		}
		spriteBatch.begin();
		//Drawing is done here
		
		debugRenderer.setProjectionMatrix(gameCam.combined);
		debugRenderer.begin(ShapeType.Filled);
		debugRenderer.setColor(Color.PINK);
		debugRenderer.rect(0, 0, gameCam.viewportWidth, gameCam.viewportHeight);
		debugRenderer.end();
		
		if(spritesLoaded){
			drawSprites();
		}
		
		spriteBatch.end();
		stage.draw();
//		drawDebug();
		
		lifeLabel.setText(model.getCity().getLife() + "");

	}
	
	private void drawSprites(){
		for(Meteor meteor : model.getVisibleMeteors()){
			float x = meteor.getX()- (meteor.getBounds().radius/2);
			float y = meteor.getY()- (meteor.getBounds().radius/2);
			meteorSprite.setPosition(x, y);
			meteorSprite.draw(spriteBatch);
		}
		citySprite.setPosition(Constants.CITY_BOUNDS.x, Constants.CITY_BOUNDS.y);
		citySprite.draw(spriteBatch);
		toolbarSprite.setPosition(Constants.CITY_BOUNDS.x, Constants.CITY_BOUNDS.y-(Constants.CITY_BOUNDS.height/((float)7/6)));
		toolbarSprite.draw(spriteBatch);
		cannonSprite.setPosition(Constants.LOGIC_SCREEN_WIDTH/2-(Constants.CANNONBARREL_LENGTH/4), Constants.LOGIC_SCREEN_HEIGHT/20);
		cannonSprite.setOrigin(cannonSprite.getWidth()/2, cannonSprite.getHeight()/3);
		cannonSprite.setRotation((float) Math.toDegrees(model.getCannonAngle())-90);
		cannonSprite.draw(spriteBatch);
		for(Projectile projectile : model.getVisibleProjectiles()){
			float x = projectile.getX();
			float y = projectile.getY();
			projectileSprite.setPosition(x, y);
			projectileSprite.setRotation((float) (projectile.getAngle()*(180/Math.PI))-90);
			projectileSprite.draw(spriteBatch);
		}
	}
	
	/**
	 * Sets the size of the view.
	 * @param width The width of the screen.
	 * @param height The height of the screen.
	 */
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		
		bgCam = new OrthographicCamera(width, height);
		bgCam.position.set(width/2, height/2, 0);
		bgCam.update();
		
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
		debugRenderer.setProjectionMatrix(gameCam.combined);
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
		gameCam.unproject(temp);
		return new Vector2(temp.x, temp.y);
	}
	
}
