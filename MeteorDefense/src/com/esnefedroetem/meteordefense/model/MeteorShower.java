package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.util.Constants;

public class MeteorShower {
	// Meteors flying over the screen
	private ArrayList<BasicMeteor> visibleMeteors = new ArrayList<BasicMeteor>();
	// Meteors not yet deployed.
	private ArrayList<BasicMeteor> invisibleMeteors = new ArrayList<BasicMeteor>();
	private long lastMeteorSpawn;
	private int meteorSpawnRate = 2000;

	public MeteorShower() {
		
		// used for testing, dummy meteors.
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE)));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE)));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE)));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE)));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE)));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE)));
		
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE*2),2));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE*2),2));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE*2),2));
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE*2),2));
		
		addMeteor(new BasicMeteor(randomStartPos(Constants.DEFAULT_METEOR_SIZE*3),3));
	}

	public MeteorShower(ArrayList<BasicMeteor> meteors) {
		this.invisibleMeteors = meteors;
	}

	public ArrayList<BasicMeteor> getVisibleMeteors() {
		return visibleMeteors;
	}

	public void start() {
		lastMeteorSpawn = TimeUtils.millis();

	}
	public boolean gameover(){
		return invisibleMeteors.size()<=0 && visibleMeteors.size() <=0;
	}

	public void update(float delta) {
		if (TimeUtils.timeSinceMillis(lastMeteorSpawn) > meteorSpawnRate) {
			if (invisibleMeteors.size() > 0)
				deployMeteor();
			lastMeteorSpawn = TimeUtils.millis();
		}

		// TODO: change loop to foreach
		for (int i = 0; i < visibleMeteors.size(); i++) {
			visibleMeteors.get(i).move(delta);
		}

	}

	// TODO: make me do something useful
	private BasicMeteor getRandomElement(ArrayList<BasicMeteor> meteors) {
		if (invisibleMeteors.size() > 0)
			return meteors.remove((int)(Math.random()*meteors.size()));

		return null;

	}

	private void deployMeteor() {
		visibleMeteors.add(getRandomElement(invisibleMeteors));

	}

	/**
	 * Adds a meter to the Meteorshower, this should be used while initializing.
	 * 
	 * @param meteor
	 *            The meteor to be added to the meteorshower
	 */
	public void addMeteor(BasicMeteor meteor) {
		invisibleMeteors.add(meteor);

	}

	/**
	 * A Meteor in the shower has been hit.
	 * 
	 * @param meteor
	 * @param damage
	 */
	public void meteorHit(BasicMeteor meteor, int damage) {
		if (meteor.willSurvive(damage)) {
			meteor.hit(damage);
		} else {
			visibleMeteors.remove(meteor);
		}

	}

	/**
	 * Generates a random spawn position for a meter guaranteed not to be
	 * outside the screen.
	 * 
	 * @param sizeOfMeteor
	 * @return Position in Vector2
	 */
	private Vector2 randomStartPos(float sizeOfMeteor) {
		int randomX = (int) ((sizeOfMeteor / 2) + (Math.random() * (Constants.LOGIC_SCREEN_WIDTH - sizeOfMeteor + 1)));
		return new Vector2(randomX, Constants.LOGIC_SCREEN_HEIGHT + sizeOfMeteor / 2);

	}
}
