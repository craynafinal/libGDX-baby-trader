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

public class CreditScreenCreator extends ScreenCreator implements Disposable {
    private static Stage stage = new Stage(new FitViewport(ConstData.SCREEN_WIDTH, ConstData.SCREEN_HEIGHT));

    // pure graphics
    private static Texture sprite_title = new Texture("sprites/credits_title_400x100.png");

    // buttons
    private static Texture sprite_button_back_up = new Texture("sprites/credits_backToTitle_400x500.png");
    private static Texture sprite_button_back_down = new Texture("sprites/credits_backToTitle_400x500_inv.png");

    private static ImageButton button_back = null;

    static {
        // start button setup
        button_back = setupButton(sprite_button_back_up, sprite_button_back_down);
        button_back.setPosition(100 + 60, 200);

        button_back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Back Button", "Activated");
                sound_buttonClick.play();
                SharedData.setGameState(SharedData.GameState.start);
            }
        });

        addActors(stage, button_back);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    public static void render(BabyTrader game) {
        game.batch.draw(sprite_title, 100, ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 50);

        // perform ui logic
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();

        // sprites
        sprite_title.dispose();

        // buttons
        sprite_button_back_up.dispose();
        sprite_button_back_down.dispose();
    }
}
