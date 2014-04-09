package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeListener;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.renderer.ScoreRenderer;


public class ScoreScreen implements Screen{
	
	private ScoreRenderer renderer;
	
	
	public ScoreScreen(ScoreRenderer renderer){
		this.renderer = renderer;
	}
	
	public void setScore(int score){
		
		renderer.setScore(score);
		
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
		renderer.show();
		
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
