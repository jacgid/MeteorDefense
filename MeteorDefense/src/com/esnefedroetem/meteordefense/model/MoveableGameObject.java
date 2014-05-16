package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public abstract class MoveableGameObject {
	private float size;
	private float speed; // Units per second
	private Vector2 position = new Vector2();
	private float angle;
	private Rectangle bounds;
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
		bounds = new Rectangle();
		calculateBounds();
	}

	public final void calculateBounds() {
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.width = size;
		bounds.height = size;
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

	public Rectangle getBounds() {
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
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof MoveableGameObject) {
			MoveableGameObject moveable = (MoveableGameObject) o;
			return size == moveable.size && speed == moveable.speed
					&& position.equals(moveable.position)
					&& angle == moveable.angle
					&& bounds.equals(moveable.bounds)
					&& damage == moveable.damage;
		}
		return false;
	}
}
