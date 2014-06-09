package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.meteor.BasicMeteor;
import com.esnefedroetem.meteordefense.model.meteor.ElectromagneticMeteor;
import com.esnefedroetem.meteordefense.model.meteor.FastMeteor;
import com.esnefedroetem.meteordefense.model.meteor.FireMeteor;
import com.esnefedroetem.meteordefense.model.meteor.IceMeteor;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.model.meteor.RadioactiveMeteor;
import com.esnefedroetem.meteordefense.model.meteor.Meteor.MeteorType;
import com.esnefedroetem.meteordefense.util.Constants;

/**This is a class for handling a specific set of different meteors.
 * It has a list of meteors for every meteor type. They are then later put in a list which then contains all the
 * meteors to make handling them a bit easier. It keeps track when its time to deploy a meteor to go flying.
 * When it is, it gets added to a list called visible meteors which other then can call for to do collision testing
 * and such.
 * 
 * @author Andreas Pegelow
 *
 */
public class MeteorShower {
	// Meteors flying over the screen
	private List<Meteor> visibleMeteors = new ArrayList<Meteor>();

	// Meteors not yet deployed.
	private List<Meteor> basicMeteors = new ArrayList<Meteor>();
	private List<Meteor> fireMeteors = new ArrayList<Meteor>();
	private List<Meteor> fastMeteors = new ArrayList<Meteor>();
	private List<Meteor> iceMeteors = new ArrayList<Meteor>();
	private List<Meteor> radioactiveMeteors = new ArrayList<Meteor>();
	private List<Meteor> electromagneticMeteors = new ArrayList<Meteor>();

	private List<List<Meteor>> allStoredMeteors = new ArrayList<List<Meteor>>();
	private long lastMeteorSpawn;
	private int meteorSpawnRate, totalMeteorCount, lastSpawnRate;
	private double originalSpawnRate = 1500;
	private int basicMeteor, fireMeteor, fastMeteor, iceMeteor, radioactiveMeteor, electromagneticMeteor, maxScore;
	private List<MeteorType> meteortypes = new ArrayList<MeteorType>();

	public MeteorShower() {
		// Used by loadService
	}

	public MeteorShower(int basicMeteor, int fireMeteor, int fastMeteor, int iceMeteor, int radioactiveMeteor, int electromagneticMeteor) {
		this.basicMeteor = basicMeteor;
		this.fireMeteor = fireMeteor;
		this.fastMeteor = fastMeteor;
		this.iceMeteor = iceMeteor;
		this.radioactiveMeteor = radioactiveMeteor;
		this.electromagneticMeteor = electromagneticMeteor;

		totalMeteorCount = basicMeteor + fireMeteor + fastMeteor + iceMeteor + radioactiveMeteor;
		meteorSpawnRate = (int) originalSpawnRate;
	}

	public List<Meteor> getVisibleMeteors() {
		return visibleMeteors;
	}

	public void start() {
		lastMeteorSpawn = TimeUtils.millis();

	}

	private void calculateMaxScore() {

		for (List<Meteor> list : allStoredMeteors) {
			for (Meteor temp : list) {
				maxScore += temp.getDifficulty();
			}
		}
	}

	public int getMaxScore() {
		return maxScore;
	}

	private boolean allMeteorsDeployed() {
		for (List<Meteor> list : allStoredMeteors) {

			if (list.size() > 0) {
				return false;
			}
		}
		return true;
	}

	public boolean gameover() {

		if (allMeteorsDeployed() && visibleMeteors.size() <= 0)
			return true;

		return false;
	}

	public void update(float delta) {
		// Spawns a new meteor if necessary
		if (TimeUtils.timeSinceMillis(lastMeteorSpawn) > meteorSpawnRate) {
			if (!allMeteorsDeployed()) {
				calculateSpawnRate();
				deployMeteor();
				lastMeteorSpawn = TimeUtils.millis();
			}
		}
		// update position of meteors
		for (Meteor meteor : visibleMeteors) {
			meteor.move(delta);
		}

	}

	private void calculateSpawnRate() {

		if (lastSpawnRate > (meteorSpawnRate * 2)) {
			meteorSpawnRate = (int) (lastSpawnRate * (1 - 1 / (double) totalMeteorCount));
		}
		lastSpawnRate = meteorSpawnRate;
		meteorSpawnRate = (int) (meteorSpawnRate * (1 - (0.5 / (double) totalMeteorCount)));

		double randomSpawn = (0.8 + (meteorSpawnRate / ((originalSpawnRate / 0.15) * ((originalSpawnRate * 0.9) / meteorSpawnRate))));
		if (Math.random() > randomSpawn) {
			meteorSpawnRate = meteorSpawnRate / 2;
		}

	}

	private Meteor getRandomElement() {
		List<Meteor> templist = null;
		Meteor randomMeteor = new BasicMeteor();
		if (!allMeteorsDeployed()) {

			do {
				templist = allStoredMeteors.get((int) (Math.random() * allStoredMeteors.size()));

			} while (templist.size() <= 0);
			randomMeteor = templist.remove(0);
		}

		return randomMeteor;

	}

	private void deployMeteor() {
		visibleMeteors.add(getRandomElement());

	}

	/**
	 * Adds the specified amount of new meteors to the individual meteor lists
	 * and then adds the all the list to a common list.
	 * 
	 * @param basicMeteorAmount
	 * @param fireMeteorAmount
	 * @param fastMeteorAmount
	 * @param iceMeteorAmount
	 * @param radioactiveMeteorAmount
	 */
	private void addMeteor(int basicMeteorAmount, int fireMeteorAmount, int fastMeteorAmount, int iceMeteorAmount,
			int radioactiveMeteorAmount, int electromagneticMeteorAmount) {
		if (basicMeteorAmount > 0) {
			meteortypes.add(MeteorType.BASIC_METEOR);
		}
		if (fireMeteorAmount > 0) {
			meteortypes.add(MeteorType.FIRE_METEOR);
		}
		if (fastMeteorAmount > 0) {
			meteortypes.add(MeteorType.FAST_METEOR);
		}
		if (iceMeteorAmount > 0) {
			meteortypes.add(MeteorType.ICE_METEOR);
		}
		if (radioactiveMeteorAmount > 0) {
			meteortypes.add(MeteorType.RADIOACTIVE_METEOR);
		}
		if (electromagneticMeteorAmount > 0) {
			meteortypes.add(MeteorType.ELECTROMAGNETIC_METEOR);
		}
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
		for (int i = 0; i < electromagneticMeteorAmount; i++) {
			electromagneticMeteors.add(new ElectromagneticMeteor(new Vector2((Constants.LOGIC_SCREEN_WIDTH-(Constants.BASE_METEOR_SIZE/2))/2, Constants.LOGIC_SCREEN_HEIGHT+1)));
		}

		allStoredMeteors.add(basicMeteors);
		allStoredMeteors.add(fireMeteors);
		allStoredMeteors.add(fastMeteors);
		allStoredMeteors.add(iceMeteors);
		allStoredMeteors.add(radioactiveMeteors);
		allStoredMeteors.add(electromagneticMeteors);
	}

	/**
	 * A Meteor in the shower has been hit and is told to react.
	 * 
	 * @param meteor
	 * @param damage
	 * @param projectileType
	 * 
	 */
	public boolean meteorHit(Meteor meteor, int damage, Projectile.ProjectileType projectileType) {
		if (meteor.willSurvive(damage)) {
			meteor.hit(damage, projectileType);
			return false;
		} else {
			return true;
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
		int randomX = (int) (Math.random() * (Constants.LOGIC_SCREEN_WIDTH - sizeOfMeteor + 1));
		return new Vector2(randomX, Constants.LOGIC_SCREEN_HEIGHT + 1);

	}

	public void unLoadMeteors() {
		visibleMeteors.clear();
		basicMeteors.clear();
		fireMeteors.clear();
		fastMeteors.clear();
		iceMeteors.clear();
		radioactiveMeteors.clear();
		electromagneticMeteors.clear();
		allStoredMeteors.clear();
		lastMeteorSpawn = 0;
		maxScore = 0;
	}

	public void loadMeteors() {
		unLoadMeteors();
		addMeteor(basicMeteor, fireMeteor, fastMeteor, iceMeteor, radioactiveMeteor, electromagneticMeteor);
		calculateMaxScore();
	}

	public List<MeteorType> getMeteorTypes() {
		return meteortypes;
	}

	public void setSpawnrate(int spawnrate) {
		originalSpawnRate = spawnrate;
	}
}
