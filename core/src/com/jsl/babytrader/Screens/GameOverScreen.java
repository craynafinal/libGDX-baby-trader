package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.ConstData;
import com.jsl.babytrader.Data.SaveData;
import com.jsl.babytrader.Data.SharedData;

/**
 * Game over screen with high score result.
 */

public class GameOverScreen extends BaseScreen {
    // pure graphics
    private Texture sprite_background = new Texture("sprites/gameOver_background_1024x576.png");

    // buttons
    private Texture sprite_button_main_menu = new Texture("sprites/gameOver_button_mainMenu_186x45.png");
    private Texture sprite_button_main_menu_inv = new Texture("sprites/gameOver_button_mainMenu_inv_186x45.png");
    private Texture sprite_button_retry = new Texture("sprites/gameOver_button_retry_186x45.png");
    private Texture sprite_button_retry_inv = new Texture("sprites/gameOver_button_retry_inv_186x45.png");

    private ImageButton button_main_menu = null;
    private ImageButton button_retry = null;

    // labels
    private Label label_highScore = null;
    private Label label_highScoreBabySold = null;
    private Label label_highScoreBabyPurchased = null;
    private Label label_highScoreCustomersVisited = null;
    private Label label_currentScore = null;
    private Label label_currentBabySold = null;
    private Label label_currentBabyPurchased = null;
    private Label label_currentCustomersVisited = null;

    // records
    private int currentScore = 0;
    private int currentBabySold = 0;
    private int currentBabyPurchased = 0;
    private int currentCustomersVisited = 0;

    private int highScore = 0;
    private int highScoreBabySold = 0;
    private int highScoreBabyPurchased = 0;
    private int highScoreCustomersVisited = 0;

    public GameOverScreen(BabyTrader game) {
        super(game);

        setupMusic("music/gameover_death_tone.mp3", true);

        buttonSetup();
        labelSetup();

        int positionY = 260;
        int positionX_high = ConstData.SCREEN_WIDTH / 4;
        int positionX_current = ConstData.SCREEN_WIDTH - ConstData.SCREEN_WIDTH / 4;

        Table highScoreTable = getTable(positionX_high, positionY, label_highScore, label_highScoreBabySold, label_highScoreBabyPurchased, label_highScoreCustomersVisited);
        Table currentScoreTable = getTable(positionX_current, positionY, label_currentScore, label_currentBabySold, label_currentBabyPurchased, label_currentCustomersVisited);

        addElementsToStage(highScoreTable, currentScoreTable, button_retry, button_main_menu);

        // taking inputs from ui
        Gdx.input.setInputProcessor(stage);
    }

    private Table getTable(int x, int y, Label score, Label sold, Label purchased, Label visited) {
        int margin = 46;

        Table table = new Table();
        table.setPosition(x, y);
        table.align(Align.center);

        table.add(score);
        table.row();
        table.add(sold).padTop(margin + 4);
        table.row();
        table.add(purchased).padTop(margin);
        table.row();
        table.add(visited).padTop(margin);

        return table;
    }

    private void labelSetup() {
        int bigFontSize = 50;
        int smallFontSize = 40;

        label_highScore = getLabel(bigFontSize);
        label_highScoreBabySold = getLabel(smallFontSize);
        label_highScoreBabyPurchased = getLabel(smallFontSize);
        label_highScoreCustomersVisited = getLabel(smallFontSize);
        label_currentScore = getLabel(bigFontSize);
        label_currentBabySold = getLabel(smallFontSize);
        label_currentBabyPurchased = getLabel(smallFontSize);
        label_currentCustomersVisited = getLabel(smallFontSize);
    }

    private Label getLabel(int size) {
        Label label = new Label("", getLabelStyle(FONT_WORK_EXTRA_BOLD, size, Color.WHITE));
        label.setAlignment(Align.center);
        label.setPosition(0, 0);

        return label;
    }

    private void buttonSetup() {
        button_main_menu = generateButton(sprite_button_main_menu, sprite_button_main_menu_inv);
        button_main_menu.setPosition(515, 29);

        button_main_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Main Menu button", "Activated");
                sound_buttonClick.play();
                switchScreen(BabyTrader.initScreen);
            }
        });

        button_retry = generateButton(sprite_button_retry, sprite_button_retry_inv);
        button_retry.setPosition(320, 29);

        button_retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Exit Game button", "Activated");
                sound_buttonClick.play();
                switchScreen(BabyTrader.sliderPositiveScreen);
            }
        });
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
        game.batch.end();
    }

    private void scoreSetup() {
        currentScore = SharedData.getMoney();
        currentBabySold = Configuration.getBabiesSold();
        currentBabyPurchased = Configuration.getBabiesPurchased();
        currentCustomersVisited = Configuration.getCustomersVisited();

        SaveData.HighScore highScoreContent = SaveData.saveHighScore(currentScore, currentBabySold, currentBabyPurchased, currentCustomersVisited);
        highScore = highScoreContent.highScore;
        highScoreBabySold = highScoreContent.babySold;
        highScoreBabyPurchased = highScoreContent.babyPurchased;
        highScoreCustomersVisited = highScoreContent.customerVisited;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
        super.show();
        scoreSetup();
        System.out.println(Configuration.getBabiesSold() + " " + Configuration.getBabiesPurchased() + " " + Configuration.getCustomersVisited());
        label_currentScore.setText("$" + currentScore);
        label_currentBabySold.setText(currentBabySold + "");
        label_currentBabyPurchased.setText(currentBabyPurchased + "");
        label_currentCustomersVisited.setText(currentCustomersVisited + "");

        label_highScore.setText("$" + highScore);
        label_highScoreBabySold.setText(highScoreBabySold + "");
        label_highScoreBabyPurchased.setText(highScoreBabyPurchased + "");
        label_highScoreCustomersVisited.setText(highScoreCustomersVisited + "");
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
        sprite_button_main_menu.dispose();
        sprite_button_main_menu_inv.dispose();
        sprite_button_retry.dispose();
        sprite_button_retry_inv.dispose();
    }
}
