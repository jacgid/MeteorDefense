package com.esnefedroetem.meteordefense;

import java.util.List;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esnefedroetem.meteordefense.model.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.util.Constants;

public class LoadService {
	
	
	public static Wallet getWallet(){
		FileHandle file = new FileHandle(Constants.walletPath);
		if(file.exists()){
			String str = file.readString();
			Json json = new Json();
			return json.fromJson(Wallet.class, str);
		}else{
			return new Wallet();
		}
	}
	
	public static boolean getSoundState(){
		FileHandle file = new FileHandle(Constants.soundStatePath);
		if(file.exists()){
			String str = file.readString();
			return Boolean.parseBoolean(str);
		}else{
			return true;
		}		
	}
	
	public static List<Continent> getContinents(){
		FileHandle file = new FileHandle(Constants.continentPath);
		if(file.exists()){
			String str = file.readString();
			Json json = new Json();
			return json.fromJson(List.class, str);
		}else{
			return null;
		}		
	}
	
	public static List<AbstractArmoryItem> getWeapons(){
		FileHandle file = new FileHandle(Constants.weaponPath);
		if(file.exists()){
			String str = file.readString();
			Json json = new Json();
			return json.fromJson(List.class, str);
		}
		return null;
	}

	public static List<AbstractArmoryItem> getChoosenWeapons(){
		FileHandle file = new FileHandle(Constants.choosenWeaponPath);
		if(file.exists()){
			String str = file.readString();
			Json json = new Json();
			return json.fromJson(List.class, str);
		}
		return null;
	}

	
}
