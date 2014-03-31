package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

public class Continent {
	
	private String name;
	private ArrayList<City> cities;
	
	public Continent(String name, ArrayList<City> cities){
		this.name = name;
		this.cities = cities;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<City> getCities(){
		return cities;
	}
	
}
