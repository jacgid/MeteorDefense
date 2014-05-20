package com.esnefedroetem.meteordefense.model.armoryitem;

import com.esnefedroetem.meteordefense.model.City;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractDefenseArmoryItem extends AbstractArmoryItem {
	
	public AbstractDefenseArmoryItem(String name, String description) {
		super(name, description);
	}

	public AbstractDefenseArmoryItem(State state, int upgradeIndex, String name, String description) {
	super(state, upgradeIndex, name, description);
	}
	
	public abstract void execute(City city);

}
