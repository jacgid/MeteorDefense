package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;
import com.esnefedroetem.meteordefense.util.Constants;

public class ReversedGravityEffectArmoryItem extends AbstractEffectArmoryItem {

	private List<Meteor> list = new ArrayList<Meteor>();
	
	public ReversedGravityEffectArmoryItem() {
		name = "ReversedGravityEffect";
		description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus pulvinar, felis hendrerit venenatis imperdiet, nisi ante mattis diam, ut suscipit augue massa vel enim. Fusce.";
	}
	
	public ReversedGravityEffectArmoryItem(State state, int upgradeIndex) {
		this();
		init(state, upgradeIndex);
	}

	@Override
	public void execute(List<Meteor> list) {
		for (Meteor meteor : list) {
			meteor.setSpeed(-1 * meteor.getSpeed());
		}
	}

	@Override
	public void initUpgrades() {
		// TODO change upgrades
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
	
	}

	@Override
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}
	
}
