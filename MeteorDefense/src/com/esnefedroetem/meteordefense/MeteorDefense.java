package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.badlogic.gdx.Game;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
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
	private ScoreScreen scoreScreen;
	
	
	@Override
	public void create() {
		init();
		setScreen(splashScreen);
	}
	
	/**
	 * Initiate screens
	 */
	private void init(){
		splashScreen = GameFactory.createSplashScreen();
		splashScreen.addChangeListener(this);
		mainMenuScreen = GameFactory.createMainMenuScreen();//change true to sound state
		mainMenuScreen.addChangeListener(this);
		armoryScreen = GameFactory.createArmoryScreen();
		//armoryScreen.addChangeListener(this);
		armoryDetaliedScreen = GameFactory.cretateArmoryDetailedScreen();
		//armoryDetaliedScreen.addChangeListener(this);
		carouselScreen = GameFactory.createCarouselScreen();
		carouselScreen.addChangeListener(this);
		scoreScreen = GameFactory.createScoreScreen();
		//scoreScreen.addChangeListener(this);
		gameScreen = GameFactory.createGameScreen();
		
	}
	
	private void changeSound(){
		
	}
	
	private void newGame(City city){
		gameScreen.newGame(city);
		setScreen(gameScreen);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName().equals(SplashScreenEvent.SPLASHSCREEN_ENDED.toString())){
			setScreen(mainMenuScreen);
		}else if(evt.getPropertyName().equals(MainMenuEvent.MAINMENU_PLAY_CLICKED.toString())){
			setScreen(carouselScreen);
		}else if(evt.getPropertyName().equals(MainMenuEvent.MAINMENU_SOUND_CLICKED.toString())){
			changeSound();
		}else if(evt.getPropertyName().equals(CarouselEvent.CAROUSEL_NEWGAME.toString())){
			newGame((City)evt.getNewValue());
		}
		
	}

}
