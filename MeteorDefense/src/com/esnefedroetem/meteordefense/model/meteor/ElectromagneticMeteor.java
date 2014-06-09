package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.util.Constants;

public class ElectromagneticMeteor extends Meteor {

	private static final int DAMAGE = 0;
	private static final float SPEED = Constants.BASE_METEOR_SPEED;
	private static final int LIFE = 5;
	private static final float SIZE = Constants.BASE_METEOR_SIZE;
	
	private float paralysisTime = 1;
	
	public ElectromagneticMeteor(){
		
	}
	
	public ElectromagneticMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);
	}
	
	@Override
	public void hit(int damage, ProjectileType projectileType) {
		decreaseHealth(damage);
	}

	@Override
	public MeteorType getType() {
		return MeteorType.ELECTROMAGNETIC_METEOR;
	}
	
	public float getParalysisTime(){
		return paralysisTime;
	}

}
