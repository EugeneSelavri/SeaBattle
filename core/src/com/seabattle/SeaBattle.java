package com.seabattle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class SeaBattle extends ApplicationAdapter implements MainGame {


	private Screen currentScreen;
	
	@Override
	public void create () {
		currentScreen = new MenuScreen(this);
	}

	@Override
	public void render () {
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}


	@Override
	public void dispose () {
		setScreen(null);
	}

	@Override
	public void setScreen(Screen screen) {
		currentScreen.dispose();
		currentScreen = screen;
	}
}
