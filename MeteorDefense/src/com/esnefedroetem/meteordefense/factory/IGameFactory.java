package com.esnefedroetem.meteordefense.factory;

import java.beans.PropertyChangeListener;

import com.esnefedroetem.meteordefense.screen.ArmoryDetailedScreen;
import com.esnefedroetem.meteordefense.screen.ArmoryScreen;
import com.esnefedroetem.meteordefense.screen.CarouselScreen;
import com.esnefedroetem.meteordefense.screen.GameScreen;
import com.esnefedroetem.meteordefense.screen.MainMenuScreen;
import com.esnefedroetem.meteordefense.screen.ScoreScreen;
import com.esnefedroetem.meteordefense.screen.SplashScreen;

public interface IGameFactory {

	public MainMenuScreen createMainMenuScreen(PropertyChangeListener listener);

	public CarouselScreen createCarouselScreen(PropertyChangeListener listener);

	public ArmoryScreen createArmoryScreen(PropertyChangeListener listener);

	public ArmoryDetailedScreen createArmoryDetailedScreen(PropertyChangeListener listener);

	public GameScreen createGameScreen(PropertyChangeListener meteorDefense);

	public ScoreScreen createScoreScreen(PropertyChangeListener listener);
	
	public SplashScreen createSplashScreen(PropertyChangeListener listener);

}
