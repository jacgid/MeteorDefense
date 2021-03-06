package com.esnefedroetem.meteordefense.screen;

import com.badlogic.gdx.Screen;
import com.esnefedroetem.meteordefense.model.ScoreHandler;
import com.esnefedroetem.meteordefense.renderer.ScoreRenderer;
 /** This is the screen which will display the score.
  * It is shown after a completed game.
  * 
  * @author Andreas Pegelow
  *
  */

public class ScoreScreen implements Screen{
	
	private ScoreRenderer renderer;
	
	
	public ScoreScreen(ScoreRenderer renderer){
		this.renderer = renderer;
	}
	
	public void setScore(ScoreHandler score) {
		renderer.setScore(score);
		
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
		renderer.dispose();
	}

	

}
