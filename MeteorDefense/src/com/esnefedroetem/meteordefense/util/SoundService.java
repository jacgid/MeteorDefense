package com.esnefedroetem.meteordefense.util;

public class SoundService {
	
	private static boolean soundState;
	
	public static void changeSoundState(){
		SoundService.soundState = !SoundService.soundState;
	}
	
	public static boolean getSoundState(){
		return SoundService.soundState;
	}
	
	public static void setSoundState(boolean soundState){
		SoundService.soundState = soundState;
	}
	
}
