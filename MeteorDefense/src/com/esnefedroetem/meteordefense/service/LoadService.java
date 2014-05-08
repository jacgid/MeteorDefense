package com.esnefedroetem.meteordefense.service;

import java.io.File;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.util.Constants;

public class LoadService implements ILoadService{
	
	private static final LoadService instance = new LoadService();
	
	private LoadService(){}
	
	public static ILoadService getInstance(){
		return instance;
	}
	
	private <T> T load(Class<T> type, String path){
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + path));
		if(file.exists()){
			String str = file.readString();
			Json json = new Json();
			return json.fromJson(type, str);
		}
		return null;
	}
		
	@Override
	public Wallet getWallet() {
		Wallet wallet = load(Wallet.class, Constants.WALLET_PATH);
		return wallet;
	}

	@Override
	public boolean getSoundState() {
		Boolean state = load(Boolean.class, Constants.SOUND_STATE_PATH);
		if(state != null){
			return state;
		}
		return true;
	}

	@Override
	public List<Continent> getContinents() {
		List<Continent> continents = load(List.class, Constants.CONTINENT_PATH);
		return continents;
	}

	@Override
	public List<AbstractArmoryItem> getArmoryItems() {
		List<AbstractArmoryItem> items = load(List.class, Constants.WEAPON_PATH);
		return items;
	}

	@Override
	public List<AbstractArmoryItem> getChoosenArmoryItems() {
		List<AbstractArmoryItem> items = load(List.class, Constants.CHOOSEN_WEAPONS_PATH);
		return items;		
	}
	
}
