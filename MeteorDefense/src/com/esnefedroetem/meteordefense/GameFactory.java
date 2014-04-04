package com.esnefedroetem.meteordefense;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.GameRenderer;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer;
import com.esnefedroetem.meteordefense.renderer.ScoreRenderer;
import com.esnefedroetem.meteordefense.screen.*;

public class GameFactory {
	
	public static SplashScreen createSplashScreen(){
		return new SplashScreen();
	}
	
	public static MainMenuScreen createMainMenuScreen(){
		return new MainMenuScreen(new MainMenuRenderer(true)); // TODO load sound state
	}
	
	public static CarouselScreen createCarouselScreen(){
		return new CarouselScreen(new CarouselRenderer(), ContinentFactory.createContinents()); // TODO load continents
	}
	
	public static ArmoryScreen createArmoryScreen(){
		return new ArmoryScreen(new ArmoryRenderer(), null ,null); // TODO load weapons
	}
	
	public static ArmoryDetailedScreen cretateArmoryDetailedScreen(){
		return new ArmoryDetailedScreen(new ArmoryDetailedRenderer());
	}
	
	public static GameScreen createGameScreen(){
		GameModel model = new GameModel(new Wallet()); // TODO load wallet from file
		GameRenderer renderer = new GameRenderer(model);
		return new GameScreen(model, renderer);
	}
	
	public static ScoreScreen createScoreScreen(){
		return new ScoreScreen(new ScoreRenderer());
	}
	
	
	
	
}
