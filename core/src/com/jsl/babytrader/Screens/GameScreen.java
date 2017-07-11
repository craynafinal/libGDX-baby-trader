package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    private BitmapFont font_nokia = null;
    private final static int FONT_NOKIA_SIZE = 20;
    private final static Color FONT_NOKIA_COLOR = Color.valueOf("2F3A42");


    // runnables
    private PromotionTeam promotionTeam = new PromotionTeam();
    private SalesTeam salesTeam = new SalesTeam();
    private PurchaseTeam purchaseTeam = new PurchaseTeam();
    private ResearchTeam researchTeam = new ResearchTeam();

    public GameScreen(BabyTrader game) {
        super(game);

        font_nokia = generateFont(FONT_NOKIA_PATH, FONT_NOKIA_SIZE, FONT_NOKIA_COLOR);

        // user may upgrade game to allow start additional threads
        // for example, two sales team threads will provide faster sales
        new Thread(promotionTeam).start();
        new Thread(salesTeam).start();
        new Thread(purchaseTeam).start();
        new Thread(researchTeam).start();

        // bgm setup
        // TODO: switch the file extension to something cheap
        setupMusic("music/bgm_usodarake.wav", true);

        /**
         * button_browse_left = null;
         private ImageButton button_browse_right = null;
         private ImageButton button_menu = null;
         private ImageButton button_promotion = null;
         private ImageButton button_research = null;
         private ImageButton button_upgrade = null;
         */

        // start button setup
        button_browse_left = generateButton(sprite_button_browse_left, sprite_button_browse_left_inv);
        button_browse_left.setPosition(513, 526);

        button_browse_left.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Browse Left button", "Activated");
                sound_buttonClick.play();
            }
        });

        button_browse_right = generateButton(sprite_button_browse_right, sprite_button_browse_right_inv);
        button_browse_right.setPosition(708, 526);

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
        button_upgrade_sell.setPosition(22, 193);

        button_upgrade_sell.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Sell Upgrade button", "Activated");
                sound_buttonClick.play();
            }
        });

        button_upgrade_buy = generateButton(sprite_button_upgrade, sprite_button_upgrade_inv);
        button_upgrade_buy.setPosition(22, 484);

        button_upgrade_buy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Buy Upgrade button", "Activated");
                sound_buttonClick.play();
            }
        });


        addElementsToStage(button_browse_left, button_browse_right, button_menu, button_promotion, button_research, button_upgrade_sell, button_upgrade_buy);

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
        stage.getBatch().end();

        stage.draw();

        game.batch.begin();

        //game.batch.draw(sprite_background, 0, 0);

        font_nokia.draw(game.batch, "$" + SharedData.getMoney(), 100, 100);
        font_nokia.draw(game.batch, "test", 200, 200);

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
