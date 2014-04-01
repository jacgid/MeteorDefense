package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public class Meteor extends MoveableGameObject {

	private int life;
	
	public Meteor(Vector2 startPosition){
		super(Constants.DEFAULT_METEOR_ANGLE, Constants.DEFAULT_METEOR_DAMAGE, Constants.DEFAULT_METEOR_SIZE, Constants.DEFAULT_METEOR_SPEED, startPosition);
		life = Constants.DEFAULT_METEOR_LIFE;
	}

	public Meteor(Vector2 startPosition,int life, int damage){
		super(Constants.DEFAULT_METEOR_ANGLE, damage, Constants.DEFAULT_METEOR_SIZE, Constants.DEFAULT_METEOR_SPEED,startPosition);
		this.life=life;
	}
	
	public Meteor(Vector2 startPosition, int life, double angle, int damage, float size){
		super(Constants.DEFAULT_METEOR_ANGLE, damage, Constants.DEFAULT_METEOR_SIZE, Constants.DEFAULT_METEOR_SPEED,startPosition);
		this.life=life;
	}
	public Meteor(Vector2 startPosition, int life, double angle, int damage, float size, float speed){
		super(Constants.DEFAULT_METEOR_ANGLE, damage, size, speed, startPosition);
		this.life=life;
	}


	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

}
