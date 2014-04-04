package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractDefenseArmoryItem extends AbstractArmoryItem {
	
	private City city;
	
	public void act() {
		getPropertyChangeSupport().firePropertyChange("addCity", null, this);
	}
	
	public void execute(City city) {
		this.city = city;
	}

}
