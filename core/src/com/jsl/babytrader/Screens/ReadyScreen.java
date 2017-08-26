package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Utilities.CommonUtilities;

/**
 * Ready screen before starting game.
 */
public class ReadyScreen extends BaseScreen {
    private Label label_ready = null;
    private Thread thread_counter = null;

    public ReadyScreen(BabyTrader game) {
        super(game);

        label_ready = new Label("Ready".toUpperCase(), getLabelStyle(FONT_WORK_EXTRA_BOLD, 50, Color.WHITE));
        label_ready.setAlignment(Align.center);
        label_ready.setPosition(SCREEN_WIDTH / 2 - label_ready.getWidth() / 2, SCREEN_HEIGHT / 2 - label_ready.getHeight() / 2);

        addElementsToStage(label_ready);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.getBatch().begin();
        stage.getBatch().end();

        stage.draw();

        game.batch.begin();
        game.batch.end();
    }

    @Override
    public void show() {
        super.show();

        thread_counter = new Thread(new Runnable() {
            @Override
            public void run() {
                CommonUtilities.sleep(3000);

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                    switchScreen(BabyTrader.gameScreen);
                    }
                });
            }
        });

        thread_counter.start();
    }

    @Override
    public void hide() {
       super.hide();
       try {
           thread_counter.interrupt();
           thread_counter.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       System.gc();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
