package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeListener;
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
	
	private ArmoryRenderer renderer;
	
	public ArmoryScreen(ArmoryRenderer renderer){
		this.renderer = renderer;
	}
	
	public List<AbstractArmoryItem> getSelectedArmoryItems() {
		return renderer.getSelectedArmoryItems();
	}
	
	public List<AbstractArmoryItem> getUnselectedArmoryItems() {
		return renderer.getUnselectedArmoryItems();
	}

	public void addChangeListener(PropertyChangeListener listener){
		renderer.addChangeListener(listener);
	}
	
	@Override
	public void render(float delta) {
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		renderer.init();
		
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
		renderer.dispose();
	}

}
