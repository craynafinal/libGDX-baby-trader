package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Runnables.Customers;
import com.jsl.babytrader.Runnables.SalesTeam;

/**
 * Actual game screen for play.
 */

public class GameScreen extends BaseScreen {

    // runnables
    private Customers customers = new Customers();
    private SalesTeam salesTeam = new SalesTeam();

    public GameScreen(BabyTrader game) {
        super(game);

        new Thread(customers).start();
        new Thread(salesTeam).start();
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
