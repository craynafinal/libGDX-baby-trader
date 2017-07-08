package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Runnables.PromotionTeam;
import com.jsl.babytrader.Runnables.PurchaseTeam;
import com.jsl.babytrader.Runnables.SalesTeam;

/**
 * Actual game screen for play.
 */

public class GameScreen extends BaseScreen {

    // runnables
    private PromotionTeam promotionTeam = new PromotionTeam();
    private SalesTeam salesTeam = new SalesTeam();
    private PurchaseTeam purchaseTeam = new PurchaseTeam();

    public GameScreen(BabyTrader game) {
        super(game);

        new Thread(promotionTeam).start();
        new Thread(salesTeam).start();
        new Thread(purchaseTeam).start();
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
