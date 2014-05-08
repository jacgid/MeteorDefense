package com.esnefedroetem.meteordefense.screen;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.GameModel;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.GameRenderer;

/**
 * The GameScreen is the screen where the game is running.
 * @author Simon Nielsen
 *
 */
public class GameScreen implements Screen, InputProcessor, PropertyChangeListener{

	private GameModel model;
	private GameRenderer renderer;
	private InputMultiplexer inputmultiplexer;
	
	public GameScreen(GameModel model, GameRenderer renderer, PropertyChangeListener meteorDefense){
		this.model = model;
		this.renderer = renderer;
		
		renderer.addChangeListener(this);
		
		renderer.addChangeListener(meteorDefense);
		model.addChangeListener(meteorDefense);
		
		inputmultiplexer = new InputMultiplexer();
		inputmultiplexer.addProcessor(this);
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
		Gdx.input.setInputProcessor(inputmultiplexer);
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * Resumes the game.
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Disposes the game.
	 */
	@Override
	public void dispose() {
		renderer.unloadTextures();
		Gdx.input.setInputProcessor(null);
	}
	
	public void newGame(City city, List<AbstractArmoryItem> selectedArmoryItems){
		model.newGame(city, selectedArmoryItems);
		
	}
	
	public GameModel getModel(){
		return model;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			model.endGame();
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		if (!Gdx.app.getType().equals(ApplicationType.Android)) {
//			return false;
//		}
		Vector2 temp = renderer.unproject(screenX, screenY);
		model.shoot(temp.x, temp.y);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("buttonClicked")){
			model.toolbarAct((Integer)evt.getOldValue());
		}
		if(evt.getPropertyName().equals("addInputProcessor")){
			inputmultiplexer.removeProcessor(this);
			inputmultiplexer.addProcessor((InputProcessor)evt.getOldValue());
			inputmultiplexer.addProcessor(this);
		}
	}
	
	public GameRenderer getRenderer(){
		return renderer;
	}

}
