package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jsl.babytrader.BabyTrader;

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
    private Texture sprite_screen_babyTrader = new Texture("sprites/tutorial_screen_babyTrader_503x342.png");
    private Texture sprite_screen_buyingAndSelling = new Texture("sprites/tutorial_screen_buyingAndSelling_503x342.png");
    private Texture sprite_screen_promotionAndResearch = new Texture("sprites/tutorial_screen_promotionAndResearch_503x342.png");
    private Texture sprite_screen_purpose = new Texture("sprites/tutorial_screen_purpose_503x342.png");
    private Texture sprite_screen_thankYou = new Texture("sprites/tutorial_screen_thankYou_503x342.png");

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

    // label
    private Label label_textbox = null;

    // meta data
    private int step = 0;
    private List<Texture> screenshots = null;
    private List<String> texts = null;


    public TutorialScreen(BabyTrader game) {
        super(game);

        // bgm setup
        setupMusic("music/tutorial_dareka.mp3", true);

        screenshots = new ArrayList<Texture>();
        screenshots.add(sprite_screen_babyTrader);
        screenshots.add(sprite_screen_purpose);
        screenshots.add(sprite_screen_setting);
        screenshots.add(sprite_screen_hud);
        screenshots.add(sprite_screen_babies);
        screenshots.add(sprite_screen_seller);
        screenshots.add(sprite_screen_buyer);
        screenshots.add(sprite_screen_upgrade);
        screenshots.add(sprite_screen_buyingAndSelling);
        screenshots.add(sprite_screen_promotionAndResearch);
        screenshots.add(sprite_screen_gameover);
        screenshots.add(sprite_screen_thankYou);

        texts = new ArrayList<String>();
        texts.add("Welcome to Baby Trader tutorial!\n\nNow you have a chance to make the world happier place by buying and selling babies.\n\nLet's start business!");
        texts.add("Customers want babies with high potential. Babies deserve nice parents who will treat them right.\n\nYou can find perfect match between them for their ultimate happiness!");
        texts.add("Start by setting values regarding talents of babies. These values will affect your game.\n\nFor example, if being smart is important for a baby, how much would you pay for? How much would you sell for?");
        texts.add("When game starts, time and money will be displayed on the right. When time or money becomes zero, the game is over.\n\nYour goal is to trade babies as many as possible.");
        texts.add("Browse your babies in stock by touching arrow buttons. You can see their name, age, and talents. Number of babies in stock will vary in the middle of game.");
        texts.add("You will have customers who would want to buy your baby. If you have a baby with talents matching their desire and the customer accepts your price, the baby will be automatically sold!");
        texts.add("Same goes for customers who would like to sell their babies to you. If customer's price falls in your price range you have set in the beginning, you will buy the baby. You need to buy babies to sell them.");
        texts.add("If you have budget, you should upgrade your abilities. Upgrading selling, buying, promotion, and research abilities will make trades go faster!");
        texts.add("Upgrading selling and buying abilities will increase number of sales representative so you can face multiple customers at the same time. This way, number of customers waiting in line will decrease.");
        texts.add("High level of promotion will increase number of customers come in your store, while high level of research will upgrade babies in your stock more often for higher chance of sales.");
        texts.add("When time or money becomes zero, the game will be over, and it will display high score based on your record.\n\nThis game keeps your high score. Challenge yourself for higher scores!");
        texts.add("Thank you for reading this tutorial.\n\nHope you enjoy trading babies and become the best Baby Trader!");

        labelSetup();
        buttonSetup();

        addElementsToStage(button_back, button_mainMenu, button_next, label_textbox);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    private void labelSetup() {
        label_textbox = new Label("", getLabelStyle(FONT_WORK_EXTRA_BOLD, 30, Color.WHITE));
        label_textbox.setAlignment(Align.topLeft);
        label_textbox.setWrap(true);
        label_textbox.setWidth(381);
        label_textbox.setHeight(367);
        label_textbox.setPosition(598, 99);
    }

    private void increaseStep() {
        if (step < screenshots.size() -1) {
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
                increaseStep();
            }
        });
    }

    @Override
    public void show() {
        super.show();
        step = 0;
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        // stage.draw() must appear before game batch
        stage.act(Gdx.graphics.getDeltaTime());

        stage.getBatch().begin();

        stage.getBatch().draw(sprite_background, 0, 0);
        stage.getBatch().draw(screenshots.get(step), 58, 110);
        label_textbox.setText(texts.get(step));

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
        super.hide();
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
        sprite_screen_babyTrader.dispose();
        sprite_screen_buyingAndSelling.dispose();
        sprite_screen_promotionAndResearch.dispose();
        sprite_screen_purpose.dispose();
        sprite_screen_thankYou.dispose();

        sprite_button_back.dispose();
        sprite_button_back_inv.dispose();

        sprite_button_mainMenu.dispose();
        sprite_button_mainMenu_inv.dispose();

        sprite_button_next.dispose();
        sprite_button_next_inv.dispose();
    }
}
