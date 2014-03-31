package com.esnefedroetem.meteordefense.model;

public class City {
	
	private String name;
	private int life;
	private MeteorShower meteorShower;
	
	public City(String name, int life, MeteorShower meteorShower){
		this.name = name;
		this.life = life;
		this.meteorShower = meteorShower;
	}
	
	public String getName() {
		return name;
	}

	public int getLife() {
		return life;
	}

	public MeteorShower getMeteorShower(){
		return meteorShower;
	}
	
}
