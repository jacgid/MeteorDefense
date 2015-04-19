package com.esnefedroetem.meteordefense.model.meteor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.util.Constants;

public class HungryMeteor extends Meteor {

	private static final int DAMAGE = 5;
	private static final float SPEED = Constants.BASE_METEOR_SPEED;
	private static final int LIFE = 10;
	private static final float SIZE = Constants.BASE_METEOR_SIZE;
	private int damageEaten, damageToExplode = LIFE;
	private float newSize = SIZE;
	private Rectangle bounds;
	
	public HungryMeteor(){
		
	}
	
	public HungryMeteor(Vector2 startPosition) {
		super(startPosition, Constants.BASE_METEOR_ANGLE, LIFE, DAMAGE, SIZE, SPEED);
		bounds = new Rectangle(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
	}
	
	@Override
	public void hit(int damage, ProjectileType projectileType) {
		System.out.println("HIT! Damage: " + damage);
		damageEaten = damageEaten + damage;
		System.out.println(newSize);
		newSize = newSize + newSize*(float)(((double)damage/(double)LIFE)*2);
		System.out.println(newSize);
		bounds.setSize(newSize);
		setBounds(bounds);
		setDamage(DAMAGE+damageEaten);
		if(damageEaten>damageToExplode){
			decreaseHealth(damageEaten);
		}
	}

	@Override
	public MeteorType getType() {
		return MeteorType.HUNGRY_METEOR;
	}

}
