package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

public class Continent {
	
	private String name;
	private List<City> cities;
	private int allStars;
	
	public Continent(String name, List<City> cities){
		this.name = name;
		this.cities = cities;
		allStars = cities.size() * 3;
	}
	
	public Continent(){
		//Used by the loadService
	}
	
	public String getName(){
		return name;
	}
	
	public List<City> getCities(){
		return cities;
	}
	
	public int getStars(){
		int stars = 0;
		for(City city : cities){
			stars += city.getStars();
		}
		return stars;
	}
	
	public int getAllStars(){
		return allStars;
	}
		
}
