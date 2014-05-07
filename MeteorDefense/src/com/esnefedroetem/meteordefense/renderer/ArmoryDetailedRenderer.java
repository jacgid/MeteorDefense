package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.environment.AmbientCubemap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.renderer.MainMenuRenderer.MainMenuEvent;

public class ArmoryDetailedRenderer {

	private PropertyChangeSupport pcs;
	private Stage stage;
	private SpriteBatch spriteBatch;
	private Label nameLabel, descriptionLabel, upgradeLabel;
	private TextButton tradeButton, upgradeButton;

	public enum ArmoryDetaliedEvent {
		ARMORY_DETAILED_BACK_PRESSED,
		ARMORY_DETAILED_ITEM_UPGRADED,
		ARMORY_DETAILED_TRADE_BUTTON_PRESSED
	}

	public ArmoryDetailedRenderer() {
		pcs = new PropertyChangeSupport(this);
		create();
	}

	private void create() {
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);

		// nameLabel
		LabelStyle nameLabelStyle = new LabelStyle();
		nameLabelStyle.font = new BitmapFont();
		nameLabel = new Label("", nameLabelStyle);
		
		table.add(nameLabel).right().expandX();
		
		table.row();
		
		// descriptionLabel
		LabelStyle descriptionLabelStyle = new LabelStyle();
		descriptionLabelStyle.font = new BitmapFont();
		descriptionLabel = new Label("", descriptionLabelStyle);
		descriptionLabel.setWrap(true);
		
		table.add(descriptionLabel).maxWidth(550).fill();

		table.row();
		
		// upgradeLabel
		upgradeLabel = new Label("", descriptionLabelStyle);
		
		table.add(upgradeLabel);
		
		table.row();

		// textButtonStyle
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = new BitmapFont();

		// upgradeButton
		upgradeButton = new TextButton("", textButtonStyle);
				
		upgradeButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
		 		pcs.firePropertyChange(ArmoryDetaliedEvent.ARMORY_DETAILED_ITEM_UPGRADED.toString(), null, null);
			}
		});
		
		table.add(upgradeButton).left();
		
		// tradeButton
		tradeButton = new TextButton("", textButtonStyle);
		
		tradeButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				pcs.firePropertyChange(ArmoryDetaliedEvent.ARMORY_DETAILED_TRADE_BUTTON_PRESSED.toString(), null, null);				
			
		}});
		
		table.add(tradeButton).right();



		stage.addActor(table);

		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.BACK) {
					pcs.firePropertyChange(
							ArmoryDetaliedEvent.ARMORY_DETAILED_BACK_PRESSED
									.toString(), false, true);
				}
				return true;
			}
		});
	}

	public void init() {
		Gdx.input.setInputProcessor(stage);
	}

	public void render() {
		stage.act();
		Gdx.gl.glClearColor(0, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	public void dispose() {
		stage.dispose();
		spriteBatch.dispose();
	}

	public void addChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void setNameLabelText(String text) {
		nameLabel.setText(text);
	}
	
	public void setDescriptionLabelText(String text) {
		descriptionLabel.setText(text);
	}
	
	public void setUpgradeLabelText(String text) {
		upgradeLabel.setText(text);
	}
	
	public void setUpgradeButtonText(String text) {
		upgradeButton.setText(text);
	}
	
	public void setTradeButtonText(String text) {
		tradeButton.setText(text);
	}
	
	public void setUpgradeButtonDisabled(boolean flag) {
		upgradeButton.setDisabled(flag);
	}

	public void setTradeButtonDisabled(boolean flag) {
		tradeButton.setDisabled(flag);
	}
}
