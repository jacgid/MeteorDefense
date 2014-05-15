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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.renderer.GameRenderer;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

public class SplashScreen implements Screen {
	
	private PropertyChangeSupport pcs;
	private final int splashTime = 300;
	private long startTime;
	private boolean isLoaded;
	private Stage stage;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	
	public enum SplashScreenEvent{
		SPLASHSCREEN_ENDED, GAMESPLASHSCREEN_ENDED
	}
	
	public SplashScreen(PropertyChangeListener listener){
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);
		table.add(new Image(new Texture("data/textures/Splash.png"))).expand().width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight());
		stage.addActor(table);
		isLoaded = false;
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		if(TimeUtils.millis() - startTime > splashTime){
			if(!isLoaded){
				assetsLoader.createFonts();
				assetsLoader.loadParticleEffect("test.p");
				assetsLoader.loadAllTextures();
				
				isLoaded = true;
				pcs.firePropertyChange(SplashScreenEvent.SPLASHSCREEN_ENDED.toString(), false, true);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		startTime = TimeUtils.millis();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
