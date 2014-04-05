package com.esnefedroetem.meteordefense.model;

public class City {
	
	public enum State {
		LOCKED, UNLOCKED;
	}
	private State state;
	private String name;
	private int life;
	private MeteorShower meteorShower;
	
	public City(String name, int life, MeteorShower meteorShower, State state){
		this.name = name;
		this.life = life;
		this.meteorShower = meteorShower;
		this.state = state;
	}
	
	public City(){
		//Used by loadService
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
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
	
	public void unLoadMeteors(){
		meteorShower.unLoadMeteors();
	}
	
}
