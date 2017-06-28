package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.ConstData;

import java.awt.Font;

/**
 * Created by crayna on 6/27/17.
 */

public abstract class BaseScreen implements Screen {
    // bgm placeholder
    private Music bgm = null;

    // game
    protected BabyTrader game = null;
    protected Stage stage = null;

    // commonly used assets
    protected Sound sound_buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/se_buttonClick.mp3"));
    protected BitmapFont font = new BitmapFont(Gdx.files.internal("bitmapFonts/carrier_command.xml"), Gdx.files.internal("bitmapFonts/carrier_command.png"), false);

    // view port code
    protected OrthographicCamera gamecam = null;
    private Viewport gamePort = null;

    public BaseScreen(BabyTrader game) {
        this.game = game;

        // view port setup
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(ConstData.SCREEN_WIDTH, ConstData.SCREEN_HEIGHT, gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        this.stage = new Stage(gamePort);
    }

    protected void switchScreen(BaseScreen screen) {
        this.game.setScreen(screen);
    }

    protected static ImageButton setupButton(Texture button_up, Texture button_down) {
        return new ImageButton (
                new TextureRegionDrawable(new TextureRegion(button_up)),
                new TextureRegionDrawable(new TextureRegion(button_down))
        );
    }

    protected void addActors(Button... buttons) {
        for (Button button : buttons) {
            stage.addActor(button);
        }
    }

    protected static void clearingScreen() {
        Gdx.gl.glClearColor(
                ConstData.COLOR_BG_RED,
                ConstData.COLOR_BG_BLUE,
                ConstData.COLOR_BG_GREEN,
                ConstData.COLOR_BG_ALPHA
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void viewportRender() {
        game.batch.setProjectionMatrix(gamecam.combined);
    }

    protected void setupMusic(String filepath, Boolean loop) {
        bgm = Gdx.audio.newMusic(Gdx.files.internal(filepath));
        bgm.setLooping(loop);
        playMusic();
    }

    protected void playMusic() {
        if (bgm != null) {
            bgm.play();
        }
    }

    protected void stopMusic() {
        if (bgm != null) {
            bgm.stop();
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void dispose() {
        sound_buttonClick.dispose();

        if (bgm != null) {
            bgm.dispose();
        }
    }
}
