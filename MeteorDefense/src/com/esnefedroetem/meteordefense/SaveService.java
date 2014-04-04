package com.esnefedroetem.meteordefense;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.util.Constants;

public class SaveService {
	
	public static void saveWallet(Wallet wallet){
		Json json = new Json();
		String str = json.toJson(wallet);
		FileHandle file = new FileHandle(Constants.walletPath);
		file.writeString(str, false);
	}
	
	public static void saveSoundState(boolean sound){
		FileHandle file = new FileHandle(Constants.soundStatePath);
		file.writeString(Boolean.toString(sound), false);		
	}
	
}
