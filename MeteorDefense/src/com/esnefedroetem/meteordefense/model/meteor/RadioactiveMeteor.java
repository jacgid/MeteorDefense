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
	private static final int DOT = 2;
	private static final float SPEED = Constants.BASE_METEOR_SPEED;
	private static final int LIFE = 10;
	private static final float SIZE = Constants.BASE_METEOR_SIZE*2;
	private int amountOfDots = -1;
	
	public RadioactiveMeteor(){
		
	}
	public RadioactiveMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);
	

	}


	@Override
	public void hit(int damage, ProjectileType projectileType) {
		decreaseHealth(damage);
		decreaseSize(2);
		damage = damage-2;
	}

	@Override
	public MeteorType getType() {
		return MeteorType.RADIOACTIVE;
	}
	
	public int getDot(){
		amountOfDots += 1;
		return DOT-amountOfDots;
	}

}
