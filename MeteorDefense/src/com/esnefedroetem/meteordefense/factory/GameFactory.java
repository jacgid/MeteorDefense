package com.esnefedroetem.meteordefense.factory;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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

	private GameFactory() {
	}

	public static IGameFactory getInstance() {
		return instance;
	}

	public MainMenuScreen createMainMenuScreen(PropertyChangeListener listener) {
		boolean soundState = LoadService.getInstance().getSoundState();
		SoundService.getInstance().setSoundState(soundState);
		return new MainMenuScreen(new MainMenuRenderer(soundState, listener));
	}

	public CarouselScreen createCarouselScreen(PropertyChangeListener listener) {
		return new CarouselScreen(new CarouselRenderer(listener),
				ContinentFactory.getInstance().createContinents(), listener);
	}

	public ArmoryScreen createArmoryScreen(PropertyChangeListener listener) {
		List<AbstractArmoryItem> items = WeaponFactory.getInstance().getWeapons();
		List<AbstractArmoryItem> choosenItems = new ArrayList<AbstractArmoryItem>(5);
		for(int i = 5; i > 0; i--){
			choosenItems.add(items.get(items.size() - i));
		}
		for(int i = 0; i < 5; i++){
			items.remove(items.size() - 1);
		}
		return new ArmoryScreen(new ArmoryRenderer(items, choosenItems,
				listener));
	}

	public ArmoryDetailedScreen createArmoryDetailedScreen(PropertyChangeListener listener) {
		ArmoryDetailedRenderer renderer = new ArmoryDetailedRenderer(listener);
		ArmoryDetailedScreen screen = new ArmoryDetailedScreen(renderer,
				LoadService.getInstance().getWallet());
		renderer.addChangeListener(screen);
		return screen;
	}

	public GameScreen createGameScreen(PropertyChangeListener meteorDefense) {
		GameModel model = new GameModel(meteorDefense, new CannonBarrel());
		GameRenderer renderer = new GameRenderer(model);
		return new GameScreen(model, renderer);
	}

	public ScoreScreen createScoreScreen(PropertyChangeListener listener) {
		return new ScoreScreen(new ScoreRenderer(listener));
	}

	@Override
	public SplashScreen createSplashScreen(PropertyChangeListener listener) {
		return new SplashScreen(listener);
	}

}
