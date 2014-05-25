package com.esnefedroetem.meteordefense.service;

import com.esnefedroetem.meteordefense.model.MeteorShower;

/**
 * LevelData is a dataholder for the citvalues life and meteorshower.
 * @author Simon
 *
 */
public class LevelData {
	
	private int cityLife;
	private MeteorShower meteorShower;
	
	public LevelData(int cityLife, MeteorShower meteorShower){
		this.cityLife = cityLife;
		this.meteorShower = meteorShower;
	}

	public int getCityLife() {
		return cityLife;
	}

	public MeteorShower getMeteorShower() {
		return meteorShower;
	}
	
}
