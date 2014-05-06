package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.Upgrade;

public class EmptyItem extends AbstractArmoryItem {

	
	public EmptyItem(){
		setName("weaponslot");
	}
	
	@Override
	public void performAct() {
		
	}

	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		for(int i = 0; i < 1; i++) {
			upgrades.add(new Upgrade(0, 0, 0));
		}
		setUpgradeList(upgrades);
		
	}

	@Override
	public void update(float delta) {
		
	}

}
