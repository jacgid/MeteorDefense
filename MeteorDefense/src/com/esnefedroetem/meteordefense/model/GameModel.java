package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.Player;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * The GameModel handles all the gamelogic.
 * 
 * @author Simon Nielsen
 * 
 */
public class GameModel {

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Player player;
	private MeteorShower meteorShower;
	public static final float WIDTH = Constants.LOGIC_SCREEN_WIDTH;
	public static final float HEIGHT = Constants.LOGIC_SCREEN_HEIGHT;

	private PropertyChangeSupport pcs;

	/**
	 * Initializes the GameModel.
	 */
	public GameModel(Player player, City city) {
		this.player = player;
		this.meteorShower = city.getMeteorShower();
		pcs = new PropertyChangeSupport(this);
		meteorShower.start();
	}

	/**
	 * Updates the model
	 * 
	 * @param delta
	 *            The time since this method was called last.
	 */
	public void update(float delta) {
		meteorShower.update(delta);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).move(delta);
		}
		collisionControll();
		removeProjectilesBeyondGameField();
	}

	/**
	 * Tells the player to shoot.
	 */
	public void shoot(float X, float Y) {
		projectiles.add(player.shoot(X, Y));
	}

	public void addChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void collisionControll() {
		int count1 = 0;
		while (count1 < projectiles.size()) {
			Projectile projectile = projectiles.get(count1);
			ArrayList<BasicMeteor> meteors = meteorShower.getVisibleMeteors();
			int count2 = 0;
			while (count2 < meteors.size()) {
				BasicMeteor meteor = meteors.get(count2);
				if (collisionOccurs(projectile, meteor)) {
					handleCollision(projectile, meteor);
//					count1--;
//					count2--;
				}
				count2++;
			}
			count1++;
		}
	}

	private void handleCollision(Projectile projectile, BasicMeteor meteor) {
		
		meteorShower.meteorHit(meteor, projectile.getDamage());
		projectiles.remove(projectile);
		
	}

	private boolean collisionOccurs(Projectile projectile, BasicMeteor meteor) {
		return projectile.getBounds().overlaps(meteor.getBounds());
	}

	private void removeProjectilesBeyondGameField() {
		int length = projectiles.size();

		for (int i = 0; i < length; i++) {
			if (outOfBounds(projectiles.get(i))) {
				projectiles.remove(projectiles.get(i));
				length--;
				i--;
			}
		}
	}

	private boolean outOfBounds(MoveableGameObject object) {
		float x = object.getX() + object.getBounds().radius;
		float y = object.getY() + object.getBounds().radius;

		return (x < 0 || x > WIDTH || y > HEIGHT);
	}

	public ArrayList<Projectile> getVisibleProjectiles() {
		return projectiles;
	}

	public ArrayList<BasicMeteor> getVisibleMeteors() {
		return meteorShower.getVisibleMeteors();
	}

	public float getCannonAngle() {
		return player.getCannonBarrel().getAngle();
	}
}
