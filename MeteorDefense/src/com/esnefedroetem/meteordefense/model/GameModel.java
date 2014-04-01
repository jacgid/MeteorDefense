package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.Player;

/**
 * The GameModel handles all the gamelogic.
 * @author Simon Nielsen
 *
 */
public class GameModel {

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Player player;
	private MeteorShower meteorShower;
	public static final float WIDTH = 100f;
	public static final float HEIGHT = 200f;
	
	private PropertyChangeSupport pcs;
	
	/**
	 * Initializes the GameModel.
	 */
	public GameModel(Player player, City city){
		this.player = player;
		this.meteorShower = city.getMeteorShower();
		pcs = new PropertyChangeSupport(this);
	}
	
	/**
	 * Updates the model
	 * @param delta The time since this method was called last.
	 */
	public void update(float delta){
		for(int i = 0; i < projectiles.size(); i++){
			projectiles.get(i).move(delta);
		}
		collisionControll();
		removeProjectilesBeyondGameField();
	}
	
	/**
	 * Tells the player to shoot.
	 */
	public void shoot(float X, float Y){
		projectiles.add(player.shoot(X, Y));
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void collisionControll() {
		for(Projectile projectile: projectiles) {
			ArrayList<Meteor> meteors = meteorShower.getNowFlyingMeteors();
			for (Meteor meteor: meteors) {
			if (collisionOccurs(projectile, meteor)) {
				handleCollision(projectile, meteor);
			}
		}
		}
	}
	
	public void handleCollision(Projectile projectile, Meteor meteor) {
		meteor.setLife(meteor.getLife() - projectile.getDamage());
		if (meteor.getLife() <= 0) {
			meteorShower.getNowFlyingMeteors().remove(meteor);
		}
		projectiles.remove(projectile);
	}
	public boolean collisionOccurs(Projectile projectile, Meteor meteor) {
		return projectile.getBounds().overlaps(meteor.getBounds());
	}
	
	public void removeProjectilesBeyondGameField() {
		int length = projectiles.size();
		
		for(int i = 0; i < length; i++) {
			if (outOfBounds(projectiles.get(i))) {
				projectiles.remove(projectiles.get(i));
				length--;
				i--;
			}
		}
	}
	
	public boolean outOfBounds(MoveableGameObject object) {
		float x = object.getX() + object.getBounds().radius;
		float y = object.getY() + object.getBounds().radius;
		
		return (x < 0 || x > WIDTH || y > HEIGHT);
	}
	
	public ArrayList<Projectile> getVisibleProjectiles(){
		return projectiles;
	}
	
	public ArrayList<Meteor> getVisibleMeteors(){
		return meteorShower.getNowFlyingMeteors();
	}
	
}
