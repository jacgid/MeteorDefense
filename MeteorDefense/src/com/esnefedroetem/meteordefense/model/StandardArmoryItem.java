/**
 * 
 */
package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

/**
 * @author Emma Lindholm
 *
 */

public class StandardArmoryItem extends AbstractProjectileArmoryItem {
	
	public StandardArmoryItem(State state, int upgradeIndex) {
		initUpgrades();
		setState(state);
		setUpgradeIndex(upgradeIndex);
	}

	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		for(int i = 0; i < 4; i++) {
			int cooldownDecrement = 0;
			if(i == 0) {
				cooldownDecrement = 10;
			}
			upgrades.add(new Upgrade(1, cooldownDecrement, i * 1000));
		}
		setUpgradeList(upgrades);
		
	}
	

}
