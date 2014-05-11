package com.esnefedroetem.meteordefense.model.armoryitem;

import com.esnefedroetem.meteordefense.model.City;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractDefenseArmoryItem extends AbstractArmoryItem {
	
	public abstract void execute(City city);

}
