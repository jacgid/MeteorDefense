package com.esnefedroetem.meteordefense;

import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.AbstractArmoryItem.State;
import com.esnefedroetem.meteordefense.model.StandardArmoryItem;

public class WeaponFactory {
	
	public static List<AbstractArmoryItem> getChoosenWeapons(){
		 ArrayList<AbstractArmoryItem> selectedArmoryItems = new  ArrayList<AbstractArmoryItem>();
		 selectedArmoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
		return selectedArmoryItems;
	}
	
	public static List<AbstractArmoryItem> getWeapons(){
		return null;
	}

	
}
