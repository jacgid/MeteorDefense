package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.util.Constants;
/** a ice meteor, for now the strongest meteor.
 * @author Andreas Pegelow
 *
 */
public class IceMeteor extends Meteor {
	private static final int DAMAGE = 15;
	private static final float SPEED = Constants.BASE_METEOR_SPEED;
	private static final int LIFE = 10;
	private static final float SIZE = Constants.BASE_METEOR_SIZE;

	public IceMeteor(){
		
	}
	public IceMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);
	

	}


	@Override
	public void hit(int damage, ProjectileType projectileType) {
		decreaseHealth(damage);
	}

	@Override
	public MeteorType getType() {
		return MeteorType.ICE_METEOR;
	}

}
