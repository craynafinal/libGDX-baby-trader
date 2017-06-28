package com.jsl.babytrader.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 6/27/17.
 */

public class InitScreenCreator extends ScreenCreator implements Disposable {
    private static Stage stage = new Stage(new FitViewport(ConstData.SCREEN_WIDTH, ConstData.SCREEN_HEIGHT));

    // pure graphics
    private static Texture sprite_babyTrader = new Texture("sprites/startPage_babyTrader_558x326.png");
    private static Texture sprite_title = new Texture("sprites/startPage_title_244x177.png");
    private static Texture sprite_copyright = new Texture("sprites/startPage_copyright_171x533.png");

    // buttons
    private static Texture sprite_button_start_up = new Texture("sprites/startPage_startButton_191x357.png");
    private static Texture sprite_button_start_down = new Texture("sprites/startPage_startButton_inv_191x357.png");
    private static Texture sprite_button_credit_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    private static Texture sprite_button_credit_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    private static ImageButton button_start = null;
    private static ImageButton button_credit = null;

    private static final int MARGIN_LEFT = 100;

    static {
        // start button setup
        button_start = setupButton(sprite_button_start_up, sprite_button_start_down);
        button_start.setPosition(MARGIN_LEFT + 60, 200);

        button_start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Start Button", "Activated");
                sound_buttonClick.play();
            }
        });

        // credit button setup
        button_credit = setupButton(sprite_button_credit_up, sprite_button_credit_down);
        button_credit.setPosition(MARGIN_LEFT + 60, 140);

        button_credit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Credit Button", "Activated");
                sound_buttonClick.play();
                SharedData.setGameState(SharedData.GameState.credit);
            }
        });

        addActors(stage, button_start, button_credit);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    public static void render(BabyTrader game) {
        game.batch.draw(sprite_babyTrader, ConstData.SCREEN_WIDTH - sprite_babyTrader.getWidth(), 0);
        game.batch.draw(sprite_title, MARGIN_LEFT, ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 50);
        game.batch.draw(sprite_copyright, 0, 0);

        // perform ui logic
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();

        // sounds
        sound_buttonClick.dispose();

        // sprites
        sprite_babyTrader.dispose();
        sprite_title.dispose();
        sprite_copyright.dispose();

        // buttons
        sprite_button_start_up.dispose();
        sprite_button_start_down.dispose();
        sprite_button_credit_up.dispose();
        sprite_button_credit_down.dispose();
    }
}
