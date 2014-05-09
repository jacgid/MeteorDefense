package com.esnefedroetem.meteordefense.screen;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer;

public class MainMenuScreen implements Screen {
	
	private MainMenuRenderer renderer;
	
	public MainMenuScreen(MainMenuRenderer renderer){
		this.renderer = renderer;
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
