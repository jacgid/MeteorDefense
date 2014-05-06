package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.renderer.GameRenderer;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

public class SplashScreen implements Screen {
	
	private PropertyChangeSupport pcs;
	private SpriteBatch spriteBatch;
	private Texture splashTexture;
	private final int splashTime = 1000;
	private long startTime;
	private boolean gameSplash = false, texturesLoaded = false;
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	private float barLenght = Constants.LOGIC_SCREEN_WIDTH/4, barHeight = Constants.LOGIC_SCREEN_HEIGHT/20;
	private OrthographicCamera cam;
	private GameRenderer gamerenderer;
	
	public enum SplashScreenEvent{
		SPLASHSCREEN_ENDED, GAMESPLASHSCREEN_ENDED
	}
	
	public SplashScreen(){
		pcs = new PropertyChangeSupport(this);
		cam = new OrthographicCamera(Constants.LOGIC_SCREEN_WIDTH, Constants.LOGIC_SCREEN_HEIGHT);
		cam.position.set(Constants.LOGIC_SCREEN_WIDTH/2, Constants.LOGIC_SCREEN_HEIGHT/2, 0);
		cam.update();
		debugRenderer.setProjectionMatrix(cam.combined);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void gameSplash(GameRenderer renderer){
		gamerenderer = renderer;
		gameSplash = true;
	}
	
	private void drawStartSplash(){
		//spriteBatch.draw(splashTexture, 0, 0);
	}
	
	private void drawGameSplash(){
		debugRenderer.begin(ShapeType.Filled);
		debugRenderer.rect(Constants.LOGIC_SCREEN_WIDTH/3, Constants.LOGIC_SCREEN_HEIGHT/2, barLenght*AssetsLoader.getProgress(), barHeight);
		debugRenderer.end();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(gameSplash){
			drawGameSplash();
		}else{
			spriteBatch.begin();
			drawStartSplash();
			spriteBatch.end();
		}
		
		if(TimeUtils.millis() - startTime > splashTime && gameSplash == false){
			pcs.firePropertyChange(SplashScreenEvent.SPLASHSCREEN_ENDED.toString(), false, true);
		}else if(gameSplash){
			if(AssetsLoader.update()){
				gamerenderer.loadScene();
				pcs.firePropertyChange(SplashScreenEvent.GAMESPLASHSCREEN_ENDED.toString(), false, true);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		//splashTexture = new Texture("some Path");
		startTime = TimeUtils.millis();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		//splashTexture.dispose();
	}

}
