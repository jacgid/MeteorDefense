package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;

/**
 * SlowMotionEffectArmoryItem extends AbstractEffectArmoryItem, execute() method
 * results in reducing the speed of all meteors in the list sent as parameter.
 * @author Emma Lindholm
 *
 */

public class SlowMotionEffectArmoryItem extends AbstractEffectArmoryItem {
	
	private static final String NAME =  "SlowMotionEffect", DESCRIPTION = "This weapon slows the meteors down, giving you more time to destroy them before they hit the city.";

	public SlowMotionEffectArmoryItem() {
		super(NAME, DESCRIPTION);		
	}
	
	public SlowMotionEffectArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION);
	}
	
	@Override
	public void execute(List<Meteor> list) {
		for(Meteor meteor : list) {
			meteor.setSpeed(meteor.getSpeed()/5);
		}
		
	}

	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		
		upgrades.add(new Upgrade(1, 25f, 3000));
		upgrades.add(new Upgrade(0, -5f, 3000));
		upgrades.add(new Upgrade(0, -7f, 6000));
		upgrades.add(new Upgrade(0, -10f, 8900));
		
		setUpgradeList(upgrades);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
