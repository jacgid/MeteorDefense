package com.esnefedroetem.meteordefense.model.armoryitem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.Upgrade;

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
	private int power, sellValue, upgradeIndex;
	private float cooldown;
	private long lastUsed;
	private List<Upgrade> upgrades;
	protected String name, description;
	public static final EmptyItem EMPTY_ITEM = new EmptyItem();
	
	public AbstractArmoryItem() {
		init(State.LOCKED, 0);
	}
	
	public void init(State state, int upgradeIndex) {
		initUpgrades();
		this.state = state;
		setUpgradeIndex(upgradeIndex);
	}
	
	public void upgrade() {
		Upgrade upgrade = upgrades.get(upgradeIndex);
		power = power + upgrade.getPowerIncrement();
		cooldown = cooldown + upgrade.getCooldownDecrement();
		calculateSellValue(upgrade.getValue());
		upgradeIndex++;
	}
	
	public boolean hasUpgrade() {
		return upgradeIndex < upgrades.size() - 1;
	}
	
	private void reset() {
		sellValue = 0;
		upgradeIndex = 0;
		power = 0;
		cooldown = 0;
		upgrade();
	}
	public void addChangeListener(PropertyChangeListener pcl) {
		pcs.addPropertyChangeListener(pcl);
	}
	
	public void removeChangeListener(PropertyChangeListener pcl) {
		pcs.removePropertyChangeListener(pcl);
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
		
	public float getCooldown() {
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
	
	public int getSellValue() {
		return sellValue;		
	}
	
	public int getNextUpgradeValue() {
		return upgrades.get(upgradeIndex).getValue();
	}
	
	public void act() {
		if(TimeUtils.timeSinceMillis(lastUsed) > cooldown * 1000) {
			lastUsed = TimeUtils.millis();
			performAct();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof AbstractArmoryItem){
			return name.equals(((AbstractArmoryItem) o).getName());
		}
		return false;
	}
	
	public abstract void performAct();
	
	public abstract void initUpgrades();

	public abstract void update(float delta);

	public String getNextUpgradeInfo() {
		if(upgrades.get(upgradeIndex).getPowerIncrement() == 0) {
			return "Cooldown: " + cooldown + upgrades.get(upgradeIndex).getCooldownDecrement() + " sec";
		}
		return "Power: " + power + upgrades.get(upgradeIndex).getPowerIncrement();
	}
		
}
