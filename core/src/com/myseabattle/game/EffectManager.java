package com.myseabattle.game;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Iterator;

public class EffectManager implements Disposable {
    private ArrayList<ParticleEffect> effectList = new ArrayList<ParticleEffect>();

    public void render(SpriteBatch batch, float dt) {
        ArrayList<ParticleEffect> newArrayList = new ArrayList<ParticleEffect>();
        for (ParticleEffect e : effectList) {
            if (!e.isComplete()) {
                newArrayList.add(e);
                e.draw(batch, dt);
            } else {
                e.dispose();
            }
        }
        effectList = newArrayList;
    }

    public void addNew(ParticleEffect t) {
        effectList.add(t);

        t.start();
    }


    @Override
    public void dispose() {
        for (ParticleEffect e : effectList) {
            e.dispose();
        }
    }
}
