/**
 * 
 */
package com.esnefedroetem.meteordefense.model;

import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractDefenseArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractProjectileArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.EmptyItem;

/**
 * @author Emma
 * 
 */
public class ArmoryItemVisitor implements IArmoryItemVisitor {

	private City city;
	private MeteorShower meteorShower;

	public ArmoryItemVisitor() {
		// TODO Auto-generated constructor stub
	}

	public ArmoryItemVisitor(City city, MeteorShower meteorShower) {
		this.city = city;
		this.meteorShower = meteorShower;
	}

	@Override
	public Projectile visit(AbstractEffectArmoryItem element) {
		if (element.readyToUse()) {
			element.execute(meteorShower.getVisibleMeteors());
		}
		return null;
	}

	@Override
	public Projectile visit(AbstractDefenseArmoryItem element) {
		if (element.readyToUse()) {
			element.execute(city);
		}
		return null;
	}

	@Override
	public Projectile visit(AbstractProjectileArmoryItem element) {
		if (element.readyToUse()) {
			return element.execute();
		}
		return null;
	}

	@Override
	public Projectile visit(EmptyItem emptyItem) {
		return null;
	}

}
