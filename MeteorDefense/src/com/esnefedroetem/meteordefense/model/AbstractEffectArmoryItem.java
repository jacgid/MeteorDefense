package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractEffectArmoryItem extends AbstractArmoryItem {
	
	private ArrayList<Meteor> visibleMeteors;
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("addVisibleMeteors", null, this);
	}
	
	public void execute(ArrayList<Meteor> visibleMeteors) {
		this.visibleMeteors = visibleMeteors;
	}

}
