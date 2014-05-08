package com.esnefedroetem.meteordefense.service;

import java.util.List;

import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;

public interface ISaveService {

	public void save(boolean soundState, Wallet wallet,
			List<Continent> continents, List<AbstractArmoryItem> items,
			List<AbstractArmoryItem> choosenItems);

}
