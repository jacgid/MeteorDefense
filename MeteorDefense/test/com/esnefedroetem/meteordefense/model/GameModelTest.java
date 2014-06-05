package com.esnefedroetem.meteordefense.model;

import static org.junit.Assert.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.StandardArmoryItem;
import com.esnefedroetem.meteordefense.model.meteor.BasicMeteor;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.esnefedroetem.meteordefense.model.meteor.RadioactiveMeteor;
import com.esnefedroetem.meteordefense.util.Constants;

public class GameModelTest {

	
	@Test
	public void testShoot(){
		IGameModel model = new GameModel(null, new CannonBarrel());
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>(5);
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		City city = new City("", 10, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
//		model.newGame(city, items, new ArmoryItemVisitor(city, city.getMeteorShower()));
		int size1 = model.getVisibleProjectiles().size();
		model.shoot(500, 500);
		assertTrue(model.getVisibleProjectiles().size() > size1);
	}
	
	@Test
	public void testCollision(){
		IGameModel model = new GameModel(null, new CannonBarrel());
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>(5);
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		City city = new City("", 10, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
//		model.newGame(city, items, new ArmoryItemVisitor(city, city.getMeteorShower()));
		
		Meteor meteor1 = new BasicMeteor(new Vector2(500, 500));
		Projectile projectile1 = new Projectile((float)Math.PI / 2, new Vector2(501, 501));
		Meteor meteor2 = new BasicMeteor(new Vector2(100, 500));
		Projectile projectile2 = new Projectile((float)Math.PI / 2, new Vector2(50, 450));
		
		Collection<Meteor> meteors = model.getVisibleMeteors();
		Collection<Projectile> projectiles = model.getVisibleProjectiles();
		
		
		meteors.add(meteor1);
		projectiles.add(projectile1);
		meteors.add(meteor2);
		projectiles.add(projectile2);
		
		model.update(0.01f);
		
		assertTrue(!meteors.contains(meteor1) && meteors.contains(meteor2) && !projectiles.contains(projectile1) && projectiles.contains(projectile2));
	}
	
	@Test
	public void testOutOfBounds(){
		IGameModel model = new GameModel(null, new CannonBarrel());
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>(5);
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		City city = new City("", 10, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
//		model.newGame(city, items, new ArmoryItemVisitor(city, city.getMeteorShower()));
		
		Projectile projectile1 = new Projectile((float)Math.PI / 2, new Vector2(501, 1600));
		Projectile projectile2 = new Projectile((float)Math.PI / 2, new Vector2(-20, 450));
		Projectile projectile3 = new Projectile((float)Math.PI / 2, new Vector2(800, 500));
		Projectile projectile4 = new Projectile((float)Math.PI / 2, new Vector2(500, 500));
		
		Collection<Projectile> projectiles = model.getVisibleProjectiles();
		
		projectiles.add(projectile1);
		projectiles.add(projectile2);
		projectiles.add(projectile3);
		projectiles.add(projectile4);
		
		model.update(0.01f);
		
		assertTrue(!projectiles.contains(projectile1) && !projectiles.contains(projectile2) && !projectiles.contains(projectile3) && projectiles.contains(projectile4));
		
	}
	
	@Test
	public void testCityHit(){
		IGameModel model = new GameModel(null, new CannonBarrel());
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>(5);
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		City city = new City("", 10, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
//		model.newGame(city, items, new ArmoryItemVisitor(city, city.getMeteorShower()));
		
		Meteor meteor1 = new BasicMeteor(new Vector2(500, 500));
		Meteor meteor2 = new BasicMeteor(new Vector2(100, Constants.CITY_BOUNDS.y + 50));
		
		Collection<Meteor> meteors = model.getVisibleMeteors();
		int cityLife = model.getCity().getLife();
		
		meteors.add(meteor1);
		meteors.add(meteor2);
		
		model.update(0.01f);
		
		assertTrue(meteors.contains(meteor1) && !meteors.contains(meteor2) && model.getCity().getLife() == cityLife - meteor2.getDamage());

	}
	
	@Test
	public void testPaus(){
		IGameModel model = new GameModel(null, new CannonBarrel());
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>(5);
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());		
		City city = new City("", 10, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
//		model.newGame(city, items, new ArmoryItemVisitor(city, city.getMeteorShower()));
		City cityCopy = new City("", 10, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
		
		Meteor meteor1 = new BasicMeteor(new Vector2(500, 500));
		Projectile projectile1 = new Projectile((float)Math.PI / 2, new Vector2(501, 501));
		Meteor meteor2 = new BasicMeteor(new Vector2(100, 500));
		Projectile projectile2 = new Projectile((float)Math.PI / 2, new Vector2(50, 450));
		
		Collection<Meteor> meteors = model.getVisibleMeteors();
		Collection<Projectile> projectiles = model.getVisibleProjectiles();
		
		meteors.add(meteor1);
		projectiles.add(projectile1);
		meteors.add(meteor2);
		projectiles.add(projectile2);
		
		List<Meteor> meteors2 = new ArrayList<Meteor>(meteors);
		List<Projectile> projectiles2 = new ArrayList<Projectile>(projectiles);
		
		model.pause();
		
		for(int i = 0; i < 10; i++){
			model.update(0.01f);
		}
		
		boolean isCooldownChanged = false;
		
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getCooldown() != model.getSelectedArmoryItems().get(i).getCooldown()){
				isCooldownChanged = true;
			}
		}
		
		assertTrue(model.getCity().equals(cityCopy) && meteors.equals(meteors2) && projectiles.equals(projectiles2) && !isCooldownChanged);

	}
	
	@Test
	public void testGameOver(){
		final boolean[] isGameOver = new boolean[]{false, false};
		final int[] index = new int[]{0};
		PropertyChangeListener listener = new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("Gameover")){
					isGameOver[index[0]] = true;
				}
			}
		};
		IGameModel model = new GameModel(listener, new CannonBarrel());
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>(5);
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		items.add(new StandardArmoryItem());
		City city = new City("", 1, new MeteorShower(1, 1, 1, 1, 1), City.State.UNLOCKED);
//		model.newGame(city, items, new ArmoryItemVisitor(city, city.getMeteorShower()));
		
		model.getCity().hit(new BasicMeteor(new Vector2(500, 500)));
		model.update(0.01f);
		
		index[0] = 1;
		
		City city2 = new City("", 5, new MeteorShower(0,0,0,0,0), City.State.UNLOCKED);
//		model.newGame(city2, items, new ArmoryItemVisitor(city2, city2.getMeteorShower()));
		
		model.update(0.01f);
		
		assertTrue(isGameOver[0] && isGameOver[1]);
	}
	@Test
	public void testRadioactveMeteor(){
		
		City city = new City("",20, new MeteorShower(0,0,0,0,1), City.State.UNLOCKED);
		city.hit(new RadioactiveMeteor());
		int life = city.getLife();
		city.update(4f);
		 assertTrue(city.getLife() < life);	
	}
	
	@Test
	public void testMeteorShower(){
		MeteorShower shower = new MeteorShower(10,1,0,0,0);
		shower.loadMeteors();
		shower.update(1f);
		shower.update(1f);
		shower.update(1f);
		assertTrue(shower.getVisibleMeteors().size()>0);
	}
}
