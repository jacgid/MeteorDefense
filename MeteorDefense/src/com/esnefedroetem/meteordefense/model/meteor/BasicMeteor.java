package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;
import com.esnefedroetem.meteordefense.util.Constants.MeteorType;
import com.esnefedroetem.meteordefense.util.Constants.ProjectileType;
/** 
 * @author Andreas Pegelow
 *
 */
public class BasicMeteor extends Meteor {

	// This constructor is for making testing easy
		public BasicMeteor(Vector2 startPosition, int scale) {
			super(startPosition, Constants.BASE_METEOR_ANGLE,Constants.BASE_METEOR_LIFE*scale, Constants.BASE_METEOR_DAMAGE*scale, Constants.BASE_METEOR_SIZE*scale,
					Constants.BASE_METEOR_SPEED);
		}

		public BasicMeteor(Vector2 startPosition) {
			super(startPosition, Constants.BASE_METEOR_ANGLE,Constants.BASE_METEOR_LIFE, Constants.BASE_METEOR_DAMAGE, Constants.BASE_METEOR_SIZE,
					Constants.BASE_METEOR_SPEED);
			
		}

		public BasicMeteor(Vector2 startPosition, int life, int damage, float size) {
			super(startPosition, Constants.BASE_METEOR_ANGLE,life, damage, size, Constants.BASE_METEOR_SPEED);
			
		}

		public BasicMeteor(Vector2 startPosition, int life, int damage, float size, float speed) {
			super(startPosition, Constants.BASE_METEOR_ANGLE,life, damage, size, speed);
			
		}
		public BasicMeteor(Vector2 startPosition,float angle, int life, int damage, float size, float speed) {
			super(startPosition, angle,life, damage, size, speed);
			
		}

		@Override
		public void hit(int damage, ProjectileType projectiletype) {
			decreaseHealth(damage);
			decreaseSize(damage * 10);
			
		}

		@Override
		public MeteorType getType() {
			return MeteorType.NONE;
		}


}
