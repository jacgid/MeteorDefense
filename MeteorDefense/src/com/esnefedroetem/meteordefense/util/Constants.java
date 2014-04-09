package com.esnefedroetem.meteordefense.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static final float DEFAULT_PROJECTILE_SPEED = 200f;
	public static final float DEFAULT_PROJECTILE_SIZE = 2f;
	public static final Vector2 DEFAULT_PROJECTILE_SPAWN = new Vector2(50,10);
	public static final int DEFAULT_PROJECTILE_DAMAGE = 1;
	
	
	public static final float BASE_METEOR_SPEED = 40f;
	public static final float BASE_METEOR_SIZE = 10f;
	public static final float BASE_METEOR_ANGLE = (float)(1.5*Math.PI);
	public static final int BASE_METEOR_DAMAGE = 1;
	public static final int BASE_METEOR_LIFE = 1;
	
	
	public static final float LOGIC_SCREEN_WIDTH = 100f;
	public static final float LOGIC_SCREEN_HEIGHT = 150f;
	
	public static final float CANNONBARREL_LENGTH = 10f;
	
	
	public static final Rectangle CITY_BOUNDS = new Rectangle(0, 0, LOGIC_SCREEN_WIDTH, LOGIC_SCREEN_HEIGHT/6);
	
	public static final String walletPath = "wallet.txt";
	public static final String soundStatePath = "sound.txt";
	public static final String continentPath = "continents.txt";
	public static final String weaponPath = "weapons.txt";
	public static final String choosenWeaponPath = "choosenWeapons.txt";
	
	public enum MeteorType{NONE, RADIOACTIVE, FIRE, FAST, ICE}
	public enum ProjectileType{NONE, WATER, FIRE}
	
}
