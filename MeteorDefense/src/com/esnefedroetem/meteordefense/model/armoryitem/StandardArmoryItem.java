/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.util.Constants;

/**
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
	
	@Override
	public int getValue() {
		return 0;
	}

	@Override
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public Projectile execute() {
		return new Projectile(getPower(), getProjectileSize(), getProjectileType());
	}
	

}
