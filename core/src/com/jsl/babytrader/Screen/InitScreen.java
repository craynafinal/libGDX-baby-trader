package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

/**
 * Created by crayna on 6/27/17.
 */

public class InitScreen extends BaseScreen {
    // pure graphics
    private Texture sprite_babyTrader = new Texture("sprites/startPage_babyTrader_558x326.png");
    private Texture sprite_title = new Texture("sprites/startPage_title_244x177.png");
    private Texture sprite_copyright = new Texture("sprites/startPage_copyright_171x533.png");

    // buttons
    private Texture sprite_button_start_up = new Texture("sprites/startPage_startButton_191x357.png");
    private Texture sprite_button_start_down = new Texture("sprites/startPage_startButton_inv_191x357.png");
    private Texture sprite_button_credit_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    private Texture sprite_button_credit_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    private ImageButton button_start = null;
    private ImageButton button_credit = null;

    private static final int MARGIN_LEFT = 100;

    public InitScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_usodarake.wav", true);

        // start button setup
        button_start = setupButton(sprite_button_start_up, sprite_button_start_down);
        button_start.setPosition(MARGIN_LEFT + 60, 200);

        button_start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Start Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
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
                stopMusic();
                switchScreen(new CreditScreen(game));
            }
        });

        addActors(button_start, button_credit);

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
        game.batch.draw(sprite_babyTrader, ConstData.SCREEN_WIDTH - sprite_babyTrader.getWidth(), 0);
        game.batch.draw(sprite_title, MARGIN_LEFT, ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 50);
        game.batch.draw(sprite_copyright, MARGIN_LEFT + 55, 50);
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
