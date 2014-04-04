package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;
import com.esnefedroetem.meteordefense.util.Constants.MeteorEffects;
import com.esnefedroetem.meteordefense.util.Constants.ProjectileType;
/** 
 * @author Andreas Pegelow
 *
 */
public class RadioactiveMeteor extends Meteor {
	private static final int DAMAGE = 30;
	private static final float SPEED = Constants.DEFAULT_METEOR_SPEED;
	private static final int LIFE = 10;
	private static final float SIZE = Constants.DEFAULT_METEOR_SIZE*2;

	public RadioactiveMeteor(Vector2 startPosition) {
		super(startPosition, Constants.DEFAULT_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);

	}


	@Override
	public void hit(int damage, ProjectileType projectiletype) {
		decreaseHealth(damage);
	}

	@Override
	public MeteorEffects getEffect() {
		return MeteorEffects.RADIOACTIVE;
	}

}
