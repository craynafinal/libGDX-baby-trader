package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jsl.babytrader.BabyTrader;

/**
 * Base abstract screen class for all other screen classes.
 */
public abstract class BaseScreen implements Screen {
    // bgm placeholder
    private Music bgm = null;

    // game
    protected BabyTrader game = null;
    protected Stage stage = null;

    // commonly used assets
    protected Sound sound_buttonClick = Gdx.audio.newSound(Gdx.files.internal("sounds/se_buttonClick.mp3"));

    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 576;

    public static final float COLOR_BG_RED = 0.6862f;
    public static final float COLOR_BG_BLUE = 0.7019f;
    public static final float COLOR_BG_GREEN = 0.7137f;
    public static final float COLOR_BG_ALPHA = 1f;

    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>•";

    final protected static String FONT_NOKIA_PATH = "fonts/nokiafc22.ttf";
    final protected static String FONT_5COMPUTERS_PATH = "fonts/5Computers-In-Love.ttf";
    final protected static String FONT_CARRIER_PATH = "fonts/Carrier_Command.ttf";
    final protected static String FONT_WORK_EXTRA_BOLD = "fonts/WorkSans-ExtraBold.ttf";
    final protected static String FONT_SARPANCH_SEMI_BOLD = "fonts/Sarpanch-SemiBold.ttf";
    final protected static Color FONT_COLOR_GREEN = Color.valueOf("35eb35");
    final protected static Color FONT_COLOR_LIGHT_GRAY = Color.valueOf("d4dbe1");
    final protected static Color FONT_COLOR_DARK_BLUE = Color.valueOf("2f3a42");

    // view port code
    protected OrthographicCamera gamecam = null;
    private Viewport gamePort = null;

    public BaseScreen(BabyTrader game) {
        this.game = game;

        // view port setup
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        this.stage = new Stage(gamePort);
    }

    protected static Label.LabelStyle getLabelStyle(String filepath, int size, Color color) {
        return new Label.LabelStyle(generateFont(filepath, size, color), color);
    }

    protected static BitmapFont generateFont(String filepath, int size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(filepath));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.characters = FONT_CHARACTERS;

        BitmapFont result = generator.generateFont(parameter);
        result.setColor(color);
        generator.dispose();

        return result;
    }

    protected void switchScreen(BaseScreen screen) {
        this.game.setScreen(screen);
    }

    protected static Table generateTable(int pad, int column, Actor ... actors) {

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        int i = 0;

        // for top x elements
        while ((i < column) && (i < actors.length)) {
            table.add(actors[i]).expandX().padTop(pad);
            i++;
        }

        // the rest of elements
        while (i < actors.length) {
            if (i % column == 0) {
                table.row();
            }
            table.add(actors[i]).expandX();
            i++;
        }

        return table;
    }

    protected static Slider generateSlider(Texture bar, Texture knob, int min, int max, int stepSize, boolean vertical) {
        Drawable drawable_bar = new TextureRegionDrawable(new TextureRegion(bar));
        Drawable drawable_knob = new TextureRegionDrawable(new TextureRegion(knob));

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(drawable_bar, drawable_knob);

        return new Slider(min, max, stepSize, vertical, sliderStyle);
    }

    protected static ImageButton generateButton(Texture button_up, Texture button_down) {
        return new ImageButton (
                new TextureRegionDrawable(new TextureRegion(button_up)),
                new TextureRegionDrawable(new TextureRegion(button_down))
        );
    }

    protected void addElementsToStage(Actor... elements) {
        for (Actor element : elements) {
            stage.addActor(element);
        }
    }

    protected static void clearingScreen(float red, float blue, float green, float alpha) {
        Gdx.gl.glClearColor(red, blue, green, alpha);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected static void clearingScreen() {
        clearingScreen(COLOR_BG_RED, COLOR_BG_BLUE, COLOR_BG_GREEN, COLOR_BG_ALPHA);
    }

    protected void viewportRender() {
        game.batch.setProjectionMatrix(gamecam.combined);
    }

    protected void setupMusic(String filepath, Boolean loop) {
        bgm = Gdx.audio.newMusic(Gdx.files.internal(filepath));
        bgm.setLooping(loop);
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
    public void hide() {
        stopMusic();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
        sound_buttonClick.dispose();

        if (bgm != null) {
            bgm.dispose();
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        playMusic();
    }
}
