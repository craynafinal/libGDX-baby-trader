package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.Attribute;

/**
 * Created by crayna on 6/28/17.
 */

public class SliderPositiveScreen extends BaseScreen {

    // TODO: this is a placeholder sprite, replace with next button
    private Texture sprite_button_next_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    private Texture sprite_button_next_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    private ImageButton button_next = null;

    // slider_smart_sell
    // TODO: sprites are place holders
    private Texture sprite_slider_bar = new Texture("sprites/startPage_creditsButton_inv_191x463.png");
    private Texture sprite_slider_knob = new Texture("sprites/temp_slider_knob.png");
    private Slider slider_smart_sell = null;
    Label slider_smart_sell_value_display = null;

    /*
    // positive
    Smart(0, true),
    Humorous(1, true),
    Fast(2, true),
    Self_Confidence(3, true),
    Nice_Job(4, true),
    Rich(5, true),
    Handsome(6, true),
    Tall(7, true),

    // negative
    Dumb(8, false),
    Boring(9, false),
    Slow(10, false),
    Lazy(11, false),
    Bad_Job(12, false),
    Poor(13, false),
    Ugly(14, false),
    Short(15, false);
     */


    private Slider setupSlider(Texture bar, Texture knob, int min, int max, int step, boolean vertical, int connectedValue, float speed, final Label textLabel, final Attribute attribute, boolean isSell) {
        final Slider result = generateSlider(bar, knob, min, max, step, vertical);
        result.setValue(connectedValue); // initial value
        result.setAnimateDuration(speed);
        result.setWidth(bar.getWidth());

        result.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                attribute.setSellValue((int) result.getValue());
                textLabel.setText(((int) result.getValue()) + "");
                Gdx.app.log("Slider", "slider_smart_sell: " + result.getValue() + " / internal value: " + attribute.getSellValue());
            }
        });

        return result;
    }

    public SliderPositiveScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        setupMusic("music/bgm_rihujin.wav", true);

        // TODO: maybe make an array and find a way to easily add listener
        // slider_smart_sell setup

        // TODO: organize label and slider_smart_sell_value_display
        // labels
        Label label = new Label(String.format("TIME"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        slider_smart_sell_value_display = new Label(String.format("%03d", Attribute.Smart.getSellValue()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        slider_smart_sell = setupSlider(sprite_slider_bar, sprite_slider_knob, 0, 100, 1, false, Attribute.Smart.getSellValue(), 0.3f, slider_smart_sell_value_display, Attribute.Smart, true);

        /*
        slider_smart_sell.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Attribute.Smart.setSellValue((int) slider_smart_sell.getValue());
                slider_smart_sell_value_display.setText(((int) slider_smart_sell.getValue()) + "");
                Gdx.app.log("Slider", "slider_smart_sell: " + slider_smart_sell.getValue() + " / internal value: " + Attribute.Smart.getSellValue());
            }
        });
        */

        Table table = generateTable(50, 4, label, slider_smart_sell_value_display, slider_smart_sell);

        // next button setup
        button_next = generateButton(sprite_button_next_up, sprite_button_next_down);
        button_next.setPosition(100, 100);

        button_next.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Next Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                //switchScreen(new CreditScreen(game));
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
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        // stage.draw() must appear before game batch
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        game.batch.begin();

        // update slider_smart_sell value texts
        //slider_smart_sell_value_display.setText(testInteger.toString());

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

        sprite_button_next_up.dispose();
        sprite_button_next_down.dispose();
    }
}
