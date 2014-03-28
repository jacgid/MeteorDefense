package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

/**
 * The GameModel handles all the gamelogic.
 * @author Simon Nielsen
 *
 */
public class GameModel {

	private CannonBarrel cannonBarrel;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	/**
	 * Initializes the GameModel.
	 */
	public GameModel(){
		cannonBarrel = new CannonBarrel();
	}
	
	/**
	 * Updates the model
	 * @param delta The time since this method was called last.
	 */
	public void update(float delta){
		
	}
	
	/**
	 * Asks the CanonBarrel to shoot.
	 */
	public void shoot(int X, int Y){
		Projectile projectile = cannonBarrel.shoot(X, Y);
		projectiles.add(projectile);
	}
	
}
