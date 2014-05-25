package com.esnefedroetem.meteordefense.model.armoryitem;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.TimeUtils;
import com.esnefedroetem.meteordefense.model.Upgrade;

/**
 * Abstract superclass to all ArmoryItems, contains all common methods.
 * Implements interface IArmoryItemElement.
 * @author Emma Lindholm
 * 
 */

public abstract class AbstractArmoryItem implements IArmoryItemElement {

	public enum State {
		LOCKED, UNLOCKED;
	}

	private State state;
	private int power, value, upgradeIndex;
	private float cooldown;
	private long lastUsed;
	private List<Upgrade> upgrades;
	private final String NAME, DESCRIPTION;
	public static final EmptyItem EMPTY_ITEM = new EmptyItem();

	
	public AbstractArmoryItem(String name, String description) {
		this.NAME = name;
		this.DESCRIPTION = description;
	}
	public AbstractArmoryItem(State state, int upgradeIndex, String name, String description) {
		this(name, description);
		init(state, upgradeIndex);
	}
	
	public void init(State state, int upgradeIndex) {
		initUpgrades();
		this.state = state;
		setUpgradeIndex(upgradeIndex);
	}
	
	public void upgrade() {
		Upgrade upgrade = upgrades.get(upgradeIndex);
		power = power + upgrade.getPowerIncrement();
		cooldown = cooldown + upgrade.getCooldownDecrement();
		calculateValue(upgrade.getUpgradeValue());
		upgradeIndex++;
	}

	public boolean hasUpgrade() {
		return upgradeIndex < upgrades.size();
	}

	private void reset() {
		value = 0;
		upgradeIndex = 0;
		power = 0;
		cooldown = 0;
		upgrade();
	}

	public State getState() {
		return state;
	}

	/**
	 * If state is set to LOCKED, item is reset to start values (upgrade[0])
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
		if (state == State.LOCKED) {
			reset();
		}
	}

	public int getPower() {
		return power;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getUpgradeIndex() {
		return upgradeIndex;
	}

	/**
	 * Upgrades item to value of upgradeIndex
	 * @param upgradeIndex
	 */
	public void setUpgradeIndex(int upgradeIndex) {
		this.upgradeIndex = 0;
		for (int i = 0; i < upgradeIndex; i++) {
			upgrade();
		}
	}

	public void setUpgradeList(ArrayList<Upgrade> upgrades) {
		this.upgrades = upgrades;
	}

	private void calculateValue(int value) {
		this.value += value / 2;
	}

	public int getPurchaseValue() {
		return upgrades.get(0).getUpgradeValue();
	}

	public int getValue() {
		return value;
	}

	public int getNextUpgradeValue() {
		return upgrades.get(upgradeIndex).getUpgradeValue();
	}

	public boolean readyToUse() {
		if (TimeUtils.timeSinceMillis(lastUsed) > cooldown * 1000) {
			lastUsed = TimeUtils.millis();
			return true;
		}
		return false;
	}

	public String getName() {
		return NAME;
	}

	public String getDescription() {
		return DESCRIPTION;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AbstractArmoryItem) {
			return NAME.equals(((AbstractArmoryItem) o).getName());
		}
		return false;
	}

	/**
	 * Creates list of upgrades.
	 * NOTE: Upgrade 0 in list represents start values of item.
	 * Cooldown should therefore be a positive value in this upgrade and
	 * upgradeValue should represent items purchase value.
	 */
	public abstract void initUpgrades();

	public abstract void update(float delta);

	public String getNextUpgradeInfo() {
		if (hasUpgrade()) {
			if (upgrades.get(upgradeIndex).getPowerIncrement() == 0) {
				return "Cooldown: "
						+ (cooldown + upgrades.get(upgradeIndex)
								.getCooldownDecrement()) + " sec";
			}
			return "Power: "
					+ (power + upgrades.get(upgradeIndex).getPowerIncrement());
		}
		return "No upgrades available";
	}

	public void resetLastUsed() {
		lastUsed = 0;
	}

	/**
	 * 
	 * @return float between 0 and 1 representing percentage of cooldown time remaining
	 */
	public float getRemainingCooldown() {
		return 1 - TimeUtils.timeSinceMillis(lastUsed) / (cooldown * 1000);
	}

	/**
	 * Adds the number of milliseconds to the lastUsed value, used to prevent cooldown
	 * from decreasing during pause of gameplay.
	 * @param milliseconds Number of milliseconds to be added to lastUsed
	 */
	public void increaseRemainingCooldown(long milliseconds) {
		if (getRemainingCooldown() > 0) {
			lastUsed += milliseconds;
		}
	}
	
}
