package com.esnefedroetem.meteordefense.service;

import java.util.List;

import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;

public interface ILoadService {
	public Wallet getWallet();
	
	public boolean getSoundState();
	
	public List<Continent> getContinents();
	
	public List<AbstractArmoryItem> getArmoryItems();
	
	public List<AbstractArmoryItem> getChoosenArmoryItems();
	
}
