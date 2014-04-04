package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.util.Constants;
import com.esnefedroetem.meteordefense.util.Constants.ProjectileType;

public class Projectile extends MoveableGameObject {
	private ProjectileType projectileType;

	public Projectile(float angle) {
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE,
				Constants.DEFAULT_PROJECTILE_SPEED, Constants.DEFAULT_PROJECTILE_SPAWN);
		projectileType = ProjectileType.NONE;
	}

	public Projectile(float angle, Vector2 startPosition) {
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE,
				Constants.DEFAULT_PROJECTILE_SPEED, startPosition);
		projectileType = ProjectileType.NONE;
	}

	public Projectile(float angle, Vector2 startPosition, ProjectileType projectileType) {
		super(angle, Constants.DEFAULT_PROJECTILE_DAMAGE, Constants.DEFAULT_PROJECTILE_SIZE,
				Constants.DEFAULT_PROJECTILE_SPEED, startPosition);
		this.projectileType = projectileType;
	}

	public Projectile(float angle, int damage, float size, ProjectileType projectileType) {
		super(angle, damage, Constants.DEFAULT_PROJECTILE_SIZE, Constants.DEFAULT_PROJECTILE_SPEED,
				Constants.DEFAULT_PROJECTILE_SPAWN);
		this.projectileType = projectileType;
	}

	public Projectile(float angle, int damage, float size, float speed, Vector2 spawn, ProjectileType projectileType) {
		super(angle, damage, size, speed, spawn);
		this.projectileType = projectileType;
	}

	public ProjectileType getProjectileType() {
		return projectileType;
	}
}
