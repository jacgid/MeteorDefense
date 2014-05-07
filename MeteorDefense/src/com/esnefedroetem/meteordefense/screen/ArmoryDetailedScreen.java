package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.Wallet;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.StandardArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer.ArmoryDetaliedEvent;

public class ArmoryDetailedScreen implements Screen, PropertyChangeListener{
	
	private ArmoryDetailedRenderer renderer;
	private Wallet wallet;
	private AbstractArmoryItem armoryItem;
	private PropertyChangeSupport pcs;
	
	public ArmoryDetailedScreen(ArmoryDetailedRenderer renderer, Wallet wallet){
		this.renderer = renderer;
		this.wallet = wallet;
		pcs = new PropertyChangeSupport(this);
		renderer.addChangeListener(this);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	@Override
	public void render(float delta) {
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		initializeInfo();
		renderer.init();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

	public void setArmoryItem(AbstractArmoryItem armoryItem) {
		this.armoryItem = armoryItem;
				
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(ArmoryDetaliedEvent.ARMORY_DETAILED_BACK_PRESSED.toString())) {
			pcs.firePropertyChange(evt);
		} else if (evt.getPropertyName().equals(ArmoryDetaliedEvent.ARMORY_DETAILED_TRADE_BUTTON_PRESSED.toString())) {
			if (armoryItem.getState() == AbstractArmoryItem.State.LOCKED) {
			itemBought();
			} else {
			itemSold();
		}
		} else if (evt.getPropertyName().equals(ArmoryDetaliedEvent.ARMORY_DETAILED_ITEM_UPGRADED.toString())) {
			itemUpgraded();
		}
		
		initializeInfo();
	}

	private void itemUpgraded() {
		wallet.removeCoins(armoryItem.getNextUpgradeValue());
		armoryItem.upgrade();
	}

	private void itemSold() {
		wallet.addCoins(armoryItem.getValue());
		armoryItem.setState(AbstractArmoryItem.State.LOCKED);		
	}

	private void itemBought() {
		wallet.removeCoins(armoryItem.getValue());
		armoryItem.setState(AbstractArmoryItem.State.UNLOCKED);	
	}
	
	private void initializeInfo() {
		renderer.setAssetsLabelText(wallet.getAssets() + "");
		renderer.setNameLabelText(armoryItem.getName());
		renderer.setDescriptionLabelText(armoryItem.getDescription() + "\n\nPower: " + armoryItem.getPower() + "\nCooldown: " + armoryItem.getCooldown() + " sec");
		renderer.setUpgradeLabelText("Next upgrade\n" + armoryItem.getNextUpgradeInfo());
		
		if(armoryItem.getState() == AbstractArmoryItem.State.LOCKED) {
			renderer.setUpgradeButtonText("Upgrade for " + armoryItem.getNextUpgradeValue());
			renderer.setUpgradeButtonDisabled(true);
			
			renderer.setTradeButtonText("Buy for " + armoryItem.getValue());
			renderer.setTradeButtonDisabled(!wallet.canAfford(armoryItem.getValue()));
		} else {
			if(armoryItem.hasUpgrade()) {
				renderer.setUpgradeButtonText("Upgrade for " + armoryItem.getNextUpgradeValue());
				renderer.setUpgradeButtonDisabled(wallet.canAfford(armoryItem.getNextUpgradeValue()));
			} else {
				renderer.setUpgradeButtonText("No Upgrades");
				renderer.setUpgradeButtonDisabled(true);
			}
			
			renderer.setTradeButtonText("Sell for " + armoryItem.getValue());
			renderer.setTradeButtonDisabled(false);
		}
		
		// standard weapon is not tradeable 
		if(armoryItem instanceof StandardArmoryItem) {
		renderer.setTradeButtonText("Not tradeable");
		renderer.setTradeButtonDisabled(true);
		}
	}
	
	public void addCoinsToWallet(int amount) {
		wallet.addCoins(amount);
	}
	
	

}
