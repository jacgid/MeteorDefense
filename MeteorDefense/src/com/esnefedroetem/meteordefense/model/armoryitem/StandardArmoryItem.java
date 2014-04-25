/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Upgrade;

/**
 * @author Emma Lindholm
 *
 */

public class StandardArmoryItem extends AbstractProjectileArmoryItem {
	
	public StandardArmoryItem() {
		setName("Standard Weapon");
		setDescription("This weapon is your standard weapon, and isn't tradeable. You may however upgrade its power and cooldown.");
	}
	
	public StandardArmoryItem(State state, int upgradeIndex) {
		this();
		init(state, upgradeIndex);
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
		// TODO Auto-generated method stub
		
	}
	

}
