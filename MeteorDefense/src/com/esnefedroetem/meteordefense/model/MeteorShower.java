package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.util.Constants;

public class MeteorShower {
	// Meteors flying over the screen
	private ArrayList<Meteor> visibleMeteors = new ArrayList<Meteor>();
	// Meteors not yet deployed.
	private ArrayList<Meteor> invisibleMeteors = new ArrayList<Meteor>();
	private long lastMeteorSpawn;
	private int meteorSpawnRate = 2000;

	public MeteorShower() {
		addMeteor(new Meteor(new Vector2(50, 200)));
	}

	public MeteorShower(ArrayList<Meteor> meteors) {
		this.invisibleMeteors = meteors;
	}

	public ArrayList<Meteor> getVisibleMeteors() {
		return visibleMeteors;
	}

	public void start() {
		lastMeteorSpawn = TimeUtils.millis();

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


	private Meteor getRandomMeteor(ArrayList<Meteor> meteors) {
		if (invisibleMeteors.size() > 0)
			return meteors.remove(meteors.size() - 1);

		return null;

	}

	private void deployMeteor() {
		visibleMeteors.add(getRandomMeteor(invisibleMeteors));

	}

	/**
	 * Adds a meter to the Meteorshower, this should be used while initializing.
	 * 
	 * @param meteor
	 *            The meteor to be added to the meteorshower
	 */
	public void addMeteor(Meteor meteor) {
		invisibleMeteors.add(meteor);

		// used for testing, dummy meteors.
		for (int i = 0; i < 10; i++) {
			invisibleMeteors.add(new Meteor(new Vector2((int) (Math.random() * (Constants.LOGIC_SCREEN_WIDTH + 1)),
					Constants.LOGIC_SCREEN_HEIGHT + 5)));
		}
	}

}
