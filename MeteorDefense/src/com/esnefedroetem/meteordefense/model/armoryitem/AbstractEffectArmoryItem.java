package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.List;

import com.esnefedroetem.meteordefense.model.IArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;

/** 
 * AbstractEffectArmoryItem extends AbstractArmoryItem and is the superclass
 * of all armory items effecting the physical laws of meteors.
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractEffectArmoryItem extends AbstractArmoryItem {
	
	public AbstractEffectArmoryItem(String name, String description) {
		super(name, description);
	}
	
	public AbstractEffectArmoryItem(State state, int upgradeIndex, String name, String description) {
		super(state, upgradeIndex, name, description);
	}

	public Projectile accept(IArmoryItemVisitor visitor) {
		return visitor.visit(this);
	}
	
	public abstract void execute(List<Meteor> list);

}
