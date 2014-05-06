package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeListener;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.ArmoryDetailedRenderer;

public class ArmoryDetailedScreen implements Screen{
	
	private ArmoryDetailedRenderer renderer;
	
	public ArmoryDetailedScreen(ArmoryDetailedRenderer renderer){
		this.renderer = renderer;
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

	public void setArmoryItem(AbstractArmoryItem armoryItem) {
		renderer.setArmoryItem(armoryItem);
		
	}

}
