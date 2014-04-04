package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * 
 * @author Andreas Pegelow
 * 
 */
public abstract class Meteor extends MoveableGameObject {

	private int life;

	public Meteor(Vector2 startPosition,float angle, int life, int damage, float size, float speed) {
		super(angle, damage, size, speed, startPosition);
		this.life = life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	/**
	 * Called when the meteor is hit, when creating new types of meteors this
	 * should be overwriten to change the behavior
	 * 
	 * @param damage
	 */
	public abstract void hit(int damage);

	public void decreaseSize(int value) {
		setSize(getSize() - value);
	}

	public void decreaseHealth(int value) {
		life -= value;

	}

	/**
	 * Checks if the meteor will survive a hit with the specified damage
	 * 
	 * @param damage
	 * @return true if it will survie, false otherwise
	 */
	public boolean willSurvive(int damage) {
		return life - damage > 0;
	}

}
