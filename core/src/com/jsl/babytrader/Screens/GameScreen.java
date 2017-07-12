package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Runnables.PromotionTeam;
import com.jsl.babytrader.Runnables.PurchaseTeam;
import com.jsl.babytrader.Runnables.ResearchTeam;
import com.jsl.babytrader.Runnables.SalesTeam;

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

    // runnables
    private PromotionTeam promotionTeam = new PromotionTeam();
    private SalesTeam salesTeam = new SalesTeam();
    private PurchaseTeam purchaseTeam = new PurchaseTeam();
    private ResearchTeam researchTeam = new ResearchTeam();

    public GameScreen(BabyTrader game) {
        super(game);
        // user may upgrade game to allow start additional threads
        // for example, two sales team threads will provide faster sales
        new Thread(promotionTeam).start();
        new Thread(salesTeam).start();
        new Thread(purchaseTeam).start();
        new Thread(researchTeam).start();

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_usodarake.wav", true);

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
                label_level_buy
        );

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

        stage.getBatch().begin();
        stage.getBatch().draw(sprite_background, 0, 0);


        label_money.setText("$" + SharedData.getMoney());
        label_time.setText("00:00");
        label_count_babies.setText(SharedData.getBabySize() + "");
        label_count_customers_sell.setText(SharedData.getCustomerSellingSize() + "");
        label_count_customers_buy.setText(SharedData.getCustomerBuyingSize() + "");

        label_properties_title_baby.setText("John(5) $500");
        label_properties_list_baby.setText(
                "• will have a nice job\n" +
                "• artistic\n" +
                "• Will be a vegetarian\n" +
                "• very kind\n" +
                "• Will be Tall"
        );
        label_properties_title_sell.setText("Jenny(45)");
        label_properties_list_sell.setText(
                "Her dream baby:\n" +
                "• will have a nice job\n" +
                "• artistic\n" +
                "• Will be a vegetarian\n" +
                "• very kind\n" +
                "• Will be Tall"
        );
        label_properties_title_buy.setText("Gale(56)");
        label_properties_list_buy.setText(
                "Her baby for sale:\n" +
                "• will have a nice job\n" +
                "• artistic\n" +
                "• Will be a vegetarian\n" +
                "• very kind\n" +
                "• Will be Tall"
        );

        label_level_sell.setText("1");
        label_level_buy.setText("1");

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
                sound_buttonClick.play();
            }
        });

        button_browse_right = generateButton(sprite_button_browse_right, sprite_button_browse_right_inv);
        button_browse_right.setPosition(738, 528);

        button_browse_right.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Browse Right button", "Activated");
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
            }
        });

        button_promotion = generateButton(sprite_button_promotion, sprite_button_promotion_inv);
        button_promotion.setPosition(817, 118);

        button_browse_right.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Promotion button", "Activated");
                sound_buttonClick.play();
            }
        });

        button_research = generateButton(sprite_button_research, sprite_button_research_inv);
        button_research.setPosition(817, 66);

        button_research.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Research button", "Activated");
                sound_buttonClick.play();
            }
        });

        button_upgrade_sell = generateButton(sprite_button_upgrade, sprite_button_upgrade_inv);
        button_upgrade_sell.setPosition(13, 193);

        button_upgrade_sell.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Sell Upgrade button", "Activated");
                sound_buttonClick.play();
            }
        });

        button_upgrade_buy = generateButton(sprite_button_upgrade, sprite_button_upgrade_inv);
        button_upgrade_buy.setPosition(13, 484);

        button_upgrade_buy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Buy Upgrade button", "Activated");
                sound_buttonClick.play();
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
    }
}
