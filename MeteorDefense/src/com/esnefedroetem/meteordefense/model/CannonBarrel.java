package com.esnefedroetem.meteordefense.model;

public class CannonBarrel {

	private float cannonX = 50, cannonY = 10;
	
	public CannonBarrel(){
		
	}
	
	public Projectile shoot(float X, float Y){
		float a = Y-cannonY;
		float b;
		if(X<cannonX){
			b = cannonX-X;
			return new Projectile(Math.atan(a/b));
		}else{
			b = X-cannonX;
			return new Projectile(Math.atan(Math.PI-(a/b)));
		}
	}
	
}
