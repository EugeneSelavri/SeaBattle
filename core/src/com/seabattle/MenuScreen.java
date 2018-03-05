package com.seabattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {
    private MainGame mainGame;
    SpriteBatch batch;
    Texture background;
    Stage stage;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;

    TextButton newGameButton;
    TextButton exitButton;

    public MenuScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        newGameButton = new TextButton("New game", textButtonStyle);
        exitButton = new TextButton("Exit", textButtonStyle);

        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GenerateScreen generateScreen = new GenerateScreen(mainGame);
                mainGame.setScreen(generateScreen);
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        stage.addActor(newGameButton);
        stage.addActor(exitButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - newGameButton.getWidth()/2, Gdx.graphics.getHeight()/2 + 20);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - exitButton.getWidth()/2, Gdx.graphics.getHeight()/2 - 20);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
