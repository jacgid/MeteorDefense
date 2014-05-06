package com.esnefedroetem.meteordefense.model;

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
