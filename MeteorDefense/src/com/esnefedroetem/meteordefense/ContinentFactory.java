package com.esnefedroetem.meteordefense;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.City.State;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.MeteorShower;

public class ContinentFactory {

	public static List<Continent> createContinents(){
		List<Continent> continents = new ArrayList<Continent>(4);
		continents.add(createEurope());
		continents.add(createAsia());
		continents.add(createAmerica());
		continents.add(createAntarctica());
		
		
		return continents;
	}
	
	private static Continent createEurope(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City("ParisParisParisParis", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("LondonLondonLondonLondon", 75, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("BerlinBerlinBerlinBerlin", 50, new MeteorShower(), State.UNLOCKED));
		return new Continent("EuropeEuropeEuropeEurope", cities);
	}
	
	private static Continent createAsia(){
		
		List<City> cities = new ArrayList<City>();
		cities.add(new City("ShanghaiShanghaiShanghai", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("TokyoTokyoTokyoTokyo", 75, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("DubaiDubaiDubaiDubai", 50, new MeteorShower(), State.UNLOCKED));
		return new Continent("AsiaAsiaAsiaAsiaAsia", cities);
	}
	
	private static Continent createAmerica(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City("NewYorkNewYorkNewYork", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("WashingtonWashington", 75, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("OttawaOttawaOttawaOttawa", 50, new MeteorShower(), State.UNLOCKED));
		return new Continent("AmericaAmericaAmerica", cities);
	}
	
	private static Continent createAntarctica(){
		List<City> cities = new ArrayList<City>();
		cities.add(new City("SouthPoleSouthPoleSouthPole", 100, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("IceIceIceIceIceIceIceIce", 75, new MeteorShower(), State.UNLOCKED));
		cities.add(new City("MountainMountainMountain", 50, new MeteorShower(), State.UNLOCKED));
		return new Continent("AntarcticaAntarcticaAntarctica", cities);
	}
	
}
