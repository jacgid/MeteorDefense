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
		Wallet wallet = load(Wallet.class, Constants.walletPath);
		return wallet;
	}

	@Override
	public boolean getSoundState() {
		Boolean state = load(Boolean.class, Constants.soundStatePath);
		if(state != null){
			return state;
		}
		return true;
	}

	@Override
	public List<Continent> getContinents() {
		List<Continent> continents = load(List.class, Constants.continentPath);
		return continents;
	}

	@Override
	public List<AbstractArmoryItem> getArmoryItems() {
		List<AbstractArmoryItem> items = load(List.class, Constants.weaponPath);
		return items;
	}

	@Override
	public List<AbstractArmoryItem> getChoosenArmoryItems() {
		List<AbstractArmoryItem> items = load(List.class, Constants.choosenWeaponPath);
		return items;		
	}
	
//	public static Wallet getWallet(){
//		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.walletPath));
//		if(file.exists()){
//			String str = file.readString();
//			Json json = new Json();
//			return json.fromJson(Wallet.class, str);
//		}else{
//			return new Wallet();
//		}
//	}
//	
//	public static boolean getSoundState(){
//		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.soundStatePath));
//		if(file.exists()){
//			String str = file.readString();
//			return Boolean.parseBoolean(str);
//		}else{
//			return true;
//		}		
//	}
//	
//	public static List<Continent> getContinents(){
//		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.continentPath));
//		if(file.exists()){
//			String str = file.readString();
//			Json json = new Json();
//			return json.fromJson(List.class, str);
//		}else{
//			return null;
//		}		
//	}
//	
//	public static List<AbstractArmoryItem> getWeapons(){
//		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.weaponPath));
//		if(file.exists()){
//			String str = file.readString();
//			Json json = new Json();
//			return json.fromJson(List.class, str);
//		}
//		return null;
//	}
//
//	public static List<AbstractArmoryItem> getChoosenWeapons(){
//		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.choosenWeaponPath));
//		if(file.exists()){
//			String str = file.readString();
//			Json json = new Json();
//			return json.fromJson(List.class, str);
//		}
//		return null;
//	}

	
}
