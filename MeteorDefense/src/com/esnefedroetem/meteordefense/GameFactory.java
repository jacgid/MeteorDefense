package com.esnefedroetem.meteordefense;

import com.esnefedroetem.meteordefense.renderer.ScoreRenderer;
import com.esnefedroetem.meteordefense.screen.*;

public class GameFactory {
	
	public static SplashScreen createSplashScreen(){
		return new SplashScreen();
	}
	
	public static MainMenuScreen createMainMenuScreen(){
		return new MainMenuScreen();
	}
	
	public static CarouselScreen createCarouselScreen(){
		return new CarouselScreen();
	}
	
	public static ArmoryScreen createArmoryScreen(){
		return new ArmoryScreen();
	}
	
	public static ArmoryDetailedScreen cretateArmoryDetailedScreen(){
		return new ArmoryDetailedScreen();
	}
	
	public static GameScreen createGameScreen(){
		return new GameScreen();
	}
	
	public static ScoreScreen createScoreScreen(){
		return new ScoreScreen(new ScoreRenderer());
	}
	
	
	
	
}
