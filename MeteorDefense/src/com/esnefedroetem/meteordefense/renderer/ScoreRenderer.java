package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.esnefedroetem.meteordefense.ScoreHandler;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class ScoreRenderer {

	private PropertyChangeSupport pcs;

	private SpriteBatch spriteBatch;
	private Stage stage;
	private int score;
	private Label scoreLabel;
	private Color color;

	public ScoreRenderer() {
		pcs = new PropertyChangeSupport(this);

		spriteBatch = new SpriteBatch();
		stage = new Stage();

		create();
	}

	private void create() {
		color = new Color(255, 0, 0, 0);

		Table table = new Table();
		table.setFillParent(true);

		stage.addActor(table);

		LabelStyle scoreLabelStyle = new LabelStyle();
		scoreLabelStyle.font = AssetsLoader.getMediumFont();
		
		scoreLabel = new Label("Score: " + score, scoreLabelStyle);

		TextButtonStyle homeButtonstyle = new TextButtonStyle();
		homeButtonstyle.font = AssetsLoader.getLargeFont();
		

		TextButton homeButton = new TextButton("Home", homeButtonstyle);

		table.add(scoreLabel).expand().bottom();
		table.row();
		table.add(homeButton).expand().bottom();

		homeButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				pcs.firePropertyChange("Scorescreen_finished", false, true);
			}

		});

	}

	public void addChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void render() {
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();

	}

	public void setScore(int score, boolean win) {
		if (win) {
			color.set(0, 255, 0, 0);
		}
		scoreLabel.setText("Score: " + score);

	}

	public void setScore(ScoreHandler score) {
		if (score.getRemaningLifeInProcent() > 0) {
			color.set(0, 255, 0, 0);
			scoreLabel.setText("Score: " + score.getTotalScore());
		}else {
			color.set(255, 0, 0, 0);
			scoreLabel.setText("City destroyed!");
		}

	}

	public void show() {
		Gdx.input.setInputProcessor(stage);

	}

}
