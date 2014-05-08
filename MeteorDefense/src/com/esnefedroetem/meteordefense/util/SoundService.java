package com.esnefedroetem.meteordefense.util;

public class SoundService {
	
	private static final SoundService instance = new SoundService();
	private boolean soundState;
	
	private SoundService(){}
	
	public static SoundService getInstance(){
		return instance;
	}
	
	public void changeSoundState(){
		soundState = !soundState;
	}
	
	public boolean getSoundState(){
		return soundState;
	}
	
	public void setSoundState(boolean soundState){
		this.soundState = soundState;
	}
	
}
