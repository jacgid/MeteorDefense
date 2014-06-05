/**
 * 
 */
package com.esnefedroetem.meteordefense.model;

import java.util.ArrayList;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractDefenseArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractProjectileArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.EmptyItem;

/**
 * A concrete Visitor of the ArmoryItem classes.
 * Needs an instance of City and MeteorShower to execute ArmoryItems
 * correctly. visit() method checks if ArmoryItem is ready to use before executing.
 * Methods returns null except the method with parameter AbstractProjectileArmoryItem 
 * when item is ready for use.
 * @author Emma Lindholm
 * 
 */
public class ArmoryItemVisitor implements IArmoryItemVisitor {

	private final City CITY;
	private final MeteorShower METEOR_SHOWER;
	private GameModel model;
	private ArrayList<Projectile> projectilesToAdd, projectilesToRemove;

	public ArmoryItemVisitor(City city, MeteorShower meteorShower, ArrayList<Projectile> projectilesToAdd, ArrayList<Projectile> projectilesToRemove) {
		this.CITY = city;
		this.METEOR_SHOWER = meteorShower;
		this.projectilesToAdd = projectilesToAdd;
		this.projectilesToRemove = projectilesToRemove;
	}

	@Override
	public Projectile visit(AbstractEffectArmoryItem element) {
		if (element.readyToUse()) {
			element.execute(METEOR_SHOWER.getVisibleMeteors());
		}
		return null;
	}

	@Override
	public Projectile visit(AbstractDefenseArmoryItem element) {
		if (element.readyToUse()) {
			element.execute(CITY);
		}
		return null;
	}

	@Override
	public Projectile visit(AbstractProjectileArmoryItem element) {
		if (element.readyToUse()) {
			return element.execute(projectilesToAdd, projectilesToRemove);
		}
		return null;
	}

	@Override
	public Projectile visit(EmptyItem emptyItem) {
		return null;
	}

}
