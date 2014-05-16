package com.esnefedroetem.meteordefense.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.esnefedroetem.meteordefense.model.City;

public class AssetsLoader {

	private AssetManager manager = new AssetManager();
	private static final String TEXTURE_DIR = "data/textures/";
	private static final String MUSIC_DIR = "data/music/";
	private static final String SOUND_DIR = "data/sounds/";
	private static final String FONT_DIR = "data/fonts/";
	private static final String PARTICLES_DIR = "data/particleeffects/";
	private static BitmapFont fontXSmall, fontSmall, fontMedium, fontLarge;
	private static AssetsLoader assetsLoader = new AssetsLoader();

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
		fontXSmall = fontGen.createFont(exoFile, "source-small", 48);
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
		if (Gdx.app.getType().equals(ApplicationType.Android)) {
			FileHandle file = Gdx.files.internal(TEXTURE_DIR);
			FileHandle[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				loadTexture(files[i].name());
			}
			finishLoading();
		} else {
			String[] files = {"America.png", 
					"Antarctica.png", 
					"armoryIcon.png",
					"ArmoryDetailedButton.png",
					"Asia.png", 
					"BASIC_METEOR.png", 
					"Berlin.png", 
					"CarouselBackground.png", 
					"CarouselBackgroundLocked.png", 
					"Dubai.png", 
					"Europe.png", 
					"Europe1.png", 
					"FAST_METEOR.png", 
					"FIRE_METEOR.png", 
					"FIRE_PROJECTILE.png", 
					"Ice.png", 
					"ICE_METEOR.png", 
					"lock.png", 
					"London.png", 
					"MDBG.png", 
					"MenuBG.png", 
					"MissileLauncher.png", 
					"MISSILE_PROJECTILE.png", 
					"Mountain.png", 
					"MusicFalse.png", 
					"MusicTrue.png", 
					"New York.png", 
					"Ottawa.png", 
					"Paris.png", 
					"Paris1.png", 
					"ParisMonument.png", 
					"PlayButton.png",  
					"RADIOACTIVE_METEOR.png", 
					"ReversedGravityEffect.png", 
					"Shanghai.png", 
					"SlowMotionEffect.png", 
					"SouthPole.png", 
					"Splash.png", 
					"StandardWeapon.png", 
					"STANDARD_PROJECTILE.png", 
					"star.png", 
					"starGrey.png", 
					"StartScreenBG.png", 
					"Tokyo.png", 
					"Washington.png", 
					"WATER_PROJECTILE.png", 
					"weaponslot.png"};
			for (int i = 0; i < files.length; i++) {
				loadTexture(files[i]);
			}
			finishLoading();
			
			
		}
	}
	
	public void loadStartupAssets(){
		
	}

	public void loadBasegameAssets(){
		
	}
	
	public void loadLevelAssets(City city){
		
	}
	
}
