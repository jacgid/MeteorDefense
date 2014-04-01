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
		cities.add(new City("ParisParisParisParis", 100, new MeteorShower()));
		cities.add(new City("LondonLondonLondonLondon", 75, new MeteorShower()));
		cities.add(new City("BerlinBerlinBerlinBerlin", 50, new MeteorShower()));
		continents.add(new Continent("EuropaEuropaEuropaEuropa", cities));
		continents.add(new Continent("AsienAsienAsienAsien", cities));
		continents.add(new Continent("AfrikaAfrikaAfrikaAfrika", cities));
		
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
