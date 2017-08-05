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
    private Texture sprite_button_exit_game = new Texture("sprites/gameOver_button_exitGame_186x45.png");
    private Texture sprite_button_exit_game_inv = new Texture("sprites/gameOver_button_exitGame_inv_186x45.png");

    private ImageButton button_main_menu = null;
    private ImageButton button_exit_game = null;

    // labels
    private Label label_highScore = null;
    private Label label_currentScore = null;
    private Label label_message = null;

    final public static String MESSAGE_POSITIVE =
        "Thank you\n" +
        "baby trader.\n\n" +
        "You have made\n" +
        "this world\n" +
        "happier place.\n\n" +
        "Until everyone finds\n" +
        "their true happiness...";

    final public static String MESSAGE_NEGATIVE =
        "You have failed\n" +
        "to bring happiness\n" +
        "to the world.\n\n" +
        "Customers didn't get\n" +
        "their dream babies,\n" +
        "and your babies didn't\n" +
        "find parents\n" +
        "they deserve...";

    public GameOverScreen(BabyTrader game) {
        super(game);

        buttonSetup();
        labelSetup();

        Table table = new Table();
        table.setPosition(780, 270);
        //table.padTop(100);
        table.add(label_highScore);
        table.row();
        table.add(label_currentScore).padTop(115);

        addElementsToStage(button_main_menu, button_exit_game, table, label_message);
    }

    private int currentScore = 0;
    private int highScore = 0;

    private void labelSetup() {
        // TODO: get highscore here and save highscore too
        label_highScore = new Label("", getLabelStyle(FONT_WORK_EXTRA_BOLD, 50, Color.WHITE));
        label_highScore.setAlignment(Align.center);
        label_highScore.setPosition(0, 0);

        label_currentScore = new Label("", getLabelStyle(FONT_WORK_EXTRA_BOLD, 50, Color.WHITE));
        label_currentScore.setAlignment(Align.center);
        label_currentScore.setPosition(0, 0);

        label_message = new Label(MESSAGE_POSITIVE, getLabelStyle(FONT_WORK_EXTRA_BOLD, 34, FONT_COLOR_LIGHT_GRAY));
        label_message.setAlignment(Align.left);
        label_message.setPosition(120, 70);
    }

    private void buttonSetup() {
        button_main_menu = generateButton(sprite_button_main_menu, sprite_button_main_menu_inv);
        button_main_menu.setPosition(598, 29);

        button_main_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Main Menu button", "Activated");
                sound_buttonClick.play();
                switchScreen(BabyTrader.initScreen);
            }
        });

        button_exit_game = generateButton(sprite_button_exit_game, sprite_button_exit_game_inv);
        button_exit_game.setPosition(793, 29);

        button_exit_game.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Exit Game button", "Activated");
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

        stage.getBatch().begin();
        stage.getBatch().draw(sprite_background, 0, 0);

        label_currentScore.setText("$" + currentScore + "");
        label_highScore.setText("$" + highScore + "");

        stage.getBatch().end();

        stage.draw();

        game.batch.begin();
        game.batch.end();
    }

    private void scoreSetup() {
        currentScore = SharedData.getMoney();
        highScore = SaveData.saveHighScore(currentScore);
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
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        sprite_button_main_menu.dispose();
        sprite_button_main_menu_inv.dispose();
        sprite_button_exit_game.dispose();
        sprite_button_exit_game_inv.dispose();
    }
}
