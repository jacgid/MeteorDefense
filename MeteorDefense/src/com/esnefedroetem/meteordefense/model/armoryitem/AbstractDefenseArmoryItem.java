package com.esnefedroetem.meteordefense.model.armoryitem;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.Projectile;

/** 
 * AbstractDefenseArmoryItem extends AbstractArmoryItem and is the superclass
 * of all armory items which protects the city.
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractDefenseArmoryItem extends AbstractArmoryItem {
	
	public AbstractDefenseArmoryItem(String name, String description) {
		super(name, description);
	}

	public AbstractDefenseArmoryItem(State state, int upgradeIndex, String name, String description) {
	super(state, upgradeIndex, name, description);
	}
	
	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}
	
	public abstract void execute(City city);

}
