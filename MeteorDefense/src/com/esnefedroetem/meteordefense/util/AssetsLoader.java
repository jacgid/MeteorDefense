package com.esnefedroetem.meteordefense.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public abstract class AssetsLoader {
	
	private static AssetManager manager = new AssetManager();
	private static final String textureDir = "data/textures/";
	private static final String musicDir = "data/music/";
	private static final String soundDir = "data/sounds/";
	private static final String fontDir = "data/fonts/";
	private static final String particlesDir = "data/particleeffects/";
	
	public static void loadTexture(String filename){
		manager.load(textureDir+filename, Texture.class);
	}
	
	public static void loadTextures(String[] filenames){
		for(String files: filenames){
			loadTexture(files);
		}
	}
	
	public static void loadMusic(String filename){
		manager.load(musicDir+filename, Music.class);
	}
	
	public static void loadMusics(String[] filenames){
		for(String files: filenames){
			loadMusic(files);
		}
	}
	
	public static void loadSound(String filename){
		manager.load(soundDir+filename, Sound.class);
	}
	
	public static void loadSounds(String[] filenames){
		for(String files: filenames){
			loadSound(files);
		}
	}
	
	public static void loadBitmapFont(String filename){
		manager.load(fontDir+filename, BitmapFont.class);
	}
	
	public static void loadBitmapFonts(String[] filenames){
		for(String files: filenames){
			loadBitmapFont(files);
		}
	}
	
	public static void loadParticleEffect(String filename){
		manager.load(particlesDir+filename, ParticleEffect.class);
	}
	
	public static void loadParticleEffects(String[] filenames){
		for(String files: filenames){
			loadParticleEffect(files);
		}
	}
	
	public static Texture getTexture(String filename){
		return manager.get(textureDir+filename, Texture.class);
	}
	
	public static Texture[] getTextures(String[] filenames){
		Texture[] textures = new Texture[filenames.length];
		for(int i = 0; i < filenames.length; i++){
			textures[i] = getTexture(filenames[i]);
		}
		return textures;
	}
	
	public static Music getMusic(String filename){
		return manager.get(musicDir+filename, Music.class);
	}
	
	public static Music[] getMusics(String[] filenames){
		Music[] musics = new Music[filenames.length];
		for(int i = 0; i < filenames.length; i++){
			musics[i] = getMusic(filenames[i]);
		}
		return musics;
	}
	
	public static Sound getSound(String filename){
		return manager.get(soundDir+filename, Sound.class);
	}
	
	public static Sound[] getSounds(String[] filenames){
		Sound[] sounds = new Sound[filenames.length];
		for(int i = 0; i < filenames.length; i++){
			sounds[i] = getSound(filenames[i]);
		}
		return sounds;
	}
	
	public static BitmapFont getBitmapFont(String filename){
		return manager.get(fontDir+filename, BitmapFont.class);
	}
	
	public static BitmapFont[] getBitmapFonts(String[] filenames){
		BitmapFont[] fonts = new BitmapFont[filenames.length];
		for(int i = 0; i < filenames.length; i++){
			fonts[i] = getBitmapFont(filenames[i]);
		}
		return fonts;
	}
	
	public static ParticleEffect getParticleEffect(String filename){
		return manager.get(particlesDir+filename, ParticleEffect.class);
	}
	
	public static ParticleEffect[] getParticleEffects(String[] filenames){
		ParticleEffect[] particleEffect = new ParticleEffect[filenames.length];
		for(int i = 0; i < filenames.length; i++){
			particleEffect[i] = getParticleEffect(filenames[i]);
		}
		return particleEffect;
	}
	
	//TODO: Add unload, clear and dispose methods
	
	public static boolean update(){
		return manager.update();
	}
	
	public static float getProgress(){
		return manager.getProgress();
	}
}
