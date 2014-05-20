package com.esnefedroetem.meteordefense.factory;


public class GameFactory {
	
	private static GameFactory instance = new GameFactory();
	
	private GameFactory(){
		
	}
	
	public static GameFactory getInstance(){
		return instance;
	}
	
	public IScreenFactory getScreenFactory(){
		return new ScreenFactory();
	}
	
}
