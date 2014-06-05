package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
 /**
  * This is a super class for all the objects that are supposed to move on the game screen. Such as meteors and projectiles.
  * It has a speed and angle, and moves automatically when you call the move method with a delta value. 
  * @author Andreas Pegelow
  * @author Simon Nielsen
  */
public abstract class MoveableGameObject {
	private float speed; // Units per second
	private float angle;
	private Rectangle bounds;
	private int damage;
	
	public MoveableGameObject(){
		
	}

	public MoveableGameObject(float angle, int damage, float width, float height, float speed, Vector2 startPosition) {
		this.angle = angle;
		this.damage = damage;
		this.speed = speed;
		bounds = new Rectangle(startPosition.x, startPosition.y, width, height);
	}

	public MoveableGameObject(float angle, int damage, float size, float speed, Vector2 startPosition) {
		this(angle, damage, size, size, speed, startPosition);
	}

	public void move(float delta) {
		bounds.x = (float) (bounds.x + speed * delta * Math.cos(angle));
		bounds.y = (float) (bounds.y + speed * delta * Math.sin(angle));
	}

	public float getX() {
		return bounds.x;
	}

	public float getY() {
		return bounds.y;
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
		bounds.setPosition(position);
	}
	
//	@Override
//	public boolean equals(Object o){
//		if (o instanceof MoveableGameObject) {
//			MoveableGameObject moveable = (MoveableGameObject) o;
//			return speed == moveable.speed
//					&& angle == moveable.angle
//					&& bounds.equals(moveable.bounds)
//					&& damage == moveable.damage;
//		}
//		return false;
//	}
}
