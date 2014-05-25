package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.model.meteor.RadioactiveMeteor;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * City represents a city in the game. It has two states, locked and unlocked
 * and contains a meterShower among other variables.
 * 
 * @author Andreas Pegelow
 * 
 */
public class City {

	public enum State {
		LOCKED, UNLOCKED;
	}

	private State state;
	private String name;
	private int currentLife, maxLife;
	private int stars = 0;
	private int score = 0;
	private Rectangle bounds = Constants.CITY_BOUNDS;
	private MeteorShower meteorShower;
	private List<RadioactiveMeteor> radioactiveMeteors = new ArrayList<RadioactiveMeteor>();

	public City(String name, int life, MeteorShower meteorShower, State state) {
		this.name = name;
		this.currentLife = life;
		maxLife = life;
		this.meteorShower = meteorShower;
		this.state = state;
	}

	public City() {
		// Used by loadService
	}

	public String getName() {
		return name;
	}

	public int getLife() {
		return currentLife;
	}

	public MeteorShower getMeteorShower() {
		return meteorShower;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void reset() {
		meteorShower.unLoadMeteors();
		radioactiveMeteors.clear();
		currentLife = maxLife;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * implements what will happen when the city is hit by a meteor.
	 * 
	 * @param meter
	 *            , the meteor to hit the city with.
	 */
	public void hit(Meteor meteor) {
		// make sure the life never goes below 0
		currentLife -= meteor.getDamage();
		if (currentLife < 0) {
			currentLife = 0;
		}
		// Different behavior according to the meteor type.
		if (meteor.getType() == Meteor.MeteorType.RADIOACTIVE_METEOR) {
			radioactiveMeteors.add((RadioactiveMeteor) meteor);
		}
	}

	public void update(float delta) {
		for (int i = 0; i < radioactiveMeteors.size(); i++) {
			radioactiveMeteors.get(i).update(delta);
			currentLife -= radioactiveMeteors.get(i).getDot();

			if (radioactiveMeteors.get(i).isDecade()) {
				radioactiveMeteors.remove(i);
			}
		}

	}

	/**
	 * 
	 * @returns the remaining life in percent
	 */
	public float getRemainingLife() {
		return ((float) getLife()) / maxLife;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public int getStars() {
		return stars;
	}

	public int getMaxScore() {
		return score;
	}

	/**
	 * Sets the amount of stars if its more than the value already set.
	 * 
	 * @param stars
	 *            , the numer of stars
	 */
	public void setStars(int stars) {
		if (stars > this.stars)
			this.stars = stars;

	}

	/**
	 * Sets the score if its better the the score already set.
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		if (score > this.score)
			this.score = score;
	}

	public int getHighScore() {
		return score;

	}

	public List<RadioactiveMeteor> getRadioactiveMeteors() {
		return radioactiveMeteors;
	}

	public void setLevelData(int cityLife, MeteorShower meteorShower) {
		maxLife = currentLife = cityLife;
		this.meteorShower = meteorShower;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof City) {
			City city = (City) obj;
			return state.equals(city.state) && name.equals(city.name) && currentLife == city.currentLife
					&& maxLife == city.maxLife && stars == city.stars && score == city.score;
		}
		return false;

	}

	public void increaseLife(int lifeIncreasement) {
		if (currentLife + lifeIncreasement <= maxLife) {
			currentLife += lifeIncreasement;
		} else {
			currentLife = maxLife;

		}
	}
}
