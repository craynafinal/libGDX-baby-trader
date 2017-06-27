package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Scenes.Hud;

/**
 * Created by crayna on 6/3/17.
 */

public class PlayScreen implements Screen {

    private BabyTrader game = null;
    //Texture texture = null;
    BitmapFont bitmapFont = null;

    // view port code
    private OrthographicCamera gamecam = null;
    private Viewport gamePort = null;

    // hud
    private Hud hud;

    // tileset
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    Thread t = null;

    int test = 1;
    int test_add = 1;

    public PlayScreen(BabyTrader game) {
        this.game = game;
        //texture = new Texture("badlogic.jpg");

        // view port code
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(BabyTrader.V_WIDTH, BabyTrader.V_HEIGHT, gamecam);

        hud = new Hud(game.batch);

        // tile
        maploader = new TmxMapLoader();
        map = maploader.load("map/test.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        bitmapFont = new BitmapFont();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int thread_test = test + test_add;

                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            test = thread_test;
                        }
                    });
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                // 1. plan is to have static methods of store components inside while loop
                // the methods will return some value
                // and it will be passed to members of playscreen
                // 2. inside the methods, it can have synchronized sections to be synchronized
                // 3. shared data between store components maybe in the team abstract class
                // so it won't be an interface


                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int thread_test_add = test_add + 1;

                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            test_add = thread_test_add;
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void show() {

    }

    // checking inputs
    public void update(float dt) {
        handleInput(dt);

        // update camera
        gamecam.update();

        renderer.setView(gamecam);
    }

    private void handleInput(float dt) {
        if(Gdx.input.isTouched()) {
            gamecam.position.x += 100 * dt;
        }
    }

    @Override
    public void render(float delta) {
        update (delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // should happen after clearing screen
        renderer.render();

        //game.batch.setProjectionMatrix(gamecam.combined);

        //game.batch.begin();
        //game.batch.draw(texture, 0, 0);

        //bitmapFont.draw(game.batch, Integer.toString(test), 500, 100);

        //game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
