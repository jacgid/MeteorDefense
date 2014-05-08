package com.esnefedroetem.meteordefense.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.util.Constants;

public class SaveService implements ISaveService{
	
	private static final SaveService instance = new SaveService();
	
	private SaveService(){}
	
	public static SaveService getInstance(){
		return instance;
	}
	
	@Override
	public void save(boolean soundState, Wallet wallet,
			List<Continent> continents, List<AbstractArmoryItem> items,
			List<AbstractArmoryItem> choosenItems) {
		save(Constants.SOUND_STATE_PATH, soundState);
		save(Constants.WALLET_PATH, wallet);
		save(Constants.CONTINENT_PATH, continents);
		
		List<WeaponData> data = new ArrayList<WeaponData>(items.size() + 5);
		
		for(AbstractArmoryItem item : items){
			data.add(new WeaponData(item.getState(), item.getUpgradeIndex(), false, item.getName()));
		}
		for(AbstractArmoryItem item : choosenItems){
			data.add(new WeaponData(item.getState(), item.getUpgradeIndex(), true, item.getName()));
		}
		
		save(Constants.WEAPON_PATH, data);
		
	}
	
	private void save(String path, Object object){
		Json json = new Json();
		String str = json.toJson(object);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + path));
		file.writeString(str, false);		
	}

}
