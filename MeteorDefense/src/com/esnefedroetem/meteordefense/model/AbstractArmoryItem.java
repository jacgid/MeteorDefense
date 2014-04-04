package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public abstract class AbstractArmoryItem {

	public enum State {
		LOCKED, UNLOCKED;
	}
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	private State state;
	private int power, cooldown, sellValue, upgradeIndex;
	private ArrayList<Upgrade> upgrades;
	
	public void upgrade() {
		Upgrade upgrade = upgrades.get(upgradeIndex);
		power = power + upgrade.getPowerIncrement();
		cooldown = cooldown + upgrade.getCooldownDecrement();
		calculateSellValue(upgrade.getValue());
		upgradeIndex++;
	}
	
	public boolean hasUpgrade() {
		return upgradeIndex > upgrades.size() - 1;
	}
	
	private void reset() {
		upgradeIndex = 0;
		upgrade();
	}
	public void addChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	public PropertyChangeSupport getPropertyChangeSupport() {
		return pcs;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
		if (state == State.LOCKED) {
			reset();
		}
	}
	
	public int getPower() {
		return power;
	}
		
	public int getCooldown() {
		return cooldown;
	}
	
	public int getUpgradeIndex() {
		return upgradeIndex;
	}
	
	public void setUpgradeIndex(int upgradeIndex) {
		this.upgradeIndex = 0;
		for(int i = 0; i < upgradeIndex; i++) {
			upgrade();
		}
	}
	
	public void setUpgradeList(ArrayList<Upgrade> upgrades) {
		this.upgrades = upgrades;
	}
	
	private void calculateSellValue(int value) {
		sellValue += value/2;
	}
	
	public int getPurchaseValue() {
		return upgrades.get(0).getValue();
	}
	
	public int sellValue() {
		return sellValue;		
	}
	
	public int getNextUpgradeValue() {
		return upgrades.get(upgradeIndex).getValue();
	}
	
	public abstract void act();
	
	public abstract void initUpgrades();
	
}
