package com.esnefedroetem.meteordefense.service;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;

public class WeaponData {
	
	private AbstractArmoryItem.State state;
	private int upgradeIndex;
	private boolean isChoosen;
	private String name;
	
	public WeaponData(AbstractArmoryItem.State state, int upgradeIndex, boolean isChoosen, String name){
		this.state = state;
		this.upgradeIndex = upgradeIndex;
		this.isChoosen = isChoosen;
		this.name = name;
	}
	
	public WeaponData(){}

	public AbstractArmoryItem.State getState() {
		return state;
	}

	public int getUpgradeIndex() {
		return upgradeIndex;
	}

	public boolean isChoosen() {
		return isChoosen;
	}
	
	public String getName(){
		return name;
	}

}
