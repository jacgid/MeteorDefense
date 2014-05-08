package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public abstract class MoveableGameObject {
	private float size;
	private float speed; // Units per second
	private Vector2 position = new Vector2();
	private float angle;
	private Circle bounds = new Circle();
	private int damage;
	
	public MoveableGameObject(){
		
	}

	public MoveableGameObject(float angle, int damage, float size, float speed, Vector2 startPosition) {
		this.angle = angle;
		this.damage = damage;
		this.size = size;
		this.speed = speed;
		this.position.x = startPosition.x;
		this.position.y = startPosition.y;
		calculateBounds();

	}

	public final void calculateBounds() {
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.radius = size / 2;
	}

	public void move(float delta) {
		position.x = (float) (position.x + speed * delta * Math.cos(angle));
		position.y = (float) (position.y + speed * delta * Math.sin(angle));
		calculateBounds();
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public Circle getBounds() {
		return bounds;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setSize(float size) {
		this.size = size;

	}

	public float getSize() {
		return size;
	}
	
	public float getAngle(){
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getSpeed() {
		return speed;
	}
}
