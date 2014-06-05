package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.model.Projectile.ProjectileType;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.meteor.BasicMeteor;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * GameModel handles all the gamelogic, implements IGameModel.
 * 
 * @author Emma Lindholm
 * @revised Jacob Gideflod, Simon Nielsen, Andreas Pegelow
 * 
 */
public class GameModel implements IGameModel {

	private Collection<Projectile> projectiles, projectilesToAdd, projectilesToRemove;
	private Collection<Meteor> meteorsToBlow;
	private List<AbstractArmoryItem> armoryItems;
	private AbstractArmoryItem selectedArmoryItem, standardWeapon;
	private final CannonBarrel CANNON_BARREL;
	private City city;
	private MeteorShower meteorShower;
	private final ScoreHandler SCORE_HANDLER;
	private PropertyChangeSupport pcs;
	private IArmoryItemVisitor visitor;
	private boolean isPaused;

	private final float WIDTH = Constants.LOGIC_SCREEN_WIDTH, HEIGHT = Constants.LOGIC_SCREEN_HEIGHT;

	public GameModel(PropertyChangeListener listener, CannonBarrel cannonBarrel) {
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		this.CANNON_BARREL = cannonBarrel;
		projectiles = new ArrayList<Projectile>();
		meteorsToBlow = new ArrayList<Meteor>();
		SCORE_HANDLER = new ScoreHandler();
	}

	@Override
	public void newGame(City city, List<AbstractArmoryItem> selectedArmoryItems, IArmoryItemVisitor armoryItemVisitor, List<Projectile> projectilesToAdd, List<Projectile> projectilesToRemove) {
		this.city = city;
		this.armoryItems = selectedArmoryItems;
		this.projectilesToAdd = projectilesToAdd;
		this.projectilesToRemove = projectilesToRemove;
		standardWeapon = selectedArmoryItems.get(2);
		selectedArmoryItem = standardWeapon;
		SCORE_HANDLER.reset();
		meteorShower = city.getMeteorShower();
		meteorShower.loadMeteors();
		meteorShower.start();
		this.visitor = armoryItemVisitor;
		CANNON_BARREL.load(standardWeapon.accept(visitor));
		isPaused = false;
	}

	@Override
	public void update(float delta) {
		if (!isPaused) {
			meteorShower.update(delta);
			System.out.println("Move projectiles " + projectiles.size());
			for (Projectile p : projectiles) {
				p.move(delta);
//				if(p instanceof SplitProjectile || p.getProjectileType() == ProjectileType.MISSILE_PROJECTILE)
//				System.out.println("Projectile speed: " + p.getSpeed() + " " +  p.getAngle());
			}
			System.out.println("Moved projectiles");
			
			projectiles.addAll(projectilesToAdd);
			projectiles.removeAll(projectilesToRemove);
			projectilesToAdd.clear();
			projectilesToRemove.clear();

			collisionControll();
			removeProjectilesBeyondGameField();
			removeMeteorsBeyondGameField();

			city.update(delta);

			if (meteorShower.gameover() || city.getLife() <= 0) {
				gameover();
			}
		} else {
			// the milliseconds that the game is paused is added to the weapons
			// cooldowns to prevent them from cooling down during pause
			for (AbstractArmoryItem item : armoryItems) {
				item.increaseRemainingCooldown((long)(delta * 1000));
			}

		}

	}

	@Override
	public void shoot(float posX, float posY) {
		
//		for(AbstractArmoryItem items : armoryItems){
//			items.update(0);
//		}
		
		CANNON_BARREL.calculateAngle(posX, posY);
		
		// if the projectile which CANNON_BARREL is loaded with is not yet added to
		// the projectiles list (in other words already deployed) a shot should be fired
		if (!projectiles.contains(CANNON_BARREL.deploy())) {
			projectiles.add(CANNON_BARREL.deploy());
			System.out.println("Deploy! Angle: " + CANNON_BARREL.deploy().getAngle());
			SCORE_HANDLER.weaponFired();
		}
		
		Projectile projectile = standardWeapon.accept(visitor);
		if (projectile != null) {
			CANNON_BARREL.load(projectile);
		}
		
	}

	@Override
	public void specialWeaponSelected(int weaponNr) {
		selectedArmoryItem = armoryItems.get(weaponNr - 1);

		Projectile projectile = selectedArmoryItem.accept(visitor);
		if (projectile != null) {
			CANNON_BARREL.load(projectile);
			System.out.println("Loading!");
		}
	}

	@Override
	public Collection<Meteor> getVisibleMeteors() {
		return meteorShower.getVisibleMeteors();
	}

	@Override
	public Collection<Projectile> getVisibleProjectiles() {
		return projectiles;
	}

	@Override
	public float getCannonAngle() {
		return CANNON_BARREL.getAngle();
	}

	@Override
	public Collection<Meteor> getMeteorsToBlow() {
		return meteorsToBlow;
	}

	@Override
	public void onBackPressed() {
		if (isPaused) {
			pcs.firePropertyChange("Quit Game", false, true);
			reset();
		} else {
			isPaused = true;
		}
	}

	@Override
	public CannonBarrel getCannonBarrel() {
		return CANNON_BARREL;
	}

	@Override
	public City getCity() {
		return city;
	}

	@Override
	public float getWidth() {
		return WIDTH;
	}

	@Override
	public float getHeight() {
		return HEIGHT;
	}

	@Override
	public int getScore() {
		return SCORE_HANDLER.getMeteorScore();
	}

	private void collisionControll() {
		Collection<Meteor> meteorsToRemove = new ArrayList<Meteor>();
		Collection<Projectile> projectilesToRemove = new ArrayList<Projectile>();
		for (Meteor meteor : meteorShower.getVisibleMeteors()) {
			if (collisionWithCityOccurs(meteor)) {
				city.hit(meteor);
				meteorsToRemove.add(meteor);
			} else {
				for (Projectile projectile : projectiles) {
					if (collisionOccurs(projectile, meteor)) {
						handleMeteorCollision(projectile, meteor,
								meteorsToRemove);
						projectilesToRemove.add(projectile);
					}

				}

			}

		}
		meteorShower.getVisibleMeteors().removeAll(meteorsToRemove);
		projectiles.removeAll(projectilesToRemove);
	}

	private void handleMeteorCollision(Projectile projectile, Meteor meteor,
			Collection<Meteor> meteorsToRemove) {
		boolean isKilled = meteorShower.meteorHit(meteor,
				projectile.getDamage(), projectile.getProjectileType());
		SCORE_HANDLER.meteorHit();

		if (isKilled) {
			meteorsToRemove.add(meteor);
			SCORE_HANDLER.meteorDestroyed(meteor);
			meteorsToBlow.add(meteor);
		}
	}

	private boolean collisionOccurs(Projectile projectile, Meteor meteor) {
		return Intersector.overlaps(meteor.getBounds(), projectile.getBounds());
	}

	private boolean collisionWithCityOccurs(Meteor meteor) {
		return city.getBounds().contains(meteor.getBounds().x,
				meteor.getBounds().y);
	}

	private void removeProjectilesBeyondGameField() {
		Collection<Projectile> projectilesToRemove = new ArrayList<Projectile>();
		for (Projectile projectile : projectiles) {
			if (outOfBounds(projectile)) {
				projectilesToRemove.add(projectile);
			}
		}
		projectiles.removeAll(projectilesToRemove);
	}

	private void removeMeteorsBeyondGameField() {
		Collection<Meteor> meteorsToRemove = new ArrayList<Meteor>();
		for (Meteor meteor : meteorShower.getVisibleMeteors()) {
			// To avoid removal of recently spawned meteors just "above" the screen
			// a temporary meteor is created below the actual meteor. Only meteors far
			// "above" is then removed, without need of another outOfBounds() method.
			Meteor temp = new BasicMeteor();
			temp.setBounds(new Rectangle(meteor.getX(), meteor.getY() - 250,
					meteor.getBounds().width, meteor.getBounds().height));
			if (outOfBounds(temp)) {
				meteorsToRemove.add(meteor);

				SCORE_HANDLER.meteorDestroyed(meteor);
			}

		}
		meteorShower.getVisibleMeteors().removeAll(meteorsToRemove);
	}

	private boolean outOfBounds(MoveableGameObject object) {
		float x = object.getBounds().x + object.getBounds().width;
		float y = object.getBounds().y + object.getBounds().height;

		return x < 0 || x > Constants.LOGIC_SCREEN_WIDTH
				|| y > Constants.LOGIC_SCREEN_HEIGHT;
	}

	private void gameover() {
		SCORE_HANDLER.gameOver(city.getLife(), city.getMaxLife(),
				city.getHighScore(), meteorShower.getMaxScore());
		city.setScore(SCORE_HANDLER.getTotalScore());
		city.setStars(SCORE_HANDLER.getStars());

		pcs.firePropertyChange("Gameover", city, SCORE_HANDLER);
		reset();

	}

	private void reset() {
		city.reset();
		projectiles.clear();
		CANNON_BARREL.reset();

		for (AbstractArmoryItem item : armoryItems) {
			item.resetLastUsed();
		}
	}

	@Override
	public void pause() {
		isPaused = true;
	}

	@Override
	public void resume() {
		isPaused = false;
	}

	public boolean isPaused() {
		return isPaused;
	}

	@Override
	public List<AbstractArmoryItem> getSelectedArmoryItems() {
		return armoryItems;
	}

}
