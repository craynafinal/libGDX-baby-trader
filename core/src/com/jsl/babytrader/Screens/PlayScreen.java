package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Scenes.Hud;
import com.jsl.babytrader.Tools.InitScreenCreator;

import static com.jsl.babytrader.Data.SharedData.GameState.credit;
import static com.jsl.babytrader.Data.SharedData.GameState.game;
import static com.jsl.babytrader.Data.SharedData.GameState.logo;
import static com.jsl.babytrader.Data.SharedData.GameState.start;

/**
 * Created by crayna on 6/3/17.
 */

public class PlayScreen implements Screen {

    // game
    private BabyTrader game = null;

    // thread test
    BitmapFont bitmapFont = null;
    Thread t = null;
    int test = 1;
    int test_add = 1;

    // view port code
    private OrthographicCamera gamecam = null;
    private Viewport gamePort = null;

    // hud
    private Hud hud;

    public PlayScreen(BabyTrader game) {
        this.game = game;

        // view port setup
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(ConstData.SCREEN_WIDTH, ConstData.SCREEN_HEIGHT, gamecam);

        // hud setup
        hud = new Hud(game.batch);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        // thread test
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
    private void update(float dt) {
        handleInput(dt);
    }

    private void handleInput(float dt) {
        /*
        if(Gdx.input.isTouched()) {
            gamecam.position.x += 100 * dt;
        }
        */
        /*
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2 (0.1f, 0), player.b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2 (-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        */

    }

    @Override
    public void render(float delta) {
        update (delta);

        clearingScreen();

        // viewport
        game.batch.setProjectionMatrix(gamecam.combined);

        switch (SharedData.getGameState()) {
            case logo:
                break;
            case start:
                game.batch.begin();
                //game.batch.draw(texture, 0, 0);
                InitScreenCreator.renderInitScreen(game);
                //bitmapFont.draw(game.batch, Integer.toString(test), 500, 100);

                game.batch.end();
            case credit:
                break;
            case game:
                break;
            default:
                break;
        }
    }

    private static void clearingScreen() {
        Gdx.gl.glClearColor(
                ConstData.COLOR_BG_RED,
                ConstData.COLOR_BG_BLUE,
                ConstData.COLOR_BG_GREEN,
                ConstData.COLOR_BG_ALPHA
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        hud.dispose();
    }
}
