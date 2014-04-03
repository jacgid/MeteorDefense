package com.esnefedroetem.meteordefense;

import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer;
import com.esnefedroetem.meteordefense.renderer.ScoreRenderer;
import com.esnefedroetem.meteordefense.screen.*;

public class GameFactory {
	
	public static SplashScreen createSplashScreen(){
		return new SplashScreen();
	}
	
	public static MainMenuScreen createMainMenuScreen(){
		return new MainMenuScreen(new MainMenuRenderer(true));
	}
	
	public static CarouselScreen createCarouselScreen(){
		return new CarouselScreen(new CarouselRenderer(), null);
	}
	
	public static ArmoryScreen createArmoryScreen(){
		return new ArmoryScreen(new ArmoryRenderer());
	}
	
	public static ArmoryDetailedScreen cretateArmoryDetailedScreen(){
		return new ArmoryDetailedScreen(new ArmoryDetailedRenderer());
	}
	
	public static GameScreen createGameScreen(){
		return new GameScreen();
	}
	
	public static ScoreScreen createScoreScreen(){
		return new ScoreScreen(new ScoreRenderer());
	}
	
	
	
	
}
