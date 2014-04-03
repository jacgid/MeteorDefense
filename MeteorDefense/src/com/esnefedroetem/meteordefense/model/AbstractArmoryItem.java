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
	private int power, cooldown, value, upgradeIndex;
	private ArrayList<Upgrade> upgrades;
	
	public int getValue() {
		return value;
	}
	
	public void upgrade() {
		Upgrade upgrade = upgrades.get(upgradeIndex);
		power = power + upgrade.getPowerIncrement();
		cooldown = cooldown + upgrade.getCooldownDecrement();
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
	
	public abstract void act();
	
}
