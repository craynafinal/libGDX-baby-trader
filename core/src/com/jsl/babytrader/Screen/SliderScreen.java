package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.Attribute;
import com.jsl.babytrader.Data.ConstData;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains common members and methods for slider screens
 */

public abstract class SliderScreen extends BaseScreen {
    // title
    private Texture sprite_title = null;

    // next button
    protected Texture sprite_button_next_up = new Texture("sprites/sliders_nextButton_234x45.png");
    protected Texture sprite_button_next_down = new Texture("sprites/sliders_nextButton_inv_234x45.png");

    // TODO: sprites are place holders
    // slider sprites
    private Texture sprite_slider_bar = new Texture("sprites/startPage_creditsButton_inv_191x463.png");
    private Texture sprite_slider_knob = new Texture("sprites/temp_slider_knob.png");

    // slider and label containers
    private List<Slider> sliders_sell = null;
    private List<Slider> sliders_buy = null;
    private List<Label> labels_title = null;
    private List<Label> labels_display_sell = null;
    private List<Label> labels_display_buy = null;
    private List<Label> labels_sell = null;
    private List<Label> labels_buy = null;

    // fonts
    // TODO: just move all fonts to base class?
    private BitmapFont font_nokia = null;
    private String font_nokia_path = "fonts/nokiafc22.ttf";
    private final static int font_nokia_size = 20;

    // common properties for slider setup
    private final static String DISPLAY_FORMAT = "%03d";
    private final static Color COLOR_TITLE = Color.WHITE;
    private final static Color COLOR_DISPLAY = Color.WHITE;
    private final static int SLIDER_VALUE_MIN = 0;
    private final static int SLIDER_VALUE_MAX = 100;
    private final static int SLIDER_VALUE_STEP = 1;
    private final static float SLIDER_VALUE_SPEED = 0.3f;
    private final static boolean IS_VERTICAL = false;
    private final static int TABLE_PAD_TOP = 90;
    private final static int TITLE_PAD_TOP = 20;

    public SliderScreen(BabyTrader game, String title_sprite) {
        super(game);

        // setup title
        sprite_title = new Texture(title_sprite);

        // font setup
        font_nokia = generateFont(font_nokia_path, font_nokia_size, Color.valueOf("2F3A42"));
    }

    // create a slider using textures, attribute and integer variables needed for libgdx method
    private Slider createSlider(int attributeValue, final Label textLabel, final Attribute attribute, final boolean isSell) {
        final Slider result = generateSlider(sprite_slider_bar, sprite_slider_knob, SLIDER_VALUE_MIN, SLIDER_VALUE_MAX, SLIDER_VALUE_STEP, IS_VERTICAL);
        result.setValue(attributeValue); // initial value
        result.setAnimateDuration(SLIDER_VALUE_SPEED);
        result.setWidth(sprite_slider_bar.getWidth());

        result.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (isSell) {
                    attribute.setSellValue((int) result.getValue());
                    textLabel.setText(((int) result.getValue()) + "");
                    Gdx.app.log("Slider", textLabel.getText() + ": " + result.getValue() + " / internal value: " + attribute.getSellValue());
                } else {
                    attribute.setBuyValue((int) result.getValue());
                    textLabel.setText(((int) result.getValue()) + "");
                    Gdx.app.log("Slider", textLabel.getText() +  ": " + result.getValue() + " / internal value: " + attribute.getBuyValue());
                }
            }
        });

        return result;
    }

    // create a set of labels_title and sliders
    private void createLabelsAndSliders(boolean isPositive, BitmapFont font) {
        for (Attribute attribute : Attribute.values()) {
            if (attribute.isPositive() == isPositive) {
                // assign index to avoid unexpected placement
                /*
                examples:

                1. attribute index: 8 / list size: 0
                    - 8-0 = 8, subtract 8 from attribute index => 0

                2. attribute index: 0 / list size: 0
                    - 0-0 = 0, subtract 0 from attribute index => 0

                3. attribute index: 3 / list size: 0
                    - 3-0 = 3, subtract 3 from attribute index => 0

                4. attribute index: 1 / list size: 3
                    - 1-3 = 2, subtract 2 from attribute index => 1
                 */
                int index = attribute.getIndex() - (attribute.getIndex() - sliders_sell.size());

                // create label
                String name = attribute.getName();
                Label label = new Label(String.format(name), new Label.LabelStyle(font, COLOR_TITLE));
                labels_title.add(index, label);

                Label label_display_sell = new Label(String.format(DISPLAY_FORMAT, attribute.getSellValue()), new Label.LabelStyle(font, COLOR_DISPLAY));
                label_display_sell.setText(attribute.getSellValue() + "");
                labels_display_sell.add(index, label_display_sell);

                Label label_display_buy = new Label(String.format(DISPLAY_FORMAT, attribute.getBuyValue()), new Label.LabelStyle(font, COLOR_DISPLAY));
                label_display_buy.setText(attribute.getBuyValue() + "");
                labels_display_buy.add(index, label_display_buy);

                // create slider
                sliders_sell.add(index, createSlider(attribute.getSellValue(), label_display_sell, attribute, true));
                sliders_buy.add(index, createSlider(attribute.getBuyValue(), label_display_buy, attribute, false));
            }
        }
    }

    // generate a table and insert a set of labels_title and sliders
    private Table createTableSliderAndLabel() {
        if ((labels_display_sell.size() != labels_display_buy.size()) || (sliders_sell.size() != sliders_buy.size()) || (labels_title.size() != labels_display_sell.size())) {
            return null;
        }

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        int spacing_title = (int) (ConstData.SCREEN_WIDTH * 0.15);
        int spacing_slider = (int) (ConstData.SCREEN_WIDTH * 0.25);
        int spacing_display = (int) (ConstData.SCREEN_WIDTH * 0.05);
        int spacing_miniTitle = (int) (ConstData.SCREEN_WIDTH * 0.05);

        int margin_left = 10;
        int margin_top = 5;

        // TODO: fix this
        Label label_sell = new Label(String.format("Sell"), new Label.LabelStyle(new BitmapFont(), COLOR_TITLE));
        Label label_buy = new Label(String.format("Buy"), new Label.LabelStyle(new BitmapFont(), COLOR_TITLE));

        // for top x elements
        table.add(labels_title.get(0)).width(spacing_title).padTop(TABLE_PAD_TOP).padLeft(margin_left);
        table.add(label_sell).width(spacing_miniTitle).padTop(TABLE_PAD_TOP).padLeft(margin_left);
        table.add(sliders_sell.get(0)).width(spacing_slider).padTop(TABLE_PAD_TOP).padLeft(margin_left);
        table.add(labels_display_sell.get(0)).width(spacing_display).padTop(TABLE_PAD_TOP).padLeft(margin_left);
        table.add(label_buy).width(spacing_miniTitle).padTop(TABLE_PAD_TOP).padLeft(margin_left);
        table.add(sliders_buy.get(0)).width(spacing_slider).padTop(TABLE_PAD_TOP).padLeft(margin_left);
        table.add(labels_display_buy.get(0)).width(spacing_display).padTop(TABLE_PAD_TOP).padLeft(margin_left);

        // the rest of elements
        for (int i = 1; i < labels_title.size(); i++) {
            table.row();

            table.add(labels_title.get(i)).width(spacing_title).padTop(margin_top).padLeft(margin_left);
            table.add(label_sell).width(spacing_miniTitle).padTop(margin_top).padLeft(margin_left);
            table.add(sliders_sell.get(i)).width(spacing_slider).padTop(margin_top).padLeft(margin_left);
            table.add(labels_display_sell.get(i)).width(spacing_display).padTop(margin_top).padLeft(margin_left);
            table.add(label_buy).width(spacing_miniTitle).padTop(margin_top).padLeft(margin_left);
            table.add(sliders_buy.get(i)).width(spacing_slider).padTop(margin_top).padLeft(margin_left);
            table.add(labels_display_buy.get(i)).width(spacing_display).padTop(margin_top).padLeft(margin_left);
        }

        return table;
    }

    // this will take care of complicated methods above at once
    protected Table generateTable(boolean isPositive) {
        // iterative way to initialize labels_title and sliders using Attribute enum
        labels_title = new ArrayList<Label>();
        labels_display_sell = new ArrayList<Label>();
        labels_display_buy = new ArrayList<Label>();
        sliders_sell = new ArrayList<Slider>();
        sliders_buy = new ArrayList<Slider>();
        createLabelsAndSliders(isPositive, font_nokia);

        return createTableSliderAndLabel();
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        game.batch.begin();
        game.batch.draw(sprite_title, (ConstData.SCREEN_WIDTH / 2) - (sprite_title.getWidth() / 2), (ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - TITLE_PAD_TOP));
        game.batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

        sprite_button_next_up.dispose();
        sprite_button_next_down.dispose();

        sprite_slider_bar.dispose();
        sprite_slider_knob.dispose();
    }
}
