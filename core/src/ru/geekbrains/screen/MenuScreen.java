package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    SpriteBatch batch;
    Texture img;
    Texture background;

    Vector2 pos;
    Vector2 v;
    Vector2 touchDownPos;

    boolean keyboardControl = false;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0, 0);
        v = new Vector2(1,1);
        touchDownPos = new Vector2(0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if (!keyboardControl && Math.abs((touchDownPos.x - pos.x)) > 1 && Math.abs((touchDownPos.y - pos.y)) > 1) {
            v.set(touchDownPos.cpy().sub(pos));
            v.nor();
            pos.add(v);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        keyboardControl = false;
        touchDownPos.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touchDown " + touchDownPos.x + " " + touchDownPos.y + " " + v.x + " " + v.y);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        keyboardControl = true;
        switch(keycode){
            case 19:    pos.add(0,10);
                        break;
            case 20:    pos.add(0,-10);
                        break;
            case 21:    pos.add(-10,0);
                        break;
            case 22:    pos.add(10,0);
                        break;
        }
        return super.keyDown(keycode);
    }
}
