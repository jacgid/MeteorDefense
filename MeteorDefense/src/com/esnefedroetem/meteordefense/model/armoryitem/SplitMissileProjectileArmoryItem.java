package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.model.SplitProjectile;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.util.Constants;

public class SplitMissileProjectileArmoryItem extends
		AbstractProjectileArmoryItem {

	private SplitProjectile projectile;
	private ArrayList<Projectile> projectilesToAdd, projectilesToRemove;
	private boolean executed = false;
	private int updatesCalled;

	private static final String NAME = "SplitMissileLauncher",
			DESCRIPTION = "The missiles launched by this weapon are more powerful than normal projectiles and does therefore cause greater damage to meteors.";

	public SplitMissileProjectileArmoryItem() {
		super(NAME, DESCRIPTION, Constants.DEFAULT_PROJECTILE_SIZE * 3,
				Projectile.ProjectileType.SPLIT_PROJECTILE);
	}

	public SplitMissileProjectileArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex, NAME, DESCRIPTION,
				Constants.DEFAULT_PROJECTILE_SIZE * 3,
				Projectile.ProjectileType.SPLIT_PROJECTILE);

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
	public Projectile execute(ArrayList<Projectile> projectilesToAdd, ArrayList<Projectile> projectilesToRemove) {
		this.projectilesToAdd = projectilesToAdd;
		this.projectilesToRemove = projectilesToRemove;
		executed = true;
		projectile = new SplitProjectile(getPower(), getProjectileSize(),
				getProjectileType());
		projectile.setProjectileLists(projectilesToAdd, projectilesToRemove);
		return projectile;
	}

	@Override
	public void update(float delta) {
//		updatesCalled++;
//		if (executed == true && delta == 0 && updatesCalled>1) {
//			System.out.println("Update! Angle: " + projectile.getAngle());
//			split();
//			updatesCalled = 0;
//			executed = false;
//		}
	}
	
}
