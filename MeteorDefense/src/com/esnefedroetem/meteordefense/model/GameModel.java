package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * The GameModel handles all the gamelogic.
 * 
 * @author Simon Nielsen
 * 
 */
public class GameModel implements PropertyChangeListener {

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private ArrayList<AbstractArmoryItem> selectedArmoryItems = new ArrayList<AbstractArmoryItem>();
	private CannonBarrel cannonBarrel;
	private City city;
	private Wallet wallet;
	private MeteorShower meteorShower;
	public static final float WIDTH = Constants.LOGIC_SCREEN_WIDTH;
	public static final float HEIGHT = Constants.LOGIC_SCREEN_HEIGHT;
	
	private boolean standardWeaponSelected = true; // TODO temporary solution, fix
	private AbstractArmoryItem selectedArmoryItem;
	

	private PropertyChangeSupport pcs;

	/**
	 * Initializes the GameModel.
	 */
	public GameModel(Wallet wallet) {
		pcs = new PropertyChangeSupport(this);
		this.wallet = wallet;
	}
	
	public void newGame(City city){
		meteorShower = city.getMeteorShower();
		meteorShower.start();
		cannonBarrel = new CannonBarrel(); // TODO temporary solution, fix
		this.city = city;
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

	public void shoot(float X, float Y) {
		cannonBarrel.calculateAngle(X, Y);
		if (standardWeaponSelected) {
			projectiles.add(cannonBarrel.shoot());
		} else {
			selectedArmoryItem.act();
		}
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
		return cannonBarrel.getAngle();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("loadCannonBarrel")) {
			AbstractProjectileArmoryItem projectileArmoryItem = (AbstractProjectileArmoryItem) evt.getNewValue();
			projectiles.add(new Projectile(cannonBarrel.getAngle() , projectileArmoryItem.getPower(), projectileArmoryItem.getProjectileSize()));
		
		} else if(evt.getPropertyName().equals("addCity")) {
			AbstractDefenseArmoryItem defenseArmoryItem = (AbstractDefenseArmoryItem) evt.getNewValue();
			defenseArmoryItem.execute(city);
			
		} else if(evt.getPropertyName().equals("addVisibleMeteors")) {
			AbstractEffectArmoryItem effectArmoryItem = (AbstractEffectArmoryItem) evt.getNewValue();
			effectArmoryItem.execute(meteorShower.getVisibleMeteors());
		}
		
	}
}
