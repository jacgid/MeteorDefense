package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class ArmoryRenderer {

	private Stage stage;
	private SpriteBatch spriteBatch;
	private PropertyChangeSupport pcs;
	private DragAndDrop dragAndDrop;
	private Table topTable, bottomTable;
	private boolean dragFinished;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();

	private ClickListener clickListener = new ClickListener() {
		public void clicked(InputEvent event, float x, float y) {
			if (dragFinished) {
				pcs.firePropertyChange(ArmoryEvent.ARMORY_ITEM_PRESSED
						.toString(), null, event.getListenerActor()
						.getUserObject());
			}
		};
	};

	public enum ArmoryEvent {
		ARMORY_BACK_PRESSED, ARMORY_ITEM_PRESSED
	}

	public ArmoryRenderer(List<AbstractArmoryItem> items,
			List<AbstractArmoryItem> choosenItems,
			PropertyChangeListener listener) {
		create(items, choosenItems);
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		dragFinished = true;
	}


	private void create(List<AbstractArmoryItem> items,
			List<AbstractArmoryItem> choosenItems) {
		stage = new Stage();
		spriteBatch = new SpriteBatch();

		Table foreground = new Table();
		foreground.setFillParent(true);

		topTable = new Table();
		bottomTable = new Table();

		dragAndDrop = new DragAndDrop();

		createActionBar(bottomTable, dragAndDrop, choosenItems);
		createItemGrid(topTable, dragAndDrop, items);

		topTable.top();
		bottomTable.bottom();

		foreground.add(topTable).expand().top();
		foreground.row();
		foreground.add(bottomTable);

		Table background = new Table();
		background.setFillParent(true);
		background.add(new Image(assetsLoader.getTexture("MenuBG.png")))
				.width(Gdx.graphics.getWidth())
				.height(Gdx.graphics.getHeight());

		stage.addActor(background);
		stage.addActor(foreground);

		stage.addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {

				if (keycode == Keys.BACK) {
					pcs.firePropertyChange(
							ArmoryEvent.ARMORY_BACK_PRESSED.toString(), false,
							true);
				}

				return true;
			}

		});

	}

	private void createActionBar(Table table, final DragAndDrop dragAndDrop,
			List<AbstractArmoryItem> items) {

		for (int i = 0; i < items.size(); i++) {
			TextButtonStyle style = new TextButtonStyle();
			style.font = new BitmapFont();
			style.up = new TextureRegionDrawable(new TextureRegion(
					assetsLoader.getTexture((items.get(i).getName() + ".png"))));
			Actor actor = new TextButton("", style);
			actor.setUserObject(items.get(i));
			actor.setName(i + "");
			table.add(actor).pad(10).width(Gdx.graphics.getWidth() / 6.5F)
					.height(Gdx.graphics.getWidth() / 6.5F);
			if (!items.get(i).equals(AbstractArmoryItem.EMPTY_ITEM)) {
				actor.addListener(clickListener);
			}

			if (i != 2) {
				dragAndDrop.addTarget(getTarget(actor));
				dragAndDrop.addSource(getSource(actor));
			}
		}

	}

	private void createItemGrid(Table table, DragAndDrop dragAndDrop,
			List<AbstractArmoryItem> items) {

		table.pad(10);

		for (int i = 1; i <= items.size(); i++) {
			AbstractArmoryItem item = items.get(i - 1);
			TextButtonStyle style = new TextButtonStyle();
			style.font = new BitmapFont();
			style.up = new TextureRegionDrawable(new TextureRegion(
					assetsLoader.getTexture(item.getName() + ".png")));
			Actor actor = new TextButton("", style);
			actor.setUserObject(item);
			actor.setName(i - 1 + "");
			table.add(actor).pad(10).width(Gdx.graphics.getWidth() / 6.5F)
					.height(Gdx.graphics.getWidth() / 6.5F);

			if (!item.equals(AbstractArmoryItem.EMPTY_ITEM)) {
				actor.addListener(clickListener);
			}

			dragAndDrop.addSource(getSource(actor));
			dragAndDrop.addTarget(getTarget(actor));

			if (i % 3 == 0) {
				table.row();
			}
		}

	}

	private Source getSource(Actor actor) {
		return new Source(actor) {

			@Override
			public Payload dragStart(InputEvent event, float x, float y,
					int pointer) {
				dragFinished = false;
				Payload payload = new Payload();
				Table table = new Table();
				table.add(
						new Image(assetsLoader
								.getTexture(((AbstractArmoryItem) getActor()
										.getUserObject()).getName() + ".png")))
						.width(Gdx.graphics.getWidth() / 6.5F)
						.height(Gdx.graphics.getWidth() / 6.5F);
				payload.setDragActor(table);
				getActor().setVisible(false);
				return payload;
			}

			@Override
			public void dragStop(InputEvent event, float x, float y,
					int pointer, Target target) {
				new Thread(new Runnable(){
					@Override
					public void run() {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
						dragFinished = true;						
					}
				}).start();
				if (target == null) {
					getActor().setVisible(true);
				}

			}

		};
	}

	private Target getTarget(Actor actor) {
		return new Target(actor) {

			@Override
			public boolean drag(Source source, Payload payload, float x,
					float y, int pointer) {
				return true;
			}

			@Override
			public void drop(Source source, Payload payload, float x, float y,
					int pointer) {
				Actor target = getActor();
				Actor actor = source.getActor();
				int targetPos = Integer.parseInt(target.getName());
				int actorPos = Integer.parseInt(actor.getName());
				target.setName(actorPos + "");
				actor.setName(targetPos + "");
				actor.setVisible(true);
				if (target.getParent() == topTable) {
					topTable.removeActor(target);
					if (topTable.getChildren().contains(actor, true)) {
						topTable.removeActor(actor);
						if (targetPos < actorPos) {
							updateTable(topTable, actor, targetPos, 3);
							updateTable(topTable, target, actorPos, 3);
						} else {
							updateTable(topTable, target, actorPos, 3);
							updateTable(topTable, actor, targetPos, 3);
						}
					} else {
						bottomTable.removeActor(actor);
						updateTable(topTable, actor, targetPos, 3);
						updateTable(bottomTable, target, actorPos, 6);
					}
				} else {
					bottomTable.removeActor(target);
					if (topTable.getChildren().contains(actor, true)) {
						topTable.removeActor(actor);
						updateTable(bottomTable, actor, targetPos, 6);
						updateTable(topTable, target, actorPos, 3);
					} else {
						bottomTable.removeActor(actor);
						if (targetPos < actorPos) {
							updateTable(bottomTable, actor, targetPos, 6);
							updateTable(bottomTable, target, actorPos, 6);
						} else {
							updateTable(bottomTable, target, actorPos, 6);
							updateTable(bottomTable, actor, targetPos, 6);
						}

					}
				}
			}

		};
	}

	private void updateTable(Table table, Actor actor, int pos, int columns) {
		List<Actor> childs = new ArrayList<Actor>(Arrays.asList(table
				.getChildren().toArray()));
		table.clearChildren();
		// childs.remove(actor);
		childs.add(pos, actor);
		for (int i = 1; i <= childs.size(); i++) {
			table.add(childs.get(i - 1)).pad(10)
					.width(Gdx.graphics.getWidth() / 6.5F)
					.height(Gdx.graphics.getWidth() / 6.5F);
			if (i % columns == 0) {
				table.row();
			}
		}
	}

	public List<AbstractArmoryItem> getSelectedArmoryItems() {
		List<AbstractArmoryItem> choosenItems = new ArrayList<AbstractArmoryItem>(
				5);
		for (Actor actor : bottomTable.getChildren()) {
			choosenItems.add((AbstractArmoryItem) actor.getUserObject());
		}
		return choosenItems;
	}

	public List<AbstractArmoryItem> getUnselectedArmoryItems() {
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>();
		for (Actor actor : topTable.getChildren()) {
			items.add((AbstractArmoryItem) actor.getUserObject());
		}
		return items;
	}

	public void init() {
		Gdx.input.setInputProcessor(stage);
		dragFinished = true;
	}

	public void render() {
		stage.act();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	public void dispose() {
		stage.dispose();
		spriteBatch.dispose();
	}

}
