package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.List;

import com.esnefedroetem.meteordefense.model.Meteor;

public class SlowMotionEffectArmoryItem extends AbstractEffectArmoryItem {

	public SlowMotionEffectArmoryItem() {
		setName("Slow Motion Effect");
		setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus pulvinar, felis hendrerit venenatis imperdiet, nisi ante mattis diam, ut suscipit augue massa vel enim. Fusce.");

	}
	
	@Override
	public void execute(List<Meteor> list) {
		for(Meteor meteor : list) {
			meteor.setSpeed(meteor.getSpeed()/5);
		}
		
	}

	@Override
	public void initUpgrades() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
