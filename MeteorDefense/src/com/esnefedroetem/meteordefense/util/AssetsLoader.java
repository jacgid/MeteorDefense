package com.esnefedroetem.meteordefense.util;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.model.Meteor;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.service.LevelData;
import com.esnefedroetem.meteordefense.service.LoadService;
import com.esnefedroetem.meteordefense.service.WeaponData;

public class AssetsLoader {

	private AssetManager manager = new AssetManager();
	public static final String TEXTURE_DIR = "data/textures/";
	public static final String MUSIC_DIR = "data/music/";
	public static final String SOUND_DIR = "data/sounds/";
	public static final String FONT_DIR = "data/fonts/";
	public static final String PARTICLES_DIR = "data/particleeffects/";
	private static BitmapFont fontXSmall, fontSmall, fontMedium, fontLarge;
	private static AssetsLoader assetsLoader = new AssetsLoader();
	private static HashMap<String, String> textures, sounds, music;

	public static AssetsLoader getInstance() {
		return assetsLoader;
	}

	public void loadTexture(String filename) {
		manager.load(TEXTURE_DIR + filename, Texture.class);
	}

	public void loadTextures(String[] filenames) {
		for (String files : filenames) {
			loadTexture(files);
		}
	}

	public void loadMusic(String filename) {
		manager.load(MUSIC_DIR + filename, Music.class);
	}

	public void loadMusics(String[] filenames) {
		for (String files : filenames) {
			loadMusic(files);
		}
	}

	public void loadSound(String filename) {
		manager.load(SOUND_DIR + filename, Sound.class);
	}

	public void loadSounds(String[] filenames) {
		for (String files : filenames) {
			loadSound(files);
		}
	}

	public void loadParticleEffect(String filename) {
		manager.load(PARTICLES_DIR + filename, ParticleEffect.class);
	}

	public void loadParticleEffects(String[] filenames) {
		for (String files : filenames) {
			loadParticleEffect(files);
		}
	}

	public Texture getTexture(String filename) {
		return manager.get(TEXTURE_DIR + filename, Texture.class);
	}

	public Texture[] getTextures(String[] filenames) {
		Texture[] textures = new Texture[filenames.length];
		for (int i = 0; i < filenames.length; i++) {
			textures[i] = getTexture(filenames[i]);
		}
		return textures;
	}

	public Music getMusic(String filename) {
		return manager.get(MUSIC_DIR + filename, Music.class);
	}

	public Music[] getMusics(String[] filenames) {
		Music[] musics = new Music[filenames.length];
		for (int i = 0; i < filenames.length; i++) {
			musics[i] = getMusic(filenames[i]);
		}
		return musics;
	}

	public Sound getSound(String filename) {
		return manager.get(SOUND_DIR + filename, Sound.class);
	}

	public Sound[] getSounds(String[] filenames) {
		Sound[] sounds = new Sound[filenames.length];
		for (int i = 0; i < filenames.length; i++) {
			sounds[i] = getSound(filenames[i]);
		}
		return sounds;
	}

	public ParticleEffect getParticleEffect(String filename) {
		return manager.get(PARTICLES_DIR + filename, ParticleEffect.class);
	}

	public ParticleEffect[] getParticleEffects(String[] filenames) {
		ParticleEffect[] particleEffect = new ParticleEffect[filenames.length];
		for (int i = 0; i < filenames.length; i++) {
			particleEffect[i] = getParticleEffect(filenames[i]);
		}
		return particleEffect;
	}

	public void unloadTexture(String filename) {
		manager.unload(TEXTURE_DIR + filename);
	}

	public void unloadTextures(String[] filenames) {
		for (String files : filenames) {
			unloadTexture(files);
		}
	}

	public void unloadMusic(String filename) {
		manager.unload(MUSIC_DIR + filename);
	}

	public void unloadMusics(String[] filenames) {
		for (String files : filenames) {
			unloadMusic(files);
		}
	}

	public void unloadSound(String filename) {
		manager.unload(SOUND_DIR + filename);
	}

	public void unloadSounds(String[] filenames) {
		for (String files : filenames) {
			unloadSound(files);
		}
	}

	public void unloadParticleEffect(String filename) {
		manager.unload(PARTICLES_DIR + filename);
	}

	public void unloadParticleEffects(String[] filenames) {
		for (String files : filenames) {
			unloadParticleEffect(files);
		}
	}

	public void clear() {
		manager.clear();
	}

	public void dispose() {
		manager.dispose();
	}

	public boolean update() {
		return manager.update();
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public void finishLoading() {
		manager.finishLoading();
	}

	public void createFonts() {
		SmartFontGenerator fontGen = new SmartFontGenerator();
		FileHandle exoFile = Gdx.files
				.internal("data/fonts/SourceSansPro-Regular.ttf");
		fontXSmall = fontGen.createFont(exoFile, "source-xsmall", 70);
		fontSmall = fontGen.createFont(exoFile, "source-small", 96);
		fontMedium = fontGen.createFont(exoFile, "source-medium", 128);
		fontLarge = fontGen.createFont(exoFile, "source-large", 212);
	}

	public BitmapFont getExtraSmallFont(){
		return fontXSmall;
	}
	
	public BitmapFont getSmallFont() {
		return fontSmall;
	}

	public BitmapFont getMediumFont() {
		return fontMedium;
	}

	public BitmapFont getLargeFont() {
		return fontLarge;
	}

	public void loadAllTextures() {
		FileHandle file;
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			file = Gdx.files.internal(TEXTURE_DIR);
		} else {
			file = Gdx.files.internal("bin/" + TEXTURE_DIR);
		}
			FileHandle[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				if(!files[i].name().equals("images") && !files[i].name().equals("Thumbs.db")) {
				loadTexture(files[i].name());
				}
			}
			finishLoading();
	}
	
	public void loadStartupAssets(){
		//Load all continent textures
		HashMap<String, String> temp = LoadService.getInstance().getFilenameMap(Continent.class);
		textures.putAll(temp);
		List<Continent> contList = LoadService.getInstance().getContinents();
		for(int i = 0; i < contList.size(); i++){
			loadTexture(temp.get(contList.get(i).getName()));
		}
		//Load all city textures
		temp = LoadService.getInstance().getFilenameMap(City.class);
		textures.putAll(temp);
		for(int i = 0 ; i < contList.size(); i++){
			List<City> cityList = contList.get(i).getCities();
			for(int j = 0; j < cityList.size(); j++){
				loadTexture(temp.get(cityList.get(i).getName()));
			}
		}
		//Load all armoryitem assets
		temp = LoadService.getInstance().getFilenameMap(AbstractArmoryItem.class);
		textures.putAll(temp);
		List<WeaponData> armoryList = LoadService.getInstance().getArmoryItems();
		for(int i = 0; i < armoryList.size(); i++){
			textures.put(armoryList.get(i).getName(), temp.get(contList.get(i).getName()));
			loadTexture(temp.get(armoryList.get(i).getName()));
		}
	}

	public void loadBasegameAssets(){
		HashMap<String, String> temp = LoadService.getInstance().getBaseGameFilenameMap();
		String[] baseGameList = LoadService.getInstance().getBaseGameNames();
		for(int i = 0; i < baseGameList.length; i++){
			loadAsset(temp.get(baseGameList[i]));
		}
		textures.putAll(LoadService.getInstance().getFilenameMap(Meteor.class));
	}
	
	public void loadLevelAssets(City city){
		List<Meteor.MeteorType> types = city.getMeteorShower().getMeteorTypes();
		for(int i = 0; i < types.size(); i++){
			loadTexture(textures.get(types.get(i).toString()));
		}
	}
	
	public Texture getTextureByName(String name){
		return getTexture(textures.get(name));
	}
	
	private void loadAsset(String asset){
		String[] t = asset.split(".");
		if(t[t.length-1].equals("png")){
			loadTexture(asset);
		} else if(t[t.length-1].equals("mp3")){
			loadSound(asset);
		}
	}
	
}
