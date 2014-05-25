package com.esnefedroetem.meteordefense.renderer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esnefedroetem.meteordefense.model.City;
import com.esnefedroetem.meteordefense.model.Continent;
import com.esnefedroetem.meteordefense.util.AssetsLoader;

/**
 * 
 * This class is responsible for visualizing CarouselScreen.
 * 
 * @author Jacob
 *
 */
public class CarouselRenderer {
	
	private PropertyChangeSupport pcs;
	private SpriteBatch spriteBatch;
	private Stage stage;
	private PagedScrollPane scroll;
	private AssetsLoader assetsLoader = AssetsLoader.getInstance();
	
	public enum CarouselEvent{
		CAROUSEL_ARMORY_CLICKED,
		CAROUSEL_CLICKED,
		CAROUSEL_NEWGAME,
		CAROUSEL_BACKBUTTON1,
		CAROUSEL_BACKBUTTON2
		
	}
	
	private ClickListener carouselListener = new ClickListener(){
	 	public void clicked (InputEvent event, float x, float y) {
	 		pcs.firePropertyChange(CarouselEvent.CAROUSEL_CLICKED.toString(), -1, Integer.parseInt(event.getListenerActor().getName()));
	 	}		
	};
	
	public CarouselRenderer(PropertyChangeListener listener){
		pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(listener);
		spriteBatch = new SpriteBatch();
		stage = new Stage();
		
		create();
	}
	
	public void addChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	private void create(){
		scroll = new PagedScrollPane();
		scroll.setFlingTime(0.1f);
		scroll.setPageSpacing(30);
		scroll.setScrollingDisabled(false, true);
		
		Table table = new Table();
		Table background = new Table();
		background.setFillParent(true);
		background.add(new Image(assetsLoader.getTexture("MenuBG.png"))).width(Gdx.graphics.getWidth()).height(Gdx.graphics.getHeight());
		stage.addActor(background);
		stage.addActor(table);
		table.setFillParent(true);
		
		ButtonStyle armoryButtonstyle = new ButtonStyle();
		armoryButtonstyle.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("armoryIcon.png")));
		
		
		Button armoryButton = new Button(armoryButtonstyle);
		
		table.add(scroll).expand().fill();
		table.row();
		table.add(armoryButton).bottom().left().width(Gdx.graphics.getWidth() / 5).height(Gdx.graphics.getWidth() / 5);
				
		armoryButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
		 		pcs.firePropertyChange(CarouselEvent.CAROUSEL_ARMORY_CLICKED.toString(), false, true);				
			}
		});
		
		stage.addListener(new InputListener(){
			
			@Override
			public boolean keyDown(InputEvent event,
		              int keycode){
				
				if(keycode == Keys.BACK || keycode == Keys.BACKSPACE){
			 		pcs.firePropertyChange(CarouselEvent.CAROUSEL_BACKBUTTON1.toString(), false, true);					
				}
				
				return true;
			}
			
		});
		
	}
	
	public void init(){
		Gdx.input.setInputProcessor(stage);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void displayContinents(List<Continent> list){
		scroll.setScrollX(0);
		scroll.removeAllPages();
		ButtonStyle btnStyle = new ButtonStyle();
		btnStyle.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("CarouselBackground.png")));
		btnStyle.disabled = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("CarouselBackgroundLocked.png")));
		LabelStyle lblStyle1 = new LabelStyle();
		lblStyle1.font = assetsLoader.getLargeFont();
		LabelStyle lblStyle2 = new LabelStyle();
		lblStyle2.font = assetsLoader.getMediumFont();
		float pageWidth = Gdx.graphics.getWidth() * 0.75F;
		float pageHeight = pageWidth * btnStyle.up.getMinHeight() / btnStyle.up.getMinWidth();
		int count = 0;
		for(Continent continent : list){
			scroll.addPage(getContinentPage(continent, count, btnStyle, lblStyle1, lblStyle2), pageWidth, pageHeight);
			count++;
		}
	}
	
	private Button getContinentPage(Continent continent, int position, ButtonStyle btnStyle, LabelStyle lblStyle1, LabelStyle lblStyle2){
		
		Label lblName = new Label(continent.getName(), lblStyle1);
		Label lblStars = new Label(continent.getStars() + "/" + continent.getAllStars(), lblStyle2);
		Image imgStar = new Image(assetsLoader.getTexture("star.png"));
		Image imgContinent = new Image(assetsLoader.getTexture(continent.getName() + ".png"));
		
		Table tableStars = new Table();
		tableStars.add(lblStars).right().padRight(10);
		tableStars.add(imgStar).right().width(Gdx.graphics.getWidth() / 10).height(Gdx.graphics.getWidth() / 10);
		
		Button btn = new Button(btnStyle);
		btn.add(lblName).top().center().expandX().padTop(30);
		btn.row();
		btn.add(tableStars).center().expandX().pad(20);
		btn.row().expand();
		float aspectRatio = imgContinent.getHeight() / imgContinent.getWidth();
		btn.add(imgContinent).width(Gdx.graphics.getWidth() * 0.75F).height(Gdx.graphics.getWidth() * 0.75F * aspectRatio);
		
		btn.setName(position + "");
		btn.addListener(carouselListener);
		
		return btn;
	}
	
	public void displayCities(List<City> list){
		scroll.setScrollX(0);
		scroll.removeAllPages();
		ButtonStyle btnStyle = new ButtonStyle();
		btnStyle.up = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("CarouselBackground.png")));
		btnStyle.disabled = new TextureRegionDrawable(new TextureRegion(assetsLoader.getTexture("CarouselBackgroundLocked.png")));
		LabelStyle lblStyle1 = new LabelStyle();
		lblStyle1.font = assetsLoader.getLargeFont();
		LabelStyle lblStyle2 = new LabelStyle();
		lblStyle2.font = assetsLoader.getSmallFont();
		float pageWidth = Gdx.graphics.getWidth() * 0.75F;
		float pageHeight = pageWidth * btnStyle.up.getMinHeight() / btnStyle.up.getMinWidth();

		int count = 0;
		for(City city : list){
			scroll.addPage(getCityPage(city, count, btnStyle, lblStyle1, lblStyle2), pageWidth, pageHeight);
			count++;
		}
	}

	private Button getCityPage(City city, int position, ButtonStyle btnStyle, LabelStyle lblStyle1, LabelStyle lblStyle2){
		
		Button btn = new Button(btnStyle);
		Label lblName = new Label(city.getName(), lblStyle1);
		Label lblScore = new Label("High score: " + city.getMaxScore(), lblStyle2);
		Image imgCity = null;
		Image[] stars = new Image[3];
		for(int i = 0; i < city.getStars(); i++){
			stars[i] = new Image(assetsLoader.getTexture("star.png"));
		}
		for(int i = 2; i > city.getStars() - 1; i--){
			stars[i] = new Image(assetsLoader.getTexture("starGrey.png"));
		}
		Table starTable = new Table();
		starTable.add(stars[0]).pad(10).width(Gdx.graphics.getWidth() / 9).height(Gdx.graphics.getWidth() / 9);
		starTable.add(stars[1]).pad(10).width(Gdx.graphics.getWidth() / 9).height(Gdx.graphics.getWidth() / 9);
		starTable.add(stars[2]).pad(10).width(Gdx.graphics.getWidth() / 9).height(Gdx.graphics.getWidth() / 9);
		
		if(city.getState() == City.State.LOCKED){
			imgCity = new Image(assetsLoader.getTexture("lock.png"));
			btn.setDisabled(true);
		}else{
			imgCity =  new Image(assetsLoader.getTexture(city.getName() + ".png"));
		}
		
		btn.add(lblName).expandX();
		btn.row();
		float aspectratio = imgCity.getHeight() / imgCity.getWidth();
		btn.add(imgCity).expand().width(Gdx.graphics.getWidth() * 0.45F).height(Gdx.graphics.getWidth() * 0.45F * aspectratio);
		btn.row();
		btn.add(starTable).expandX();
		btn.row();
		btn.add(lblScore).expandX().pad(20);
		
		btn.setName(position + "");
		btn.addListener(carouselListener);

		return btn;
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
		spriteBatch.dispose();
		stage.dispose();
	}
	
}
