/**
 * 
 */
package com.esnefedroetem.meteordefense.model;

/**
 * @author Emma
 *
 */
public interface IArmoryItemElement {
	
	public Projectile accept(IArmoryItemVisitor visitor);

}
