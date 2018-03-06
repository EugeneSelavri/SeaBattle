package com.myseabattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private float HEIGHT;
    private float WIDTH;
    private final float mapSize;
    private final float cellSize;
    private Field field1;
    private Field field2;
    private MainGame mainGame;
    private Texture background;
    private Label labelYou;
    private Label labelAI;
    private EffectManager effectManager;

    public GameScreen(final MainGame mainGame, Field field) {
        this.mainGame = mainGame;

        batch = new SpriteBatch();
        background = new Texture("background.png");
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        labelYou = new Label("YOU:", style);
        labelAI = new Label("AI:", style);

        HEIGHT = Gdx.graphics.getHeight();
        WIDTH = Gdx.graphics.getWidth();
        mapSize = WIDTH/2/1.5f;
        cellSize = mapSize/10;
        field1 = field;
        field2 = new Field();
        field2.setField(Map.generate());
        field2.setDrawShips(false);
        Gdx.input.setInputProcessor(new InputAdapter(){

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                float x = (screenX + mapSize - WIDTH + WIDTH/2/5) / mapSize;
                float y = (screenY + mapSize - HEIGHT + HEIGHT/5) / mapSize;

                int i = (int)(x * Field.n);
                int j = Field.n - (int)(y * Field.n) - 1;

                field2.hit(i,j);
                makeExplosion(screenX, (int)(HEIGHT - screenY));
                if (field2.checkDeath()) {
                    ResultScreen rs = new ResultScreen(mainGame, "YOU");
                    mainGame.setScreen(rs);
                }
                logicAI();

                return true;
            }

        });

        batch = new SpriteBatch();
        effectManager = new EffectManager();
    }

    private void makeExplosion(int x, int y) {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal("effects/explosion.p"), Gdx.files.internal("img"));
        effect.setPosition(x, y);
        effect.scaleEffect(0.2f);

        effectManager.addNew(effect);
    }

    @Override
    public void show() {

    }

    public void logicAI() {
        int[][] supposedMap = new int[Field.n][Field.n];
        int i;
        int j;

        do {
            i = MathUtils.random(0, supposedMap.length - 1);
            j = MathUtils.random(0, supposedMap.length - 1);
        } while (supposedMap[i][j] != 0);

        field1.hit(i, j);
//        makeExplosion(screenX, screenY);
        supposedMap[i][j] = 1;
        if (field1.checkDeath()) {
            ResultScreen rs = new ResultScreen(mainGame, "AI");
            mainGame.setScreen(rs);
        }
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        labelYou.setPosition(WIDTH/4 - 10, HEIGHT/5 + mapSize + 20);
        labelYou.draw(batch, 1);
        labelAI.setPosition(WIDTH*3/4 - 10, HEIGHT/5 + mapSize + 20);
        labelAI.draw(batch, 1);
        batch.end();

        field1.draw(WIDTH/2/5, HEIGHT/5, mapSize);
        field2.draw(WIDTH - WIDTH/2/5 - mapSize, HEIGHT/5, mapSize);

        batch.begin();
        effectManager.render(batch, delta);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
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
        effectManager.dispose();
    }
}
