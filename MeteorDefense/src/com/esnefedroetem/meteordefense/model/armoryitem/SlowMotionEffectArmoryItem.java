package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.Upgrade;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;

public class SlowMotionEffectArmoryItem extends AbstractEffectArmoryItem {

	public SlowMotionEffectArmoryItem() {
		setName("SlowMotionEffect");
		setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus pulvinar, felis hendrerit venenatis imperdiet, nisi ante mattis diam, ut suscipit augue massa vel enim. Fusce.");

	}
	
	public SlowMotionEffectArmoryItem(State state, int upgradeIndex) {
		this();
		init(state, upgradeIndex);
	}
	
	@Override
	public void execute(List<Meteor> list) {
		for(Meteor meteor : list) {
			meteor.setSpeed(meteor.getSpeed()/5);
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
		// TODO Auto-generated method stub
		
	}

}
