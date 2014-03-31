package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public abstract class MoveableGameObject {
	private float size;
	private float speed; // Units per second
	private Vector2 position;
	private double angle;
	private Circle bounds;
	private int damage;
	
	public MoveableGameObject(double angle, int damage, float size, float speed){
		this.angle = angle;
		this.damage = damage;
		this.size = size;
		this.speed = speed;
		position.x = 50f;
		position.y = 10f;
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.radius = size/2;
	}
	public void move(float delta){
		position.x = (float) (position.x + (speed * delta * Math.cos(angle)));
		position.y = (float) (position.y + (speed * delta * Math.sin(angle)));
		bounds.x = position.x;
		bounds.y = position.y;
	}
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public Circle getBounds(){
		return bounds;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public int getDamage(){
		return damage;
	}

}
