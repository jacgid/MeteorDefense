package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.util.Constants;

public class City {
	
	public enum State {
		LOCKED, UNLOCKED;
	}
	private State state;
	private String name;
	private int life;
	private Rectangle bounds = Constants.CITY_BOUNDS;
	private MeteorShower meteorShower;
	private List<Meteor> dashedMeteors = new ArrayList<Meteor>();
	
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
		dashedMeteors.clear();
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void hit(Meteor meteor) {
		System.out.println("Meteor hit city");
		life -= meteor.getDamage();
		if(meteor.getType() == Constants.MeteorType.RADIOACTIVE) {
			dashedMeteors.add(meteor);			
		}
	}
	public void update(float delta) {
		// TODO implement dashedMeteors continuously damaging city
	}
}
