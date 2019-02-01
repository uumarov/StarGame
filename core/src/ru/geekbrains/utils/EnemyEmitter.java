package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.game.Enemy;

public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 0.7f;
    private static final int ENEMY_SMALL_HP = 100;

    private Vector2 enemySmallV = new Vector2(0, -0.2f);
    private TextureRegion[] enemySmallRegion;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.12f;
    private static final float ENEMY_MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_MEDIUM_BULLET_VY = -0.4f;
    private static final int ENEMY_MEDIUM_DAMAGE = 2;
    private static final float ENEMY_MEDIUM_RELOAD_INTERVAL = 0.6f;
    private static final int ENEMY_MEDIUM_HP = 200;

    private Vector2 enemyMediumV = new Vector2(0, -0.1f);
    private TextureRegion[] enemyMediumRegion;

    private static final float ENEMY_BIG_HEIGHT = 0.17f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.03f;
    private static final float ENEMY_BIG_BULLET_VY = -0.5f;
    private static final int ENEMY_BIG_DAMAGE = 3;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 0.5f;
    private static final int ENEMY_BIG_HP = 300;

    private Vector2 enemyBigV = new Vector2(0, -0.05f);
    private TextureRegion[] enemyBigRegion;

    private TextureRegion bulletRegion;

    private float generateInterval = 4f;
    private float generateTimer;

    private EnemyPool enemyPool;

    private Rect worldBounds;

    public EnemyEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds) {
        this.enemyPool = enemyPool;
        TextureRegion textureRegionSmall = atlas.findRegion("enemy0");
        this.enemySmallRegion = Regions.split(textureRegionSmall, 1,2,2);
        TextureRegion textureRegionMedium = atlas.findRegion("enemy1");
        this.enemyMediumRegion = Regions.split(textureRegionMedium, 1,2,2);
        TextureRegion textureRegionBig = atlas.findRegion("enemy2");
        this.enemyBigRegion = Regions.split(textureRegionBig, 1,2,2);
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.worldBounds = worldBounds;
    }

    public void generate(float delta) {
        int num = Rnd.nextInt(1,3);
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain();
            switch (num) {
                case 1: generateSmall(enemy);
                    break;
                case 2: generateMedium(enemy);
                    break;
                case 3: generateBig(enemy);
                    break;
            }
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }


    }

    public void generateSmall(Enemy enemy) {

            enemy.set(
                    enemySmallRegion,
                    enemySmallV,
                    bulletRegion,
                    ENEMY_SMALL_BULLET_HEIGHT,
                    ENEMY_SMALL_BULLET_VY,
                    ENEMY_SMALL_DAMAGE,
                    ENEMY_SMALL_RELOAD_INTERVAL,
                    ENEMY_SMALL_HEIGHT,
                    ENEMY_SMALL_HP,
                    worldBounds
            );
    }

    public void generateMedium(Enemy enemy) {
            enemy.set(
                    enemyMediumRegion,
                    enemyMediumV,
                    bulletRegion,
                    ENEMY_MEDIUM_BULLET_HEIGHT,
                    ENEMY_MEDIUM_BULLET_VY,
                    ENEMY_MEDIUM_DAMAGE,
                    ENEMY_MEDIUM_RELOAD_INTERVAL,
                    ENEMY_MEDIUM_HEIGHT,
                    ENEMY_MEDIUM_HP,
                    worldBounds
            );
    }

    public void generateBig(Enemy enemy) {
            enemy.set(
                    enemyBigRegion,
                    enemyBigV,
                    bulletRegion,
                    ENEMY_BIG_BULLET_HEIGHT,
                    ENEMY_BIG_BULLET_VY,
                    ENEMY_BIG_DAMAGE,
                    ENEMY_BIG_RELOAD_INTERVAL,
                    ENEMY_BIG_HEIGHT,
                    ENEMY_BIG_HP,
                    worldBounds
            );
    }
}
