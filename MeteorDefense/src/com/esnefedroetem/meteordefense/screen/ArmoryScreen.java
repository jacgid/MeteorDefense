package com.esnefedroetem.meteordefense.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;

import com.esnefedroetem.meteordefense.model.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public class ArmoryScreen implements Screen{
	
	private ArrayList<AbstractArmoryItem> armoryItems, selectedArmoryItems;
	private ArmoryRenderer renderer;
	
	public ArmoryScreen(ArmoryRenderer renderer){
		this.renderer = renderer;
	}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
