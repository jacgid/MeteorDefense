package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public class Meteor extends MoveableGameObject {

	private int life;

	public Meteor(int life, int damage){
		super(Constants.DEFAULT_METEOR_ANGLE, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED);
	}
	
	public Meteor(double angle, int damage, float size){
		super(Constants.DEFAULT_METEOR_ANGLE, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED);
	}
	public Meteor(double angle, int damage, float size, float speed){
		super(Constants.DEFAULT_METEOR_ANGLE, damage, size, speed);
	}


	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

}
