package com.esnefedroetem.meteordefense.factory;

import java.beans.PropertyChangeListener;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.CannonBarrel;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.GameRenderer;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer;
import com.esnefedroetem.meteordefense.renderer.ScoreRenderer;
import com.esnefedroetem.meteordefense.screen.ArmoryDetailedScreen;
import com.esnefedroetem.meteordefense.screen.ArmoryScreen;
import com.esnefedroetem.meteordefense.screen.CarouselScreen;
import com.esnefedroetem.meteordefense.screen.GameScreen;
import com.esnefedroetem.meteordefense.screen.MainMenuScreen;
import com.esnefedroetem.meteordefense.screen.ScoreScreen;
import com.esnefedroetem.meteordefense.screen.SplashScreen;
import com.esnefedroetem.meteordefense.service.LoadService;
import com.esnefedroetem.meteordefense.util.SoundService;

public class GameFactory implements IGameFactory {
	private static final IGameFactory instance = new GameFactory();
	
	private GameFactory(){}
	
	public static IGameFactory getInstance(){
		return instance;
	}
	
	@Override
	public void createScreens(PropertyChangeListener listener, Screen mainMenuScreen, Screen armoryScreen,
			Screen armoryDetaliedScreen, Screen gameScreen,
			Screen carouselScreen, Screen scoreScreen) {
		
		mainMenuScreen = createMainMenuScreen(listener);
		
	}

	
	 private Screen createMainMenuScreen(PropertyChangeListener listener){
		boolean soundState = LoadService.getInstance().getSoundState();
		SoundService.getInstance().setSoundState(soundState);
		return new MainMenuScreen(new MainMenuRenderer(soundState, listener));
	}
	
	private Screen createCarouselScreen(PropertyChangeListener listener){
		return new CarouselScreen(new CarouselRenderer(listener), ContinentFactory.getInstance().createContinents(), listener);
	}
	
	private Screen createArmoryScreen(PropertyChangeListener listener){
		return new ArmoryScreen(new ArmoryRenderer(WeaponFactory.getInstance().getWeapons(), WeaponFactory.getInstance().getChoosenWeapons(), listener));
	}
	
	private Screen createArmoryDetailedScreen(PropertyChangeListener listener){
		ArmoryDetailedRenderer renderer = new ArmoryDetailedRenderer(listener);
		ArmoryDetailedScreen screen = new ArmoryDetailedScreen(renderer, LoadService.getInstance().getWallet());
		renderer.addChangeListener(screen);
		return screen;
	}
	
	private Screen createGameScreen(){
		GameModel model = new GameModel(new CannonBarrel());
		GameRenderer renderer = new GameRenderer(model);
		return new GameScreen(model, renderer);
	}
	
	private Screen createScoreScreen(PropertyChangeListener listener){
		return new ScoreScreen(new ScoreRenderer(listener));
	}

	
	
	
	
}
