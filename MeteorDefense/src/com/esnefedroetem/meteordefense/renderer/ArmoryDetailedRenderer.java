package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ArmoryDetailedRenderer {
	
	private PropertyChangeSupport pcs;
	private Stage stage;
	private SpriteBatch spriteBatch;
	
	public enum ArmoryDetaliedEvent{
		ARMORY_DETAILED_BACK_PRESSED
	}
	
	public ArmoryDetailedRenderer(){
		pcs = new PropertyChangeSupport(this);
		create();
	}
	
	private void create(){
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		
		stage.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event,
		              int keycode){
				if(keycode == Keys.BACK){
			 		pcs.firePropertyChange(ArmoryDetaliedEvent.ARMORY_DETAILED_BACK_PRESSED.toString(), false, true);					
				}
				return true;
			}
		});
	}
	
	public void init(){
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render(){
		stage.act();
		Gdx.gl.glClearColor(0, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
	
	public void dispose(){
		stage.dispose();
		spriteBatch.dispose();
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	
}
