package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class MainMenuRenderer {
	
	private PropertyChangeSupport pcs;
	private SpriteBatch spriteBatch;
	private Stage stage;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	
	public enum MainMenuEvent{
		MAINMENU_PLAY_CLICKED,
		MAINMENU_SOUND_CLICKED
	}
	
	public MainMenuRenderer(boolean sound, PropertyChangeListener listener){
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		create(sound);
	}
	
	private void create(boolean sound){
		Table table = new Table();
		table.setFillParent(true);
		Table tablebottom = new Table();
		tablebottom.setFillParent(true);
		Table background = new Table();
		background.setFillParent(true);
		background.add(new Image(assetsLoader.getTexture("StartScreenBG.png"))).width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight());
		stage.addActor(background);
		stage.addActor(table);
		stage.addActor(tablebottom);
		
		ButtonStyle playButtonstyle = new ButtonStyle();
		playButtonstyle.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("PlayButton.png")));
		
		ButtonStyle soundButtonstyle = new ButtonStyle();
		soundButtonstyle.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("MusicTrue.png")));
		soundButtonstyle.checked = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("MusicFalse.png")));

		Button playButton = new Button(playButtonstyle);
		final Button soundButton = new Button(soundButtonstyle);
		
		Table wrapper = new Table();
		float aspect = playButton.getHeight() / playButton.getWidth();
		wrapper.add(playButton).width(Gdx.graphics.getWidth() * 0.8F).height(Gdx.graphics.getWidth() * 0.8F * aspect);
		
		table.add(wrapper).expand().center();
		tablebottom.add(soundButton).left().bottom().expand().pad(10).width(Gdx.graphics.getWidth() / 5).height(Gdx.graphics.getWidth() / 5);
		
		soundButton.setChecked(!sound);
				
		soundButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
		 		pcs.firePropertyChange(MainMenuEvent.MAINMENU_SOUND_CLICKED.toString(), false, true);
			}
		});
		
		playButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
		 		pcs.firePropertyChange(MainMenuEvent.MAINMENU_PLAY_CLICKED.toString(), false, true);				
			}
		});
		
		stage.addListener(new InputListener(){
			
			@Override
			public boolean keyDown(InputEvent event,
		              int keycode){
				if(keycode == Keys.BACK){
			 		pcs.firePropertyChange("Exit application", false, true);					
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
