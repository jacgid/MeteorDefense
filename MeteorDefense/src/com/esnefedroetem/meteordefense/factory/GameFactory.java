package com.esnefedroetem.meteordefense.factory;

/**
 * 
 * This is the only visible class from outside the factory package.
 * It is responsible for providing access to the other factories
 *  without revealing the concrete classes.
 * 
 * @author Jacob
 *
 */
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
