package com.esnefedroetem.meteordefense.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * DataReader reads all the xml-files and stores the filenames for the textures of most objects in the game.
 * @author Simon Nielsen
 *
 */
public class DataReader {
	
	private static DataReader dataReader = new DataReader();
	private LevelData levelData;
	private XmlReader xmlreader;
	private Element root;
	private HashMap<String, String> continentFilenames, cityFilenames, armoryItemFilenames, 
	meteorFilenames, menuFilenames, basegameFilenames;
	
	public DataReader(){
	 
		xmlreader = new XmlReader();
		continentFilenames = new HashMap<String, String>();
		cityFilenames = new HashMap<String, String>();
		armoryItemFilenames = new HashMap<String, String>();
		meteorFilenames = new HashMap<String, String>();
		menuFilenames = new HashMap<String, String>();
		basegameFilenames = new HashMap<String, String>();
		
	}
	
	public List<Continent> readContinents(){
		return null;
	}
	
	public void loadFilenames(){
		
		if(parseFile("Filenames.xml")){
		
			Element element = root.getChildByName("Continents");
			for(int i = 0 ; i < element.getChildCount() ; i++){
				continentFilenames.put(element.getChild(i).getName(), element.getChild(i).getText());
			}
			
			element = root.getChildByName("Cities");
			for(int i = 0 ; i < element.getChildCount() ; i++){
				cityFilenames.put(element.getChild(i).getName(), element.getChild(i).getText());
			}
			
			element = root.getChildByName("Meteors");
			for(int i = 0 ; i < element.getChildCount() ; i++){
				meteorFilenames.put(element.getChild(i).getName(), element.getChild(i).getText());
			}
		}
		
	}
	
	private boolean parseFile(String file){
		try {
			root = xmlreader.parse(Gdx.files.internal("xml/"+file));
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
		
		parseFile("Levels.xml");
		
		String newCity = city.replaceAll(" ", "_");
		Element level = root.getChildByName(newCity);
		
		int life = level.getInt("Life");
		
		Element msElement = level.getChildByName("MeteorShower"); 
		
		int spawnrate = msElement.getInt("spawnrate");
		
		int[] amounts = {0,0,0,0,0};
		for(int i = 0 ; i < Meteor.MeteorType.getTypes().length ; i++){
			amounts[i] = msElement.getChildByName(Meteor.MeteorType.getTypes()[i]).getInt("amount");
		}
		
		MeteorShower ms = new MeteorShower(amounts[0], amounts[1], amounts[2], amounts[3], amounts[4]);
		ms.setSpawnrate(spawnrate);
		
		levelData = new LevelData(life, ms);
		
		return levelData;
	}
	
	public static DataReader getInstance(){
		return dataReader;
	}

	public String[] getLevelFilesnames(String city) {
		return null;
	}
}
