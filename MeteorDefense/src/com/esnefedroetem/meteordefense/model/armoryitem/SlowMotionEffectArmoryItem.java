package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.List;

import com.esnefedroetem.meteordefense.model.Meteor;

public class SlowMotionEffectArmoryItem extends AbstractEffectArmoryItem {

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
