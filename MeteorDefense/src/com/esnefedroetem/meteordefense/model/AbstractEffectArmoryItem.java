package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractEffectArmoryItem extends AbstractArmoryItem {
	
	public void performAct() {
		getPropertyChangeSupport().firePropertyChange("addVisibleMeteors", null, this);
	}
	
	public abstract void execute(List<Meteor> list);

}
