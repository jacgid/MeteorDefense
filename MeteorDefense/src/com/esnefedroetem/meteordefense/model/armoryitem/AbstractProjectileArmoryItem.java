package com.esnefedroetem.meteordefense.model.armoryitem;


import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractProjectileArmoryItem extends AbstractArmoryItem {
	
	private float projectileSize;
	private ProjectileType projectileType;
	
	public AbstractProjectileArmoryItem() {
	}
	
	public AbstractProjectileArmoryItem(State state, int upgradeIndex,
			float projectileSize, Projectile.ProjectileType projectileType) {
		super(state, upgradeIndex);
		this.projectileSize = projectileSize;
		this.projectileType = projectileType;
	}

	public float getProjectileSize() {
		return projectileSize;
	}
	
	public ProjectileType getProjectileType() {
		return projectileType;
	}
	
	public void setProjectileSize(float projectileSize) {
		this.projectileSize = projectileSize;
	}
	
	public void setProjectileType(ProjectileType projectileType) {
		this.projectileType = projectileType;
	}

	public abstract Projectile execute();
}
