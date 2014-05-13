package com.esnefedroetem.meteordefense.model;

import java.util.Collection;
import java.util.List;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;

public interface IGameModel  {

	public void newGame(City city, List<AbstractArmoryItem> selectedArmoryItems);
	
	public void update(float delta);
	
	public void shoot(float posX, float posY);
	
	public void specialWeaponSelected(int weaponNr);
	
	public Collection<Meteor> getVisibleMeteors();
	
	public Collection<Projectile> getVisibleProjectiles();

	public float getCannonAngle();
	
	public Collection<Meteor> getMeteorsToBlow();
	
	public void onBackPressed();
	
	public CannonBarrel getCannonBarrel();
	
	public City getCity();
	
	public float getWidth();
	
	public float getHeight();
	
	public int getScore();
	
	public void pause();
	
	public void resume();
	
}
