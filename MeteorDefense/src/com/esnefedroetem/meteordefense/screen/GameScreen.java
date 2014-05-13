package com.esnefedroetem.meteordefense.screen;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.GameRenderer;
import com.esnefedroetem.meteordefense.util.Constants;

/**
 * The GameScreen is the screen where the game is running.
 * @author Simon Nielsen
 *
 */
public class GameScreen implements Screen{

	private GameModel model;
	private GameRenderer renderer;
	
	private ClickListener specialWeaponListener = new ClickListener(){
		@Override
		public void clicked(InputEvent event, float x, float y) {
			model.specialWeaponSelected((Integer)event.getListenerActor().getUserObject());
		}
	};
	
	private InputListener inputListener = new InputListener(){
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if(keycode == Keys.BACK){
				model.onBackPressed();
				return true;
			}
			return false;
		}
		
		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			if(y > Constants.CITY_BOUNDS.y + Constants.CITY_BOUNDS.height){
				model.shoot(x, y);
				return true;
			}
			return false;
		}
		
	};
	
	public GameScreen(GameModel model, GameRenderer renderer){
		this.model = model;
		this.renderer = renderer;
		renderer.addListeners(inputListener, specialWeaponListener);
	}
	
	/**
	 * The render method which updates the model and then calls the renderer to render.
	 * @param delta The time since the last time this method was called.
	 */
	@Override
	public void render(float delta) {
		model.update(delta);
		renderer.render();
	}

	/**
	 * Resizes the view. Is called when the screen is resized.
	 * @param width The width of the screen.
	 * @param height The height of the screen.
	 */
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
	}

	/**
	 * Initializes GameScreen.
	 */
	@Override
	public void show() {
		renderer.setInputProcessor();
	}

	/**
	 * 
	 */
	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	/**
	 * Pauses the game.
	 */
	@Override
	public void pause() {
		model.pause();
		
	}

	/**
	 * Resumes the game.
	 */
	@Override
	public void resume() {
		model.resume();
	}

	/**
	 * Disposes the game.
	 */
	@Override
	public void dispose() {
		renderer.dispose();
		Gdx.input.setInputProcessor(null);
	}
	
	public void newGame(City city, List<AbstractArmoryItem> selectedArmoryItems){
		model.newGame(city, selectedArmoryItems);
		renderer.newGame(city, selectedArmoryItems);
	}
	
	public GameRenderer getRenderer(){
		return renderer;
	}
	
}
