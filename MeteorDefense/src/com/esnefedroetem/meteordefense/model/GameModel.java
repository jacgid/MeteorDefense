package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractDefenseArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractProjectileArmoryItem;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * The GameModel handles all the gamelogic.
 * 
 * @author Simon Nielsen
 * 
 */
public class GameModel implements PropertyChangeListener {

	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<AbstractArmoryItem> selectedArmoryItems;
	private AbstractArmoryItem selectedArmoryItem, standardWeapon;
	private CannonBarrel cannonBarrel;
	private City city;
	private Wallet wallet;
	private MeteorShower meteorShower;
	public static final float WIDTH = Constants.LOGIC_SCREEN_WIDTH;
	public static final float HEIGHT = Constants.LOGIC_SCREEN_HEIGHT;
	private int numberOfProjectiles, meteorHits;

	private PropertyChangeSupport pcs;

	/**
	 * Initializes the GameModel.
	 */
	public GameModel(Wallet wallet, CannonBarrel cannonBarrel) {
		pcs = new PropertyChangeSupport(this);
		this.cannonBarrel = cannonBarrel;
		this.wallet = wallet;
	}

	public void newGame(City city, List<AbstractArmoryItem> selectedArmoryItems) {
		meteorShower = city.getMeteorShower();
		meteorShower.loadMeteors();
		meteorShower.start();
		this.city = city;
		this.selectedArmoryItems = selectedArmoryItems;
		standardWeapon = selectedArmoryItems.get(2);
		selectedArmoryItem = standardWeapon;
		selectedArmoryItem.removeChangeListener(this);
		selectedArmoryItem.addChangeListener(this); // TODO temporary solution,
													// fix
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

		city.update(delta);

		if (meteorShower.gameover() || city.getLife() <= 0) {
			gameover();
		}
	}

	public void shoot(float X, float Y) {
		cannonBarrel.calculateAngle(X, Y);
		selectedArmoryItem.act();
	}

	private void gameover() {
		if (city.getLife() > 0) {
			pcs.firePropertyChange("Gameover", true, calculateScore());
		} else {
			pcs.firePropertyChange("Gameover", false, calculateScore());
		}
		reset();
		
	}

	private void reset() {
		city.reset();
		projectiles.clear();
		numberOfProjectiles = 0;
		meteorHits = 0;
		cannonBarrel.reset();
	}
	
	public void toolbarAct(int buttonNr){
		System.out.println(buttonNr);
		selectedArmoryItem.removeChangeListener(this);
		selectedArmoryItem = selectedArmoryItems.get(buttonNr-1);
		selectedArmoryItem.addChangeListener(this);
		selectedArmoryItem.act();
	}

	public void addChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void collisionControll() {
		int count1 = 0;
		while (count1 < meteorShower.getVisibleMeteors().size()) {
			Meteor meteor = meteorShower.getVisibleMeteors().get(count1);
			if (collisionWithCityOccurs(meteor)) {
				city.hit(meteor);
				meteorShower.getVisibleMeteors().remove(meteor);
			} else {
				int count2 = 0;
				while (count2 < projectiles.size()) {
					Projectile projectile = projectiles.get(count2);
					if (collisionOccurs(projectile, meteor)) {
						handleMeteorCollision(projectile, meteor);
					}
					count2++;
				}
			}
			count1++;
		}
	}

	private void handleMeteorCollision(Projectile projectile, Meteor meteor) {
		meteorShower.meteorHit(meteor, projectile.getDamage(), projectile.getProjectileType());
		projectiles.remove(projectile);
		meteorHits += 1;
	}

	private boolean collisionOccurs(Projectile projectile, Meteor meteor) {
		return projectile.getBounds().overlaps(meteor.getBounds());
	}

	private boolean collisionWithCityOccurs(Meteor meteor) {
		return city.getBounds().contains(meteor.getBounds().x, meteor.getBounds().y);
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

	public List<Projectile> getVisibleProjectiles() {
		return projectiles;
	}

	public List<Meteor> getVisibleMeteors() {
		return meteorShower.getVisibleMeteors();
	}

	public float getCannonAngle() {
		return cannonBarrel.getAngle();
	}

	public Wallet getWallet() {
		return wallet;
	}

	public City getCity() {
		return city;
	}
	
	public CannonBarrel getCannonBarrel(){
		return cannonBarrel;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("loadCannonBarrel")) {
			AbstractProjectileArmoryItem projectileArmoryItem = (AbstractProjectileArmoryItem) evt.getNewValue();
			projectiles.add(new Projectile(cannonBarrel.getAngle(), projectileArmoryItem.getPower(),
					projectileArmoryItem.getProjectileSize(), cannonBarrel.getStartPosition(), projectileArmoryItem
							.getProjectileType()));
			numberOfProjectiles += 1;

		} else if (evt.getPropertyName().equals("addCity")) {
			AbstractDefenseArmoryItem defenseArmoryItem = (AbstractDefenseArmoryItem) evt.getNewValue();
			defenseArmoryItem.execute(city);

		} else if (evt.getPropertyName().equals("addVisibleMeteors")) {
			AbstractEffectArmoryItem effectArmoryItem = (AbstractEffectArmoryItem) evt.getNewValue();
			effectArmoryItem.execute(meteorShower.getVisibleMeteors());
		}
		
		selectedArmoryItem.removeChangeListener(this);
		selectedArmoryItem = standardWeapon;
		selectedArmoryItem.addChangeListener(this);
	}

	private int calculateScore() {
		float hitRating = ((float) meteorHits) / numberOfProjectiles;

		int score = Math.round(hitRating * getCity().getRemainingLife() * 1337);

		return score;

	}
}
