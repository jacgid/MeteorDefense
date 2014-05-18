package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * @author Andreas Pegelow
 * 
 */
public class RadioactiveMeteor extends Meteor {
	private static final int DAMAGE = 10;
	private static final int DOT = 3;
	private static final float SPEED = Constants.BASE_METEOR_SPEED;
	private static final int LIFE = 10;
	private static final float SIZE = Constants.BASE_METEOR_SIZE * 2;
	private int amountOfDots = 4;
	private int dotRate = 3;
	private float totalTimePassed;
	private boolean hit= false;

	public RadioactiveMeteor() {

	}

	public RadioactiveMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);

	}

	@Override
	public void hit(int damage, ProjectileType projectileType) {
		decreaseHealth(damage);
	}

	@Override
	public MeteorType getType() {
		return MeteorType.RADIOACTIVE_METEOR;
	}

	public int getDot() {
		if (hit) {
			hit = false;
			amountOfDots -= 1;
			return DOT;
		}
		return 0;
	}

	public void update(float delta) {
		totalTimePassed = totalTimePassed + delta;
		if (totalTimePassed > dotRate) {
			hit = true;
			totalTimePassed = 0;
		}
		
	}
	
	public boolean isDecade(){
		return amountOfDots <= 0;
	}
	

}
