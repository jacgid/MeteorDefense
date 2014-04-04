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
		return new MainMenuScreen(new MainMenuRenderer(true));
	}
	
	public static CarouselScreen createCarouselScreen(){
		List<Continent> continents = new ArrayList<Continent>();
		List<City> cities = new ArrayList<City>();
		cities.add(new City("ParisParisParisParis", 100, new MeteorShower()));
		cities.add(new City("LondonLondonLondonLondon", 75, new MeteorShower()));
		cities.add(new City("BerlinBerlinBerlinBerlin", 50, new MeteorShower()));
		continents.add(new Continent("EuropaEuropaEuropaEuropa", cities));
		continents.add(new Continent("AsienAsienAsienAsien", cities));
		continents.add(new Continent("AfrikaAfrikaAfrikaAfrika", cities));
		return new CarouselScreen(new CarouselRenderer(), continents);
	}
	
	public static ArmoryScreen createArmoryScreen(){
		return new ArmoryScreen(new ArmoryRenderer());
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
