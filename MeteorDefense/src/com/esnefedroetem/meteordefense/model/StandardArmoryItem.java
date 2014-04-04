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
			upgrades.add(new Upgrade(1, 0, i * 1000));
		}
		setUpgradeList(upgrades);
		
	}
	

}
