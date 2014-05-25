package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.model.ScoreHandler;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
/**
 * This class is responsible for rendering the score screen.
 * @author Andreas Pegelow
 *
 */
public class ScoreRenderer {

	private PropertyChangeSupport pcs;

	private SpriteBatch spriteBatch;
	private Stage stage;
	private Label totalScoreLabel,accuracyLabel, remainingLifeLable, meteorScoreLable;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	
	private Color color;
	private Table starTable;
	private Image divider;

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
		
		table.setBackground(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("ArmoryBG.png"))));
		table.setWidth(Gdx.graphics.getWidth());
		
		LabelStyle mediumLabelStyle = new LabelStyle();
		mediumLabelStyle.font = assetsLoader.getMediumFont();
		mediumLabelStyle.fontColor = new Color(Color.WHITE);
		
		LabelStyle largeLabelStyle = new LabelStyle();
		largeLabelStyle.font = assetsLoader.getLargeFont();
		largeLabelStyle.fontColor = new Color(Color.WHITE);

		meteorScoreLable = new Label("Meteor score: " , mediumLabelStyle);
		remainingLifeLable = new Label("Remaining life: ", mediumLabelStyle);
		accuracyLabel = new Label("Accuracy: ", mediumLabelStyle);
		totalScoreLabel = new Label("Total score: ", largeLabelStyle);

		TextButtonStyle homeButtonstyle = new TextButtonStyle();
		homeButtonstyle.font = assetsLoader.getLargeFont();
		homeButtonstyle.up = new TextureRegionDrawable(new TextureRegion(
				assetsLoader.getTexture("ArmoryDetailedButton.png")));
		homeButtonstyle.up.setBottomHeight(Gdx.graphics.getWidth() * 0.02F);


		Table buttonTable = new Table();
		buttonTable.setBackground(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("buttonpanel.png"))));
		
		TextButton homeButton = new TextButton("Home", homeButtonstyle);
		float aspect = homeButton.getHeight() / homeButton.getWidth();
		buttonTable.add(homeButton).center().width(Gdx.graphics.getWidth() * 0.7F)
				.height(Gdx.graphics.getWidth() * 0.7F * aspect).padTop(Gdx.graphics.getHeight() * 0.05f);

		divider = new Image(assetsLoader.getTexture("divider.png"));
		
		starTable = new Table();

		
		table.add(meteorScoreLable).bottom().expand();
		table.row();
		table.add(remainingLifeLable).bottom();
		table.row();
		table.add(accuracyLabel).bottom();
		table.row();
		table.add(divider).width(Gdx.graphics.getWidth()).height(Gdx.graphics.getWidth() * 0.9F
				* divider.getHeight() / divider.getWidth()).padTop(Gdx.graphics.getHeight() * 0.1f).row();
		table.add(totalScoreLabel).expand().bottom();
		table.row();
		table.add(starTable).expand();
		table.row();

		table.add(buttonTable).bottom().height(Gdx.graphics.getHeight() * 0.2f).width(Gdx.graphics.getWidth()).expandX();

		homeButton.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				pcs.firePropertyChange("Scorescreen_finished", false, true);
			}

		});
		
		stage.addActor(table);
	}

	public void render() {
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();

	}

	public void setScore(ScoreHandler score) {
		if (score.getRemaningLifeInProcent() > 0) {
			color.set(0, 255, 0, 0);
			
			meteorScoreLable.setText("Meteor score: " + score.getMeteorScore());
			remainingLifeLable.setText("Remaining life: " + (int)(score.getRemaningLifeInProcent()*100) + "%");
			accuracyLabel.setText("Accuracy: " + (int)(score.getAccuracy()*100) + "%");
			totalScoreLabel.setText("Total: " + score.getTotalScore());
			totalScoreLabel.getStyle().fontColor = Color.WHITE;
			
			starTable.clear();
			// Fills the starTable with golden stars.
			for (int i = 0; i < score.getStars(); i++) {

				starTable.add(new Image(assetsLoader.getTexture("star.png"))).pad(10)
						.width(Gdx.graphics.getWidth() / 9).height(Gdx.graphics.getWidth() / 9);
			}
			// Fills the starTable with the remaining grey stars if needed.
			for (int i = 2; i > score.getStars() - 1; i--) {

				starTable.add(new Image(assetsLoader.getTexture("starGrey.png"))).pad(10)
						.width(Gdx.graphics.getWidth()/9).height(Gdx.graphics.getWidth()/9);
			}
			divider.setVisible(true);
		} else {
			color.set(255, 0, 0, 0);
			starTable.clear();
			meteorScoreLable.setText("");
			remainingLifeLable.setText("");
			accuracyLabel.setText("");
			divider.setVisible(false);
			totalScoreLabel.getStyle().fontColor = Color.RED;
			totalScoreLabel.setText("City destroyed");
		}

	}

	public void show() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(stage);
	}
	
	public void dispose(){
		stage.dispose();
		spriteBatch.dispose();
	}

}
