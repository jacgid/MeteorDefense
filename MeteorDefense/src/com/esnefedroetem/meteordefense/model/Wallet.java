package com.esnefedroetem.meteordefense.model;

/**
 * The Wallet holds and handles the players coins and is saved
 * as persistent data between sessions.
 * @author Emma Lindholm
 *
 */
public class Wallet {
	
	private int coins = 0;
	
	public void addCoins(int amount){
		coins += amount;
	}
	
	public void removeCoins(int amount) {
		coins -= amount;
	}
	
	public boolean canAfford(int price){
		return coins >= price;
	}
	
	public int getAssets() {
		return coins;
	}
	
	
}
