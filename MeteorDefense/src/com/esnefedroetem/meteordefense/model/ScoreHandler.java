package com.esnefedroetem.meteordefense.model;

import com.esnefedroetem.meteordefense.model.meteor.Meteor;

/**A class to handle the score and score calculation for a level.
 * 
 * @author Andreas Pegelow
 * 
 */

public class ScoreHandler {
	private static final int ACCURACY_CONSTANT = 500;
	private static final int REMANING_LIFE_CONSTANT = 100;
	private int numberOfMeteorHits, numberOfProjectilesUsed;
	private int remaningLife, maxLife;
	private int meteorScore, maxMeteorScore;
	private int oldScore;
	
	public void reset(){
		this.numberOfMeteorHits = 0;
		this.numberOfProjectilesUsed = 0;
		this.remaningLife = 0;
		this.maxLife = 0;
		this.meteorScore = 0;
		this.maxMeteorScore = 0;
		this.oldScore = 0;
	}
	
	public void weaponFired(){
		numberOfProjectilesUsed++;
	}
	
	public void meteorHit(){
		numberOfMeteorHits++;
	}
	
	public void meteorDestroyed(Meteor meteor){
		meteorScore += meteor.getDifficulty();
	}
	
	public void gameOver(int remainingLife, int maxLife, int oldScore, int maxMeteorScore){
		this.remaningLife = remainingLife;
		this.maxLife = maxLife;
		this.oldScore = oldScore;
		this.maxMeteorScore = maxMeteorScore;
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
/**
 * Calculates the amount of starts you will get according to your score.
 * @return
 */
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

	public final int calculateMaxScore() {

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
	/**
	 * Calculates hoe much money you will get after you've completed a level. You will only get money if your
	 * score is better then you previous best score.
	 * @return the money you got for completing the level.
	 */
	public int getNewMoney(){
		int currentScore = getTotalScore();
		int difference = 0;
		
		if(currentScore > oldScore){
			difference = currentScore - oldScore;
			
		}
		return difference;
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

	public boolean isGameLost() {
		return remaningLife <= 0;
	}
}
