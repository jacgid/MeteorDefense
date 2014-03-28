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
import com.esnefedroetem.meteordefense.screen.*;

public class MeteorDefense extends Game implements PropertyChangeListener {
	
	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private ArmoryScreen armoryScreen;
	private ArmoryDetailedScreen armoryDetaliedScreen;
	//private GameScreen gameScreen;
	private CarouselScreen carouselScreen;
	
	
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
		mainMenuScreen = new MainMenuScreen();
		mainMenuScreen.addChangeListener(this);
		armoryScreen = new ArmoryScreen();
		//armoryScreen.addChangeListener(this);
		armoryDetaliedScreen = new ArmoryDetailedScreen();
		//armoryDetaliedScreen.addChangeListener(this);
		carouselScreen = new CarouselScreen();
		carouselScreen.addChangeListener(this);
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName().equals(SplashScreen.SplashScreenEvent.SPLASHSCREEN_ENDED.toString())){
			setScreen(mainMenuScreen);
		}
		
	}

}
