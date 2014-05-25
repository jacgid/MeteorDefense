/**
 * 
 */
package com.esnefedroetem.meteordefense.model.armoryitem;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.ArmoryItemVisitor;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.ReversedGravityEffectArmoryItem;

/**
 * @author Emma Lindholm
 *
 */
public class AbstractArmoryItemTest {

	@Test
	public void testGetRemainingCooldown() throws Exception {
		AbstractEffectArmoryItem item = new ReversedGravityEffectArmoryItem(State.UNLOCKED, 1);
		
		MeteorShower meteorShower = new MeteorShower(5,0,0,0,5);
		meteorShower.loadMeteors();
		meteorShower.start();
		
		item.readyToUse();
		item.execute(meteorShower.getVisibleMeteors());
		
		long millis = TimeUtils.millis();
		while(TimeUtils.timeSinceMillis(millis) < 20) {
			
		}
		
		System.out.println("Ready to use: " + item.readyToUse());
		System.out.println("Remaining cooldown: " + item.getRemainingCooldown());
		assertTrue(item.getRemainingCooldown() != 1);
		
		
	}
	
	@Test
	public void testInit() {
		AbstractArmoryItem item = new StandardArmoryItem();
		
		int upgradeIndex = 2;
		
		item.init(State.LOCKED, upgradeIndex);
		
		assertTrue(item.getUpgradeIndex() == upgradeIndex && item.getState() == State.LOCKED);
	}
	
	@Test
	public void testUpgrade() {
		AbstractArmoryItem item = new StandardArmoryItem(State.UNLOCKED, 1);
		
		int prevUpgradeIndex = item.getUpgradeIndex();
		item.upgrade();
		
		assertTrue(item.getUpgradeIndex() == prevUpgradeIndex + 1);
	}
	
	@Test
	public void testHasUpgrade() {
		AbstractArmoryItem item = new StandardArmoryItem(State.UNLOCKED, 1);
		
		assertTrue(item.hasUpgrade() == true);
	}
	
	@Test
	public void testIncreaseRemainingCooldown() {
		AbstractProjectileArmoryItem item = new StandardArmoryItem(State.UNLOCKED, 1);
		item.readyToUse();
		item.execute();
		
		float prevRemainingCooldown = item.getRemainingCooldown();
		item.increaseRemainingCooldown(2000);
		
		assertTrue(prevRemainingCooldown < item.getRemainingCooldown());
	}
}
