package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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

		spriteBatch = new SpriteBatch();
		stage = new Stage();
		
		create(sound);
	}
	
	private void create(boolean sound){
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		TextButtonStyle playButtonstyle = new TextButtonStyle();
		playButtonstyle.font = new BitmapFont();
		playButtonstyle.font.scale(5);
		
		TextButtonStyle soundButtonstyle = new TextButtonStyle();
		soundButtonstyle.font = new BitmapFont();
		soundButtonstyle.font.scale(2);

		TextButton playButton = new TextButton("Play", playButtonstyle);
		final TextButton soundButton = new TextButton("Sound", soundButtonstyle);
		
		table.add(playButton).expand().bottom();
		table.row().bottom().left().expand();
		table.add(soundButton).left().bottom();
		
		soundButton.setChecked(!sound);
		soundButton.setText(!soundButton.isChecked() + "");
				
		soundButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
		 		pcs.firePropertyChange(MainMenuEvent.MAINMENU_SOUND_CLICKED.toString(), false, true);
				soundButton.setText(!soundButton.isChecked() + "");				
			}
		});
		
		playButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
		 		pcs.firePropertyChange(MainMenuEvent.MAINMENU_PLAY_CLICKED.toString(), false, true);				
			}
		});
		
	}
	
	public void init(){
		Gdx.input.setInputProcessor(stage);		
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void render(){
		stage.act();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();

	}
	
	public void dispose(){
		spriteBatch.dispose();
		stage.dispose();
	}
	
}
