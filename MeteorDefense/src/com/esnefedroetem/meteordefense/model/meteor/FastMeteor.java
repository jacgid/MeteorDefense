package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.util.Constants;
/** A fast meteor.
 * @author Andreas Pegelow
 *
 */
public class FastMeteor extends Meteor {
	private static final int DAMAGE = 2;
	private static final float SPEED = (float) (Constants.BASE_METEOR_SPEED*2.4);
	private static final int LIFE = 1;
	private static final float SIZE = Constants.BASE_METEOR_SIZE;
	
	public FastMeteor(){
		
	}
	public FastMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);
		
		
	}

	@Override
	public void hit(int damage, ProjectileType projectileType) {
		decreaseHealth(damage);
	}

	@Override
	public MeteorType getType() {
		return MeteorType.FAST_METEOR;
	}

}
