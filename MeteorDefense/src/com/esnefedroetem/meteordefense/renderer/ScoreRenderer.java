package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ScoreRenderer {

	private PropertyChangeSupport pcs;

	private SpriteBatch spriteBatch;
	private Stage stage;
	private int score;
	private Label scoreLabel;

	public ScoreRenderer() {
		pcs = new PropertyChangeSupport(this);

		spriteBatch = new SpriteBatch();
		stage = new Stage();

		create();
	}

	private void create() {
		Table table = new Table();
		table.setFillParent(true);

		stage.addActor(table);
		
		
		LabelStyle scoreLabelStyle = new LabelStyle();
		scoreLabelStyle.font = new BitmapFont();
		scoreLabelStyle.font.scale(3);
		scoreLabel = new Label("Score: " + score, scoreLabelStyle);
		
		TextButtonStyle homeButtonstyle = new TextButtonStyle();
		homeButtonstyle.font = new BitmapFont();
		homeButtonstyle.font.scale(4);
		
		TextButton homeButton = new TextButton("Home", homeButtonstyle);
		
		table.add(scoreLabel).expand().bottom();
		table.row();
		table.add(homeButton).expand().bottom();
		
		homeButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
		 	}
		 
		 	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
		 		pcs.firePropertyChange("Scorescreen_finished", false, true);
		 	}

		});
		
		

	}

	public void addChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void render() {
		Gdx.gl.glClearColor(255, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();

	}

	public void setScore(int score) {
		scoreLabel.setText("Score: " + score);

	}

	public void show() {
		Gdx.input.setInputProcessor(stage);
		
	}

}
