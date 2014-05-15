package com.esnefedroetem.meteordefense.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.meteor.BasicMeteor;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * The GameModel handles all the gamelogic.
 * 
 * @author Simon Nielsen
 * 
 */
public class GameModel implements IGameModel {
	
	private Collection<Projectile> projectiles;
	private Collection<Meteor> meteorsToBlow;
	private List<AbstractArmoryItem> armoryItems;
	private AbstractArmoryItem selectedArmoryItem, standardWeapon;
	private CannonBarrel cannonBarrel;
	private City city;
	private MeteorShower meteorShower;
	private ScoreHandler scoreHandler;
	private PropertyChangeSupport pcs;
	private ArmoryItemVisitor visitor;
	private boolean isPaused;
	private long pauseTime;
	
	private final float width, height;
	
	
	public GameModel(PropertyChangeListener listener, CannonBarrel cannonBarrel){
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		this.cannonBarrel = cannonBarrel;
		projectiles = new ArrayList<Projectile>();
		meteorsToBlow = new ArrayList<Meteor>();
		scoreHandler = new ScoreHandler();
		width = 720;
		height = 1280;
	}
	
	
	
	
	@Override
	public void newGame(City city, List<AbstractArmoryItem> selectedArmoryItems) {
		this.city = city;
		this.armoryItems = selectedArmoryItems;
		standardWeapon = selectedArmoryItems.get(2);
		selectedArmoryItem = standardWeapon;
		scoreHandler.reset();
		meteorShower = city.getMeteorShower();
		meteorShower.loadMeteors();
		meteorShower.start();
		this.visitor = new ArmoryItemVisitor(this.city, this.meteorShower);
		cannonBarrel.load(standardWeapon.accept(visitor));
		isPaused = false;
	}

	@Override
	public void update(float delta) {
		if(!isPaused) {
		meteorShower.update(delta);
		for (Projectile p : projectiles) {
			p.move(delta);
		}

		collisionControll();
		removeProjectilesBeyondGameField();
		removeMeteorsBeyondGameField();

		city.update(delta);

		if (meteorShower.gameover() || city.getLife() <= 0) {
			gameover();
		}
		}

	}

	@Override
	public void shoot(float posX, float posY) {
		cannonBarrel.calculateAngle(posX, posY);
		projectiles.add(cannonBarrel.deploy());
		scoreHandler.weaponFired();

		Projectile projectile = standardWeapon.accept(visitor);
		if (projectile != null) {
			cannonBarrel.load(projectile);
		}
	}

	@Override
	public void specialWeaponSelected(int weaponNr) {
		selectedArmoryItem = armoryItems.get(weaponNr - 1);

		Projectile projectile = selectedArmoryItem.accept(visitor);
		if (projectile != null) {
			cannonBarrel.load(projectile);
		}
	}

	@Override
	public Collection<Meteor> getVisibleMeteors() {
		return meteorShower.getVisibleMeteors();
	}
	
	@Override
	public Collection<Projectile> getVisibleProjectiles(){
		return projectiles;
	}

	@Override
	public float getCannonAngle() {
		return cannonBarrel.getAngle();
	}

	@Override
	public Collection<Meteor> getMeteorsToBlow() {
		return meteorsToBlow;
	}
	
	@Override
	public void onBackPressed(){
		if(isPaused){
			pcs.firePropertyChange("Quit Game", false, true);
			reset();
		}else{
			isPaused = true;
		}
	}
	
	@Override
	public CannonBarrel getCannonBarrel() {
		return cannonBarrel;
	}


	@Override
	public City getCity() {
		return city;
	}




	@Override
	public float getWidth() {
		return width;
	}




	@Override
	public float getHeight() {
		return height;
	}




	@Override
	public int getScore() {
		return scoreHandler.getMeteorScore();
	}

	
	
	private void collisionControll() {
		Collection<Meteor> meteorsToRemove = new ArrayList<Meteor>();
		Collection<Projectile> projectilesToRemove = new ArrayList<Projectile>();
		for(Meteor meteor : meteorShower.getVisibleMeteors()){
			if (collisionWithCityOccurs(meteor)) {
				city.hit(meteor);
				meteorsToRemove.add(meteor);
			} else {
				for(Projectile projectile : projectiles){
					if (collisionOccurs(projectile, meteor)) {
						handleMeteorCollision(projectile, meteor, meteorsToRemove, projectilesToRemove);
						projectilesToRemove.add(projectile);
					}
					
				}
				
			}
			
		}
		meteorShower.getVisibleMeteors().removeAll(meteorsToRemove);
		projectiles.removeAll(projectilesToRemove);
	}

	private void handleMeteorCollision(Projectile projectile, Meteor meteor, Collection<Meteor> meteorsToRemove, Collection<Projectile> projectilesToRemove) {
		boolean isKilled = meteorShower.meteorHit(meteor, projectile.getDamage(), projectile.getProjectileType());
		projectilesToRemove.add(projectile);
		scoreHandler.meteorHit();
		
		if (isKilled) {
			meteorsToRemove.add(meteor);
			scoreHandler.meteorDestroyed(meteor);
			meteorsToBlow.add(meteor);
		}
	}

	private boolean collisionOccurs(Projectile projectile, Meteor meteor) {
		return Intersector.overlaps(meteor.getBounds(), projectile.getBounds());
		//return projectile.getBounds().overlaps(meteor.getBounds());
	}

	private boolean collisionWithCityOccurs(Meteor meteor) {
		return city.getBounds().contains(meteor.getBounds().x, meteor.getBounds().y);
	}

	private void removeProjectilesBeyondGameField() {
		Collection<Projectile> projectilesToRemove = new ArrayList<Projectile>();
		for(Projectile projectile : projectiles){
			if(outOfBounds(projectile)){
				projectilesToRemove.add(projectile);
			}
		}
		projectiles.removeAll(projectilesToRemove);
	}
	
	private void removeMeteorsBeyondGameField() {
		Collection<Meteor> meteorsToRemove = new ArrayList<Meteor>();
		for(Meteor meteor : meteorShower.getVisibleMeteors()){
			Meteor temp = new BasicMeteor();
			temp.setBounds(new Rectangle(meteor.getX(), meteor.getY() - 250, meteor.getBounds().width, meteor.getBounds().height));
			if(outOfBounds(temp)) {
				meteorsToRemove.add(meteor);
				scoreHandler.meteorHit();
				scoreHandler.meteorDestroyed(meteor);
			}
			
		}
		meteorShower.getVisibleMeteors().removeAll(meteorsToRemove);
	}

	private boolean outOfBounds(MoveableGameObject object) {
		float x = object.getBounds().x + object.getBounds().width;
		float y = object.getBounds().y + object.getBounds().height;

		return x < 0 || x > Constants.LOGIC_SCREEN_WIDTH || y > Constants.LOGIC_SCREEN_HEIGHT;
	}
	
	private void gameover() {
		scoreHandler.gameOver(city.getLife(), city.getMaxLife(), city.getHighScore(), meteorShower.getMaxScore());
		city.setScore(scoreHandler.getTotalScore());
		city.setStars(scoreHandler.getStars());

		pcs.firePropertyChange("Gameover", city, scoreHandler);
		reset();

	}
	
	private void reset() {
		city.reset();
		projectiles.clear();
		cannonBarrel.reset();
		
		for(AbstractArmoryItem item : armoryItems) {
			item.resetLastUsed();
		}
	}

	@Override
	public void pause() {
		isPaused = true;
		pauseTime = TimeUtils.millis();
	}

	@Override
	public void resume() {
		isPaused = false;
		
		// the milliseconds that the game was paused is added to the weapons cooldowns
		// to prevent them from cooling down during pause
		for(AbstractArmoryItem item : armoryItems) {
			item.increaseRemainingCooldown(TimeUtils.timeSinceMillis(pauseTime));
		}
		
	}
	
	public boolean isPaused(){
		return isPaused;
	}

}
