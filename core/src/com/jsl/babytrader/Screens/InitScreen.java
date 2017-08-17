package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

import java.util.Calendar;
import java.util.Date;

/**
 * Game startThreadsAndTimer up menu screen.
 */

public class InitScreen extends BaseScreen {
    // pure graphics
    private Texture sprite_babyTrader = new Texture("sprites/startPage_babyTrader_558x326.png");
    private Texture sprite_title = new Texture("sprites/startPage_title_244x177.png");

    // buttons
    private Texture sprite_button_start_up = new Texture("sprites/startPage_startButton_255x45.png");
    private Texture sprite_button_start_down = new Texture("sprites/startPage_startButton_inv_255x45.png");
    private ImageButton button_start = null;

    private Texture sprite_button_credit_up = new Texture("sprites/startPage_creditsButton_255x45.png");
    private Texture sprite_button_credit_down = new Texture("sprites/startPage_creditsButton_inv_255x45.png");
    private ImageButton button_credit = null;

    private Texture sprite_button_howToPlay_up = new Texture("sprites/startPage_howToPlayButton_255x45.png");
    private Texture sprite_button_howToPlay_down = new Texture("sprites/startPage_howToPlayButton_inv_255x45.png");
    private ImageButton button_howToPlay = null;

    private Texture sprite_button_exit_up = new Texture("sprites/starPage_button_exitGame_255x45.png");
    private Texture sprite_button_exit_down = new Texture("sprites/starPage_button_exitGame_inv_255x45.png");
    private ImageButton button_exit = null;

    // copyright
    private Label label_copyright = null;

    private static final int MARGIN_LEFT = 100;

    public InitScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        setupMusic("music/init_biai.mp3", true);
        buttonSetup();

        labelSetup();

        addElementsToStage(button_start, button_credit, button_howToPlay, button_exit, label_copyright);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    private void labelSetup() {
        label_copyright = new Label("Copyright (c) " + Calendar.getInstance().get(Calendar.YEAR) + " Jong Seong Lee", getLabelStyle(FONT_WORK_EXTRA_BOLD, 14, FONT_COLOR_DARK_BLUE));
        label_copyright.setPosition(MARGIN_LEFT + 60, 24);
    }

    private void buttonSetup() {
        int posX = MARGIN_LEFT + 60;
        int posY_start = 240;
        int posY_interval = 60;

        // startThreadsAndTimer button setup
        button_start = generateButton(sprite_button_start_up, sprite_button_start_down);
        button_start.setPosition(posX, posY_start);

        button_start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Start Button", "Activated");
                sound_buttonClick.play();
                switchScreen(BabyTrader.sliderPositiveScreen);
            }
        });

        // credit button setup
        button_credit = generateButton(sprite_button_credit_up, sprite_button_credit_down);
        button_credit.setPosition(posX, posY_start - posY_interval);

        button_credit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Credit Button", "Activated");
                sound_buttonClick.play();
                switchScreen(BabyTrader.creditScreen);
            }
        });

        button_howToPlay = generateButton(sprite_button_howToPlay_up, sprite_button_howToPlay_down);
        button_howToPlay.setPosition(posX, posY_start - posY_interval * 2);

        button_howToPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Tutorial Button", "Activated");
                sound_buttonClick.play();
                switchScreen(BabyTrader.tutorialScreen);
            }
        });

        button_exit = generateButton(sprite_button_exit_up, sprite_button_exit_down);
        button_exit.setPosition(posX, posY_start - posY_interval * 3);

        button_exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Exit Button", "Activated");
                sound_buttonClick.play();
                Gdx.app.exit();
            }
        });
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
        game.batch.draw(sprite_title, MARGIN_LEFT, ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 30);

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
        super.hide();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dispose() {
        super.dispose();

        // sprites
        sprite_babyTrader.dispose();
        sprite_title.dispose();

        // buttons
        sprite_button_start_up.dispose();
        sprite_button_start_down.dispose();
        sprite_button_credit_up.dispose();
        sprite_button_credit_down.dispose();
        sprite_button_howToPlay_up.dispose();
        sprite_button_howToPlay_down.dispose();
    }
}
