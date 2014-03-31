package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.CarouselModel;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;

public class CarouselScreen implements Screen {
	private PropertyChangeSupport pcs;
	private CarouselRenderer renderer;
	private CarouselModel model;
	
	public CarouselScreen(){
		pcs = new PropertyChangeSupport(this);
		renderer = new CarouselRenderer();
		model = new CarouselModel();
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
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

}
