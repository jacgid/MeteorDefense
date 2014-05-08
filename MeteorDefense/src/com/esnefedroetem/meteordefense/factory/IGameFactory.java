package com.esnefedroetem.meteordefense.factory;

import java.beans.PropertyChangeListener;
import com.badlogic.gdx.Screen;

public interface IGameFactory {

	public void createScreens(PropertyChangeListener listener, Screen mainMenuScreen, Screen armoryScreen,
			Screen armoryDetaliedScreen, Screen gameScreen,
			Screen carouselScreen, Screen scoreScreen);

}
