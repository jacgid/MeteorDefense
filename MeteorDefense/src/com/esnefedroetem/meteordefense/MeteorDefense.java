package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.badlogic.gdx.Game;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;
import com.esnefedroetem.meteordefense.screen.ArmoryDetailedScreen;
import com.esnefedroetem.meteordefense.screen.ArmoryScreen;
import com.esnefedroetem.meteordefense.screen.CarouselScreen;
import com.esnefedroetem.meteordefense.screen.GameScreen;
import com.esnefedroetem.meteordefense.screen.MainMenuScreen;
import com.esnefedroetem.meteordefense.screen.SplashScreen;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;

public class MeteorDefense extends Game implements PropertyChangeListener {
	
	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private ArmoryScreen armoryScreen;
	private ArmoryDetailedScreen armoryDetaliedScreen;
	private GameScreen gameScreen;
	private CarouselScreen carouselScreen;
	
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
		
	}
	
	private void changeSound(){
		
	}
	
	private void newGame(City city){
		gameScreen = new GameScreen(player, city);
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
