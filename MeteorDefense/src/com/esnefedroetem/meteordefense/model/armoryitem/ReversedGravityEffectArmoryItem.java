package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;

/**
 * ReversedGravityEffectArmoryItem extends AbstractEffectArmoryItem, execute() method
 * results in reverting the speed of all meteors in the list sent as parameter, sending
 * them back the way they came from.
 * @author Emma Lindholm
 *
 */
public class ReversedGravityEffectArmoryItem extends AbstractEffectArmoryItem {

	private static final String NAME =  "ReversedGravityEffect", DESCRIPTION = "This weapon has the power to revert meteors gravity, sending them back up to space where they eventually will be destroyed.";
		
	public ReversedGravityEffectArmoryItem() {
		super(NAME, DESCRIPTION);
	}
	
	public ReversedGravityEffectArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION);
	}

	@Override
	public void execute(List<Meteor> list) {
		for (Meteor meteor : list) {
			meteor.setSpeed(-1 * meteor.getSpeed());
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
	
	}
	
}
