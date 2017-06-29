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

/**
 * Created by crayna on 6/28/17.
 */

public class SliderPositiveScreen extends BaseScreen {

    // TODO: this is a placeholder sprite, replace with next button
    private Texture sprite_button_next_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    private Texture sprite_button_next_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    private ImageButton button_next = null;

    // slider
    // TODO: sprites are place holders
    private Texture sprite_slider_bar = new Texture("sprites/startPage_creditsButton_inv_191x463.png");
    private Texture sprite_slider_knob = new Texture("sprites/temp_slider_knob.png");
    private Slider slider = null;
    Label count = null;

    // TODO: change this to static data
    Integer testInteger = 50;


    public Slider setupSlider(Texture bar, Texture knob, int min, int max, int step, boolean vertical, int connectedValue, float speed) {
        Slider result = generateSlider(bar, knob, min, max, step, vertical);
        result.setValue(connectedValue); // initial value
        result.setAnimateDuration(speed);
        result.setWidth(bar.getWidth());

        return result;
    }

    public SliderPositiveScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        setupMusic("music/bgm_rihujin.wav", true);

        // TODO: maybe make an array and find a way to easily add listener
        // slider setup
        slider = setupSlider(sprite_slider_bar, sprite_slider_knob, 0, 100, 10, false, testInteger, 0.3f);

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                testInteger = (int) slider.getValue();
                count.setText(testInteger.toString());
                Gdx.app.log("Slider", "slider: " + slider.getValue() + " / internal value: " + testInteger);
            }
        });

        // TODO: organize label and count
        // labels
        Label label = new Label(String.format("TIME"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        count = new Label(String.format("%03d", testInteger), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Table table = generateTable(50, 4, label, count, slider);

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

        //addElementsToStage(button_next, slider);
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

        // update slider value texts
        //count.setText(testInteger.toString());

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
