package com.jsl.babytrader.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

/**
 * Created by crayna on 6/27/17.
 */

public class InitScreenCreator extends ScreenCreator {
    // pure graphics
    private static Texture babyTrader = new Texture("sprites/startPage_babyTrader_558x326.png");
    private static Texture title = new Texture("sprites/startPage_title_244x177.png");
    private static Texture copyright = new Texture("sprites/startPage_copyright_171x533.png");

    // buttons
    private static Stage stage = new Stage(new FitViewport(ConstData.SCREEN_WIDTH, ConstData.SCREEN_HEIGHT));

    private static Texture button_start_up = new Texture("sprites/startPage_startButton_191x357.png");
    private static Texture button_start_down = new Texture("sprites/startPage_startButton_inv_191x357.png");
    private static Texture button_credit_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    private static Texture button_credit_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    private static ImageButton button_start = null;
    private static ImageButton button_credit = null;

    private static final int MARGIN_LEFT = 100;

    static {
        // start button setup
        button_start = setupButton(button_start_up, button_start_down);
        button_start.setPosition(MARGIN_LEFT + 60, 200);

        // credit button setup
        button_credit = setupButton(button_credit_up, button_credit_down);
        button_credit.setPosition(MARGIN_LEFT + 60, 140);

        addActors(stage, button_start, button_credit);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    public static void renderInitScreen(BabyTrader game) {
        game.batch.draw(babyTrader, ConstData.SCREEN_WIDTH - babyTrader.getWidth(), 0);
        game.batch.draw(title, MARGIN_LEFT, ConstData.SCREEN_HEIGHT - title.getHeight() - 50);
        game.batch.draw(copyright, 0, 0);

        // perform ui logic
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
