package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;

public class ReversedGravityEffectArmoryItem extends AbstractEffectArmoryItem {

	private List<Meteor> list = new ArrayList<Meteor>();
	
	public ReversedGravityEffectArmoryItem() {
	}
	
	public ReversedGravityEffectArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex);
		name = "ReversedGravityEffect";
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	}

	@Override
	public void execute(List<Meteor> list) {
		for (Meteor meteor : list) {
			meteor.setSpeed(-1 * meteor.getSpeed());
		}
	}

	@Override
	public void initUpgrades() {
		ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
		
		upgrades.add(new Upgrade(1, 25f, 3000));
		upgrades.add(new Upgrade(0, -5f, 3000));
		upgrades.add(new Upgrade(0, -7f, 6000));
		upgrades.add(new Upgrade(0, -10f, 8900));
		
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
