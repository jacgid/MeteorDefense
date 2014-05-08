package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer;
import com.esnefedroetem.meteordefense.renderer.CarouselRenderer.CarouselEvent;
import com.esnefedroetem.meteordefense.screen.SplashScreen.SplashScreenEvent;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class CarouselScreen implements Screen, PropertyChangeListener {
	private PropertyChangeSupport pcs;
	private CarouselRenderer renderer;
	private boolean isCitiesDisplayed;
	private List<Continent> continents;
	private Continent currentContinent;

	
	public CarouselScreen(CarouselRenderer renderer, List<Continent> continents, PropertyChangeListener listener){
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		renderer.addChangeListener(this);
		this.renderer = renderer;
		this.continents = continents;
		isCitiesDisplayed = false;
		currentContinent = null;
		for(Continent continent : continents){
			AssetsLoader.loadTexture(continent.getName() + ".png");
			for(City city : continent.getCities()){
				AssetsLoader.loadTexture(city.getName() + ".png");
			}
		}
		AssetsLoader.loadTexture("star.png");
		AssetsLoader.loadTexture("starGrey.png");
		AssetsLoader.loadTexture("CarouselBackground.png");
		AssetsLoader.loadTexture("CarouselBackgroundLocked.png");
		AssetsLoader.loadTexture("lock.png");
		AssetsLoader.loadTexture("MenuBG.png");
		AssetsLoader.finishLoading();
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
	
	public void update(){
		if(isCitiesDisplayed){
			renderer.displayCities(currentContinent.getCities());
		}else{
			renderer.displayContinents(continents);
		}
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
			City city = currentContinent.getCities().get(position);
			if(city.getState() == City.State.UNLOCKED){
				pcs.firePropertyChange(CarouselEvent.CAROUSEL_NEWGAME.toString(), null, city);
			}
		}else{
			currentContinent = continents.get(position);
			renderer.displayCities(currentContinent.getCities());
			isCitiesDisplayed = true;
		}
	}
	
}
