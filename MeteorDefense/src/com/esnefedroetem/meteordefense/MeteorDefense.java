package com.esnefedroetem.meteordefense;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.esnefedroetem.meteordefense.factory.GameFactory;
import com.esnefedroetem.meteordefense.factory.IScreenFactory;
import com.esnefedroetem.meteordefense.model.ArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.ScoreHandler;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer.ArmoryDetaliedEvent;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer.ArmoryEvent;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;
import com.esnefedroetem.meteordefense.screen.*;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;
import com.esnefedroetem.meteordefense.service.ServiceFactory;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.SoundService;

/**
 * 
 * This is the main controller of the application.
 * It is responsible for switching screens and saving the application state.
 * 
 * @author Jacob
 *
 */
public class MeteorDefense extends Game implements PropertyChangeListener {

	private SplashScreen splashScreen;
	private MainMenuScreen mainMenuScreen;
	private ArmoryScreen armoryScreen;
	private ArmoryDetailedScreen armoryDetaliedScreen;
	private GameScreen gameScreen;
	private CarouselScreen carouselScreen;
	private ScoreScreen scoreScreen;
	private boolean inGame = false;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);
		Gdx.input.setCatchBackKey(true);
		splashScreen = GameFactory.getInstance().getScreenFactory().createSplashScreen(this);
		setScreen(splashScreen);
	}

	@Override
	public void pause() {
		if (Gdx.app.getType() == ApplicationType.Android) {
			if (!inGame) {
				resetCities();
			}
			save();
		}
		super.pause();
	}

	@Override
	public void dispose() {
		if (Gdx.app.getType() != ApplicationType.Android) {
			if (inGame) {
				resetCities();
			}
			save();
		}
		splashScreen.dispose();
		mainMenuScreen.dispose();
		armoryScreen.dispose();
		armoryDetaliedScreen.dispose();
		gameScreen.dispose();
		carouselScreen.dispose();
		scoreScreen.dispose();
		assetsLoader.clear();
		super.dispose();
	}

	private void save() {
		List<Continent> continents = carouselScreen.getContinents();
		List<AbstractArmoryItem> items = armoryScreen.getUnselectedArmoryItems();
		List<AbstractArmoryItem> choosenItems = armoryScreen.getSelectedArmoryItems();

		ServiceFactory.getInstance().getSaveService().save(SoundService.getInstance().getSoundState(), armoryDetaliedScreen.getWallet(),
				continents, items, choosenItems);
	}
	
	private void resetCities(){
		for (Continent c : carouselScreen.getContinents()) {
			for (City city : c.getCities()) {
				city.reset();
			}
		}
	}

	/**
	 * Initiate screens
	 */
	private void init() {
		IScreenFactory factory = GameFactory.getInstance().getScreenFactory();
		this.mainMenuScreen = factory.createMainMenuScreen(this);
		this.armoryScreen = factory.createArmoryScreen(this);
		this.armoryDetaliedScreen = factory.createArmoryDetailedScreen(this);
		this.gameScreen = factory.createGameScreen(this);
		this.carouselScreen = factory.createCarouselScreen(this);
		this.scoreScreen = factory.createScoreScreen(this);
	}

	private void changeSound() {
		SoundService.getInstance().changeSoundState();
	}

	private void newGame(City city) {
		ArrayList<Projectile> projectilesToAdd = new ArrayList<Projectile>();
		ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
		gameScreen.newGame(city, armoryScreen.getSelectedArmoryItems(), new ArmoryItemVisitor(city, city.getMeteorShower(), projectilesToAdd, projectilesToRemove), projectilesToAdd, projectilesToRemove);
		setScreen(gameScreen);
		inGame = true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equals(SplashScreenEvent.SPLASHSCREEN_ENDED.toString())) {
			init();
			setScreen(mainMenuScreen);
		} else if (evt.getPropertyName().equals(MainMenuEvent.MAINMENU_PLAY_CLICKED.toString())) {
			setScreen(carouselScreen);
		} else if (evt.getPropertyName().equals(MainMenuEvent.MAINMENU_SOUND_CLICKED.toString())) {
			changeSound();
		} else if (evt.getPropertyName().equals(CarouselEvent.CAROUSEL_NEWGAME.toString())) {
			newGame((City) evt.getNewValue());
		} else if (evt.getPropertyName().equals("Gameover")) {
			inGame = false;
			ScoreHandler scoreHandler = (ScoreHandler) evt.getNewValue();
			checkUnlockNextCity(scoreHandler, (City) evt.getOldValue());
			scoreScreen.setScore(scoreHandler);
			armoryDetaliedScreen.getWallet().addCoins(scoreHandler.getNewMoney());
			setScreen(scoreScreen);
		} else if (evt.getPropertyName().equals("Scorescreen_finished")) {
			carouselScreen.update();
			setScreen(carouselScreen);
		} else if (evt.getPropertyName().equals(ArmoryEvent.ARMORY_BACK_PRESSED.toString())) {
			setScreen(carouselScreen);
		} else if (evt.getPropertyName().equals(CarouselEvent.CAROUSEL_ARMORY_CLICKED.toString())) {
			setScreen(armoryScreen);
		} else if (evt.getPropertyName().equals(ArmoryEvent.ARMORY_ITEM_PRESSED.toString())) {
			armoryDetaliedScreen.setArmoryItem((AbstractArmoryItem) evt.getNewValue());
			setScreen(armoryDetaliedScreen);
		} else if (evt.getPropertyName().equals(ArmoryDetaliedEvent.ARMORY_DETAILED_BACK_PRESSED.toString())) {
			setScreen(armoryScreen);
		} else if (evt.getPropertyName().equals("Exit application")) {
			Gdx.app.exit();
		} else if (evt.getPropertyName().equals(SplashScreenEvent.GAMESPLASHSCREEN_ENDED.toString())) {
			setScreen(gameScreen);
		} else if (evt.getPropertyName().equals("Quit Game")) {
			setScreen(carouselScreen);
		}else if (evt.getPropertyName().equals(CarouselRenderer.CarouselEvent.CAROUSEL_BACKBUTTON2.toString())){
			setScreen(mainMenuScreen);
		}

	}

	private void checkUnlockNextCity(ScoreHandler scoreHandler, City city) {
		// if game is won, next city in the continent should be unlocked
		// (unless already unlocked)
		if (!scoreHandler.isGameLost()) {
			
			// find which continent the city belongs to
			for (Continent continent : carouselScreen.getContinents()) {
				List<City> cities = continent.getCities();
				if (cities.contains(city)) {
					
					// get next city and unlock it
					if (cities.indexOf(city) + 1 < cities.size()) {
						City nextCity = cities.get(cities.indexOf(city) + 1);
						if (nextCity.getState() == City.State.LOCKED) {
							nextCity.setState(City.State.UNLOCKED);
						}
					}
				}
			}
		}
	}

}
