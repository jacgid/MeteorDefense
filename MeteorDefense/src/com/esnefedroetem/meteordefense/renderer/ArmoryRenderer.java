package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem.State;
import com.esnefedroetem.meteordefense.util.AssetsLoader;
import com.esnefedroetem.meteordefense.util.Constants;

public class ArmoryRenderer {

	private Stage stage;
	private SpriteBatch spriteBatch;
	private PropertyChangeSupport pcs;
	private DragAndDrop dragAndDrop;
	private Table topTable, bottomTable;
	private boolean dragFinished;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	private AbstractArmoryItem standardItem;
	private float aspect;
	private final int columns = 4;

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

		standardItem = choosenItems.get(2);

		topTable.top();
		bottomTable.bottom();
		bottomTable.setBackground(new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("buttonpanel.png"))));

		foreground.add(topTable).expand().top();
		foreground.row();
		foreground.add(bottomTable).height(Gdx.graphics.getHeight()/5.5f);

		Table background = new Table();
		background.setFillParent(true);
		background.add(new Image(assetsLoader.getTexture("ArmoryBG.png")))
				.width(Gdx.graphics.getWidth())
				.height(Gdx.graphics.getHeight());

		stage.addActor(background);
		stage.addActor(foreground);

		stage.addListener(new InputListener() {

			@Override
			public boolean keyDown(InputEvent event, int keycode) {

				if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
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
			ButtonStyle style = new ButtonStyle();
			style.up = new TextureRegionDrawable(new TextureRegion(
					assetsLoader.getTexture((items.get(i).getName() + ".png"))));
			Actor actor = new Button(style);
			actor.setUserObject(items.get(i));
			actor.setName(i + "");
			float aspect = style.up.getMinHeight() / style.up.getMinWidth();
			if(i == 2){
				this.aspect = aspect;
			}
			table.add(actor).width(Gdx.graphics.getWidth() / 5.5F)
			.height(Gdx.graphics.getWidth() / 5.5F * aspect);
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
			ButtonStyle style = new ButtonStyle();
			style.up = new TextureRegionDrawable(new TextureRegion(
					assetsLoader.getTexture(item.getName() + ".png")));
			Actor actor = new Button(style);
			actor.setUserObject(item);
			actor.setName(i - 1 + "");
			table.add(actor).width(Gdx.graphics.getWidth() / 5.5F)
					.height(Gdx.graphics.getWidth() / 5.5F);

			if (!item.equals(AbstractArmoryItem.EMPTY_ITEM)) {
				actor.addListener(clickListener);
			}

			dragAndDrop.addSource(getSource(actor));
			dragAndDrop.addTarget(getTarget(actor));

			if (i % columns == 0) {
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
						.width(Gdx.graphics.getWidth() / 5.5F)
						.height(Gdx.graphics.getWidth() / 5.5F);
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
				actor.setVisible(true);
				if( ((AbstractArmoryItem)target.getUserObject()).getState() == AbstractArmoryItem.State.LOCKED 
						|| ((AbstractArmoryItem)actor.getUserObject()).getState() == AbstractArmoryItem.State.LOCKED){
					return;
				}
				int targetPos = Integer.parseInt(target.getName());
				int actorPos = Integer.parseInt(actor.getName());
				target.setName(actorPos + "");
				actor.setName(targetPos + "");
				if (target.getParent() == topTable) {
					topTable.removeActor(target);
					if (topTable.getChildren().contains(actor, true)) {
						topTable.removeActor(actor);
						if (targetPos < actorPos) {
							updateTable(topTable, actor, targetPos, columns);
							updateTable(topTable, target, actorPos, columns);
						} else {
							updateTable(topTable, target, actorPos, columns);
							updateTable(topTable, actor, targetPos, columns);
						}
					} else {
						bottomTable.removeActor(actor);
						updateTable(topTable, actor, targetPos, columns);
						updateTable(bottomTable, target, actorPos, 6);
					}
				} else {
					bottomTable.removeActor(target);
					if (topTable.getChildren().contains(actor, true)) {
						topTable.removeActor(actor);
						updateTable(bottomTable, actor, targetPos, 6);
						updateTable(topTable, target, actorPos, columns);
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
			Actor child = childs.get(i - 1); 
			if(child.getUserObject().equals(standardItem)){
				table.add(child)
				.width(Gdx.graphics.getWidth() / 5.5F)
				.height(Gdx.graphics.getWidth() / 5.5F * aspect);
				
			}else{
				table.add(child)
					.width(Gdx.graphics.getWidth() / 5.5F)
					.height(Gdx.graphics.getWidth() / 5.5F);
			}
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
	
	private void removeSoldItems(){
		Actor actor = getSoldItem();
		while(actor != null){
			Actor emptyActor = getEmptyItem();
			int emptyPos = Integer.parseInt(emptyActor.getName());
			int actorPos = Integer.parseInt(actor.getName());
			emptyActor.setName(actorPos + "");
			actor.setName(emptyPos + "");
			bottomTable.removeActor(actor);
			topTable.removeActor(emptyActor);
			updateTable(bottomTable, emptyActor, actorPos, 6);
			updateTable(topTable, actor, emptyPos, columns);
			
			actor = getSoldItem();
		}
		
	}
	
	private Actor getSoldItem(){
		for(Actor actor : bottomTable.getChildren()){
			AbstractArmoryItem item = (AbstractArmoryItem)actor.getUserObject();
			if(item.getState() == AbstractArmoryItem.State.LOCKED){
				return actor;
			}
		}
		return null;
	}
	
	private Actor getEmptyItem(){
		for(Actor actor : topTable.getChildren()){
			AbstractArmoryItem item = (AbstractArmoryItem)actor.getUserObject();
			if(item.equals(AbstractArmoryItem.EMPTY_ITEM)){
				return actor;
			}
		}
		return null;		
	}

	public void init() {
		Gdx.input.setInputProcessor(stage);
		dragFinished = true;
		removeSoldItems();
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
