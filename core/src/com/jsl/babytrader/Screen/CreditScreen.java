package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

/**
 * Credit screen that shows the creator name and tools used for this project.
 */

public class CreditScreen extends BaseScreen {
    // pure graphics
    private  Texture sprite_title = new Texture("sprites/credits_title_400x100.png");

    // buttons
    private Texture sprite_button_back_up = new Texture("sprites/credits_backToTitle_400x500.png");
    private Texture sprite_button_back_down = new Texture("sprites/credits_backToTitle_400x500_inv.png");

    private ImageButton button_back = null;

    // fonts
    private BitmapFont font_nokia = null;
    final private static int FONT_NOKIA_SIZE = 10;
    final private static Color FONT_NOKIA_COLOR = Color.valueOf("2F3A42");

    public CreditScreen(final BabyTrader game) {
        super(game);

        // font setup
        font_nokia = generateFont(FONT_NOKIA_PATH, FONT_NOKIA_SIZE, FONT_NOKIA_COLOR);
        //font_nokia.getData().setScale(10);

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_makkura.wav", true);

        // start button setup
        button_back = generateButton(sprite_button_back_up, sprite_button_back_down);
        button_back.setPosition((ConstData.SCREEN_WIDTH / 2) - (sprite_button_back_up.getWidth() / 2), 60);

        button_back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Back Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                switchScreen(new InitScreen(game));
            }
        });

        addElementsToStage(button_back);

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

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        game.batch.begin();

        // title
        game.batch.draw(
                sprite_title,
                (ConstData.SCREEN_WIDTH / 2) - (sprite_title.getWidth() / 2),
                ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 50
        );

        font_nokia.draw(game.batch, "test", 100, 100);

        // text
        //font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        //font.getData().setScale(1f);
        //font.draw(game.batch, "some string", 25, 160);
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

        // sprites
        sprite_title.dispose();

        // buttons
        sprite_button_back_up.dispose();
        sprite_button_back_down.dispose();
    }
}
