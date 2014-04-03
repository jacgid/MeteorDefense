package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractDefenseArmoryItem extends AbstractArmoryItem {
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("addCity", null, this);
	}

}
