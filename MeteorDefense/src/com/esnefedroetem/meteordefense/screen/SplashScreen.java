package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen {
	
	private PropertyChangeSupport pcs;
	private SpriteBatch spriteBatch;
	private Texture splashTexture;
	private final int splashTime = 4000;
	private long startTime;
	
	public enum SplashScreenEvent{
		SPLASHSCREEN_ENDED
	}
	
	public SplashScreen(){
		pcs = new PropertyChangeSupport(this);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		//spriteBatch.draw(splashTexture, 0, 0);
		spriteBatch.end();
		
		if(TimeUtils.millis() - startTime > splashTime){
			pcs.firePropertyChange(SplashScreenEvent.SPLASHSCREEN_ENDED.toString(), false, true);
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
