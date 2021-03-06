package com.esnefedroetem.meteordefense.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A class to collect constants in one place.
 * @author Andreas Pegelow
 *
 */
public class Constants {
	
	public static final float LOGIC_SCREEN_WIDTH = 720f;
	public static final float LOGIC_SCREEN_HEIGHT = 1280f;
	public static final float LOGIC_ASPECTRATIO = LOGIC_SCREEN_WIDTH/LOGIC_SCREEN_HEIGHT;
	
	public static final float BASE_METEOR_SPEED = LOGIC_SCREEN_HEIGHT/4f;
	public static final float BASE_METEOR_SIZE = LOGIC_SCREEN_WIDTH/7f;
	public static final float BASE_METEOR_ANGLE = (float)(1.5*Math.PI);
	public static final int BASE_METEOR_DAMAGE = 1;
	public static final int BASE_METEOR_LIFE = 1;
	
	public static final float CANNON_HEIGHT = 191;
	public static final float CANNON_WIDTH = 160;
	public static final float CANNON_ORIGIN_X = CANNON_WIDTH / 2 + 2;
	public static final float CANNON_ORIGIN_Y = CANNON_HEIGHT - 111;
	
	public static final float DEFAULT_PROJECTILE_SPEED = LOGIC_SCREEN_HEIGHT*1.5f;
	public static final float DEFAULT_PROJECTILE_SIZE = LOGIC_SCREEN_WIDTH/30f;
	public static final Vector2 DEFAULT_PROJECTILE_SPAWN = new Vector2(LOGIC_SCREEN_WIDTH / 2, 0);
	public static final int DEFAULT_PROJECTILE_DAMAGE = 1;
	
	public static final float TOOLBAR_HEIGHT = 172;
	
	public static final Rectangle CITY_BOUNDS = new Rectangle(0, 172, LOGIC_SCREEN_WIDTH, 179);
	
	public static final String WALLET_PATH = "wallet.txt";
	public static final String SOUND_STATE_PATH = "sound.txt";
	public static final String CONTINENT_PATH = "continents.txt";
	public static final String WEAPON_PATH = "weapon.txt";
	
	
	
	
}
