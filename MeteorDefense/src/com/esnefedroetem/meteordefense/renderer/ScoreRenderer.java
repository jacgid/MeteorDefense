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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.esnefedroetem.meteordefense.model.ScoreHandler;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class ScoreRenderer {

	private PropertyChangeSupport pcs;

	private SpriteBatch spriteBatch;
	private Stage stage;
	private int score;
	private Label totalScoreLabel,accuracyLabel, remainingLifeLable, meteorScoreLable;
	
	private Color color;
	Table starTable;

	public ScoreRenderer(PropertyChangeListener listener) {
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
			
		spriteBatch = new SpriteBatch();
		stage = new Stage();

		create();
	}

	private void create() {
		color = new Color(255, 0, 0, 0);

		Table table = new Table();
		table.setFillParent(true);

		stage.addActor(table);

		LabelStyle mediumLabelStyle = new LabelStyle();
		mediumLabelStyle.font = AssetsLoader.getMediumFont();
		mediumLabelStyle.fontColor = new Color(Color.DARK_GRAY);
		
		LabelStyle largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = AssetsLoader.getLargeFont();
		largeLabelStyle.fontColor = new Color(Color.WHITE);

		meteorScoreLable = new Label("Meteor score: " , mediumLabelStyle);
		remainingLifeLable = new Label("Remaining life: ", mediumLabelStyle);
		accuracyLabel = new Label("Accuracy: ", mediumLabelStyle);
		totalScoreLabel = new Label("Total score: ", largeLabelStyle);

		TextButtonStyle homeButtonstyle = new TextButtonStyle();
		homeButtonstyle.font = AssetsLoader.getLargeFont();

		TextButton homeButton = new TextButton("Home", homeButtonstyle);

		
		starTable = new Table();

		
		table.add(meteorScoreLable).bottom().expand();
		table.row();
		table.add(remainingLifeLable).bottom();
		table.row();
		table.add(accuracyLabel).bottom();
		table.row();
		table.add(totalScoreLabel).expand().bottom();
		table.row();
		table.add(starTable).expand();
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

	}

	public void setScore(ScoreHandler score) {
		if (score.getRemaningLifeInProcent() > 0) {
			color.set(0, 255, 0, 0);
			
			meteorScoreLable.setText("Meteor score: " + score.getMeteorScore());
			remainingLifeLable.setText("Remaining life: " + (int)(score.getRemaningLifeInProcent()*100) + "%");
			accuracyLabel.setText("Accuracy : " + (int)(score.getAccuracy()*100) + "%");
			totalScoreLabel.setText("Total: " + score.getTotalScore());
			
			starTable.clear();
			// Fills the starTable with golden stars.
			for (int i = 0; i < score.getStars(); i++) {

				starTable.add(new Image(AssetsLoader.getTexture("star.png"))).pad(10)
						.width(Gdx.graphics.getWidth() / 9).height(Gdx.graphics.getWidth() / 9);
			}
			// Fills the starTable with the remaining grey stars if needed.
			for (int i = 2; i > score.getStars() - 1; i--) {

				starTable.add(new Image(AssetsLoader.getTexture("starGrey.png"))).pad(10)
						.width(Gdx.graphics.getWidth() / 9).height(Gdx.graphics.getWidth() / 9);
			}
		} else {
			color.set(255, 0, 0, 0);
			starTable.clear();
			meteorScoreLable.setText("");
			remainingLifeLable.setText("");
			accuracyLabel.setText("");
			totalScoreLabel.setText("City destroyed");
		}

	}

	public void show() {
		Gdx.input.setInputProcessor(stage);

	}

}
