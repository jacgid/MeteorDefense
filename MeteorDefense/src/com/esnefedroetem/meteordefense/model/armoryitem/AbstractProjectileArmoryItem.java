package com.esnefedroetem.meteordefense.model.armoryitem;


import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;

/** 
 * AbstractProjectileArmoryItem extends AbstractArmoryItem and is the superclass
 * of all armory item firing a projectile. Has the attributes PROJECTILE_SIZE and
 * PROJECTILE_TYPE.
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractProjectileArmoryItem extends AbstractArmoryItem {
	
	private final float PROJECTILE_SIZE;
	private final ProjectileType PROJECTILE_TYPE;
	
	public AbstractProjectileArmoryItem(String name, String description, float projectileSize, ProjectileType projectileType) {
		super(name, description);
		this.PROJECTILE_SIZE = projectileSize;
		this.PROJECTILE_TYPE = projectileType;
	}
	
	public AbstractProjectileArmoryItem(State state, int upgradeIndex, String name, String description,
			float projectileSize, Projectile.ProjectileType projectileType) {
		super(state, upgradeIndex, name, description);
		this.PROJECTILE_SIZE = projectileSize;
		this.PROJECTILE_TYPE = projectileType;
	}

	public float getProjectileSize() {
		return PROJECTILE_SIZE;
	}
	
	public ProjectileType getProjectileType() {
		return PROJECTILE_TYPE;
	}
		
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}
	
	public Projectile execute(ArrayList<Projectile> projectilesToAdd, ArrayList<Projectile> projectilesToRemove) {
		return new Projectile(getPower(), getProjectileSize(), getProjectileType());
	}
}
