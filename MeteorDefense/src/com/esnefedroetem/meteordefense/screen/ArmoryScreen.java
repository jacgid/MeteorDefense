package com.esnefedroetem.meteordefense.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer;

/** 
 * 
 *  @author Emma Lindholm
 *  
 */

public class ArmoryScreen implements Screen{
	
	private List<AbstractArmoryItem> armoryItems, selectedArmoryItems;
	private ArmoryRenderer renderer;
	
	public ArmoryScreen(ArmoryRenderer renderer, List<AbstractArmoryItem> armoryItems, List<AbstractArmoryItem> selectedArmoryItems){
		this.renderer = renderer;
		this.armoryItems = armoryItems;
		this.selectedArmoryItems = selectedArmoryItems;
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
