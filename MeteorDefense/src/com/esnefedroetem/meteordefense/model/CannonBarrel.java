package com.esnefedroetem.meteordefense.model;

public class CannonBarrel {

	private float cannonX = 50, cannonY = 10;
	
	public CannonBarrel(){
		
	}
	
	public Projectile shoot(float X, float Y){
		float a = Y-cannonY;
		float b = Math.abs(cannonX-X);
		if(X<cannonX){
			return new Projectile(Math.atan(Math.PI-(a/b)), 1);
		}else{
			return new Projectile(Math.atan(a/b), 1);
		}
	}
	
}
