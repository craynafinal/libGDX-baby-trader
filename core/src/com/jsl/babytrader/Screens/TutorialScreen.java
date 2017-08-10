package com.jsl.babytrader.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.jsl.babytrader.BabyTrader;

/**
 * Created by crayna on 8/9/17.
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

    public TutorialScreen(BabyTrader game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {

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
