package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.environment.AmbientCubemap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class ArmoryDetailedRenderer {

	private PropertyChangeSupport pcs;
	private Stage stage;
	private SpriteBatch spriteBatch;
	private Label assetsLabel, nameLabel, descriptionLabel, powerLabel, cooldownLabel, upgradeLabel;
	private TextButton tradeButton, upgradeButton;
	private Image itemImage, upgradeImage;

	public enum ArmoryDetaliedEvent {
		ARMORY_DETAILED_BACK_PRESSED, ARMORY_DETAILED_ITEM_UPGRADED, ARMORY_DETAILED_TRADE_BUTTON_PRESSED
	}

	public ArmoryDetailedRenderer(PropertyChangeListener listener) {
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		AssetsLoader.loadTexture("PlayButton.png");
		AssetsLoader.finishLoading();
		create();
	}

	public void addChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	private void create() {
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);

		// assetsLabel
		LabelStyle textLabelStyle = new LabelStyle();
		textLabelStyle.font = AssetsLoader.getSmallFont();
		assetsLabel = new Label("", textLabelStyle);
		
		table.add(assetsLabel).right();
		
		// coinImage
		Image coinImage = new Image(AssetsLoader.getTexture("PlayButton.png"));
		
		table.add(coinImage)
		.width(Gdx.graphics.getWidth() * 0.05F)
		.height(Gdx.graphics.getWidth() * 0.05F
				* coinImage.getHeight() / coinImage.getWidth());

		table.row();

		// nameLabel
		LabelStyle nameLabelStyle = new LabelStyle();
		nameLabelStyle.font = AssetsLoader.getMediumFont();
		nameLabel = new Label("", nameLabelStyle);

		table.add(nameLabel).left();

		table.row();

		// itemImage
		itemImage = new Image(AssetsLoader.getTexture("PlayButton.png"));
		table.add(itemImage)
				.width(Gdx.graphics.getWidth() * 0.25F)
				.height(Gdx.graphics.getWidth() * 0.25F
						* itemImage.getHeight() / itemImage.getWidth());

		// descriptionLabel
		descriptionLabel = new Label("", textLabelStyle);
		descriptionLabel.setWrap(true);

		table.add(descriptionLabel).right().fill();

		table.row();
		
		// powerLabel
		powerLabel = new Label("", textLabelStyle);
		
		table.add(powerLabel).left().row();
		
		// cooldownLabel
		cooldownLabel = new Label("", textLabelStyle);

		table.add(cooldownLabel).left().row();
		
		// upgradeImage
		upgradeImage = new Image(AssetsLoader.getTexture("PlayButton.png"));
		table.add(upgradeImage)
				.width(Gdx.graphics.getWidth() * 0.15F)
				.height(Gdx.graphics.getWidth() * 0.15F
						* upgradeImage.getHeight() / upgradeImage.getWidth());

		
		// upgradeLabel
		upgradeLabel = new Label("", textLabelStyle);

		table.add(upgradeLabel).left().expand();

		table.row();

		// textButtonStyle
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = AssetsLoader.getSmallFont();
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(
				AssetsLoader.getTexture("PlayButton.png")));

		// upgradeButton
		upgradeButton = new TextButton("", textButtonStyle);

		upgradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!upgradeButton.isDisabled()) {
					pcs.firePropertyChange(
							ArmoryDetaliedEvent.ARMORY_DETAILED_ITEM_UPGRADED
									.toString(), null, null);
				}
			}
		});

		float aspect = upgradeButton.getHeight() / upgradeButton.getWidth();
		table.add(upgradeButton).left().width(Gdx.graphics.getWidth() * 0.5F)
				.height(Gdx.graphics.getWidth() * 0.5F * aspect);

		// tradeButton
		TextButtonStyle tradeButtonStyle = new TextButtonStyle(textButtonStyle);
		tradeButton = new TextButton("", tradeButtonStyle);

		tradeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!tradeButton.isDisabled()) {
					pcs.firePropertyChange(
							ArmoryDetaliedEvent.ARMORY_DETAILED_TRADE_BUTTON_PRESSED
									.toString(), null, null);
				}
			}
		});

		table.add(tradeButton).right().width(Gdx.graphics.getWidth() * 0.5F)
				.height(Gdx.graphics.getWidth() * 0.5F * aspect);

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
		Gdx.gl.glClearColor(0f, 0.4f, 0.5f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	public void dispose() {
		stage.dispose();
		spriteBatch.dispose();
	}

	public void setAssetsLabelText(String text) {
		assetsLabel.setText(text);
	}

	public void setNameLabelText(String text) {
		nameLabel.setText(text);
	}

	public void setDescriptionLabelText(String text) {
		descriptionLabel.setText(text);
	}
	
	public void setPowerLabelText(String text) {
		powerLabel.setText(text);
	}
	
	public void setCooldownLabelText(String text) {
		cooldownLabel.setText(text);
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
		if (flag) {
			upgradeButton.getStyle().fontColor = Color.LIGHT_GRAY;
		} else {
			upgradeButton.getStyle().fontColor = Color.CYAN;
		}
	}

	public void setTradeButtonDisabled(boolean flag) {
		tradeButton.setDisabled(flag);
		if (flag) {
			tradeButton.getStyle().fontColor = Color.GRAY;
		} else {
			tradeButton.getStyle().fontColor = Color.CYAN;
		}
	}
	
	public void setItemImage(Image image) {
		itemImage = image;
	}
	
	public void setUpgradeImage(Image image) {
		upgradeImage = image;
	}
}
