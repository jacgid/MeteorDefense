package com.esnefedroetem.meteordefense.model.armoryitem;


import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractProjectileArmoryItem extends AbstractArmoryItem {
	
	private int projectileSize;
	private ProjectileType projectileType;
	
	public AbstractProjectileArmoryItem() {
		projectileType = Projectile.ProjectileType.NONE;
		projectileSize = 1;
	}
	
	public void performAct() {
		getPropertyChangeSupport().firePropertyChange("loadCannonBarrel", null, this);
	}
	
	public int getProjectileSize() {
		return projectileSize;
	}
	
	public ProjectileType getProjectileType() {
		return projectileType;
	}

	public abstract Projectile execute();
}
