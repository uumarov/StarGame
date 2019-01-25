package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import ru.geekbrains.base.Base2DScreen;

public class GameScreen extends Base2DScreen {
    @Override
    public void render(float delta) {
        super.render(delta);
        draw();
    }


    public void draw() {
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
    }
}
