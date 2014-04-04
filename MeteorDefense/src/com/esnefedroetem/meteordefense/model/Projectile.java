package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.*;

public class Projectile extends MoveableGameObject{

	public Projectile(float angle){
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
	}
	
	public Projectile(float angle, Vector2 startPosition){
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED, startPosition);
	}
	
	public Projectile(float angle, int damage, float size){
		super(angle, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
	}
	public Projectile(float angle, int damage, float size, float speed, Vector2 spawn){
		super(angle, damage, size, speed, spawn);
	}
	
	
	
	
	
	
}
