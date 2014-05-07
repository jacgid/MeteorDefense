package com.esnefedroetem.meteordefense;

/**
 * 
 * @author Andreas Pegelow
 * 
 */

public class ScoreHandler {
	private static final int ACCURACY_CONSTANT = 500;
	private static final int REMANING_LIFE_CONSTANT = 100;
	private int numberOfMeteorHits;
	private int numberOfProjectilesUsed;
	private int remaningLife;
	private int maxLife;
	private int meteorScore, maxMeteorScore;
	private int stars;

	public ScoreHandler(int meteorHits, int numberOfProjectilesUsed, int remaningLife, int maxLife, int meteorScore,
			int maxMeteorScore) {
		this.numberOfMeteorHits = meteorHits;
		this.numberOfProjectilesUsed = numberOfProjectilesUsed;
		this.remaningLife = remaningLife;
		this.maxLife = maxLife;
		this.meteorScore = meteorScore;
		this.maxMeteorScore = maxMeteorScore;
		calculateMaxScore();

	}

	public float getRemaningLifeInProcent() {
		return ((float) remaningLife) / maxLife;

	}

	public int getTotalScore() {
		int totalScore = 0;
		if (getRemaningLife() > 0) {
			totalScore = (int) (getAccuracy() * ACCURACY_CONSTANT + getRemaningLifeInProcent() * REMANING_LIFE_CONSTANT);
			totalScore += meteorScore;
		}
		return totalScore;
	}

	public int getStars() {
		int score = getTotalScore();
		int maxScore = calculateMaxScore();
		if (getRemaningLife() > 0) {
			if (score > maxScore * 0.9) {
				return 3;
			} else if (score > maxScore * 0.65) {
				return 2;
			}
			return 1;
		}
		return 0;

	}

	public int calculateMaxScore() {

		return maxMeteorScore + ACCURACY_CONSTANT + REMANING_LIFE_CONSTANT;

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
