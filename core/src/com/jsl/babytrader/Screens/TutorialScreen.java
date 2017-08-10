package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

import java.util.ArrayList;
import java.util.List;

/**
 * Tutorial screen.
 */

public class TutorialScreen extends BaseScreen {

    // pure textures
    private Texture sprite_background = new Texture("sprites/tutorial_background_1024x576.png");
    private Texture sprite_screen_babies = new Texture("sprites/tutorial_screen_babies_503x342.png");
    private Texture sprite_screen_buyer = new Texture("sprites/tutorial_screen_buyer_503x342.png");
    private Texture sprite_screen_gameover = new Texture("sprites/tutorial_screen_gameover_503x342.png");
    private Texture sprite_screen_hud = new Texture("sprites/tutorial_screen_hud_503x342.png");
    private Texture sprite_screen_seller = new Texture("sprites/tutorial_screen_seller_503x342.png");
    private Texture sprite_screen_setting = new Texture("sprites/tutorial_screen_setting_503x342.png");
    private Texture sprite_screen_upgrade = new Texture("sprites/tutorial_screen_upgrade_503x342.png");

    // buttons
    private Texture sprite_button_back = new Texture("sprites/tutorial_button_back_186x45.png");
    private Texture sprite_button_back_inv = new Texture("sprites/tutorial_button_back_inv_186x45.png");
    private ImageButton button_back = null;

    private Texture sprite_button_mainMenu = new Texture("sprites/tutorial_button_mainMenu_186x45.png");
    private Texture sprite_button_mainMenu_inv = new Texture("sprites/tutorial_button_mainMenu_inv_186x45.png");
    private ImageButton button_mainMenu = null;

    private Texture sprite_button_next = new Texture("sprites/tutorial_button_next_186x45.png");
    private Texture sprite_button_next_inv = new Texture("sprites/tutorial_button_next_inv_186x45.png");
    private ImageButton button_next = null;

    // meta data
    private int step = 0;
    private List<Texture> texture_screens = null;


    public TutorialScreen(BabyTrader game) {
        super(game);

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_makkura.wav", true);

        // do same for label
        texture_screens = new ArrayList<Texture>();
        texture_screens.add(sprite_screen_setting);
        texture_screens.add(sprite_screen_hud);
        texture_screens.add(sprite_screen_babies);
        texture_screens.add(sprite_screen_seller);
        texture_screens.add(sprite_screen_buyer);
        texture_screens.add(sprite_screen_upgrade);
        texture_screens.add(sprite_screen_gameover);

        buttonSetup();

        addElementsToStage(button_back, button_mainMenu, button_next);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    private void increaseStep() {
        if (step < texture_screens.size() -1) {
            step++;
        }
    }

    private void decreaseStep() {
        if (step > 0) {
            step--;
        }
    }

    private void buttonSetup() {
        button_back = generateButton(sprite_button_back, sprite_button_back_inv);
        button_back.setPosition(598, 29);

        button_back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Back Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                decreaseStep();
            }
        });

        button_mainMenu = generateButton(sprite_button_mainMenu, sprite_button_mainMenu_inv);
        button_mainMenu.setPosition(44, 29);

        button_mainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Main Menu Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                switchScreen(BabyTrader.initScreen);
            }
        });

        button_next = generateButton(sprite_button_next, sprite_button_next_inv);
        button_next.setPosition(793, 29);

        button_next.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Next Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                increaseStep();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        // stage.draw() must appear before game batch
        stage.act(Gdx.graphics.getDeltaTime());

        stage.getBatch().begin();
        stage.getBatch().draw(sprite_background, 0, 0);

        stage.getBatch().draw(texture_screens.get(step), 58, 110);

        stage.getBatch().end();

        stage.draw();

        game.batch.begin();
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

        sprite_background.dispose();
        sprite_screen_babies.dispose();
        sprite_screen_buyer.dispose();
        sprite_screen_gameover.dispose();
        sprite_screen_hud.dispose();
        sprite_screen_seller.dispose();
        sprite_screen_setting.dispose();
        sprite_screen_upgrade.dispose();

        sprite_button_back.dispose();
        sprite_button_back_inv.dispose();

        sprite_button_mainMenu.dispose();
        sprite_button_mainMenu_inv.dispose();

        sprite_button_next.dispose();
        sprite_button_next_inv.dispose();
    }
}
