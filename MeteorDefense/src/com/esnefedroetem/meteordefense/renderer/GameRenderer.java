package com.esnefedroetem.meteordefense.renderer;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.IGameModel;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * GameRenderer is responsible for rendering everything in the GameModel.
 * @author Simon Nielsen
 */
public class GameRenderer {

	private OrthographicCamera gameCam;
	private SpriteBatch spriteBatch;
	private int width, height;
	private IGameModel model;
	private Stage gameStage, bgStage;
	private Label lifeLabel, scoreLable;
	private LabelStyle lblStyleMedium;
	private Button btnItem1, btnItem2, btnItem3, btnItem4;
	private ButtonStyle btnstyle1, btnstyle2, btnstyle3, btnstyle4;
	private Image imgCityMonument, imgCity, imgCannon;
	private Sprite projectileSprite, meteorSprite, cannonSprite, citySprite, toolbarSprite, bgSprite;
	private Rectangle viewport;
	private float scale;
	private HashMap<String, Sprite> spriteMap;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();

	
	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(IGameModel model){
		this.model = model;
		gameCam = new OrthographicCamera(model.getWidth(), model.getHeight());
		gameCam.position.set(model.getWidth() / 2, model.getHeight() / 2, 0);
		gameCam.update();
		spriteBatch = new SpriteBatch();
		loadSprites();
		createUI();
	}

	private void createUI() {
		gameStage = new Stage();
		gameStage.setCamera(gameCam);
		bgStage = new Stage();
		
		Table bgImgTable = new Table();
		bgImgTable.setFillParent(true);
		bgImgTable.add(new Image(assetsLoader.getTexture("MDBG.png"))).expand().fill();
		bgStage.addActor(bgImgTable);
		
		lblStyleMedium = new LabelStyle();
		lblStyleMedium.font = assetsLoader.getMediumFont();
		lifeLabel = new Label("", lblStyleMedium);
		scoreLable = new Label("0", lblStyleMedium);
		Table lblTable = new Table();
		lblTable.setFillParent(true);
		lblTable.add(lifeLabel).top().left().expand();
		lblTable.add(scoreLable).top().right();
		bgStage.addActor(lblTable);
		
		btnstyle1 = new ButtonStyle();
		btnstyle2 = new ButtonStyle();
		btnstyle3 = new ButtonStyle();
		btnstyle4 = new ButtonStyle();
		btnItem1 = new Button(btnstyle1);
		btnItem1.setUserObject(1);
		btnItem2 = new Button(btnstyle2);
		btnItem2.setUserObject(2);
		btnItem3 = new Button(btnstyle3);
		btnItem3.setUserObject(4);
		btnItem4 = new Button(btnstyle4);
		btnItem4.setUserObject(5);
		imgCannon = new Image();
		Table toolBarTable = new Table();
		toolBarTable.setFillParent(true);
		toolBarTable.add(btnItem1).bottom().expand();
		toolBarTable.add(btnItem2).bottom().expand();
		toolBarTable.add(imgCannon).bottom().expand();
		toolBarTable.add(btnItem3).bottom().expand();
		toolBarTable.add(btnItem4).bottom().expand();
		Table toolBarBG = new Table();
		toolBarBG.setFillParent(true);
		toolBarBG.add(new Image(assetsLoader.getTexture("Toolbar.png"))).bottom().expand();
		
		imgCityMonument = new Image();
		imgCity = new Image();
		Table cityTable = new Table();
		cityTable.setFillParent(true);
		cityTable.add(imgCity).expand().bottom();
		cityTable.add(imgCityMonument).bottom();
		cityTable.padBottom(Constants.CITY_BOUNDS.y);
		
		gameStage.addActor(cityTable);
		gameStage.addActor(toolBarBG);
		gameStage.addActor(toolBarTable);
		
	}
	
	private void loadSprites(){
//		meteorTexture = AssetsLoader.getTexture(textures[0]);
//		projectileTexture = AssetsLoader.getTexture(textures[1]);
//		cannonTexture = AssetsLoader.getTexture(textures[2]);
//		cityTexture = AssetsLoader.getTexture(textures[3]);
//		toolbarTexture = AssetsLoader.getTexture(textures[4]);
//		bgTexture = AssetsLoader.getTexture(textures[5]);
//		
//		meteorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		projectileTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		cannonTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		cityTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		toolbarTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		meteorSprite = new Sprite(assetsLoader.getTexture("meteor1.png"));
		projectileSprite = new Sprite(assetsLoader.getTexture("projectile1.png"));
//		cannonSprite = new Sprite(cannonTexture);
//		citySprite = new Sprite(cityTexture);
//		toolbarSprite = new Sprite(toolbarTexture);
//		bgSprite = new Sprite(bgTexture);
		
		meteorSprite.setSize(Constants.BASE_METEOR_SIZE, Constants.BASE_METEOR_SIZE);
		projectileSprite.setSize(Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SIZE);
//		cannonSprite.setSize(model.getCannonBarrel().getBounds().width, model.getCannonBarrel().getBounds().height);
//		citySprite.setSize(Constants.CITY_BOUNDS.width, Constants.CITY_BOUNDS.height);
//		toolbarSprite.setSize(Constants.CITY_BOUNDS.width, Constants.CITY_BOUNDS.height);
//		bgSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * Renders the view. Draws all the textures to the screen.
	 */
	public void render(){
		Gdx.gl.glClearColor(94f, 97f, 225f, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_STENCIL_BUFFER_BIT);
		
		Gdx.gl.glViewport(0, 0, width, height);
		lifeLabel.setText(model.getCity().getLife() + "");
		scoreLable.setText(model.getScore() + "");
		bgStage.act();
		bgStage.draw();
		drawHits();
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                (int) viewport.width, (int) viewport.height);
		gameCam.update();
		gameCam.apply(Gdx.gl10);
		spriteBatch.setProjectionMatrix(gameCam.combined);
		gameStage.act();
		gameStage.draw();
		spriteBatch.begin();
		drawSprites();
		spriteBatch.end();
//		drawDebug();
		

	}
	
	private void drawSprites(){
		for(Meteor meteor : model.getVisibleMeteors()){
			float x = meteor.getX() - meteor.getBounds().radius;
			float y = meteor.getY() - meteor.getBounds().radius;
			meteorSprite.setPosition(x, y);
			meteorSprite.draw(spriteBatch);
		}
//		citySprite.setPosition(Constants.CITY_BOUNDS.x, Constants.CITY_BOUNDS.y);
//		citySprite.draw(spriteBatch);
//		toolbarSprite.setPosition(Constants.CITY_BOUNDS.x, Constants.CITY_BOUNDS.y - Constants.CITY_BOUNDS.height/((float)7/6));
//		toolbarSprite.draw(spriteBatch);
//		cannonSprite.setPosition(model.getCannonBarrel().getBounds().x, model.getCannonBarrel().getBounds().y);
//		cannonSprite.setOrigin(cannonSprite.getWidth()/2, cannonSprite.getHeight()/3);
//		cannonSprite.setRotation((float) Math.toDegrees(model.getCannonAngle())-90);
//		cannonSprite.draw(spriteBatch);
		imgCannon.setOrigin(imgCannon.getWidth() / 2 + 2, imgCannon.getHeight() - 111);
		imgCannon.setRotation(((float) Math.toDegrees(model.getCannonAngle())-90));
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
			Label hitLabel = new Label(""+meteor.getDifficulty(), lblStyleMedium);
			hitLabel.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.removeActor()));
			gameStage.addActor(hitLabel);
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
        gameStage.setViewport(gameCam.viewportWidth, gameCam.viewportHeight, true, viewport.x, viewport.y, viewport.width, viewport.height);
	}
	
	public void addListeners(InputListener inputListener, ClickListener clickListener){
		gameStage.addListener(inputListener);
		btnItem1.addListener(clickListener);
		btnItem2.addListener(clickListener);
		btnItem3.addListener(clickListener);
		btnItem4.addListener(clickListener);
	}
	
	public void setInputProcessor(){
		Gdx.input.setInputProcessor(gameStage);
	}
	
	public void newGame(City city, List<AbstractArmoryItem> items){
		btnstyle1.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(0).getName() + ".png")));
		btnstyle2.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(1).getName() + ".png")));
		btnstyle3.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(3).getName() + ".png")));
		btnstyle4.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(4).getName() + ".png")));
		imgCityMonument.setDrawable(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("ParisMonument.png"))));
		imgCity.setDrawable(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("Europe1.png"))));
		imgCannon.setDrawable(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(2).getName() + ".png"))));
		//TODO change to specific city monument
	}
	
	public void dispose(){
		gameStage.dispose();
		spriteBatch.dispose();
	}
	
}
