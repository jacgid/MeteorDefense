package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.model.meteor.RadioactiveMeteor;
import com.esnefedroetem.meteordefense.util.Constants;

public class City {
	
	public enum State {
		LOCKED, UNLOCKED;
	}
	private State state;
	private String name;
	private int currentLife, maxLife;
	private Rectangle bounds = Constants.CITY_BOUNDS;
	private MeteorShower meteorShower;
	private List<Meteor> dashedMeteors = new ArrayList<Meteor>();
	private float lastTime, lastDelta, totalTimePassed;
	
	public City(String name, int life, MeteorShower meteorShower, State state){
		this.name = name;
		this.currentLife = life;
		maxLife = life;
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
		return currentLife;
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
	
	public void reset(){
		meteorShower.unLoadMeteors();
		dashedMeteors.clear();
		currentLife = maxLife;
		lastTime = 0;
		lastDelta = 0;
		totalTimePassed = 0;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void hit(Meteor meteor) {
		System.out.println("Meteor hit city");
		currentLife -= meteor.getDamage();
		if(meteor.getType() == Constants.MeteorType.RADIOACTIVE) {
			dashedMeteors.add(meteor);			
		}
	}
	public void update(float delta) {
		// TODO implement dashedMeteors continuously damaging city
		totalTimePassed = totalTimePassed + delta;
		if(totalTimePassed > 1){
			int size = dashedMeteors.size();
			for(int i = 0; i<size; i++){
				RadioactiveMeteor radio = (RadioactiveMeteor)dashedMeteors.get(i);
				if(radio.getDot()>=0){
					currentLife -= radio.getDot();
				}else{
					dashedMeteors.remove(i);
					size = dashedMeteors.size();
				}
			}
			totalTimePassed = 0;
		}
	}
}
