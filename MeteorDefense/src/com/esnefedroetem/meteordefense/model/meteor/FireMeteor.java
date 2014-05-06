package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.util.Constants;
/** 
 * @author Andreas Pegelow
 *
 */
public class FireMeteor extends Meteor {
	private static final int DAMAGE = 5;
	private static final float SPEED = Constants.BASE_METEOR_SPEED;
	private static final int LIFE = 3;
	private static final float SIZE = Constants.BASE_METEOR_SIZE;
	
	public FireMeteor(){
		
	}
	public FireMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);

	}


	@Override
	public void hit(int damage, ProjectileType projectileType) {
		decreaseHealth(damage);
	}

	@Override
	public MeteorType getType() {
		return MeteorType.FIRE;
	}

}
