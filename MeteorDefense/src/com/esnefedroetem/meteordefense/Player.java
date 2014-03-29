package com.esnefedroetem.meteordefense;

import com.esnefedroetem.meteordefense.model.CannonBarrel;
import com.esnefedroetem.meteordefense.model.Projectile;

public class Player {
	
	private CannonBarrel cannonBarrel;
	
	public Player(){
		cannonBarrel = new CannonBarrel();
	}
	
	public Projectile shoot(float X, float Y){
		return cannonBarrel.shoot(X, Y);
	}
	
}
