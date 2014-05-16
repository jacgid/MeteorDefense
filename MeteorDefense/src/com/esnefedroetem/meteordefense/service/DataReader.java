package com.esnefedroetem.meteordefense.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DataReader {
	
	private static DataReader dataReader = new DataReader();
	private LevelData levelData;
	private XmlReader xmlreader;
	private Element root;
	private HashMap<String, String> continentFilenames, cityFilenames, armoryItemFilenames, 
	meteorFilenames, menuFilenames, basegameFilenames;
	
	public DataReader(){
	 
		xmlreader = new XmlReader();
		
	}
	
	public List<Continent> readContinents(){
		return null;
	}
	
	public void loadFilenames(){
		
		if(parseFile("Filenames.xml")){
		
			Element element = root.getChildByName("Continents");
			for(int i = 0 ; i < element.getChildCount() ; i++){
				continentFilenames.put(element.getChild(i).toString(), element.getChild(i).getText());
			}
			
			element = root.getChildByName("Cities");
			for(int i = 0 ; i < element.getChildCount() ; i++){
				cityFilenames.put(element.getChild(i).toString(), element.getChild(i).getText());
			}
			
			element = root.getChildByName("Meteors");
			for(int i = 0 ; i < element.getChildCount() ; i++){
				meteorFilenames.put(element.getChild(i).toString(), element.getChild(i).getText());
			}
		}
		
	}
	
	private boolean parseFile(String file){
		try {
			root = new XmlReader().parse(Gdx.files.internal(file));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String[] getBaseGameNames(){
		return null;
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

	public String[] getLevelFilesnames(String city) {
		return null;
	}
}
