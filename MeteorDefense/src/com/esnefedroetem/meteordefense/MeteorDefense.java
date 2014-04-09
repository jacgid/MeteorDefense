package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;
import com.esnefedroetem.meteordefense.screen.*;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;
import com.esnefedroetem.meteordefense.util.SoundService;

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
		Gdx.input.setCatchBackKey(true);
		setScreen(splashScreen);
	}
	
	@Override
	public void pause(){
		SaveService.saveSoundState(SoundService.getSoundState());
		SaveService.saveWallet(gameScreen.getModel().getWallet());
		List<Continent> continents = carouselScreen.getContinents();
		for(Continent continent : continents){
			continent.unLoadMeteors(); //TODO unload meteors in when a game is finished not now!
		}
		SaveService.saveContinents(continents);
	}
	
	/**
	 * Initiate screens
	 */
	private void init(){
		splashScreen = GameFactory.createSplashScreen();
		splashScreen.addChangeListener(this);
		mainMenuScreen = GameFactory.createMainMenuScreen();
		mainMenuScreen.addChangeListener(this);
		armoryScreen = GameFactory.createArmoryScreen();
		//armoryScreen.addChangeListener(this);
		armoryDetaliedScreen = GameFactory.cretateArmoryDetailedScreen();
		//armoryDetaliedScreen.addChangeListener(this);
		carouselScreen = GameFactory.createCarouselScreen();
		carouselScreen.addChangeListener(this);
		scoreScreen = GameFactory.createScoreScreen();
		scoreScreen.addChangeListener(this);
		gameScreen = GameFactory.createGameScreen();
		
	}
	
	private void changeSound(){
		SoundService.changeSoundState();
	}
	
	private void newGame(City city){
		gameScreen.newGame(city, armoryScreen.getSelectedArmoryItems());
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
		}else if(evt.getPropertyName().equals("Gameover")){
			setScreen(scoreScreen);
		}
		
	}

}
