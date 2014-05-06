package com.esnefedroetem.meteordefense;

/**
 * 
 * @author Andreas Pegelow
 * 
 */

public class ScoreHandler {

	int meteorHits;
	int numberOfProjectilesUsed;
	int remaningLife;
	int maxLife;
	int meteorScore;
	int stars;

	public ScoreHandler(int meteorHits, int numberOfProjectilesUsed, int remaningLife, int maxLife, int meteorScore) {
		this.meteorHits = meteorHits;
		this.numberOfProjectilesUsed = numberOfProjectilesUsed;
		this.remaningLife = remaningLife;
		this.maxLife = maxLife;
		this.meteorScore = meteorScore;

	}

	public float getRemaningLifeInProcent() {
		return ((float) remaningLife) / maxLife;

	}

	public int getTotalScore() {
		int totalScore = Math.round(getAccuracy() * getRemaningLifeInProcent()* 1337);
		//TODO: Make me do something useful
		return totalScore;
	}

	public int getStars() {
		//TODO: make me;
		return 1;

	}

	public float getAccuracy() {
		if(numberOfProjectilesUsed == 0)
			return 0;
		return ((float) meteorHits )/ numberOfProjectilesUsed;

	}

	public int getMaxLife() {
		return maxLife;
	}

	public int getMeteorHits() {
		return meteorHits;
	}

	public int getNumberOfMeteors() {
		return numberOfProjectilesUsed;
	}

	public int getRemaningLife() {
		return remaningLife;
	}

	public int getMeteorScore() {
		return meteorScore;
	}

}
