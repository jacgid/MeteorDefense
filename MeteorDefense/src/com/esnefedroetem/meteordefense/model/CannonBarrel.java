package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public class CannonBarrel {

	private float angle = (float) (Math.PI / 2);
	private Vector2 startPosition;
	private Rectangle bounds;
	private Projectile projectile;

	public CannonBarrel() {
		bounds = new Rectangle(Constants.LOGIC_SCREEN_WIDTH/2-(Constants.CANNONBARREL_LENGTH/4), Constants.LOGIC_SCREEN_HEIGHT/20, Constants.CANNONBARREL_LENGTH/2, Constants.CANNONBARREL_LENGTH);
	}

	public void calculateAngle(final float posX, final float posY) {
		float a = posY - bounds.y;
		float b = Math.abs(bounds.x - posX);
		if (posX < bounds.x) {
			angle = (float) (Math.PI - (Math.atan(a / b)));
		} else {
			angle = (float) Math.atan(a / b);
		}
		// Calculate the spawn point for the projectile.
		startPosition = new Vector2(bounds.x, bounds.y);
		startPosition.x = (float) (startPosition.x + (Constants.CANNONBARREL_LENGTH * Math.cos(angle)));
		startPosition.y = (float) (startPosition.y + (Constants.CANNONBARREL_LENGTH * Math.sin(angle)));
	}

	public Projectile deploy() {
		projectile.setAngle(angle);
		projectile.setPosition(startPosition);
		return projectile;
	}

	public Vector2 getStartPosition() {
		return startPosition;
	}

	public float getAngle() {
		return angle;
	}

	public void reset() {
		angle = (float) (Math.PI * 0.5);

	}
	
	public Rectangle getBounds(){
		return bounds;
	}

	public void load(Projectile projectile) {
		this.projectile = projectile;
	}

}
