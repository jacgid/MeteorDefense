package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractEffectArmoryItem extends AbstractArmoryItem {
	
	private ArrayList<BasicMeteor> visibleMeteors;
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("addVisibleMeteors", null, this);
	}
	
	public void addVisibleMeteors(ArrayList<BasicMeteor> visibleMeteors) {
		this.visibleMeteors = visibleMeteors;
	}

}
