package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.Attribute;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.ConstData;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Popups.PopupPause;
import com.jsl.babytrader.Popups.PopupUpgrade;

/**
 * Actual game screen for play.
 */

public class GameScreen extends BaseScreen {
    // background graphic
    private Texture sprite_background = new Texture("sprites/gameScreen_background_1024x576.png");

    // buttons
    private Texture sprite_button_browse_left = new Texture("sprites/gameScreen_browse_left_34x35.png");
    private Texture sprite_button_browse_left_inv = new Texture("sprites/gameScreen_browse_left_inv_34x35.png");
    private Texture sprite_button_browse_right = new Texture("sprites/gameScreen_browse_right_34x35.png");
    private Texture sprite_button_browse_right_inv = new Texture("sprites/gameScreen_browse_right_inv_34x35.png");
    private Texture sprite_button_menu = new Texture("sprites/gameScreen_menuButton_186x45.png");
    private Texture sprite_button_menu_inv = new Texture("sprites/gameScreen_menuButton_inv_186x45.png");
    private Texture sprite_button_promotion = new Texture("sprites/gameScreen_promotionButton_186x45.png");
    private Texture sprite_button_promotion_inv = new Texture("sprites/gameScreen_promotionButton_inv_186x45.png");
    private Texture sprite_button_research = new Texture("sprites/gameScreen_researchButton_186x45.png");
    private Texture sprite_button_research_inv = new Texture("sprites/gameScreen_researchButton_inv_186x45.png");
    private Texture sprite_button_upgrade = new Texture("sprites/gameScreen_upgradeButton_167x45.png");
    private Texture sprite_button_upgrade_inv = new Texture("sprites/gameScreen_upgradeButton_inv_167x45.png");

    private ImageButton button_browse_left = null;
    private ImageButton button_browse_right = null;
    private ImageButton button_menu = null;
    private ImageButton button_promotion = null;
    private ImageButton button_research = null;
    private ImageButton button_upgrade_sell = null;
    private ImageButton button_upgrade_buy = null;

    private Label label_money = null;
    private Label label_time = null;

    private Label label_count_babies = null;
    private Label label_count_customers_sell = null;
    private Label label_count_customers_buy = null;

    private Label label_properties_title_baby = null;
    private Label label_properties_list_baby = null;
    private Label label_properties_title_sell = null;
    private Label label_properties_list_sell = null;
    private Label label_properties_title_buy = null;
    private Label label_properties_list_buy = null;

    private Label label_level_sell = null;
    private Label label_level_buy = null;

    // pause popup windows
    private PopupPause popup_pause = null;
    private Texture sprite_popup_paused = new Texture("sprites/popup_pause_305x240.png");
    private Texture sprite_button_popup_continue = new Texture("sprites/popup_pause_button_continue_186x45.png");
    private Texture sprite_button_popup_continue_inv = new Texture("sprites/popup_pause_button_continue_186x45.png");
    private Texture sprite_button_popup_mainMenu = new Texture("sprites/popup_pause_button_mainMenu_186x45.png");
    private Texture sprite_button_popup_mainMenu_inv = new Texture("sprites/popup_pause_button_mainMenu_inv_186x45.png");

    private ImageButton button_popup_continue = null;
    private ImageButton button_popup_mainMenu = null;

    // upgrade popup windows
    private PopupUpgrade popup_upgrade = null;
    private Texture sprite_popup_upgrade = new Texture("sprites/popup_upgrade_background_525x390.png");
    private Texture sprite_button_popup_cancel = new Texture("sprites/button_popup_cancel_186x45.png");
    private Texture sprite_button_popup_cancel_inv = new Texture("sprites/button_popup_cancel_inv_186x45.png");
    private Texture sprite_button_popup_upgrade = new Texture("sprites/button_popup_upgrade_186x45.png");
    private Texture sprite_button_popup_upgrade_inv = new Texture("sprites/button_popup_upgrade_inv_186x45.png");

    private ImageButton button_popup_cancel = null;
    private ImageButton button_popup_upgrade = null;

    // meta data
    private int currentBabyIndex = 0;

    // configuration
    private Configuration config = null;

    public GameScreen(BabyTrader game) {
        super(game);

        config = new Configuration();
        config.timerSetup();

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_usodarake.wav", true);

        popupSetup();
        labelSetup();
        buttonSetup();

        addElementsToStage(
            button_browse_left,
            button_browse_right,
            button_menu,
            button_promotion,
            button_research,
            button_upgrade_sell,
            button_upgrade_buy,
            label_money,
            label_time,
            label_count_babies,
            label_count_customers_sell,
            label_count_customers_buy,
            label_properties_title_baby,
            label_properties_list_baby,
            label_properties_title_sell,
            label_properties_list_sell,
            label_properties_title_buy,
            label_properties_list_buy,
            label_level_sell,
            label_level_buy,
            popup_pause.getTable(),
            popup_upgrade.getTable()
        );

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    private void popupSetup() {
        // popup pause
        popup_pause = new PopupPause(sprite_popup_paused);

        button_popup_continue = generateButton(sprite_button_popup_continue, sprite_button_popup_continue_inv);
        button_popup_continue.setPosition(0, 0);

        button_popup_continue.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Continue button", "Activated");
                sound_buttonClick.play();
                popup_pause.getTable().setVisible(false);
                resume();
            }
        });

        button_popup_mainMenu = generateButton(sprite_button_popup_mainMenu, sprite_button_popup_mainMenu_inv);
        button_popup_mainMenu.setPosition(0, 0);

        button_popup_mainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Main Menu button", "Activated");
                sound_buttonClick.play();

                switchScreen(BabyTrader.initScreen);
            }
        });

        popup_pause.addElements(button_popup_continue, button_popup_mainMenu);

        // popup upgrade
        popup_upgrade = new PopupUpgrade(
                sprite_popup_upgrade,
                getLabelStyle(FONT_WORK_EXTRA_BOLD, 29, Color.WHITE)
        );

        button_popup_cancel = generateButton(sprite_button_popup_cancel, sprite_button_popup_cancel_inv);
        button_popup_cancel.setPosition(0, 0);

        button_popup_cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Cancel Button", "Activated");
                sound_buttonClick.play();
                resume();
                popup_upgrade.setVisible(false);
            }
        });

        button_popup_upgrade = generateButton(sprite_button_popup_upgrade, sprite_button_popup_upgrade_inv);
        button_popup_upgrade.setPosition(0, 0);

        button_popup_upgrade.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Upgrade Button", "Activated");
                sound_buttonClick.play();
                resume();
                popup_upgrade.setVisible(false);
            }
        });

        popup_upgrade.addElements(button_popup_cancel, button_popup_upgrade);
    }

    private void renderCustomer(Customer customer, Label label_title, Label label_properties, int x, int y, String description) {
        if (customer != null) {
            stage.getBatch().draw(customer.getSprite(), x, y);

            label_title.setText(customer.getName() + " (" + customer.getAge() + ")");

            StringBuilder stringBuilder = new StringBuilder((customer.isMale() ? "His" : "Her") + " " + description + ":\n");

            for (Attribute attribute : customer.getAttributes()) {
                stringBuilder.append(propertyFormat(attribute.getName()));
            }

            label_properties.setText(stringBuilder);
        }
    }

    private void renderBaby() {
        Baby baby = null;

        synchronized (this) {
            if (currentBabyIndex >= SharedData.getBabySize()) {
                currentBabyIndex = SharedData.getBabySize() - 1;
            } else if (currentBabyIndex < 0) {
                currentBabyIndex = 0;
            }
            baby = SharedData.getBabyWithoutRemoval(currentBabyIndex);
        }

        stage.getBatch().draw(baby.getSprite(), 484, 131);

        label_properties_title_baby.setText(baby.getName() + " (" + baby.getAge() + ") $"+ baby.getSellPrice());

        StringBuilder stringBuilder = new StringBuilder();

        for (Attribute attribute : baby.getAttributes()) {
            stringBuilder.append(propertyFormat(attribute.getName()));
        }

        label_properties_list_baby.setText(stringBuilder);
    }

    // returns an attribute in a formatted string
    private static String propertyFormat(String attribute) {
        return "• " + attribute + "\n";
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        // stage.draw() must appear before game batch
        stage.act(Gdx.graphics.getDeltaTime());

        stage.getBatch().begin();
        stage.getBatch().draw(sprite_background, 0, 0);

        // baby sprite
        renderBaby();

        // customer sprites
        // this one should appear when customer is accepted by sales / purchase team
        renderCustomer(SharedData.getCustomerSellingLatest(), label_properties_title_sell, label_properties_list_sell, 15, 306, "dream baby");
        renderCustomer(SharedData.getCustomerBuyingLatest(), label_properties_title_buy, label_properties_list_buy, 15, 15, "baby for sale");

        label_money.setText("$" + SharedData.getMoney());
        label_time.setText(config.getTime());
        label_count_babies.setText(SharedData.getBabySize() + "");
        label_count_customers_sell.setText(SharedData.getCustomerSellingSize() + "");
        label_count_customers_buy.setText(SharedData.getCustomerBuyingSize() + "");

        label_level_sell.setText(config.getLevelSeller() + "");
        label_level_buy.setText(config.getLevelBuyer() + "");

        stage.getBatch().end();

        stage.draw();

        game.batch.begin();
        game.batch.end();

    }

    protected static void clearingScreen() {
        Gdx.gl.glClearColor(
                ConstData.COLOR_BG_RED_GAME,
                ConstData.COLOR_BG_BLUE_GAME,
                ConstData.COLOR_BG_GREEN_GAME,
                ConstData.COLOR_BG_ALPHA
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void buttonSetup() {
        button_browse_left = generateButton(sprite_button_browse_left, sprite_button_browse_left_inv);
        button_browse_left.setPosition(467, 528);

        button_browse_left.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Browse Left button", "Activated");
                currentBabyIndex--;
                sound_buttonClick.play();
            }
        });

        button_browse_right = generateButton(sprite_button_browse_right, sprite_button_browse_right_inv);
        button_browse_right.setPosition(738, 528);

        button_browse_right.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Browse Right button", "Activated");
                currentBabyIndex++;
                sound_buttonClick.play();
            }
        });

        button_menu = generateButton(sprite_button_menu, sprite_button_menu_inv);
        button_menu.setPosition(817, 14);

        button_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Menu button", "Activated");
                sound_buttonClick.play();

                // TODO: currently this is here for testing purpose
                pause();
                popup_pause.setVisible(true);
            }
        });

        button_promotion = generateButton(sprite_button_promotion, sprite_button_promotion_inv);
        button_promotion.setPosition(817, 118);

        button_promotion.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Promotion button", "Activated");
                sound_buttonClick.play();
                pause();
            }
        });

        button_research = generateButton(sprite_button_research, sprite_button_research_inv);
        button_research.setPosition(817, 66);

        button_research.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Research button", "Activated");
                sound_buttonClick.play();

                // TODO: currently this is here for testing purpose
                pause();
            }
        });

        button_upgrade_sell = generateButton(sprite_button_upgrade, sprite_button_upgrade_inv);
        button_upgrade_sell.setPosition(13, 484);

        button_upgrade_sell.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Sell Upgrade button", "Activated");
                sound_buttonClick.play();

                // TODO: currently this is here for testing purpose
                // need more configuration such as popup window

                pause();
                popup_upgrade.setVisible(true);

                // config.levelUpSeller();
            }
        });

        button_upgrade_buy = generateButton(sprite_button_upgrade, sprite_button_upgrade_inv);
        button_upgrade_buy.setPosition(13, 193);

        button_upgrade_buy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Buy Upgrade button", "Activated");
                sound_buttonClick.play();

                // need more configuration such as popup window
                config.levelUpBuyer();
            }
        });
    }

    private void labelSetup() {
        label_money = new Label("", new Label.LabelStyle(generateFont(FONT_SARPANCH_SEMI_BOLD, 28, FONT_COLOR_GREEN), FONT_COLOR_GREEN));
        label_money.setAlignment(Align.right);
        label_money.setPosition(994, 190);

        label_time = new Label("", new Label.LabelStyle(generateFont(FONT_SARPANCH_SEMI_BOLD, 28, FONT_COLOR_GREEN), FONT_COLOR_GREEN));
        label_time.setAlignment(Align.right);
        label_time.setPosition(994, 260);

        label_count_babies = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 43, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_count_babies.setAlignment(Align.right);
        label_count_babies.setPosition(607, 545);

        label_count_customers_sell = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 43, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_count_customers_sell.setAlignment(Align.right);
        label_count_customers_sell.setPosition(290, 502);

        label_count_customers_buy = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 43, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_count_customers_buy.setAlignment(Align.right);
        label_count_customers_buy.setPosition(290, 209);

        label_properties_title_baby = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 20, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_properties_title_baby.setPosition(505, 113);

        label_properties_list_baby = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 14, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_properties_list_baby.setPosition(505, 55);

        label_properties_title_sell = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 20, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_properties_title_sell.setPosition(193, 418);

        label_properties_list_sell = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 14, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_properties_list_sell.setPosition(193, 354);

        label_properties_title_buy = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 20, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_properties_title_buy.setPosition(193, 127);

        label_properties_list_buy = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 14, FONT_COLOR_LIGHT_GRAY), FONT_COLOR_LIGHT_GRAY));
        label_properties_list_buy.setPosition(193, 63);

        label_level_sell = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 15, Color.WHITE), Color.WHITE));
        label_level_sell.setPosition(154, 549);

        label_level_buy = new Label("", new Label.LabelStyle(generateFont(FONT_WORK_EXTRA_BOLD, 15, Color.WHITE), Color.WHITE));
        label_level_buy.setPosition(154, 258);
    }

    @Override
    public void pause() {
        Timer.instance().stop();
        SharedData.pause();

        button_browse_left.setDisabled(true);
        button_browse_right.setDisabled(true);
        button_menu.setDisabled(true);
        button_promotion.setDisabled(true);
        button_research.setDisabled(true);
        button_upgrade_sell.setDisabled(true);
        button_upgrade_buy.setDisabled(true);
    }

    @Override
    public void resume() {
        Timer.instance().start();
        SharedData.resume();

        button_browse_left.setDisabled(false);
        button_browse_right.setDisabled(false);
        button_menu.setDisabled(false);
        button_promotion.setDisabled(false);
        button_research.setDisabled(false);
        button_upgrade_sell.setDisabled(false);
        button_upgrade_buy.setDisabled(false);
    }

    @Override
    public void hide() {
        config.killThreads();
    }

    @Override
    public void show() {
        super.show();
        config.initialize();
        config.startThreadsAndTimer();
        popup_pause.setVisible(false);
    }

    @Override
    public void dispose() {
        super.dispose();

        sprite_background.dispose();
        sprite_button_browse_left.dispose();
        sprite_button_browse_left_inv.dispose();
        sprite_button_browse_right.dispose();
        sprite_button_browse_right_inv.dispose();
        sprite_button_menu.dispose();
        sprite_button_menu_inv.dispose();
        sprite_button_promotion.dispose();
        sprite_button_promotion_inv.dispose();
        sprite_button_research.dispose();
        sprite_button_research_inv.dispose();
        sprite_button_upgrade.dispose();
        sprite_button_upgrade_inv.dispose();

        sprite_popup_paused.dispose();
        sprite_button_popup_continue.dispose();
        sprite_button_popup_continue_inv.dispose();
        sprite_button_popup_mainMenu.dispose();
        sprite_button_popup_mainMenu_inv.dispose();

        sprite_popup_upgrade.dispose();
        sprite_button_popup_cancel.dispose();
        sprite_button_popup_cancel_inv.dispose();
        sprite_button_popup_upgrade.dispose();
        sprite_button_popup_upgrade_inv.dispose();
    }
}
