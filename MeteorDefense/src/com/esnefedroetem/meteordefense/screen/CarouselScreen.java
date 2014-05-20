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
import com.esnefedroetem.meteordefense.service.LevelData;
import com.esnefedroetem.meteordefense.service.ServiceFactory;

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
			//City set leveldata
			if(city.getState() == City.State.UNLOCKED){
				setLevelData(city);
				pcs.firePropertyChange(CarouselEvent.CAROUSEL_NEWGAME.toString(), null, city);
			}
		}else{
			currentContinent = continents.get(position);
			renderer.displayCities(currentContinent.getCities());
			isCitiesDisplayed = true;
		}
	}
	
	private void setLevelData(City city){
		LevelData lvlData = ServiceFactory.getInstance().getLoadService().getLevelData(city.getName());
		city.setLevelData(lvlData.getCityLife(), lvlData.getMeteorShower());
	}
	
}
