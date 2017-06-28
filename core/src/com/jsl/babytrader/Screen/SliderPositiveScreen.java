package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
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

    public SliderPositiveScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        setupMusic("music/bgm_rihujin.wav", true);

        // slider setup
        slider = generateSlider(sprite_slider_bar, sprite_slider_knob, 0, 100, 10, false);
        slider.setAnimateDuration(0.3f);
        slider.setPosition(100, 200);
        slider.setWidth(sprite_slider_bar.getWidth());

        slider.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.log("Slider", "slider: " + slider.getValue());
            }
        });

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

        addElementsToStage(button_next, slider);

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
        //game.batch.draw(sprite_babyTrader, ConstData.SCREEN_WIDTH - sprite_babyTrader.getWidth(), 0);
        //game.batch.draw(sprite_title, MARGIN_LEFT, ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 50);
        //game.batch.draw(sprite_copyright, MARGIN_LEFT + 55, 50);
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
