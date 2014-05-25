/**
 * 
 */
package com.esnefedroetem.meteordefense.model;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractDefenseArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractProjectileArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.EmptyItem;

/**
 * A Vistor of the ArmoryItem classes must implement this interface to
 * be able to execute all ArmoryItem types.
 * @author Emma Lindholm
 *
 */
public interface IArmoryItemVisitor {
	
	public Projectile visit(AbstractEffectArmoryItem element);
	
	public Projectile visit(AbstractDefenseArmoryItem element);
	
	public Projectile visit(AbstractProjectileArmoryItem element);

	public Projectile visit(EmptyItem emptyItem);

}
