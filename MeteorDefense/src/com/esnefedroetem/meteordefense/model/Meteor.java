package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;

public class Meteor {

	private int life;
	private Circle bounds;

	public Meteor() {

	}

	public Circle getBounds() {
		return bounds;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

}
