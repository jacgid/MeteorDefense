package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractProjectileArmoryItem extends AbstractArmoryItem {
	
	private int projectileSize;
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("loadCannonBarrel", null, this);
	}
	

	public int getProjectileSize() {
		return projectileSize;
	}

}
