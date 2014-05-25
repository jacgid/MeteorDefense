package com.esnefedroetem.meteordefense.model;

/** 
 * Upgrades for ArmoryItems.
 * Has a powerIncrement and cooldownDecrement value, of which one should always
 * be 0. Also has a upgradeValue, which is the upgrades coinvalue when bought.
 *  @author Emma Lindholm
 *  
 */

public class Upgrade {
	
	private final int POWER_INCREMENT, UPGRADE_VALUE;
	private final float COOLDOWN_DECREMENT;
	
	/**
	 * 
	 * @param powerIncrement positive value to be added to weapons power
	 * @param cooldownDecrement negative value of milliseconds to be added to weapons cooldown
	 * @param upgradeValue value in coins that the upgrade is worth
	 */
	public Upgrade(int powerIncrement, float cooldownDecrement, int upgradeValue) {
		this.POWER_INCREMENT = powerIncrement;
		this.COOLDOWN_DECREMENT = cooldownDecrement;
		this.UPGRADE_VALUE = upgradeValue;
	}
	
	public float getCooldownDecrement() {
		return COOLDOWN_DECREMENT;
	}
	
	public int getPowerIncrement() {
		return POWER_INCREMENT;
	}
	
	public int getUpgradeValue() {
		return UPGRADE_VALUE;
	}
}
