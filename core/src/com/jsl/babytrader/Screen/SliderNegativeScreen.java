package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

/**
 * This is a stage where it handles sliders for a user to save sales values for each negative attributes.
 */

public class SliderNegativeScreen extends SliderScreen {
    private ImageButton button_next = null;

    public SliderNegativeScreen(final BabyTrader game) {
        super(game, "sprites/sliders_negTitle_591x52.png");

        // bgm setup
        setupMusic("music/bgm_rihujin.wav", true);

        // create a table that contains all sliders and labels
        Table table = generateTable(false);

        // TODO: refactor, at least next two lines can be in base class
        // next button setup
        button_next = generateButton(sprite_button_next_up, sprite_button_next_down);
        button_next.setPosition((ConstData.SCREEN_WIDTH / 2) - (sprite_button_next_up.getWidth() / 2), 20);

        button_next.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Next Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                switchScreen(new InitScreen(game));
            }
        });

        //addElementsToStage(button_next, slider_smart_sell);
        addElementsToStage(button_next, table);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

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
