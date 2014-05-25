/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.Projectile;

/**
 * IArmoryItemElement is the interface an armory item must implement enable itself
 * to be executed by an IArmoryItemVisitor.
 * @author Emma Lindholm
 *
 */
public interface IArmoryItemElement {
	
	public Projectile accept(IArmoryItemVisitor visitor);

}
