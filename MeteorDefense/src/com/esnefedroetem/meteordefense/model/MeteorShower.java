package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.meteor.BasicMeteor;
import com.esnefedroetem.meteordefense.model.meteor.FastMeteor;
import com.esnefedroetem.meteordefense.model.meteor.FireMeteor;
import com.esnefedroetem.meteordefense.model.meteor.IceMeteor;
import com.esnefedroetem.meteordefense.model.meteor.RadioactiveMeteor;
import com.esnefedroetem.meteordefense.util.Constants;
import com.esnefedroetem.meteordefense.util.Constants.ProjectileType;

public class MeteorShower {
	// Meteors flying over the screen
	private ArrayList<Meteor> visibleMeteors = new ArrayList<Meteor>();

	// Meteors not yet deployed.
	private ArrayList<Meteor> basicMeteors = new ArrayList<Meteor>();
	private ArrayList<Meteor> fireMeteors = new ArrayList<Meteor>();
	private ArrayList<Meteor> fastMeteors = new ArrayList<Meteor>();
	private ArrayList<Meteor> iceMeteors = new ArrayList<Meteor>();
	private ArrayList<Meteor> radioactiveMeteors = new ArrayList<Meteor>();

	private ArrayList<ArrayList> allStoredMeteors = new ArrayList<ArrayList>();
	private long lastMeteorSpawn;
	private int meteorSpawnRate = 2000;
	private int basicMeteor, fireMeteor, fastMeteor, iceMeteor, radioactiveMeteor;

	public MeteorShower() {
		//Used by loadService
	}

	public MeteorShower(int basicMeteor, int fireMeteor, int fastMeteor, int iceMeteor, int radioactiveMeteor) {
		//addMeteor(basicMeteor, fireMeteor, fastMeteor, iceMeteor, radioactiveMeteor);
		this.basicMeteor = basicMeteor;
		this.fireMeteor = fireMeteor;
		this.fastMeteor = fastMeteor;
		this.iceMeteor = iceMeteor;
		this.radioactiveMeteor = radioactiveMeteor;

	}

	public ArrayList<Meteor> getVisibleMeteors() {
		return visibleMeteors;
	}

	public void start() {
		lastMeteorSpawn = TimeUtils.millis();

	}

	private boolean allMeteorsDepolyed() {
		for (ArrayList<Meteor> list : allStoredMeteors) {

			if (list.size() > 0) {
				return false;
			}
		}
		return true;
	}

	public boolean gameover() {

		if (allMeteorsDepolyed() && visibleMeteors.size() <= 0)
			return true;

		return false;
	}

	public void update(float delta) {
		// Spawns a new meteor if necessary
		if (TimeUtils.timeSinceMillis(lastMeteorSpawn) > meteorSpawnRate) {
			if (!allMeteorsDepolyed()) {
				deployMeteor();
				lastMeteorSpawn = TimeUtils.millis();
			}
		}
		// update position of meteors
		// TODO: change loop to foreach
		for (int i = 0; i < visibleMeteors.size(); i++) {
			visibleMeteors.get(i).move(delta);
		}

	}

	// TODO: make me do something useful
	private Meteor getRandomElement() {
		ArrayList<Meteor> templist = null;
		if (!allMeteorsDepolyed())

			do {
				templist = allStoredMeteors.get((int) (Math.random() * allStoredMeteors.size()));
				
			} while (templist.size() <= 0);

		return templist.remove(0);

	}

	private void deployMeteor() {
		visibleMeteors.add(getRandomElement());

	}

	private void addMeteor(int basicMeteorAmount, int fireMeteorAmount, int fastMeteorAmount, int iceMeteorAmount,
			int radioactiveMeteorAmount) {
		for (int i = 0; i < basicMeteorAmount; i++) {
			basicMeteors.add(new BasicMeteor(randomStartPos(Constants.BASE_METEOR_SIZE)));
		}
		for (int i = 0; i < fireMeteorAmount; i++) {
			fireMeteors.add(new FireMeteor(randomStartPos(Constants.BASE_METEOR_SIZE)));
		}
		for (int i = 0; i < fastMeteorAmount; i++) {
			fastMeteors.add(new FastMeteor(randomStartPos(Constants.BASE_METEOR_SIZE)));
		}
		for (int i = 0; i < iceMeteorAmount; i++) {
			iceMeteors.add(new IceMeteor(randomStartPos(Constants.BASE_METEOR_SIZE)));
		}
		for (int i = 0; i < radioactiveMeteorAmount; i++) {
			radioactiveMeteors.add(new RadioactiveMeteor(randomStartPos(Constants.BASE_METEOR_SIZE * 2)));
		}

		allStoredMeteors.add(basicMeteors);
		allStoredMeteors.add(fireMeteors);
		allStoredMeteors.add(fastMeteors);
		allStoredMeteors.add(iceMeteors);
		allStoredMeteors.add(radioactiveMeteors);
	}

	/**
	 * A Meteor in the shower has been hit.
	 * 
	 * @param meteor
	 * @param damage
	 * @param projectileType
	 * 
	 */
	public void meteorHit(Meteor meteor, int damage, ProjectileType projectileType) {
		if (meteor.willSurvive(damage)) {
			meteor.hit(damage, projectileType);
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
	
	public void unLoadMeteors(){
		visibleMeteors.clear();
		basicMeteors.clear();
		fireMeteors.clear();
		fastMeteors.clear();
		iceMeteors.clear();
		radioactiveMeteors.clear();
		allStoredMeteors.clear();
		lastMeteorSpawn = 0;
	}
	
	public void loadMeteors(){
		unLoadMeteors();
		addMeteor(basicMeteor, fireMeteor, fastMeteor, iceMeteor, radioactiveMeteor);
	}
}
