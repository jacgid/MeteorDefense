package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

public class Continent {
	
	private String name;
	private List<City> cities;
	
	public Continent(String name, List<City> cities){
		this.name = name;
		this.cities = cities;
	}
	
	public String getName(){
		return name;
	}
	
	public List<City> getCities(){
		return cities;
	}
	
}
