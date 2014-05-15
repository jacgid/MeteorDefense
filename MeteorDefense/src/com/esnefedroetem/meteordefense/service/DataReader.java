package com.esnefedroetem.meteordefense.service;

import java.util.HashMap;
import java.util.List;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;

public class DataReader {
	
	private static DataReader dataReader = new DataReader();
	private LevelData levelData;
	private HashMap<String, String> continentFilenames, cityFilenames, armoryItemFilenames, 
	meteorFilenames, menuFilenames, basegameFilenames;
	
	public DataReader(){
		
	}
	
	public List<Continent> readContinents(){
		return null;
	}
	
	public void loadFilenames(){
//		xml.parse(filenames)
	}
	
	public <T> HashMap<String, String> getFilenameMap(Class<T> type){
		if(type == Continent.class){
			return continentFilenames;
		}else if(type == City.class){
			return cityFilenames;
		}else if(type == AbstractArmoryItem.class){
			return armoryItemFilenames;
		}else if(type == Meteor.class){
			return meteorFilenames;
		}else{
			return null;
		}
	}
	
	public HashMap<String, String> getMenuFilenameMap(){
		return menuFilenames;
	}
	
	public HashMap<String, String> getBaseGameFilenameMap(){
		return basegameFilenames;
	}
	
	public LevelData readLevel(String city){
		
//		xmlReader.parse(city)
		
		levelData = new LevelData(10, new MeteorShower(5,5,5,5,5));
		
		return levelData;
	}
	
	public static DataReader getInstance(){
		return dataReader;
	}
}
