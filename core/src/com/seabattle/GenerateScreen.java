package com.seabattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GenerateScreen implements Screen {
    private MainGame mainGame;

    SpriteBatch batch;
    Stage stage;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;
    Field field;
    Texture background;

    TextButton generateButton;
    TextButton startGameButton;

    public GenerateScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        field = new Field();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        generateButton = new TextButton("Generate map", textButtonStyle);
        startGameButton = new TextButton("Start game", textButtonStyle);

        generateButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("print generate");
                field.setField(Map.generate());
            }
        });

        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen gameScreen = new GameScreen(mainGame, field);
                mainGame.setScreen(gameScreen);
            }
        });

        stage.addActor(generateButton);
        stage.addActor(startGameButton);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        field.draw(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/2/1.5f/2, Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth()/2/1.5f);

        generateButton.setPosition(Gdx.graphics.getWidth()/2 - generateButton.getWidth()/2, Gdx.graphics.getHeight()/5 - 10);
        startGameButton.setPosition(Gdx.graphics.getWidth()/2 - startGameButton.getWidth()/2, Gdx.graphics.getHeight()/5 - 60);
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
