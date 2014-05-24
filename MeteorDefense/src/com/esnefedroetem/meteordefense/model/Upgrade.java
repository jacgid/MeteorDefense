package com.esnefedroetem.meteordefense.model;

/** 
 * Upgrades for ArmoryItems.
 * Has a powerIncrement and cooldownDecrement value, of which one should always
 * be 0. Also has a upgradeValue, which is the upgrades coinvalue when bought.
 *  @author Emma Lindholm
 *  
 */

public class Upgrade {
	
	private int powerIncrement, upgradeValue;
	private float cooldownDecrement;
	
	/**
	 * 
	 * @param powerIncrement positive value to be added to weapons power
	 * @param cooldownDecrement negative value of milliseconds to be added to weapons cooldown
	 * @param upgradeValue value in coins that the upgrade is worth
	 */
	public Upgrade(int powerIncrement, float cooldownDecrement, int upgradeValue) {
		this.powerIncrement = powerIncrement;
		this.cooldownDecrement = cooldownDecrement;
		this.upgradeValue = upgradeValue;
	}
	
	public float getCooldownDecrement() {
		return cooldownDecrement;
	}
	
	public int getPowerIncrement() {
		return powerIncrement;
	}
	
	public int getUpgradeValue() {
		return upgradeValue;
	}
}
