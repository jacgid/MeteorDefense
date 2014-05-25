/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * MissileProjectileArmoryItem extends AbstractProjectileArmoryItem, execute() method
 * resulting in the return of a Projectile of the type MISSILE_PROJECTILE.
 * @author Emma Lindholm
 *
 */
public class MissileProjectileArmoryItem extends AbstractProjectileArmoryItem{
	
	private static final String NAME =  "MissileLauncher", DESCRIPTION = "The missiles launched by this weapon are more powerful than normal projectiles and does therefore cause greater damage to meteors.";
	
	public MissileProjectileArmoryItem() {
		super(NAME, DESCRIPTION, Constants.DEFAULT_PROJECTILE_SIZE * 3, Projectile.ProjectileType.MISSILE_PROJECTILE);
	}

	public MissileProjectileArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION, Constants.DEFAULT_PROJECTILE_SIZE * 3, Projectile.ProjectileType.MISSILE_PROJECTILE);
		
	}


	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		
		upgrades.add(new Upgrade(3, 7f, 1700));
		upgrades.add(new Upgrade(2, 0f, 3600));
		upgrades.add(new Upgrade(0, -2f, 4900));
		upgrades.add(new Upgrade(4, 0f, 8900));
		
		setUpgradeList(upgrades);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
