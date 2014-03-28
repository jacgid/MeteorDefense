package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenuRenderer {
	
	private PropertyChangeSupport pcs;
	private SpriteBatch spriteBatch;
	private Stage stage;
	
	public enum MainMenuEvent{
		MAINMENU_PLAY_CLICKED,
		MAINMENU_SOUND_CLICKED
	}
	
	public MainMenuRenderer(boolean sound){
		pcs = new PropertyChangeSupport(this);
	}
	
	public void init(){
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		//Layout components
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void render(){
		
	}
	
	public void dispose(){
		spriteBatch.dispose();
		stage.dispose();
	}
	
}
