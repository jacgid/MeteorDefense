package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer;

public class MainMenuScreen implements Screen {
	
	private PropertyChangeSupport pcs;
	private MainMenuRenderer renderer;
	private boolean sound;
	
	public MainMenuScreen(boolean sound){
		pcs = new PropertyChangeSupport(this);
		this.sound = sound;
		renderer = new MainMenuRenderer(sound);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		renderer.addChangeListener(listener);
	}
	
	@Override
	public void render(float delta) {
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		renderer.init();
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
		renderer.dispose();
	}

}
