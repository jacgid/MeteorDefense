package com.esnefedroetem.meteordefense.renderer;

import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
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
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.model.meteor.Meteor.MeteorType;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * GameRenderer is responsible for rendering everything in the GameModel.
 * 
 * @author Simon Nielsen
 */
public class GameRenderer {

	private OrthographicCamera gameCam;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private int width, height;
	private IGameModel model;
	private Stage gameStage, bgStage;
	private Label lifeLabel, scoreLable;
	private LabelStyle lblStyleMedium;
	private Button btnItem1, btnItem2, btnItem3, btnItem4;
	private ButtonStyle btnstyle1, btnstyle2, btnstyle3, btnstyle4;
	private Image imgCannon;
	private Sprite cityMonumentSprite, citySprite;
	private Rectangle viewport;
	private float scale;
	private HashMap<String, Sprite> spriteMap;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	private ParticleEffect fireEffect, explosionEffect;
	private ParticleEmitter fireEmitters[] = new ParticleEmitter[10];
	private int startedFires = 0;

	/**
	 * Initializes GameRenderer.
	 */
	public GameRenderer(IGameModel model) {
		this.model = model;
		gameCam = new OrthographicCamera(model.getWidth(), model.getHeight());
		gameCam.position.set(model.getWidth() / 2, model.getHeight() / 2, 0);
		gameCam.update();
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		loadSprites();
		explosionEffect = assetsLoader.getParticleEffect("Explosion.p");
		fireEffect = assetsLoader.getParticleEffect("Fire.p");


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

		gameStage.addActor(toolBarTable);

		
	}

	private void loadSprites() {
		spriteMap = new HashMap<String, Sprite>();
		String[] meteors = MeteorType.getTypes();
		for (int i = 0; i < meteors.length; i++) {
			spriteMap.put(meteors[i], new Sprite(assetsLoader.getTexture(meteors[i] + ".png")));
		}
		String[] projectiles = ProjectileType.getTypes();
		for (int i = 0; i < projectiles.length; i++) {
			spriteMap.put(projectiles[i], new Sprite(assetsLoader.getTexture(projectiles[i] + ".png")));
		}

	}

	/**
	 * Renders the view. Draws all the textures to the screen.
	 */
	public void render(float delta) {
		Gdx.gl.glClearColor(94f, 97f, 225f, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);

		Gdx.gl.glViewport(0, 0, width, height);
		lifeLabel.setText(model.getCity().getLife() + "");
		updateLifeVisuals(model.getCity().getRemainingLife());
		scoreLable.setText(model.getScore() + "");
		model.getCity().getRemainingLife();

		bgStage.act();
		bgStage.draw();
		drawHits();
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
		gameCam.update();
		gameCam.apply(Gdx.gl10);
		spriteBatch.setProjectionMatrix(gameCam.combined);
		spriteBatch.begin();
		drawSprites();

		fireEffect.draw(spriteBatch, delta);
		explosionEffect.draw(spriteBatch, delta);

		spriteBatch.end();
		gameStage.act();
		gameStage.draw();
		drawWeaponCooldown();

	}

	private void drawSprites() {
		citySprite.draw(spriteBatch);
		cityMonumentSprite.draw(spriteBatch);
		for (Meteor meteor : model.getVisibleMeteors()) {
			float x = meteor.getX();
			float y = meteor.getY();
			Sprite meteorSprite = spriteMap.get(meteor.getType().toString());
			meteorSprite.setBounds(x, y, meteor.getBounds().width, meteor.getBounds().height);
			meteorSprite.draw(spriteBatch);
		}
		imgCannon.setOrigin(Constants.CANNON_ORIGIN_X, Constants.CANNON_ORIGIN_Y);
		imgCannon.setRotation(((float) Math.toDegrees(model.getCannonAngle()) - 90));
		for (Projectile projectile : model.getVisibleProjectiles()) {
			float x = projectile.getX() - projectile.getBounds().width / 2;
			float y = projectile.getY() - projectile.getBounds().height / 2;
			Sprite projectileSprite = spriteMap.get(projectile.getProjectileType().toString());
			projectileSprite.setRotation((float) (projectile.getAngle() * (180 / Math.PI)) - 90);
			projectileSprite.setBounds(x, y, projectile.getBounds().width, projectile.getBounds().height);
			projectileSprite.draw(spriteBatch);
		}
	}

	private void drawHits() {

		for (Meteor meteor : model.getMeteorsToBlow()) {
			Label hitLabel = new Label("" + meteor.getDifficulty(), lblStyleMedium);
			hitLabel.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.removeActor()));
			gameStage.addActor(hitLabel);
			hitLabel.setPosition(meteor.getBounds().x, meteor.getBounds().y
					+ meteor.getBounds().getHeight());
			explosionEffect.start();
			explosionEffect.setPosition(meteor.getBounds().x + meteor.getBounds().getWidth() / 2, meteor.getBounds().y
					+ meteor.getBounds().getHeight() / 2);
			explosionEffect.reset();
		}

		model.getMeteorsToBlow().clear();

	}

	private void drawWeaponCooldown() {
		Gdx.gl.glEnable(GL11.GL_BLEND);
		List<AbstractArmoryItem> items = model.getSelectedArmoryItems();
		shapeRenderer.setProjectionMatrix(gameCam.combined);
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.5f);
		shapeRenderer.rect(btnItem1.getX(), btnItem1.getY(), btnItem1.getWidth(), btnItem1.getHeight()
				* items.get(0).getRemainingCooldown());
		shapeRenderer.rect(btnItem2.getX(), btnItem2.getY(), btnItem2.getWidth(), btnItem2.getHeight()
				* items.get(1).getRemainingCooldown());
		shapeRenderer.rect(btnItem3.getX(), btnItem3.getY(), btnItem3.getWidth(), btnItem3.getHeight()
				* items.get(3).getRemainingCooldown());
		shapeRenderer.rect(btnItem4.getX(), btnItem4.getY(), btnItem4.getWidth(), btnItem4.getHeight()
				* items.get(4).getRemainingCooldown());
		shapeRenderer.end();
		Gdx.gl.glDisable(GL11.GL_BLEND);
	}

	/**
	 * Sets the size of the view.
	 * 
	 * @param width
	 *            The width of the screen.
	 * @param height
	 *            The height of the screen.
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;

		float aspectRatio = (float) width / (float) height;
		scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);

		if (aspectRatio > Constants.LOGIC_ASPECTRATIO) {
			scale = (float) height / Constants.LOGIC_SCREEN_HEIGHT;
			crop.x = (width - Constants.LOGIC_SCREEN_WIDTH * scale) / 2f;
		} else if (aspectRatio < Constants.LOGIC_ASPECTRATIO) {
			scale = (float) width / (float) Constants.LOGIC_SCREEN_WIDTH;
			crop.y = (height - Constants.LOGIC_SCREEN_HEIGHT * scale) / 2f;
		} else {
			scale = (float) width / (float) Constants.LOGIC_SCREEN_WIDTH;
		}

		float w = (float) Constants.LOGIC_SCREEN_WIDTH * scale;
		float h = (float) Constants.LOGIC_SCREEN_HEIGHT * scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);
		gameStage.setViewport(gameCam.viewportWidth, gameCam.viewportHeight, true, viewport.x, viewport.y,
				viewport.width, viewport.height);
	}

	public void addListeners(InputListener inputListener, ClickListener clickListener) {
		gameStage.addListener(inputListener);
		btnItem1.addListener(clickListener);
		btnItem2.addListener(clickListener);
		btnItem3.addListener(clickListener);
		btnItem4.addListener(clickListener);
	}

	public void setInputProcessor() {
		Gdx.input.setInputProcessor(gameStage);
	}

	public void newGame(City city, List<AbstractArmoryItem> items) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
		btnstyle1.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(0).getName()
				+ ".png")));
		btnstyle2.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(1).getName()
				+ ".png")));
		btnstyle3.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(3).getName()
				+ ".png")));
		btnstyle4.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(4).getName()
				+ ".png")));
		imgCannon.setDrawable(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture(items.get(2)
				.getName() + ".png"))));
		cityMonumentSprite = new Sprite(assetsLoader.getTexture("ParisMonument.png"));
		citySprite = new Sprite(assetsLoader.getTexture("Europe1.png"));
		citySprite.setPosition(0, Constants.CITY_BOUNDS.y);
		cityMonumentSprite.setPosition(citySprite.getWidth(), Constants.CITY_BOUNDS.y);
		initCityFire();
		

		// TODO change to specific city monument
	}

	public void dispose() {
		gameStage.dispose();
		spriteBatch.dispose();
	}

	private void initCityFire() {
		float fireInterval = model.getCity().getBounds().getWidth() / 10;
		float yPos = model.getCity().getBounds().getY();
		startedFires = 0;

		fireEffect.setPosition(0, yPos);

		for (int i = 0; i < 10; i++) {
			fireEmitters[i] = fireEffect.findEmitter("Fire" + i);
			fireEmitters[i].setMinParticleCount(0);
			fireEmitters[i].setMaxParticleCount(0);
			fireEmitters[i].setPosition(fireInterval * (i + 1), yPos);
		}

		fireEffect.start();

	}

	private void updateLifeVisuals(float remainingLife) {

		// calculate how many fires to start.
		int damage = (int) ((1 - remainingLife) * 10);

		while (startedFires < damage) {
			fireEmitters[startedFires].setMinParticleCount(20);
			fireEmitters[startedFires].setMaxParticleCount(50);

			startedFires++;
		}

	}

}
