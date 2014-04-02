package com.esnefedroetem.meteordefense.model;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;

public class CannonBarrel {

	private float cannonX = Constants.LOGIC_SCREEN_WIDTH/2, cannonY = 10;
	private float angle = (float)(Math.PI/2);
	
	public CannonBarrel(){
		
	}
	
	public Projectile shoot(float X, float Y){
		float a = Y-cannonY;
		float b = Math.abs(cannonX-X);
		if(X<cannonX){
			angle = (float)(Math.PI-(Math.atan(a/b)));
		}else{
			angle = (float)Math.atan(a/b);
		}
		//Calculate the spawn point for the projectile.
		Vector2 startPosition = new Vector2(Constants.LOGIC_SCREEN_WIDTH/2,0);
		startPosition.x = (float) (startPosition.x + (Constants.CANNONBARREL_LENGTH * Math.cos(angle)));
		startPosition.y = (float) (startPosition.y + (Constants.CANNONBARREL_LENGTH * Math.sin(angle)));
		
		return new Projectile(angle, startPosition);
	}
	
	public float getAngle(){
		return angle;
	}
	
}
