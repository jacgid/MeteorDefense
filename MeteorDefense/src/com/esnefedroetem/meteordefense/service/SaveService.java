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

public class SaveService {
	
	public static void saveWallet(Wallet wallet){
		Json json = new Json();
		String str = json.toJson(wallet);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.WALLET_PATH));
		file.writeString(str, false);
	}
	
	public static void saveSoundState(boolean sound){
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.SOUND_STATE_PATH));
		file.writeString(Boolean.toString(sound), false);		
	}
	
	public static void saveContinents(List<Continent> continents){
		Json json = new Json();
		String str = json.toJson(continents);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.CONTINENT_PATH));
		file.writeString(str, false);
	}
	
	public static void saveWeapons(List<AbstractArmoryItem> weapons){
		Json json = new Json();
		String str = json.toJson(weapons);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.WEAPON_PATH));
		file.writeString(str, false);
	}

	public static void saveChoosenWeapons(List<AbstractArmoryItem> weapons){
		Json json = new Json();
		String str = json.toJson(weapons);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.CHOOSEN_WEAPONS_PATH));
		file.writeString(str, false);
	}

}
