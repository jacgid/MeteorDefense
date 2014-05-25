package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;

/**
 * EmptyItem extends AbstractArmoryItem and is an empty object used to fill empty
 * spaces in the toolbar of chosen armory items.
 * @author Emma
 *
 */

public class EmptyItem extends AbstractArmoryItem {

	
	public EmptyItem(){
		super("weaponslot", "");
	}
	
	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		upgrades.add(new Upgrade(0, 0, 0));
		setUpgradeList(upgrades);
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}

}
