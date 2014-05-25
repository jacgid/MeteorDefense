package com.esnefedroetem.meteordefense.service;

/**
 * 
 * The ServiceFactory is responsible for creating services.
 * 
 * @author Jacob
 *
 */
public class ServiceFactory {
	
	private static ServiceFactory instance = new ServiceFactory();
	
	private ServiceFactory(){
		
	}
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	public ISaveService getSaveService(){
		return new SaveService();
	}
	
	public ILoadService getLoadService(){
		return new LoadService();
	}
	
}
