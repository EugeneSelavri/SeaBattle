package com.seabattle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Field {
    final static int n = 10;
    private boolean drawShips = true;

    public void setDrawShips(boolean drawShips) {
        this.drawShips = drawShips;
    }

    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private int[][] field = new int[n][n];

    public void setField(int[][] field) {
        this.field = field;
    }

    public void draw(float x, float y, float size) {
        final float cellSize = size / n;

        shapeRenderer.setAutoShapeType(true);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        fillColor(x, y, cellSize);
        shapeRenderer.end();

        shapeRenderer.begin();
        shapeRenderer.setColor(Color.WHITE);
        drawLines(x, y, size, cellSize);
        shapeRenderer.end();
    }

    private void drawLines(float xOffset, float yOffset, float size, float d) {
        for (int i = 0; i <= n; i++) {
            shapeRenderer.line(i * d + xOffset, yOffset, i * d + xOffset, size + yOffset);
            shapeRenderer.line(xOffset, i * d + yOffset, size + xOffset, i * d + yOffset);
        }
    }

    public int[][] getField() {
        return field;
    }

    public void hit(int i, int j) {
        if (!isAvailable(i,j)) {
            return;
        }

        if (field[i][j] == 1) {
            field[i][j] = 2;
        }
    }

    private boolean isAvailable(int i, int j) {
        return i >= 0 && i < field.length && j >= 0 && j < field[0].length;
    }

    public boolean checkDeath() {
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }


    private void fillColor(float xOffset, float yOffset, float d) {
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field[i].length ; j++) {
                if (field[i][j]==1) {
                    if (drawShips) {
                        shapeRenderer.setColor(Color.BLUE);
                        shapeRenderer.rect(i * d + xOffset, j * d + yOffset, d, d);
                    }
                }

                if (field[i][j]==2) {
                    shapeRenderer.setColor(Color.RED);
                    shapeRenderer.rect(i * d + xOffset, j * d + yOffset, d, d);
                }
            }
        }
    }
}
