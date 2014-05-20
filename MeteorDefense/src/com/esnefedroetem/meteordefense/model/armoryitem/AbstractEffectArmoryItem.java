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
	
	public AbstractEffectArmoryItem(String name, String description) {
		super(name, description);
	}
	
	public AbstractEffectArmoryItem(State state, int upgradeIndex, String name, String description) {
		super(state, upgradeIndex, name, description);
	}

	public abstract void execute(List<Meteor> list);

}
