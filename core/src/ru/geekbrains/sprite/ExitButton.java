package ru.geekbrains.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.menu.ScaledTouchUpButton;

public class ExitButton extends ScaledTouchUpButton {

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
        setHeightProportion(0.1f);
    }

    @Override
    public void resize(Rect worldBounds) {
        float posX = worldBounds.getRight() - halfWidth;
        float posY = worldBounds.getTop() - halfHeight;
        pos.set(posX, posY);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }


}