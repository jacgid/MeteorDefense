package com.esnefedroetem.meteordefense.factory;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.City.State;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.service.LoadService;

class ContinentFactory {
	private static final ContinentFactory instance = new ContinentFactory();
	
	private ContinentFactory(){}
	
	protected static ContinentFactory getInstance(){
		return instance;
	}
	
	protected List<Continent> createContinents(){
		List<Continent> continents = null;
		continents = LoadService.getInstance().getContinents();
		if(continents == null){
			continents = new ArrayList<Continent>(4);
			continents.add(createEurope());
			continents.add(createAsia());
			continents.add(createAmerica());
			continents.add(createAntarctica());			
		}
		return continents;
	}
	
	private Continent createEurope(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City("Paris", 20, new MeteorShower(5,5,5,5,5), State.UNLOCKED));
		cities.add(new City("London", 10, new MeteorShower(0,0,20,0,0), State.UNLOCKED));
		cities.add(new City("Berlin", 50, new MeteorShower(5,0,0,0,5), State.UNLOCKED));
		return new Continent("Europe", cities);
	}
	
	private Continent createAsia(){
		
		List<City> cities = new ArrayList<City>();
		cities.add(new City("Shanghai", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("Tokyo", 75, new MeteorShower(), State.LOCKED));
		cities.add(new City("Dubai", 50, new MeteorShower(), State.LOCKED));
		return new Continent("Asia", cities);
	}
	
	private Continent createAmerica(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City("New York", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("Washington", 75, new MeteorShower(), State.LOCKED));
		cities.add(new City("Ottawa", 50, new MeteorShower(), State.LOCKED));
		return new Continent("America", cities);
	}
	
	private Continent createAntarctica(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City("SouthPole", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("Ice", 75, new MeteorShower(), State.LOCKED));
		cities.add(new City("Mountain", 50, new MeteorShower(), State.LOCKED));
		return new Continent("Antarctica", cities);
	}
	
}
