package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private static final float V_LEN = 0.01f;
    private static final float STEP = 0.1f;


    Texture img;
    Texture background;

    Vector2 pos;
    Vector2 v;
    Vector2 touch;
    Vector2 buf;

    boolean keyboardControl = false;

    @Override
    public void show() {
        super.show();
        background = new Texture("bg.png");
        img = new Texture("badlogic.jpg");
        pos = new Vector2(-0.5f, -0.5f);
        v = new Vector2(0f, 0f);
        touch = new Vector2(0f,0f);
        buf = new Vector2(0f,0f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, -0.5f, -0.5f, 1f, 1f);
        batch.draw(img, pos.x, pos.y, 0.5f, 0.5f);
        batch.end();
        buf.set(touch);
        if (!keyboardControl && buf.sub(pos).len() > V_LEN) {
            pos.add(v);
        } else if(!keyboardControl) {
            pos.set(touch);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        keyboardControl = false;
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos).setLength(V_LEN));
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        keyboardControl = true;
        switch(keycode){
            case 19:    pos.add(0,STEP);
                break;
            case 20:    pos.add(0,-STEP);
                break;
            case 21:    pos.add(-STEP,0);
                break;
            case 22:    pos.add(STEP,0);
                break;
        }
        return super.keyDown(keycode);
    }
}
