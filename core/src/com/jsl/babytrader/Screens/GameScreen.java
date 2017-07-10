package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Runnables.PromotionTeam;
import com.jsl.babytrader.Runnables.PurchaseTeam;
import com.jsl.babytrader.Runnables.ResearchTeam;
import com.jsl.babytrader.Runnables.SalesTeam;

/**
 * Actual game screen for play.
 */

public class GameScreen extends BaseScreen {

    private BitmapFont font_nokia = null;
    private final static int FONT_NOKIA_SIZE = 20;
    private final static Color FONT_NOKIA_COLOR = Color.valueOf("2F3A42");


    // runnables
    private PromotionTeam promotionTeam = new PromotionTeam();
    private SalesTeam salesTeam = new SalesTeam();
    private PurchaseTeam purchaseTeam = new PurchaseTeam();
    private ResearchTeam researchTeam = new ResearchTeam();

    public GameScreen(BabyTrader game) {
        super(game);

        font_nokia = generateFont(FONT_NOKIA_PATH, FONT_NOKIA_SIZE, FONT_NOKIA_COLOR);

        // user may upgrade game to allow start additional threads
        // for example, two sales team threads will provide faster sales
        new Thread(promotionTeam).start();
        new Thread(salesTeam).start();
        new Thread(purchaseTeam).start();
        new Thread(researchTeam).start();


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

        font_nokia.draw(game.batch, "$" + SharedData.getMoney(), 100, 100);
        font_nokia.draw(game.batch, "test", 200, 200);

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
