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
import com.esnefedroetem.meteordefense.util.Constants;

public class ArmoryDetailedRenderer {

	private PropertyChangeSupport pcs;
	private Stage stage;
	private SpriteBatch spriteBatch;
	private Label assetsLabel, nameLabel, descriptionLabel, powerLabel, cooldownLabel, upgradeLabel;
	private TextButton tradeButton, upgradeButton;
	private Image itemImage, upgradeImage;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	private Table descriptionTable, upgradeTable;
	private final float STANDARD_PADDING = Gdx.graphics.getWidth()/25f;

	public enum ArmoryDetaliedEvent {
		ARMORY_DETAILED_BACK_PRESSED, ARMORY_DETAILED_ITEM_UPGRADED, ARMORY_DETAILED_TRADE_BUTTON_PRESSED
	}

	public ArmoryDetailedRenderer(PropertyChangeListener listener) {
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
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
		table.setBackground(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("ArmoryDetailedBackground.png"))));
		
		// assetsTable
		Table assetsTable = new Table();
		assetsTable.setBackground(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("blackpixel.png"))));
		
		// assetsLabel
		LabelStyle textLabelStyle = new LabelStyle();
		textLabelStyle.font = assetsLoader.getExtraSmallFont();
		assetsLabel = new Label("", textLabelStyle);
		
		assetsTable.add(assetsLabel).padRight(STANDARD_PADDING/2).padLeft(STANDARD_PADDING/2).right().expandX();
		
		// coinImage
		Image coinImage = new Image(assetsLoader.getTexture("coin.png"));
		
		assetsTable.add(coinImage)
		.width(Gdx.graphics.getWidth() * 0.05F)
		.height(Gdx.graphics.getWidth() * 0.05F
				* coinImage.getHeight() / coinImage.getWidth()).right().padRight(STANDARD_PADDING/2);

		table.add(assetsTable).width(Gdx.graphics.getWidth());

		table.row();

		
		// nameLabel
		LabelStyle nameLabelStyle = new LabelStyle();
		nameLabelStyle.font = assetsLoader.getSmallFont();
		nameLabel = new Label("", nameLabelStyle);

		table.add(nameLabel).left().padLeft(STANDARD_PADDING).padTop(STANDARD_PADDING/2);
		table.row();
		
		Image divider1 = new Image(assetsLoader.getTexture("divider.png"));
		table.add(divider1).width(Gdx.graphics.getWidth())
				.height(Gdx.graphics.getWidth() * 0.9F
						* divider1.getHeight() / divider1.getWidth()).padBottom(STANDARD_PADDING);

		table.row();

		// descriptionTable
		descriptionTable = new Table();
		
		// itemImage
		itemImage = new Image(assetsLoader.getTexture("PlayButton.png"));
		descriptionTable.add(itemImage)
		.width(Gdx.graphics.getWidth() * 0.25F)
		.height(Gdx.graphics.getWidth() * 0.25F
				* itemImage.getHeight() / itemImage.getWidth()).top().left().padLeft(STANDARD_PADDING).padRight(STANDARD_PADDING);

		// descriptionLabel
		descriptionLabel = new Label("", textLabelStyle);
		descriptionLabel.setWrap(true);

		descriptionTable.add(descriptionLabel).width(Gdx.graphics.getWidth()/1.6f).top().left();

		table.add(descriptionTable).left().top().expand();
		table.row();
		
		// powerLabel
		powerLabel = new Label("", textLabelStyle);
		
		table.add(powerLabel).left().padLeft(STANDARD_PADDING).row();
		
		// cooldownLabel
		cooldownLabel = new Label("", textLabelStyle);

		table.add(cooldownLabel).left().padLeft(STANDARD_PADDING).row();
		
		table.row();
		
		Image divider2 = new Image(assetsLoader.getTexture("divider.png"));
		table.add(divider2).width(Gdx.graphics.getWidth())
				.height(Gdx.graphics.getWidth() * 0.9F
						* divider2.getHeight() / divider2.getWidth()).padTop(STANDARD_PADDING).padBottom(STANDARD_PADDING).row();
		// upgradeTable
		upgradeTable = new Table();
		
		// upgradeImage
		upgradeImage = new Image(assetsLoader.getTexture("PlayButton.png"));
		upgradeTable.add(upgradeImage)
				.width(Gdx.graphics.getWidth() * 0.15F)
				.height(Gdx.graphics.getWidth() * 0.15F
						* upgradeImage.getHeight() / upgradeImage.getWidth()).top().left().padLeft(STANDARD_PADDING);

		
		// upgradeLabel
		upgradeLabel = new Label("", textLabelStyle);

		upgradeTable.add(upgradeLabel).right();
		
		table.add(upgradeTable).left().top().expandY();
		table.row();

		// buttonTable
		Table buttonTable = new Table();
		buttonTable.setBackground(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("buttonpanel.png"))));
				
		// textButtonStyle
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = assetsLoader.getExtraSmallFont();
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(
				assetsLoader.getTexture("ArmoryDetailedButton.png")));
		textButtonStyle.up.setBottomHeight(Gdx.graphics.getWidth() * 0.02F);

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
		buttonTable.add(upgradeButton).left().width(Gdx.graphics.getWidth() * 0.5F)
				.height(Gdx.graphics.getWidth() * 0.5F * aspect).padTop(STANDARD_PADDING * 1.5f);

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

		buttonTable.add(tradeButton).right().width(Gdx.graphics.getWidth() * 0.5F)
				.height(Gdx.graphics.getWidth() * 0.5F * aspect).padTop(STANDARD_PADDING * 1.5f);

		table.add(buttonTable).bottom().height(Gdx.graphics.getHeight() * 0.14f);
		
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
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
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
	
	public void setItemImage(String textureName) {
		itemImage = new Image(assetsLoader.getTexture(textureName));
		
		descriptionTable.reset();
		
		descriptionTable.add(itemImage)
				.width(Gdx.graphics.getWidth() * 0.25F)
				.height(Gdx.graphics.getWidth() * 0.25F
						* itemImage.getHeight() / itemImage.getWidth()).top().left().padLeft(STANDARD_PADDING).padRight(STANDARD_PADDING);

		descriptionTable.add(descriptionLabel).width(Gdx.graphics.getWidth()/1.6f).top().left();
	}
	
	public void setUpgradeImage(String textureName) {
		upgradeImage = new Image(assetsLoader.getTexture(textureName));
		
		upgradeTable.reset();
		
		upgradeTable.add(upgradeImage)
		.width(Gdx.graphics.getWidth() * 0.15F)
		.height(Gdx.graphics.getWidth() * 0.15F
				* upgradeImage.getHeight() / upgradeImage.getWidth()).top().left().padLeft(STANDARD_PADDING);

		upgradeTable.add(upgradeLabel).padLeft(STANDARD_PADDING).right();
	}
}
