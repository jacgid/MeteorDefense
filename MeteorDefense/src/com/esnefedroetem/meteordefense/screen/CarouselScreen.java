package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;

public class CarouselScreen implements Screen, PropertyChangeListener {
	private PropertyChangeSupport pcs;
	private CarouselRenderer renderer;
	private boolean isCitiesDisplayed;
	private List<Continent> continents;
	private Continent currentContinent;

	
	public CarouselScreen(CarouselRenderer renderer, List<Continent> continents){
		pcs = new PropertyChangeSupport(this);
		this.renderer = renderer;
		renderer.addChangeListener(this);
		this.continents = continents;
		isCitiesDisplayed = false;
		currentContinent = null;
		renderer.displayContinents(continents);
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
		}else if(evt.getPropertyName().equals(CarouselRenderer.CarouselEvent.CAROUSEL_BACKBUTTON.toString())){
			onBackPressed();
		}
	}
	
	public List<Continent> getContinents(){
		return continents;
	}
	
	private void onBackPressed(){
		if(isCitiesDisplayed){
			renderer.displayContinents(continents);
			isCitiesDisplayed = false;
		}else{
			pcs.firePropertyChange(SplashScreenEvent.SPLASHSCREEN_ENDED.toString(), false, true);
		}
	}
	
	private void processCarouselClick(int position){
		if(isCitiesDisplayed){
			pcs.firePropertyChange(CarouselEvent.CAROUSEL_NEWGAME.toString(), null, currentContinent.getCities().get(position));
		}else{
			currentContinent = continents.get(position);
			renderer.displayCities(currentContinent.getCities());
			isCitiesDisplayed = true;
		}
	}
	
}
