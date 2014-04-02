package com.esnefedroetem.meteordefense.model;
import com.esnefedroetem.meteordefense.util.*;

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
		return new Projectile(angle, 1);
	}
	
	public float getAngle(){
		return angle;
	}
	
}
