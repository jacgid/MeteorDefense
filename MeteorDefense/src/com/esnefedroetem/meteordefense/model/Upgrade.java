package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public class Upgrade {
	
	private int powerIncrement, cooldownDecrement;
	
	/**
	 * 
	 * @param powerIncrement positive value to be added to weapons power
	 * @param cooldownDecrement negative value of milliseconds to be added to weapons cooldown
	 */
	public Upgrade(int powerIncrement, int cooldownDecrement ) {
		this.powerIncrement = powerIncrement;
		this.cooldownDecrement = cooldownDecrement;
	}
	
	public int getCooldownDecrement() {
		return cooldownDecrement;
	}
	
	public int getPowerIncrement() {
		return powerIncrement;
	}
}
