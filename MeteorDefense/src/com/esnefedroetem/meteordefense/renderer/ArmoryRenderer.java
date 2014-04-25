package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esnefedroetem.meteordefense.model.armoryitem.AbstractArmoryItem;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

public class ArmoryRenderer {
	
	private Stage stage;
	private SpriteBatch spriteBatch;
	private PropertyChangeSupport pcs;
	private DragAndDrop dragAndDrop;
	private Table topTable, bottomTable;
	private int topTableCount;
	
	private ClickListener clickListener = new ClickListener(){
		public void clicked(InputEvent event, float x, float y) {
			pcs.firePropertyChange(ArmoryEvent.ARMORY_ITEM_PRESSED.toString(), null, event.getListenerActor().getUserObject());
		};
	};
	
	public enum ArmoryEvent{
		ARMORY_BACK_PRESSED,
		ARMORY_ITEM_PRESSED
	}
	
	public ArmoryRenderer(List<AbstractArmoryItem> items, List<AbstractArmoryItem> choosenItems){
		create(items, choosenItems);
		pcs = new PropertyChangeSupport(this);
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	private void create(List<AbstractArmoryItem> items, List<AbstractArmoryItem> choosenItems){
		stage = new Stage();
		spriteBatch = new SpriteBatch();
		
		Table table = new Table();
		table.setFillParent(true);
		
		topTable = new Table();
		bottomTable = new Table();
		
		dragAndDrop = new DragAndDrop();
		
		createActionBar(bottomTable, dragAndDrop, choosenItems);
		createItemGrid(topTable, dragAndDrop, items);
		
		topTableCount = topTable.getChildren().size;
		
		topTable.top();
		bottomTable.bottom();

		table.add(topTable).expand().top();
		table.row();
		table.add(bottomTable);
		stage.addActor(table);
		
		stage.addListener(new InputListener(){
			
			@Override
			public boolean keyDown(InputEvent event,
		              int keycode){
				
				if(keycode == Keys.BACK){
			 		pcs.firePropertyChange(ArmoryEvent.ARMORY_BACK_PRESSED.toString(), false, true);					
				}
				
				return true;
			}
			
		});

	}
	
	private void createActionBar(Table table, final DragAndDrop dragAndDrop, List<AbstractArmoryItem> items){
		TextButtonStyle style = new TextButtonStyle();
		style.font = new BitmapFont();
		style.up = new TextureRegionDrawable(new TextureRegion(new Texture("data/textures/weapon1.png")));

		for(int i = 0; i < items.size(); i++){
			Actor actor = null;
			if(items.get(i) == null){
				actor = new Image(new Texture("data/textures/weaponslot.png"));
			}else{
				actor = new TextButton("", style);
				actor.addListener(clickListener);
			}
			actor.setUserObject(items.get(i));
			actor.setName(i + "");
			table.add(actor).pad(10);
			
			if(i != 2){
				dragAndDrop.addTarget(getTarget(actor));
				dragAndDrop.addSource(getSource(actor));
			}
		}
		
	}
	

	private void createItemGrid(Table table, DragAndDrop dragAndDrop, List<AbstractArmoryItem> items){
		TextButtonStyle style = new TextButtonStyle();
		style.font = new BitmapFont();
		style.up = new TextureRegionDrawable(new TextureRegion(new Texture("data/textures/weapon1.png")));		
		
		table.pad(10);
		
		for(int i = 1; i <= items.size(); i++){
			Actor actor = null;
			if(items.get(i - 1) == null){
				actor = new Image(new Texture("data/textures/weaponslot.png"));
			}else{
				actor = new TextButton("", style);
				actor.addListener(clickListener);
			}
			actor.setUserObject(items.get(i - 1));
			actor.setName(i - 1 + "");
			table.add(actor).pad(10);
			
			dragAndDrop.addSource(getSource(actor));
			dragAndDrop.addTarget(getTarget(actor));
			
			if(i % 3 == 0){
				table.row();
			}
		}
		
		
	}
	
	
	private Source getSource(Actor actor){
		return new Source(actor) {
			
			@Override
			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
				Payload payload = new Payload();
				payload.setDragActor(getActor());
				return payload;
			}
			
			@Override
			public void dragStop(InputEvent event, float x, float y,
					int pointer, Target target) {
				if(target == null){
					int pos = Integer.parseInt(getActor().getName());
					if(topTable.getChildren().size < topTableCount){
						updateTable(topTable, getActor(), pos, 3);
					}else{
						updateTable(bottomTable, getActor(), pos, 6);
					}
				}
				
			}
			
		};
	}
	
	
	private Target getTarget(Actor actor){
		return new Target(actor){

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
				if(target.getParent() == topTable){
					if(topTable.getChildren().size < topTableCount){
						if(targetPos < actorPos){
							updateTable(topTable, actor, targetPos, 3);
							updateTable(topTable, target, actorPos, 3);							
						}else{
							updateTable(topTable, target, actorPos, 3);							
							updateTable(topTable, actor, targetPos, 3);
						}
					}else{
						updateTable(topTable, actor, targetPos, 3);
						updateTable(bottomTable, target, actorPos, 6);
					}
				}else{
					if(topTable.getChildren().size < topTableCount){
						updateTable(bottomTable, actor, targetPos, 6);
						updateTable(topTable, target, actorPos, 3);
					}else{
						if(targetPos < actorPos){
							updateTable(bottomTable, actor, targetPos, 6);
							updateTable(bottomTable, target, actorPos, 6);							
						}else{
							updateTable(bottomTable, target, actorPos, 6);							
							updateTable(bottomTable, actor, targetPos, 6);
						}
						
					}					
				}
			}
			
		};
	}
	
	private void updateTable(Table table, Actor actor, int pos, int columns){
		List<Actor> childs = new ArrayList<Actor>(Arrays.asList(table.getChildren().toArray()));
		table.clearChildren();
		childs.add(pos, actor);
		for(int i = 1; i <= childs.size(); i++){
			table.add(childs.get(i - 1)).pad(10);
			if(i % columns == 0){
				table.row();
			}
		}
	}
	
	public List<AbstractArmoryItem> getSelectedArmoryItems(){
		List<AbstractArmoryItem> choosenItems = new ArrayList<AbstractArmoryItem>(5);
		for(Actor actor : bottomTable.getChildren()){
			choosenItems.add((AbstractArmoryItem)actor.getUserObject());
		}
		return choosenItems;
	}
	
	public List<AbstractArmoryItem> getUnselectedArmoryItems(){
		List<AbstractArmoryItem> items = new ArrayList<AbstractArmoryItem>();
		for(Actor actor : topTable.getChildren()){
			items.add((AbstractArmoryItem)actor.getUserObject());
		}
		return items;
	}
	
	public void init(){
		Gdx.input.setInputProcessor(stage);
	}
	
	public void render(){
		stage.act();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
	
	public void dispose(){
		stage.dispose();
		spriteBatch.dispose();
	}
	
	
	
}
