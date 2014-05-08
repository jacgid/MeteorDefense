package com.esnefedroetem.meteordefense.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Constants {
	
	public static final float LOGIC_SCREEN_WIDTH = 720f;
	public static final float LOGIC_SCREEN_HEIGHT = 1280f;
	public static final float LOGIC_ASPECTRATIO = LOGIC_SCREEN_WIDTH/LOGIC_SCREEN_HEIGHT;
	
	public static final float BASE_METEOR_SPEED = LOGIC_SCREEN_HEIGHT/2.5f;
	public static final float BASE_METEOR_SIZE = LOGIC_SCREEN_WIDTH/7f;
	public static final float BASE_METEOR_ANGLE = (float)(1.5*Math.PI);
	public static final int BASE_METEOR_DAMAGE = 1;
	public static final int BASE_METEOR_LIFE = 1;
	
	public static final float CANNONBARREL_LENGTH = LOGIC_SCREEN_HEIGHT/8f;
	
	public static final float DEFAULT_PROJECTILE_SPEED = LOGIC_SCREEN_HEIGHT*1.5f;
	public static final float DEFAULT_PROJECTILE_SIZE = LOGIC_SCREEN_WIDTH/50f;
	public static final Vector2 DEFAULT_PROJECTILE_SPAWN = new Vector2(LOGIC_SCREEN_WIDTH/2f+DEFAULT_PROJECTILE_SIZE/2f, CANNONBARREL_LENGTH);
	public static final int DEFAULT_PROJECTILE_DAMAGE = 1;
	
	public static final float TOOLBAR_HEIGHT = LOGIC_SCREEN_HEIGHT/10f;
	
	public static final Rectangle CITY_BOUNDS = new Rectangle(0, LOGIC_SCREEN_HEIGHT/8, LOGIC_SCREEN_WIDTH, LOGIC_SCREEN_WIDTH/4);
	
	public static final String WALLET_PATH = "wallet.txt";
	public static final String SOUND_STATE_PATH = "sound.txt";
	public static final String CONTINENT_PATH = "continents.txt";
	public static final String WEAPON_PATH = "weapons.txt";
	public static final String CHOOSEN_WEAPONS_PATH = "choosenWeapons.txt";
	
	
	
	
}
