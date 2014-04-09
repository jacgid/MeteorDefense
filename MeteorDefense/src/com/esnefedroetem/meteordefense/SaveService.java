package com.esnefedroetem.meteordefense;

import java.io.File;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esnefedroetem.meteordefense.model.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.util.Constants;

public class SaveService {
	
	public static void saveWallet(Wallet wallet){
		Json json = new Json();
		String str = json.toJson(wallet);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.walletPath));
		file.writeString(str, false);
	}
	
	public static void saveSoundState(boolean sound){
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.soundStatePath));
		file.writeString(Boolean.toString(sound), false);		
	}
	
	public static void saveContinents(List<Continent> continents){
		Json json = new Json();
		String str = json.toJson(continents);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.continentPath));
		file.writeString(str, false);
	}
	
	public static void saveWeapons(List<AbstractArmoryItem> weapons){
		Json json = new Json();
		String str = json.toJson(weapons);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.weaponPath));
		file.writeString(str, false);
	}

	public static void saveChoosenWeapons(List<AbstractArmoryItem> weapons){
		Json json = new Json();
		String str = json.toJson(weapons);
		FileHandle file = new FileHandle(new File(Gdx.files.getLocalStoragePath() + Constants.choosenWeaponPath));
		file.writeString(str, false);
	}

}
