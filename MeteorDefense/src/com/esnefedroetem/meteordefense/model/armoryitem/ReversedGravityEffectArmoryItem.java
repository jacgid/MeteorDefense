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
	private List<Meteor> visibleMeteors;
	
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
		visibleMeteors = list;
		for (Meteor meteor : list) {
			this.list.add(meteor);
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
		/*int length = list.size();
		for (int i = 0; i < length; i++) {
			Meteor meteor = list.get(i);
			float x = meteor.getX() + meteor.getBounds().radius;
			float y = meteor.getY() + meteor.getBounds().radius;

			if (x < 0 || x > Constants.LOGIC_SCREEN_WIDTH
					|| y > Constants.LOGIC_SCREEN_HEIGHT) {
				meteor.hit(meteor.getLife(), ProjectileType.NONE);
				visibleMeteors.remove(meteor);
				length--;
				i--;
			}
		}*/
	}

	@Override
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}
	
}
