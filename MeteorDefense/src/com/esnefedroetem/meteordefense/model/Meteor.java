package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * 
 * @author Andreas Pegelow
 * 
 */
public class BasicMeteor extends MoveableGameObject {

	private int life;

	// This is for making testing easy
	public BasicMeteor(Vector2 startPosition, int scale) {
		super(Constants.DEFAULT_METEOR_ANGLE, Constants.DEFAULT_METEOR_DAMAGE * scale, Constants.DEFAULT_METEOR_SIZE
				* scale, Constants.DEFAULT_METEOR_SPEED, startPosition);
		life = Constants.DEFAULT_METEOR_LIFE * scale;
	}

	public BasicMeteor(Vector2 startPosition) {
		super(Constants.DEFAULT_METEOR_ANGLE, Constants.DEFAULT_METEOR_DAMAGE, Constants.DEFAULT_METEOR_SIZE,
				Constants.DEFAULT_METEOR_SPEED, startPosition);
		life = Constants.DEFAULT_METEOR_LIFE;
	}

	public BasicMeteor(Vector2 startPosition, int life, int damage, float size) {
		super(Constants.DEFAULT_METEOR_ANGLE, damage, size, Constants.DEFAULT_METEOR_SPEED, startPosition);
		this.life = life;
	}

	public BasicMeteor(Vector2 startPosition, int life, float angle, int damage, float size) {
		super(angle, damage, size, Constants.DEFAULT_METEOR_SPEED, startPosition);
		this.life = life;
	}

	public BasicMeteor(Vector2 startPosition, int life, int damage, float size, float speed) {
		super(Constants.DEFAULT_METEOR_ANGLE, damage, size, speed, startPosition);
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
	public void hit(int damage) {
		decreaseHealth(damage);
		decreaseSize(damage * 10);

	}

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
