package com.esnefedroetem.meteordefense.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap.Keys;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.MeteorShower;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.meteor.Meteor;

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
	meteorFilenames, menuFilenames, basegameFilenames, atlases;
	private HashMap<String, ArrayList<String>> meteorAnimations;
	
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
				atlases.put(element.getChild(i).getName(), element.getChild(i).getText());
//				Keys<String> objMap = element.getAttributes().keys();
//				ArrayList<String> textures = new ArrayList<String>();
//				while(objMap.hasNext){
//					String next = objMap.next();
//					String texture = element.getAttributes().get(next);
//					textures.add(texture);
//				}
//				meteorAnimations.put(element.getChild(i).getName(), textures);
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
	
	public HashMap<String, ArrayList<String>> getAnimationFileMap(){
		return meteorAnimations;
	}
	
	public String getAtlasName(String type){
		return atlases.get(type);
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
		
		MeteorShower ms = new MeteorShower();
		
		for(int i = 0; i < msElement.getChildCount(); i++){
			ms.setMeteorAmount(msElement.getChild(i).getName(),msElement.getChild(i).getInt("amount"));
		}
		
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
