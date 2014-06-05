/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * @author Emma Lindholm
 *
 */
public class BlackHoleEffectArmoryItem extends AbstractEffectArmoryItem {

	private static final String NAME =  "BlackHoleEffect", DESCRIPTION = "This weapon launches a black hole which draws the meteors into its core and destroyes them.";
	private List<Meteor> list = new ArrayList<Meteor>();
	private float duration = 5, time = 0;
	
	float radius = Constants.LOGIC_SCREEN_WIDTH/6;
	Vector2 center = new Vector2(Constants.LOGIC_SCREEN_WIDTH/2, Constants.LOGIC_SCREEN_HEIGHT/2);
	private Circle bounds = new Circle(center.x, center.y, radius);
	
	public BlackHoleEffectArmoryItem() {
		super(NAME, DESCRIPTION);
	}
	
	public BlackHoleEffectArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION);
	}
	
	@Override
	public void execute(List<Meteor> list) {
		time = duration;
		this.list = list;
	}

	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		for(int i = 0; i < 4; i++) {
			float cooldownDecrement = 0f;
			if(i == 0) {
				cooldownDecrement = 0.2f;
			}
			upgrades.add(new Upgrade(1, cooldownDecrement, i * 1000));
		}
		setUpgradeList(upgrades);
		
	}

	@Override
	public void update(float delta) {
		time -= delta;
		
		if(time > 0) {
			setMeteorAngles();
			removeCollidingMeteors();
			
		} else {
			// reset meteor angle
			for(Meteor meteor : list) {
				meteor.setAngle((float) Math.PI / 2 * 3);
			}
		}
	}
	
	private void setMeteorAngles() {
		for(Meteor meteor : this.list) {
			float angle = 0;
			
			float meteorX = meteor.getX() + meteor.getBounds().width/2;
			float meteorY = meteor.getY() + meteor.getBounds().height/2;
			
			float x = Math.abs(center.x - meteorX);
			float y = Math.abs(center.y - meteorY);
			
			if (meteorX < center.x && meteorY > center.y) {
				angle = (float) (Math.PI/2 * 3 + Math.atan(x/y));
			} else if (meteorX > center.x && meteorY > center.y) {
				angle = (float) (Math.PI/2 * 3 - Math.atan(x/y));
			} else if (meteorX < center.x && meteorY < center.y) {
				angle = (float) (Math.PI/2 - Math.atan(x/y));
			} else if (meteorX > center.x && meteorY < center.y) {
				angle = (float) (Math.PI/2 + Math.atan(x/y));
			} else if (meteorY == center.y) {
				if(meteorX < center.x) {
					angle = 0;
				} else {
					angle = (float) Math.PI;
				}
			} else if (meteorX == center.x && meteorY < center.y) {
				angle = meteor.getAngle() * -1;
			}
			
			meteor.setAngle(angle);
		}
	}
	
	private void removeCollidingMeteors() {
		List<Meteor> meteorsToRemove = new ArrayList<Meteor>();
		for(Meteor meteor : list) {
			if(Intersector.overlaps(bounds, meteor.getBounds())) {
				meteor.hit(meteor.getLife(), ProjectileType.STANDARD_PROJECTILE);
				meteorsToRemove.add(meteor);
			}
		}
		list.removeAll(meteorsToRemove);
	}

}
