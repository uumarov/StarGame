package ru.geekbrains.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;
import ru.geekbrains.sprite.menu.ScaledTouchUpButton;

public class PlayButton extends ScaledTouchUpButton {

    private Game game;

    public PlayButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
        setHeightProportion(0.3f);
    }

    @Override
    public void resize(Rect worldBounds) {
        float posX = 0;
        float posY = 0;
        pos.set(posX, posY);
    }

    @Override
    public void action() {
        game.setScreen(new GameScreen());
    }




}
