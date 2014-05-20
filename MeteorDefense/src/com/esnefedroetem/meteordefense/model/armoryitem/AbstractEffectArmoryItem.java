package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.meteor.Meteor;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractEffectArmoryItem extends AbstractArmoryItem {
	
	public AbstractEffectArmoryItem() {
	}
	
	public AbstractEffectArmoryItem(State state, int upgradeIndex) {
		super(state, upgradeIndex);
	}

	public abstract void execute(List<Meteor> list);

}
