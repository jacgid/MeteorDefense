package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.IntIntMap;
import com.esnefedroetem.meteordefense.factory.GameFactory;
import com.esnefedroetem.meteordefense.factory.IGameFactory;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.ScoreHandler;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer.ArmoryDetaliedEvent;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer.ArmoryEvent;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;
import com.esnefedroetem.meteordefense.screen.*;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;
import com.esnefedroetem.meteordefense.service.SaveService;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
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
		Texture.setEnforcePotImages(false);
		Gdx.input.setCatchBackKey(true);
		splashScreen = new SplashScreen(this);
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
		SaveService.saveSoundState(SoundService.getInstance().getSoundState());
		SaveService.saveWallet(armoryDetaliedScreen.getWallet());
		List<Continent> continents = carouselScreen.getContinents();
		SaveService.saveContinents(continents);
	}
	
	/**
	 * Initiate screens
	 */
	private void init(){
		GameFactory.getInstance().createScreens(this);
	}
	
	public void setScreens(MainMenuScreen mainMenuScreen, ArmoryScreen armoryScreen,
			ArmoryDetailedScreen armoryDetaliedScreen, GameScreen gameScreen,
			CarouselScreen carouselScreen, ScoreScreen scoreScreen){
		this.mainMenuScreen = mainMenuScreen;
		this.armoryScreen = armoryScreen;
		this.armoryDetaliedScreen = armoryDetaliedScreen;
		this.gameScreen = gameScreen;
		this.carouselScreen = carouselScreen;
		this.scoreScreen = scoreScreen;
		
	}
	
	private void changeSound(){
		SoundService.getInstance().changeSoundState();
	}
	
	private void newGame(City city){
		gameScreen.newGame(city, armoryScreen.getSelectedArmoryItems());
		splashScreen.gameSplash(gameScreen.getRenderer());
		setScreen(splashScreen);
		inGame = true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(evt.getPropertyName().equals(SplashScreenEvent.SPLASHSCREEN_ENDED.toString())){
			init();
			setScreen(mainMenuScreen);
		}else if(evt.getPropertyName().equals(MainMenuEvent.MAINMENU_PLAY_CLICKED.toString())){
			setScreen(carouselScreen);
		}else if(evt.getPropertyName().equals(MainMenuEvent.MAINMENU_SOUND_CLICKED.toString())){
			changeSound();
		}else if(evt.getPropertyName().equals(CarouselEvent.CAROUSEL_NEWGAME.toString())){
			newGame((City)evt.getNewValue());
		}else if(evt.getPropertyName().equals("Gameover")){
			inGame= false;
			ScoreHandler scoreHandler = (ScoreHandler)evt.getNewValue();
			scoreScreen.setScore(scoreHandler);
			armoryDetaliedScreen.getWallet().addCoins(scoreHandler.getNewMoney());
			setScreen(scoreScreen);
		}else if(evt.getPropertyName().equals("Scorescreen_finished")){
			carouselScreen.update();
			setScreen(carouselScreen);	
		}else if(evt.getPropertyName().equals(ArmoryEvent.ARMORY_BACK_PRESSED.toString())){
			setScreen(carouselScreen);			
		}else if(evt.getPropertyName().equals(CarouselEvent.CAROUSEL_ARMORY_CLICKED.toString())){
			setScreen(armoryScreen);
		}else if(evt.getPropertyName().equals(ArmoryEvent.ARMORY_ITEM_PRESSED.toString())){
			armoryDetaliedScreen.setArmoryItem((AbstractArmoryItem) evt.getNewValue());
			setScreen(armoryDetaliedScreen);
		}else if(evt.getPropertyName().equals(ArmoryDetaliedEvent.ARMORY_DETAILED_BACK_PRESSED.toString())){
			setScreen(armoryScreen);
		}else if(evt.getPropertyName().equals("Exit application")){
			Gdx.app.exit();
		}else if(evt.getPropertyName().equals(SplashScreenEvent.GAMESPLASHSCREEN_ENDED.toString())){
			setScreen(gameScreen);
		}
		
	}

}
