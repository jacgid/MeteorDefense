/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.Projectile;

/**
 * @author Emma
 *
 */
public interface IArmoryItemElement {
	
	public Projectile accept(IArmoryItemVisitor visitor);

}
