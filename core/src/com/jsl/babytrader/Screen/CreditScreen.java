package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

/**
 * Created by crayna on 6/27/17.
 */

public class CreditScreen extends BaseScreen {
    // pure graphics
    private  Texture sprite_title = new Texture("sprites/credits_title_400x100.png");

    // buttons
    private Texture sprite_button_back_up = new Texture("sprites/credits_backToTitle_400x500.png");
    private Texture sprite_button_back_down = new Texture("sprites/credits_backToTitle_400x500_inv.png");

    private ImageButton button_back = null;

    public CreditScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_makkura.wav", true);

        // start button setup
        button_back = setupButton(sprite_button_back_up, sprite_button_back_down);
        button_back.setPosition(100 + 60, 200);

        button_back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Back Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                switchScreen(new InitScreen(game));
            }
        });

        addActors(button_back);

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
        game.batch.draw(sprite_title, 0, 0);
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
