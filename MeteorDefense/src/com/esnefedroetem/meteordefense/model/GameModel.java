package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.esnefedroetem.meteordefense.Player;

/**
 * The GameModel handles all the gamelogic.
 * @author Simon Nielsen
 *
 */
public class GameModel {

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Player player;
	public static final float WIDTH = 100f;
	public static final float HEIGHT = 200f;
	
	private PropertyChangeSupport pcs;
	
	/**
	 * Initializes the GameModel.
	 */
	public GameModel(Player player){
		this.player = player;
		pcs = new PropertyChangeSupport(this);
	}
	
	/**
	 * Updates the model
	 * @param delta The time since this method was called last.
	 */
	public void update(float delta){
		
	}
	
	/**
	 * Tells the player to shoot.
	 */
	public void shoot(float X, float Y){
		Projectile projectile = player.shoot(X, Y);
		projectiles.add(projectile);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
}
