package com.esnefedroetem.meteordefense.model;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public class Upgrade {
	
	private int powerIncrement, value;
	private float cooldownDecrement;
	
	/**
	 * 
	 * @param powerIncrement positive value to be added to weapons power
	 * @param cooldownDecrement negative value of milliseconds to be added to weapons cooldown
	 * @param value value in coins that the upgrade is worth
	 */
	public Upgrade(int powerIncrement, float cooldownDecrement, int value) {
		this.powerIncrement = powerIncrement;
		this.cooldownDecrement = cooldownDecrement;
		this.value = value;
	}
	
	public float getCooldownDecrement() {
		return cooldownDecrement;
	}
	
	public int getPowerIncrement() {
		return powerIncrement;
	}
	
	public int getValue() {
		return value;
	}
}
