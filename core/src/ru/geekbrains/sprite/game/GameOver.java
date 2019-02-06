package ru.geekbrains.sprite.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class GameOver extends Sprite {
    private final Vector2 v = new Vector2(0, 0.5f);
    private Rect worldBounds;

    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.1f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(getBottom() < worldBounds.getTop()) {
            pos.mulAdd(v, delta);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        hide();
    }

    public void hide(){
        setTop(worldBounds.getBottom());
    }

}
