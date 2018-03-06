package com.myseabattle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MySeaBattleGame extends ApplicationAdapter implements MainGame {


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
