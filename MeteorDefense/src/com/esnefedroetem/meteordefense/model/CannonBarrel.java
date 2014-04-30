package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public class CannonBarrel {

	private float cannonX = Constants.LOGIC_SCREEN_WIDTH / 2, cannonY = 10;
	private float angle = (float) (Math.PI / 2);
	private Vector2 startPosition;
	private Rectangle bounds;

	public CannonBarrel() {
		bounds = new Rectangle(Constants.LOGIC_SCREEN_WIDTH/2-(Constants.CANNONBARREL_LENGTH/4), Constants.LOGIC_SCREEN_HEIGHT/20, Constants.CANNONBARREL_LENGTH/2, Constants.CANNONBARREL_LENGTH);
	}

	public void calculateAngle(float X, float Y) {
		float a = Y - cannonY;
		float b = Math.abs(cannonX - X);
		float barrelAngle;
		if (X < cannonX) {
			barrelAngle = (float) (Math.PI - (Math.atan(a / b)));
			startPosition = new Vector2(Constants.LOGIC_SCREEN_WIDTH / 2, 0);
			startPosition.x = (float) (startPosition.x + (Constants.CANNONBARREL_LENGTH * Math.cos(barrelAngle)));
			startPosition.y = (float) (startPosition.y + (Constants.CANNONBARREL_LENGTH * Math.sin(barrelAngle)));
			a = Y - startPosition.y;
			b = Math.abs(startPosition.x- X);
			angle = (float) (Math.PI - (Math.atan(a / b)));
		} else {
			barrelAngle = (float) Math.atan(a / b);
			startPosition = new Vector2(Constants.LOGIC_SCREEN_WIDTH / 2, 0);
			startPosition.x = (float) (startPosition.x + (Constants.CANNONBARREL_LENGTH * Math.cos(barrelAngle)));
			startPosition.y = (float) (startPosition.y + (Constants.CANNONBARREL_LENGTH * Math.sin(barrelAngle)));
			angle = (float) Math.atan(a / b);
		}
		// Calculate the spawn point for the projectile.
//		startPosition = new Vector2(Constants.LOGIC_SCREEN_WIDTH / 2, 0);
//		startPosition.x = (float) (startPosition.x + (Constants.CANNONBARREL_LENGTH * Math.cos(angle)));
//		startPosition.y = (float) (startPosition.y + (Constants.CANNONBARREL_LENGTH * Math.sin(angle)));
	}

	public Projectile shoot() {
		return new Projectile(angle, startPosition);
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

}
