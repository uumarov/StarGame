package ru.geekbrains.sprite.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;
import ru.geekbrains.sprite.menu.ScaledTouchUpButton;

public class NewGameButton extends ScaledTouchUpButton {
    private final Vector2 v = new Vector2(0, 0.5f);

    private GameScreen gameScreen;
    private Rect worldBounds;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
        setHeightProportion(0.1f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if(getTop() < 0) {
            pos.mulAdd(v, delta);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        hide();
    }

    @Override
    public void action() {
        gameScreen.getMainShip().flushDestroy();
        gameScreen.getMainShip().refreshHp();
        gameScreen.getGameOver().hide();
        hide();
    }

    public void hide(){
        setTop(worldBounds.getBottom() - 0.12f);
    }
}
