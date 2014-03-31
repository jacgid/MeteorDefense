package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;


public class MeteorShower {
	
	private ArrayList<Meteor> nowFlyingMeteors = new ArrayList<Meteor>();
	
	public MeteorShower getInstance() {
		return this;
	}

	public ArrayList<Meteor> getNowFlyingMeteors() {

		return nowFlyingMeteors;
	}

	public void start() {

	}

}
