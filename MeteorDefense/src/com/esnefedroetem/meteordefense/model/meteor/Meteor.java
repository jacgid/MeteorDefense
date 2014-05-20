package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.MoveableGameObject;
import com.esnefedroetem.meteordefense.model.Projectile;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;

/**
 * 
 * @author Andreas Pegelow
 * 
 */
public abstract class Meteor extends MoveableGameObject {

	private int life, startLife;
	private int difficulty = 1;

	public enum MeteorType {
		BASIC_METEOR, RADIOACTIVE_METEOR, FIRE_METEOR, FAST_METEOR, ICE_METEOR;
		
		public static String[] getTypes(){
			return new String[]{BASIC_METEOR.toString(), RADIOACTIVE_METEOR.toString(), FIRE_METEOR.toString(), FAST_METEOR.toString(), ICE_METEOR.toString()};
		}
		
	}

	public Meteor() {

	}

	public Meteor(Vector2 startPosition, float angle, int life, int damage, float size, float speed) {
		super(angle, damage, size, speed, startPosition);
		this.life = life;
		startLife = life;
		calculateDifficulty();
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	public int getStartLife() {
		return startLife;
	}

	/**
	 * Called when the meteor is hit, when creating new types of meteors this
	 * should be implemented to get different behavior
	 * 
	 * @param damage
	 */
	public abstract void hit(int damage, Projectile.ProjectileType projectileType);


	public void decreaseHealth(int value) {
		life -= value;
	}

	/**
	 * Checks if the meteor will survive a hit with the specified damage
	 * 
	 * @param damage
	 * @return true if it will survive, false otherwise
	 */
	public boolean willSurvive(int damage) {
		return life - damage > 0;
	}

	public abstract MeteorType getType();

	public final void calculateDifficulty() {
		difficulty = (int) (getDamage() * 0.7 + startLife * 0.01 + startLife * 0.7)*10;

	}

	public int getDifficulty() {
		return difficulty;
	}

	public void update(float delta){
		
	}
}
