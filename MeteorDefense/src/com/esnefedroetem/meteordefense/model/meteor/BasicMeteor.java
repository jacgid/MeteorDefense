package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;
import com.esnefedroetem.meteordefense.util.Constants.meteorEffects;

public class BasicMeteor extends Meteor {

	// This constructor is for making testing easy
		public BasicMeteor(Vector2 startPosition, int scale) {
			super(startPosition, Constants.DEFAULT_METEOR_ANGLE,Constants.DEFAULT_METEOR_LIFE*scale, Constants.DEFAULT_METEOR_DAMAGE*scale, Constants.DEFAULT_METEOR_SIZE*scale,
					Constants.DEFAULT_METEOR_SPEED);
		}

		public BasicMeteor(Vector2 startPosition) {
			super(startPosition, Constants.DEFAULT_METEOR_ANGLE,Constants.DEFAULT_METEOR_LIFE, Constants.DEFAULT_METEOR_DAMAGE, Constants.DEFAULT_METEOR_SIZE,
					Constants.DEFAULT_METEOR_SPEED);
			
		}

		public BasicMeteor(Vector2 startPosition, int life, int damage, float size) {
			super(startPosition, Constants.DEFAULT_METEOR_ANGLE,life, damage, size, Constants.DEFAULT_METEOR_SPEED);
			
		}

		public BasicMeteor(Vector2 startPosition, int life, int damage, float size, float speed) {
			super(startPosition, Constants.DEFAULT_METEOR_ANGLE,life, damage, size, speed);
			
		}
		public BasicMeteor(Vector2 startPosition,float angle, int life, int damage, float size, float speed) {
			super(startPosition, angle,life, damage, size, speed);
			
		}

		@Override
		public void hit(int damage) {
			decreaseHealth(damage);
			decreaseSize(damage * 10);
			
		}

		@Override
		public meteorEffects getEffect() {
			return meteorEffects.NONE;
		}

}
