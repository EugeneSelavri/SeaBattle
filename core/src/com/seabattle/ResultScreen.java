package com.seabattle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ResultScreen implements Screen {
    private MainGame mainGame;
    SpriteBatch batch;
    Texture background;
    Label label;

    Stage stage;
    TextButton.TextButtonStyle textButtonStyle;
    BitmapFont font;

    TextButton menuButton;

    public ResultScreen(MainGame mainGame, String winner) {
        this.mainGame = mainGame;
        batch = new SpriteBatch();
        background = new Texture("background.png");
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        label = new Label(winner + " WINNER!!!", style);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        menuButton = new TextButton("Menu", textButtonStyle);

        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen ms = new MenuScreen(mainGame);
                mainGame.setScreen(ms);
            }
        });

        stage.addActor(menuButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0);
        label.setPosition(Gdx.graphics.getWidth()/2 - label.getWidth()/2, Gdx.graphics.getHeight()/2 - label.getHeight()/2);
        label.draw(batch, 1);
        batch.end();

        menuButton.setPosition(Gdx.graphics.getWidth()/2 - menuButton.getWidth()/2, Gdx.graphics.getHeight()/3 - menuButton.getHeight()/2);
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
