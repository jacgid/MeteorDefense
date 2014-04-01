package com.esnefedroetem.meteordefense.model;
import com.esnefedroetem.meteordefense.util.*;

public class CannonBarrel {

	private float cannonX = Constants.LOGIC_SCREEN_WIDTH/2, cannonY = 10;
	
	public CannonBarrel(){
		
	}
	
	public Projectile shoot(float X, float Y){
		float a = Y-cannonY;
		float b = Math.abs(cannonX-X);
		if(X<cannonX){
			return new Projectile(Math.PI-(Math.atan(a/b)), 1);
		}else{
			return new Projectile(Math.atan(a/b), 1);
		}
	}
	
}
