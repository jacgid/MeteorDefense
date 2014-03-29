package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;
import com.esnefedroetem.meteordefense.screen.*;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;

public class MeteorDefense extends Game implements PropertyChangeListener {
	
	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private ArmoryScreen armoryScreen;
	private ArmoryDetailedScreen armoryDetaliedScreen;
	private GameScreen gameScreen;
	private CarouselScreen carouselScreen;
	private GameScreen gamescreen;
	
	private Player player;
	
	
	@Override
	public void create() {
		init();
		setScreen(splashScreen);
	}
	
	/**
	 * Initiate screens
	 */
	private void init(){
		splashScreen = new SplashScreen();
		splashScreen.addChangeListener(this);
		mainMenuScreen = new MainMenuScreen(true);//change true to sound state
		mainMenuScreen.addChangeListener(this);
		armoryScreen = new ArmoryScreen();
		//armoryScreen.addChangeListener(this);
		armoryDetaliedScreen = new ArmoryDetailedScreen();
		//armoryDetaliedScreen.addChangeListener(this);
		carouselScreen = new CarouselScreen();
		carouselScreen.addChangeListener(this);
		player = new Player();
		gamescreen = new GameScreen(player);
		
	}
	
	private void changeSound(){
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName().equals(SplashScreenEvent.SPLASHSCREEN_ENDED.toString())){
			setScreen(mainMenuScreen);
		}else if(evt.getPropertyName().equals(MainMenuEvent.MAINMENU_PLAY_CLICKED.toString())){
			setScreen(carouselScreen);
		}else if(evt.getPropertyName().equals(MainMenuEvent.MAINMENU_SOUND_CLICKED.toString())){
			changeSound();
		}
		
	}

}
