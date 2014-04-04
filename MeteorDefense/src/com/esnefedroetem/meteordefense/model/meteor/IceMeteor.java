package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;
import com.esnefedroetem.meteordefense.util.Constants.meteorEffects;

public class IceMeteor extends Meteor {
	private static final int DAMAGE = 15;
	private static final float SPEED = Constants.DEFAULT_METEOR_SPEED;
	private static final int LIFE = 10;
	private static final float SIZE = Constants.DEFAULT_METEOR_SIZE;

	public IceMeteor(Vector2 startPosition) {
		super(startPosition, Constants.DEFAULT_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);

	}


	@Override
	public void hit(int damage) {
		decreaseHealth(damage);
	}

	@Override
	public meteorEffects getEffect() {
		return meteorEffects.NONE;
	}

}
