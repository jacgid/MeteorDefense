/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Upgrade;

/**
 * HealingDefenseArmoryItem extends AbstractDefenseArmoryItem, execute() method
 * resulting in an increase of the life of the city provided as parameter.
 * @author Emma Lindholm
 *
 */
public class HealingDefenseArmoryItem extends AbstractDefenseArmoryItem {

	private static final String NAME =  "HealingDefense", DESCRIPTION = "This utility heals the city with the number of life units as it has got in power, helping the city to survive.";
	
	public HealingDefenseArmoryItem() {
		super(NAME, DESCRIPTION);
	}
	
	public HealingDefenseArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION);
	}
	
	@Override
	public void execute(City city) {
		city.increaseLife(getPower());
	}

	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		
		upgrades.add(new Upgrade(30, 25f, 3000));
		upgrades.add(new Upgrade(0, -10f, 1500));
		upgrades.add(new Upgrade(20, 0f, 4000));
		upgrades.add(new Upgrade(0, -7f, 5500));
		
		setUpgradeList(upgrades);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
