package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.*;

public class Projectile extends MoveableGameObject{

	public Projectile(double angle){
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
	}
	
	public Projectile(double angle, int damage){
		super(angle, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
	}
	
	public Projectile(double angle, int damage, float size){
		super(angle, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
	}
	public Projectile(double angle, int damage, float size, float speed){
		super(angle, damage, size, speed, Constants.DEFAULT_PROJECTILE_SPAWN);
	}
	
	
	
	
	
}
