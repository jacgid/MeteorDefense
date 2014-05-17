package com.esnefedroetem.meteordefense.service;

import java.util.HashMap;
import java.util.List;

import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;

public interface ILoadService {
	public Wallet getWallet();
	
	public boolean getSoundState();
	
	public List<Continent> getContinents();
	
	public List<WeaponData> getArmoryItems();
	
	public LevelData getLevelData(String city);
	
	public void loadFilenames();
	
	public String[] getLevelFilenames(String city);
	
	public String[] getBaseGameNames();
	
	public <T> HashMap<String, String> getFilenameMap(Class<T> type);
	
	public HashMap<String, String> getMenuFilenameMap();
	
	public HashMap<String, String> getBaseGameFilenameMap();
}
