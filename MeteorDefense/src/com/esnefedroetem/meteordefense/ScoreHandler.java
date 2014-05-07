package com.esnefedroetem.meteordefense;

/**
 * 
 * @author Andreas Pegelow
 * 
 */

public class ScoreHandler {
	private static final int ACCURACY_CONSTANT = 500;
	private static final int REMANING_LIFE_CONSTANT = 1000;
	private int numberOfMeteorHits;
	private int numberOfProjectilesUsed;
	private int remaningLife;
	private int maxLife;
	private int meteorScore;
	private int stars;

	public ScoreHandler(int meteorHits, int numberOfProjectilesUsed, int remaningLife, int maxLife, int meteorScore) {
		this.numberOfMeteorHits = meteorHits;
		this.numberOfProjectilesUsed = numberOfProjectilesUsed;
		this.remaningLife = remaningLife;
		this.maxLife = maxLife;
		this.meteorScore = meteorScore;

	}

	public float getRemaningLifeInProcent() {
		return ((float) remaningLife) / maxLife;

	}

	public int getTotalScore() {
		int totalScore = 0;
		if (getRemaningLife() > 0) {
			totalScore = (int) (getAccuracy() * ACCURACY_CONSTANT + getRemaningLifeInProcent() * REMANING_LIFE_CONSTANT);
			totalScore += meteorScore * 100;
		}
		return totalScore;
	}

	public int getStars() {
		// TODO: make me;
		return 1;

	}

	/**
	 * 
	 * @returns the accuracy in procent
	 */
	public float getAccuracy() {
		if (numberOfProjectilesUsed == 0)
			return 0;
		return ((float) numberOfMeteorHits) / numberOfProjectilesUsed;

	}

	public int getMaxLife() {
		return maxLife;
	}

	public int getMeteorHits() {
		return numberOfMeteorHits;
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
