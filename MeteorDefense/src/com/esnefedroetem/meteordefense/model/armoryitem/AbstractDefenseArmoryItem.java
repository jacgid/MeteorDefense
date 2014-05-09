package com.esnefedroetem.meteordefense.model.armoryitem;

import com.esnefedroetem.meteordefense.model.City;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractDefenseArmoryItem extends AbstractArmoryItem {
	
	public void performAct() {
		getPropertyChangeSupport().firePropertyChange("addCity", null, this);
	}
	
	public abstract void execute(City city);

}
