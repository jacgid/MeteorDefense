package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

	private static final float SIZE = 2f;
	private float speed = 50f; // Units per second
	private Vector2 position;
	private double angle;
	private Circle bounds;
	
	public Projectile(double angle){
		this.angle = angle;
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.radius = SIZE/2;
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
}
