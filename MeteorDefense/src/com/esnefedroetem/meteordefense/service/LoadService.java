package com.esnefedroetem.meteordefense.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.util.Constants;

class LoadService implements ILoadService{
	
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
		if(wallet != null){
			return wallet;
		}
		return new Wallet();
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
	public List<WeaponData> getArmoryItems() {
		List<WeaponData> items = load(List.class, Constants.WEAPON_PATH);
		return items;
	}
	
	@Override
	public void loadFilenames(){
		DataReader.getInstance().loadFilenames();
	}

	@Override
	public LevelData getLevelData(String city) {
		return DataReader.getInstance().readLevel(city);
	}
	
	public String[] getBaseGameNames(){
		return DataReader.getInstance().getBaseGameNames();
	}

	@Override
	public <T> HashMap<String, String> getFilenameMap(Class<T> type) {
		return DataReader.getInstance().getFilenameMap(type);
	}

	@Override
	public HashMap<String, String> getMenuFilenameMap() {
		return DataReader.getInstance().getMenuFilenameMap();
	}

	@Override
	public HashMap<String, String> getBaseGameFilenameMap() {
		return DataReader.getInstance().getBaseGameFilenameMap();
	}

	@Override
	public String[] getLevelFilenames(String city) {
		return DataReader.getInstance().getLevelFilesnames(city);
	}

}
