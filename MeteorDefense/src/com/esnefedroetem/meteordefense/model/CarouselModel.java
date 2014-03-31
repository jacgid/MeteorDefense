package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

public class CarouselModel {
	
	private boolean isCitiesDisplayed;
	private List<Continent> continents;
	private Continent currentContinent;
	
	public CarouselModel(){
		isCitiesDisplayed = false;
		currentContinent = null;
		continents = new ArrayList<Continent>();
		List<City> cities = new ArrayList<City>();
		cities.add(new City("Paris", 100, new MeteorShower()));
		cities.add(new City("London", 75, new MeteorShower()));
		cities.add(new City("Berlin", 50, new MeteorShower()));
		continents.add(new Continent("Europa", cities));
		continents.add(new Continent("Asien", cities));
		continents.add(new Continent("Afrika", cities));
		
	}
	
	public boolean isCitiesDisplayed(){
		return isCitiesDisplayed;
	}
	
	public List<City> displayCities(int position){
		isCitiesDisplayed = true;
		currentContinent = continents.get(position);
		return currentContinent.getCities();
	}
	
	public List<Continent> displayContinents(){
		isCitiesDisplayed = false;
		currentContinent = null;
		return continents;
	}

	public City getCity(int position){
		return currentContinent.getCities().get(position);
	}
	
	public List<City> getCurrentCitites(){
		return currentContinent.getCities();
	}

}
