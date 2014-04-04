package com.esnefedroetem.meteordefense;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
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
	
}
