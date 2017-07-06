package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.BabyTrader;

/**
 * Actual game screen for play.
 */

public class GameScreen extends BaseScreen {


    public GameScreen(BabyTrader game) {
        super(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        // stage.draw() must appear before game batch
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        game.batch.begin();
        game.batch.end();

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
        super.dispose();
    }
}
