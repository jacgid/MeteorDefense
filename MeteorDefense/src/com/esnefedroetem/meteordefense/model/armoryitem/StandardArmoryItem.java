/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * StandardArmoryItem extends AbstractProjectileArmoryItem, execute() method
 * resulting in the return of a Projectile of the type STANDARD_PROJECTILE.
 * @author Emma Lindholm
 *
 */

public class StandardArmoryItem extends AbstractProjectileArmoryItem {
	
	private static final String NAME =  "StandardWeapon", DESCRIPTION = "This weapon is your standard weapon, and isn't tradeable. You may however upgrade its power and cooldown.";
	
	public StandardArmoryItem() {
		super(NAME, DESCRIPTION, Constants.DEFAULT_PROJECTILE_SIZE, Projectile.ProjectileType.STANDARD_PROJECTILE);
	}
	
	public StandardArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION, Constants.DEFAULT_PROJECTILE_SIZE, Projectile.ProjectileType.STANDARD_PROJECTILE);
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
	
	/**
	 * Standardweapon should not be tradeable, should be available for user from start 
	 * without option to sell.
	 */
	@Override
	public int getValue() {
		return 0;
	}


}
