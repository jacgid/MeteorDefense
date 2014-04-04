package com.esnefedroetem.meteordefense.util;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static final float DEFAULT_PROJECTILE_SPEED = 200f;
	public static final float DEFAULT_PROJECTILE_SIZE = 2f;
	public static final Vector2 DEFAULT_PROJECTILE_SPAWN = new Vector2(50,10);
	public static final int DEFAULT_PROJECTILE_DAMAGE = 1;
	
	
	public static final float DEFAULT_METEOR_SPEED = 40f;
	public static final float DEFAULT_METEOR_SIZE = 10f;
	public static final float DEFAULT_METEOR_ANGLE = (float)(1.5*Math.PI);
	public static final int DEFAULT_METEOR_DAMAGE = 1;
	public static final int DEFAULT_METEOR_LIFE = 1;
	
	
	public static final float LOGIC_SCREEN_WIDTH = 100f;
	public static final float LOGIC_SCREEN_HEIGHT = 150f;
	
	public static final float CANNONBARREL_LENGTH = 10f;
	
	public enum meteorEffects{NONE, RADIOACTIVE, FIRE}
}
