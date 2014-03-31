package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeSupport;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;

public class CarouselRenderer {
	
	private PropertyChangeSupport pcs;
	private SpriteBatch spriteBatch;
	private Stage stage;
	private PagedScrollPane scroll;
	
	public enum CarouselEvent{
		CAROUSEL_ARMORY_CLICKED,
		CAROUSEL_CLICKED
	}
	
	public CarouselRenderer(){
		pcs = new PropertyChangeSupport(this);
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		
		create();
	}
	
	private void create(){
		scroll = new PagedScrollPane();
		scroll.setFlingTime(0.1f);
		scroll.setPageSpacing(25);
		
		Table table = new Table();
		stage.addActor(table);
		table.setFillParent(true);
		
		TextButtonStyle armoryButtonstyle = new TextButtonStyle();
		armoryButtonstyle.font = new BitmapFont();
		armoryButtonstyle.font.scale(2);

		TextButton armoryButton = new TextButton("Armory", armoryButtonstyle);
		
		table.add(scroll).expand().fill();
		table.row();
		table.add(armoryButton).bottom().left();
		
		armoryButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
		 	}
		 
		 	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		 		pcs.firePropertyChange(CarouselEvent.CAROUSEL_ARMORY_CLICKED.toString(), false, true);
		 	}

		});
		
	}
	
	public void init(){
		Gdx.input.setInputProcessor(stage);
	}
	
	public void displayContinents(List<Continent> list){
		
	}
	
	public void displayCities(List<City> list){
		
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
