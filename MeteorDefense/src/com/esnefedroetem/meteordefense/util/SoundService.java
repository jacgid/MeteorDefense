package com.esnefedroetem.meteordefense.util;

import com.badlogic.gdx.audio.Sound;

/**
 * 
 * SoundService is responsible for all kind of sound/music in the application.
 * 
 * @author Jacob
 *
 */
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
	
	public void playSound(String sound){
		Sound toPlay = AssetsLoader.getInstance().getSound(sound+".mp3");
		toPlay.play(0.5f);
	}
	
}
