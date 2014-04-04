package com.esnefedroetem.meteordefense.model;

import com.esnefedroetem.meteordefense.util.Constants.ProjectileType;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractProjectileArmoryItem extends AbstractArmoryItem {
	
	private int projectileSize;
	private ProjectileType projectileType;
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("loadCannonBarrel", null, this);
	}
	

	public int getProjectileSize() {
		return projectileSize;
	}
	
	public ProjectileType getProjectileType() {
		return projectileType;
	}

}
