package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

	public static final float DEFAULT_SPEED = 50f;
	public static final float DEFAULT_SIZE = 2f;
	
	private float size;
	private float speed; // Units per second
	private Vector2 position;
	private double angle;
	private Circle bounds;
	
	private int damage;
	
	public Projectile(double angle, int damage){
		this(angle, damage, DEFAULT_SIZE, DEFAULT_SPEED);
	}
	
	public Projectile(double angle, int damage, float size){
		this(angle, damage, DEFAULT_SIZE, DEFAULT_SPEED);
	}
	
	public Projectile(double angle, int damage, float size, float speed){
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
