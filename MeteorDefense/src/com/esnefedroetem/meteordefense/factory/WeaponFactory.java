package com.esnefedroetem.meteordefense.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.EmptyItem;
import com.esnefedroetem.meteordefense.model.armoryitem.MissileProjectileArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.ReversedGravityEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.SlowMotionEffectArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.StandardArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;
import com.esnefedroetem.meteordefense.service.ServiceFactory;
import com.esnefedroetem.meteordefense.service.WeaponData;

class WeaponFactory {
	
	private static final WeaponFactory instance = new WeaponFactory();
	private HashMap<String, AbstractArmoryItem> items;
	private WeaponFactory(){
		items = new HashMap<String, AbstractArmoryItem>(10);
		AbstractArmoryItem item1 = new EmptyItem();
		AbstractArmoryItem item2 = new MissileProjectileArmoryItem();
		AbstractArmoryItem item7 = new ReversedGravityEffectArmoryItem();
		AbstractArmoryItem item8 = new StandardArmoryItem();
		AbstractArmoryItem item9 = new SlowMotionEffectArmoryItem();
		items.put(item1.getName(), item1);
		items.put(item2.getName(), item2);
		items.put(item7.getName(), item7);
		items.put(item8.getName(), item8);
		items.put(item9.getName(), item9);
	}
	
	protected static WeaponFactory getInstance(){
		return instance;
	}


	protected List<AbstractArmoryItem> getWeapons() {
		List<WeaponData> data = ServiceFactory.getInstance().getLoadService().getArmoryItems();
		List<AbstractArmoryItem> armoryItems = new ArrayList<AbstractArmoryItem>();
		List<AbstractArmoryItem> choosenItems = new ArrayList<AbstractArmoryItem>();
		
		if(data != null){
			for(WeaponData wd : data){
				AbstractArmoryItem item = items.get(wd.getName());
				item.init(wd.getState(), wd.getUpgradeIndex());
				if(wd.isChoosen()){
					choosenItems.add(item);
				}else{
					armoryItems.add(item);
				}
			}
			armoryItems.addAll(choosenItems);
		}else{
			armoryItems.add(new EmptyItem());
			armoryItems.add(new EmptyItem());
			armoryItems.add(new EmptyItem());
			armoryItems.add(new EmptyItem());
			armoryItems.add(new EmptyItem());
			armoryItems.add(new EmptyItem());
			armoryItems.add(new ReversedGravityEffectArmoryItem(State.UNLOCKED, 1));
			armoryItems.add(new StandardArmoryItem(State.UNLOCKED, 1));
			armoryItems.add(new SlowMotionEffectArmoryItem(State.UNLOCKED, 1));
			armoryItems.add(new MissileProjectileArmoryItem(State.UNLOCKED, 1));
		}
		return armoryItems;
	}

}
