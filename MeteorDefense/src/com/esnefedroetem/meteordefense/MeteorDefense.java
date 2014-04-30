package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer.ArmoryDetaliedEvent;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer.ArmoryEvent;
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
	private boolean inGame= false;
	
	
	@Override
	public void create() {
		init();
		Gdx.input.setCatchBackKey(true);
		setScreen(splashScreen);
	}
	
	@Override
	public void pause(){
		super.pause();
		if(Gdx.app.getType() == ApplicationType.Android){
			save();
		}
	}
	
	
	@Override
	public void dispose(){
		super.dispose();
		if(inGame){
			for ( Continent c : carouselScreen.getContinents()){
				for(City city : c.getCities()){
					city.reset();
				}
			}
		}		
		save();
	}
	
	private void save(){
		SaveService.saveSoundState(SoundService.getSoundState());
		SaveService.saveWallet(gameScreen.getModel().getWallet());
		List<Continent> continents = carouselScreen.getContinents();
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
		armoryScreen.addChangeListener(this);
		armoryDetaliedScreen = GameFactory.cretateArmoryDetailedScreen();
		armoryDetaliedScreen.addChangeListener(this);
		carouselScreen = GameFactory.createCarouselScreen();
		carouselScreen.addChangeListener(this);
		scoreScreen = GameFactory.createScoreScreen();
		scoreScreen.addChangeListener(this);
		gameScreen = GameFactory.createGameScreen();
		gameScreen.addChangeListener(this);
		
	}
	
	private void changeSound(){
		SoundService.changeSoundState();
	}
	
	private void newGame(City city){
		gameScreen.newGame(city, WeaponFactory.getChoosenWeapons());//armoryScreen.getSelectedArmoryItems());
		setScreen(gameScreen);
		inGame = true;
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
			inGame= false;
			scoreScreen.setScore((Integer)evt.getNewValue(), (Boolean)evt.getOldValue());
			setScreen(scoreScreen);
		}else if(evt.getPropertyName().equals("Scorescreen_finished")){
			setScreen(carouselScreen);	
		}else if(evt.getPropertyName().equals(ArmoryEvent.ARMORY_BACK_PRESSED.toString())){
			setScreen(carouselScreen);			
		}else if(evt.getPropertyName().equals(CarouselEvent.CAROUSEL_ARMORY_CLICKED.toString())){
			setScreen(armoryScreen);
		}else if(evt.getPropertyName().equals(ArmoryEvent.ARMORY_ITEM_PRESSED.toString())){
			setScreen(armoryDetaliedScreen);
		}else if(evt.getPropertyName().equals(ArmoryDetaliedEvent.ARMORY_DETAILED_BACK_PRESSED.toString())){
			setScreen(armoryScreen);
		}
		
	}

}
