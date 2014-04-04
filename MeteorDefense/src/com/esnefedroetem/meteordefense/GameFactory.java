package com.esnefedroetem.meteordefense;

import com.esnefedroetem.meteordefense.model.CannonBarrel;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.Wallet;
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

public class GameFactory {
	
	public static SplashScreen createSplashScreen(){
		return new SplashScreen();
	}
	
	public static MainMenuScreen createMainMenuScreen(){
		return new MainMenuScreen(new MainMenuRenderer(true)); // TODO load sound state
	}
	
	public static CarouselScreen createCarouselScreen(){
		return new CarouselScreen(new CarouselRenderer(), ContinentFactory.createContinents());
	}
	
	public static ArmoryScreen createArmoryScreen(){
		return new ArmoryScreen(new ArmoryRenderer(), WeaponFactory.getWeapons(), WeaponFactory.getChoosenWeapons()); // TODO load weapons
	}
	
	public static ArmoryDetailedScreen cretateArmoryDetailedScreen(){
		return new ArmoryDetailedScreen(new ArmoryDetailedRenderer());
	}
	
	public static GameScreen createGameScreen(){
		GameModel model = new GameModel(new Wallet(), new CannonBarrel()); // TODO load wallet from file
		GameRenderer renderer = new GameRenderer(model);
		return new GameScreen(model, renderer);
	}
	
	public static ScoreScreen createScoreScreen(){
		return new ScoreScreen(new ScoreRenderer());
	}
	
	
	
	
}
