package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;

public class SplitProjectile extends Projectile {

	private final float SPLIT_DELAY = 0.3f;
	private float timeFlown;
	private ArrayList<Projectile> projectilesToAdd, projectilesToRemove;
	
	public SplitProjectile(int damage, float size, ProjectileType projectileType) {
		super(damage, size, projectileType);
		setSpeed(this.getSpeed()/4);
	}
	
	@Override
	public void move(float delta){
		super.move(delta);
		timeFlown = timeFlown + delta;
		if(timeFlown>SPLIT_DELAY){
			split();
		}
	}
	
	public void setProjectileLists(ArrayList<Projectile> projectilesToAdd, ArrayList<Projectile> projectilesToRemove){
		this.projectilesToAdd = projectilesToAdd;
		this.projectilesToRemove = projectilesToRemove;
	}
	
	private void split(){
		Projectile left = new Projectile(this.getDamage(), this.getBounds().width, ProjectileType.MISSILE_PROJECTILE);
		Projectile right = new Projectile(this.getDamage(), this.getBounds().width, ProjectileType.MISSILE_PROJECTILE);
		Projectile middle = new Projectile(this.getDamage(), this.getBounds().width, ProjectileType.MISSILE_PROJECTILE);
		left.setBounds(new Rectangle(this.getBounds()));
		right.setBounds(new Rectangle(this.getBounds()));
		middle.setBounds(new Rectangle(this.getBounds()));
		left.setAngle(this.getAngle()+(float)Math.PI/6);
		right.setAngle(this.getAngle()-(float)Math.PI/6);
		middle.setAngle(this.getAngle());
		left.setSpeed(this.getSpeed()*2);
		right.setSpeed(this.getSpeed()*2);
		middle.setSpeed(this.getSpeed()*2);
		projectilesToAdd.add(middle);
		projectilesToAdd.add(left);
		projectilesToAdd.add(right);
		projectilesToRemove.add(this);
	}

}
