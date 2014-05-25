package com.esnefedroetem.meteordefense.screen;

import java.util.List;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryRenderer;

/** 
 * 
 * ArmoryScreen controls the ArmoryRenderer
 * and decides what to do when the armory is displayed or disposed.
 * 
 *  @author Jacob
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

	@Override
	public void render(float delta) {
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		renderer.init();
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

}
