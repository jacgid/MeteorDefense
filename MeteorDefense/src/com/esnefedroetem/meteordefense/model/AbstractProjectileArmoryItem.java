package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractProjectileArmoryItem extends AbstractArmoryItem {
	
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("loadCannonBarrel", null, this);
	}

}
