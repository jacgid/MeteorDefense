package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractEffectArmoryItem extends AbstractArmoryItem {
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("addVisibleMeteors", null, this);
	}

}
