package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;


public class Projectile extends MoveableGameObject {
	private ProjectileType projectile;
	
	public enum ProjectileType{STANDARD_PROJECTILE, WATER_PROJECTILE, FIRE_PROJECTILE;
	
		public static String[] getTypes(){
			return new String[]{STANDARD_PROJECTILE.toString(), WATER_PROJECTILE.toString(), FIRE_PROJECTILE.toString()};
		}
	
	}

	public Projectile(float angle) {
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE,
				Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
		projectile = ProjectileType.STANDARD_PROJECTILE;
	}

	public Projectile(float angle, Vector2 startPosition) {
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE,
				Constants.DEFAULT_PROJECTILE_SPEED, startPosition);
		projectile = ProjectileType.STANDARD_PROJECTILE;
	}

	public Projectile(float angle, Vector2 startPosition, ProjectileType projectileType) {
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE,
				Constants.DEFAULT_PROJECTILE_SPEED, startPosition);
		this.projectile = projectileType;
	}

	public Projectile(float angle, int damage, float size, Vector2 spawn, ProjectileType projectileType) {
		super(angle, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED,
				spawn);
		this.projectile = projectileType;
	}
	
	public Projectile(int damage, float size, ProjectileType projectileType) {
		super(0, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED,
				Constants.DEFAULT_PROJECTILE_SPAWN);
		this.projectile = projectileType;
	}

	public Projectile(float angle, int damage, float size, float speed, Vector2 spawn, ProjectileType projectileType) {
		super(angle, damage, size, speed, spawn);
		this.projectile = projectileType;
	}

	public ProjectileType getProjectileType() {
		return projectile;
	}
}
