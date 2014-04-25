package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;

public class ReversedGravityEffectArmoryItem extends AbstractEffectArmoryItem {

	private List<Meteor> list = new ArrayList<Meteor>();
	private List<Meteor> visibleMeteors;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float delta) {
		int length = list.size();
		for (int i = 0; i < length; i++) {
			Meteor meteor = list.get(i);
			float x = meteor.getX() + meteor.getBounds().radius;
			float y = meteor.getY() + meteor.getBounds().radius;

			if (x < 0 || x > Constants.LOGIC_SCREEN_WIDTH
					|| y > Constants.LOGIC_SCREEN_HEIGHT) {
				visibleMeteors.remove(meteor);
				length--;
				i--;
			}
		}
	}

}
