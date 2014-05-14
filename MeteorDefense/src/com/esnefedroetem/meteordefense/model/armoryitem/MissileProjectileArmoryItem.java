/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import org.w3c.dom.views.AbstractView;

import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * @author Emma
 *
 */
public class MissileProjectileArmoryItem extends AbstractProjectileArmoryItem{

	public MissileProjectileArmoryItem() {
		name = "MissileLauncher";
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
		setProjectileSize(Constants.DEFAULT_PROJECTILE_SIZE * 3);
		setProjectileType(Projectile.ProjectileType.NONE);
	}
	@Override
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public Projectile execute() {
		return new Projectile(getPower(), getProjectileSize(), getProjectileType());
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
