package com.esnefedroetem.meteordefense;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.StandardArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;

public class WeaponFactory {

	public static List<AbstractArmoryItem> getChoosenWeapons() {
		ArrayList<AbstractArmoryItem> armoryItems = new ArrayList<AbstractArmoryItem>(5);
		armoryItems.add(null);
		armoryItems.add(null);
		armoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		armoryItems.add(null);
		armoryItems.add(null);
		return armoryItems;
	}

	public static List<AbstractArmoryItem> getWeapons() {
		ArrayList<AbstractArmoryItem> selectedArmoryItems = new ArrayList<AbstractArmoryItem>(5);
		selectedArmoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		selectedArmoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		selectedArmoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		selectedArmoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		selectedArmoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		return selectedArmoryItems;
	}

}
