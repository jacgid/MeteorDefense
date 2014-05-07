package com.esnefedroetem.meteordefense.factory;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.EmptyItem;
import com.esnefedroetem.meteordefense.model.armoryitem.ReversedGravityEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.SlowMotionEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.StandardArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;

public class WeaponFactory {

	public static List<AbstractArmoryItem> getChoosenWeapons() {
		ArrayList<AbstractArmoryItem> armoryItems = new ArrayList<AbstractArmoryItem>(5);
		armoryItems.add(new EmptyItem());
		armoryItems.add(new ReversedGravityEffectArmoryItem(State.UNLOCKED, 1));
		armoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		armoryItems.add(new SlowMotionEffectArmoryItem(State.UNLOCKED, 1));
		armoryItems.add(new EmptyItem());
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
