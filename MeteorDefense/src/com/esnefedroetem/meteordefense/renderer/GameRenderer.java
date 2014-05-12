package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.IGameModel;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * GameRenderer is responsible for rendering everything in the GameModel.
 * @author Simon Nielsen
 */
public class GameRenderer {

	private OrthographicCamera gameCam, bgCam;
	private SpriteBatch spriteBatch;
	
	private int width, height;
	
	private IGameModel model;
	
	private Stage stage;
	private Table debugTable;
	private Label lifeLabel, scoreLable, hitLable;
	private Table toolbarTable, UITable;
	LabelStyle hitLabelStyle;
	
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private boolean spritesLoaded = false;
	
	private String[] textures = {"meteor1.png", "projectile1.png", "cannonbarrel.png", "city1.png", "toolbar1.png", "MDBG1.png"};
	private Texture meteorTexture, gunbarrel, projectileTexture, cannonTexture, cityTexture, toolbarTexture, bgTexture;
	private Sprite projectileSprite, meteorSprite, cannonSprite, citySprite, toolbarSprite, bgSprite;
	private Rectangle viewport;
	private float scale;

	private int score;
	private BitmapFont hitScore;
	
	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(IGameModel model){
		this.model = model;
		gameCam = new OrthographicCamera(model.getWidth(), model.getHeight());
		gameCam.position.set(model.getWidth() / 2, model.getHeight() / 2, 0);
		gameCam.update();
		bgCam = new OrthographicCamera();
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		stage.setCamera(gameCam);
		loadTextures();
		
		createUI();
	}

	private void createUI() {
		debugTable = new Table();
		debugTable.bottom().center().padLeft(10).left();
		debugTable.setFillParent(true);
		stage.addActor(debugTable);
		
		LabelStyle lifeLabelStyle = new LabelStyle();
		lifeLabelStyle.font = AssetsLoader.getMediumFont();
//		lifeLabelStyle.font.scale(5);
		lifeLabel = new Label("", lifeLabelStyle);
		debugTable.add(lifeLabel).left();
		
		toolbarTable = new Table();
		toolbarTable.setFillParent(true);
		toolbarTable.bottom().left();
		stage.addActor(toolbarTable);
		
		UITable = new Table();
//		UITable.debug();
//		UITable.debugTable();
		UITable.setFillParent(true);
		UITable.top().right().padRight(10);
		stage.addActor(UITable);
		
		LabelStyle scoreLabelStyle = new LabelStyle();
		scoreLabelStyle.font = AssetsLoader.getMediumFont();
//		scoreLabelStyle.font.scale(7);
//		score = model.calculateScore().getMeteorScore();
		scoreLable = new Label(score + "", scoreLabelStyle);
		scoreLable.setPosition(0, model.getHeight()-scoreLable.getHeight());
//		UITable.add(scoreLable).fill();
		
		stage.addActor(scoreLable);
		
		hitScore = new BitmapFont();
		hitScore.scale(2);
		
		hitLabelStyle = new LabelStyle();
		hitLabelStyle.font = AssetsLoader.getSmallFont();
	}
	
	private void loadUI(){
		final ImageButton button1 = new ImageButton(new SpriteDrawable(meteorSprite));
		final ImageButton button2 = new ImageButton(new SpriteDrawable(meteorSprite));
		final ImageButton button3 = new ImageButton(new SpriteDrawable(meteorSprite));
		final ImageButton button4 = new ImageButton(new SpriteDrawable(meteorSprite));
		toolbarTable.add(button1).width(Constants.LOGIC_SCREEN_WIDTH/8).padLeft(Constants.LOGIC_SCREEN_WIDTH/32).padRight(Constants.LOGIC_SCREEN_WIDTH/32);
		toolbarTable.add(button2).width(Constants.LOGIC_SCREEN_WIDTH/8).padLeft(Constants.LOGIC_SCREEN_WIDTH/32).padRight(Constants.LOGIC_SCREEN_WIDTH/6);
		toolbarTable.add(button3).width(Constants.LOGIC_SCREEN_WIDTH/8).padLeft(Constants.LOGIC_SCREEN_WIDTH/6).padRight(Constants.LOGIC_SCREEN_WIDTH/32);
		toolbarTable.add(button4).width(Constants.LOGIC_SCREEN_WIDTH/8).padLeft(Constants.LOGIC_SCREEN_WIDTH/32).padRight(Constants.LOGIC_SCREEN_WIDTH/32);
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
		bgTexture = AssetsLoader.getTexture(textures[5]);
		
		meteorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		projectileTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cannonTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		cityTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		toolbarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		projectileSprite = new Sprite(projectileTexture);
		meteorSprite = new Sprite(meteorTexture);
		cannonSprite = new Sprite(cannonTexture);
		citySprite = new Sprite(cityTexture);
		toolbarSprite = new Sprite(toolbarTexture);
		bgSprite = new Sprite(bgTexture);
		
		meteorSprite.setSize(Constants.BASE_METEOR_SIZE, Constants.BASE_METEOR_SIZE);
		projectileSprite.setSize(Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SIZE);
		cannonSprite.setSize(model.getCannonBarrel().getBounds().width, model.getCannonBarrel().getBounds().height);
		citySprite.setSize(Constants.CITY_BOUNDS.width, Constants.CITY_BOUNDS.height);
		toolbarSprite.setSize(Constants.CITY_BOUNDS.width, Constants.CITY_BOUNDS.height);
		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		
	}

	/**
	 * Renders the view. Draws all the textures to the screen.
	 */
	public void render(){
		Gdx.gl.glClearColor(94f, 97f, 225f, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_STENCIL_BUFFER_BIT);
		
		bgCam.update();
		bgCam.apply(Gdx.gl10);
		Gdx.gl.glViewport(0, 0, width, height);
		
		spriteBatch.setProjectionMatrix(bgCam.combined);
		spriteBatch.begin();
		
		if(spritesLoaded)
		bgSprite.draw(spriteBatch);
		spriteBatch.end();
		
		spriteBatch.setProjectionMatrix(gameCam.combined);
		
		gameCam.update();
		gameCam.apply(Gdx.gl10);
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
		
		spriteBatch.begin();
		//Drawing is done here
		
		if(spritesLoaded){
			drawSprites();
		}
		
		spriteBatch.end();
		drawHits();
		stage.act();
		stage.draw();
//		drawDebug();
		
		lifeLabel.setText(model.getCity().getLife() + "");
		score = model.getScore();
		scoreLable.setText(score + "");

	}
	
	public void loadScene(){
		if(AssetsLoader.update() && spritesLoaded==false){
			loadSprites();
			loadUI();
			spritesLoaded = true;
		}
	}
	
	private void drawSprites(){
		for(Meteor meteor : model.getVisibleMeteors()){
			float x = meteor.getX() - meteor.getBounds().radius;
			float y = meteor.getY() - meteor.getBounds().radius;
			meteorSprite.setPosition(x, y);
			meteorSprite.draw(spriteBatch);
		}
		citySprite.setPosition(Constants.CITY_BOUNDS.x, Constants.CITY_BOUNDS.y);
		citySprite.draw(spriteBatch);
		toolbarSprite.setPosition(Constants.CITY_BOUNDS.x, Constants.CITY_BOUNDS.y - Constants.CITY_BOUNDS.height/((float)7/6));
		toolbarSprite.draw(spriteBatch);
		cannonSprite.setPosition(model.getCannonBarrel().getBounds().x, model.getCannonBarrel().getBounds().y);
		cannonSprite.setOrigin(cannonSprite.getWidth()/2, cannonSprite.getHeight()/3);
		cannonSprite.setRotation((float) Math.toDegrees(model.getCannonAngle())-90);
		cannonSprite.draw(spriteBatch);
		for(Projectile projectile : model.getVisibleProjectiles()){
			float x = projectile.getX() - projectile.getBounds().radius;
			float y = projectile.getY() - projectile.getBounds().radius;
			projectileSprite.setPosition(x, y);
			projectileSprite.setRotation((float) (projectile.getAngle()*(180/Math.PI))-90);
			projectileSprite.draw(spriteBatch);
		}
	}
	
	private void drawHits(){
		
		for(Meteor meteor : model.getMeteorsToBlow()){
			Label hitLabel = new Label(""+meteor.getDifficulty(),hitLabelStyle);
			hitLabel.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.removeActor()));
			stage.addActor(hitLabel);
			hitLabel.setPosition(meteor.getBounds().x, meteor.getBounds().y);
		}
		
		model.getMeteorsToBlow().clear();
		
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
		bgCam.viewportWidth = width;
		bgCam.viewportHeight = height;
		bgCam.position.set(width/2, height/2, 0);
		bgCam.update();
		
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
	
	public void addListeners(InputListener inputListener, ClickListener clickListener){
		stage.addListener(inputListener);
	}
	
	public void setInputProcessor(){
		Gdx.input.setInputProcessor(stage);
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
			case BASIC:
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
		
		Table.drawDebug(stage);
	}
	
	public Vector2 unproject(float x, float y){
		Vector3 temp = new Vector3(x,y,0);
		gameCam.unproject(temp, viewport.x, viewport.y,
                viewport.width, viewport.height);
		return new Vector2(temp.x, temp.y);
	}
	
	public boolean doneLoading(){
		return spritesLoaded;
	}
	
}
