package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.CarouselModel;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;

public class CarouselScreen implements Screen, PropertyChangeListener {
	private PropertyChangeSupport pcs;
	private CarouselRenderer renderer;
	private CarouselModel model;
	
	public CarouselScreen(){
		pcs = new PropertyChangeSupport(this);
		renderer = new CarouselRenderer();
		model = new CarouselModel();
		renderer.addChangeListener(this);
		
		if(model.isCitiesDisplayed()){
			renderer.displayCities(model.getCurrentCitites());			
		}else{
			renderer.displayContinents(model.displayContinents());
		}
		
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
		renderer.addChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals(CarouselRenderer.CarouselEvent.CAROUSEL_CLICKED.toString())){
			processCarouselClick((Integer) evt.getNewValue());
		}
	}
	
	private void processCarouselClick(int position){
		if(model.isCitiesDisplayed()){
			pcs.firePropertyChange(CarouselEvent.CAROUSEL_NEWGAME.toString(), null, model.getCity(position));
		}else{
			renderer.displayCities(model.displayCities(position));
		}
	}
	
}
