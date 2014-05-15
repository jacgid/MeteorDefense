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
 * @author Emma
 *
 */
public class AbstractArmoryItemTest {

	@Test
	public void test() throws Exception {
		testGetRemainingCooldown();
	}

	public void testGetRemainingCooldown() throws Exception {
		AbstractEffectArmoryItem item = new ReversedGravityEffectArmoryItem(State.UNLOCKED, 1);
		
		MeteorShower meteorShower = new MeteorShower(5,0,0,0,5);
		meteorShower.loadMeteors();
		meteorShower.start();
		
		item.readyToUse();
		item.execute(meteorShower.getVisibleMeteors());
		
		long time = TimeUtils.millis();
		
		while(TimeUtils.timeSinceMillis(time) < 2000) {
			
		}
		
		System.out.println("ready to use: " + item.readyToUse());
		System.out.println("Remaining cooldown: " + item.getRemainingCooldown());
		assertTrue(item.getRemainingCooldown() == 1);
		
		
	}
}
