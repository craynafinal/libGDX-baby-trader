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

    // TODO: this is a placeholder sprite, replace with next button
    protected Texture sprite_button_next_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    protected Texture sprite_button_next_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    // TODO: sprites are place holders
    protected Texture sprite_slider_bar = new Texture("sprites/startPage_creditsButton_inv_191x463.png");
    protected Texture sprite_slider_knob = new Texture("sprites/temp_slider_knob.png");

    protected List<Slider> sliders_sell = null;
    protected List<Slider> sliders_buy = null;
    protected List<Label> labels = null;
    protected List<Label> labels_value_sell = null;
    protected List<Label> labels_value_buy = null;

    private BitmapFont font_nokia = null;
    private String font_nokia_path = "fonts/nokiafc22.ttf";
    private final static int font_nokia_size = 20;

    // common properties for slider setup
    protected final static String DISPLAY_FORMAT = "%03d";
    protected final static Color color_title = Color.WHITE;
    protected final static Color color_display = Color.WHITE;
    protected final static int SLIDER_VALUE_MIN = 0;
    protected final static int SLIDER_VALUE_MAX = 100;
    protected final static int SLIDER_VALUE_STEP = 1;
    protected final static float SLIDER_VALUE_SPEED = 0.3f;
    protected final static boolean IS_VERTICAL = false;
    protected final static int TABLE_PAD_TOP = 50;

    public SliderScreen(BabyTrader game) {
        super(game);

        // font setup
        font_nokia = generateFont(font_nokia_path, font_nokia_size, Color.valueOf("2F3A42"));
    }

    // create a slider using textures, attribute and integer variables needed for libgdx method
    private static Slider createSlider(Texture bar, Texture knob, int min, int max, int step, boolean vertical, int connectedValue, float speed, final Label textLabel, final Attribute attribute, final boolean isSell) {
        final Slider result = generateSlider(bar, knob, min, max, step, vertical);
        result.setValue(connectedValue); // initial value
        result.setAnimateDuration(speed);
        result.setWidth(bar.getWidth());

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

    // create a set of labels and sliders
    private static void createLabelsAndSliders(List<Slider> sliders_sell, List<Slider> sliders_buy, List<Label> labels, List<Label> labels_display_sell, List<Label> labels_display_buy, String label_display_format, boolean isPositive, BitmapFont font, Color color_title, Color color_display, Texture bar, Texture knob, int min, int max, int step, boolean vertical, float speed) {
        for (Attribute attribute : Attribute.values()) {
            if (attribute.isPositive() == isPositive) {
                // assign index to avoid unexpected placement
                int index = attribute.getIndex();

                // create label
                String name = attribute.getName();
                Label label = new Label(String.format(name), new Label.LabelStyle(font, color_title));
                labels.add(index, label);

                Label label_display_sell = new Label(String.format(label_display_format, attribute.getSellValue()), new Label.LabelStyle(font, color_display));
                label_display_sell.setText(attribute.getSellValue() + "");
                labels_display_sell.add(index, label_display_sell);

                Label label_display_buy = new Label(String.format(label_display_format, attribute.getBuyValue()), new Label.LabelStyle(font, color_display));
                label_display_buy.setText(attribute.getBuyValue() + "");
                labels_display_buy.add(index, label_display_buy);

                // create slider
                sliders_sell.add(index, createSlider(bar, knob, min, max, step, vertical, attribute.getSellValue(), speed, label_display_sell, attribute, true));
                sliders_buy.add(index, createSlider(bar, knob, min, max, step, vertical, attribute.getBuyValue(), speed, label_display_buy, attribute, false));
            }
        }
    }

    // generate a table and insert a set of labels and sliders
    private static Table createTableSliderAndLabel(int pad, List<Label> labels_title, List<Label> labels_sell, List<Label> labels_buy, List<Slider> sliders_sell, List<Slider> sliders_buy) {

        if ((labels_sell.size() != labels_buy.size()) || (sliders_sell.size() != sliders_buy.size()) || (labels_title.size() != labels_sell.size())) {
            return null;
        }

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        int spacing_title = (int) (ConstData.SCREEN_WIDTH * 0.15);
        int spacing_slider = (int) (ConstData.SCREEN_WIDTH * 0.3);
        int spacing_display = (int) (ConstData.SCREEN_WIDTH * 0.05);
        int margin_left = 10;
        int margin_top = 5;

        // for top x elements
        table.add(labels_title.get(0)).width(spacing_title).padTop(pad).padLeft(margin_left);
        table.add(sliders_sell.get(0)).width(spacing_slider).padTop(pad).padLeft(margin_left);
        table.add(labels_sell.get(0)).width(spacing_display).padTop(pad).padLeft(margin_left);
        table.add(sliders_buy.get(0)).width(spacing_slider).padTop(pad).padLeft(margin_left);
        table.add(labels_buy.get(0)).width(spacing_display).padTop(pad).padLeft(margin_left);

        // the rest of elements
        for (int i = 1; i < labels_title.size(); i++) {
            table.row();

            table.add(labels_title.get(i)).width(spacing_title).padTop(margin_top).padLeft(margin_left);
            table.add(sliders_sell.get(i)).width(spacing_slider).padTop(margin_top).padLeft(margin_left);
            table.add(labels_sell.get(i)).width(spacing_display).padTop(margin_top).padLeft(margin_left);
            table.add(sliders_buy.get(i)).width(spacing_slider).padTop(margin_top).padLeft(margin_left);
            table.add(labels_buy.get(i)).width(spacing_display).padTop(margin_top).padLeft(margin_left);
        }

        return table;
    }

    // this will take care of complicated methods above at once
    protected Table generateTable(boolean isPositive) {
        // iterative way to initialize labels and sliders using Attribute enum
        labels = new ArrayList<Label>();
        labels_value_sell = new ArrayList<Label>();
        labels_value_buy = new ArrayList<Label>();
        sliders_sell = new ArrayList<Slider>();
        sliders_buy = new ArrayList<Slider>();
        createLabelsAndSliders(sliders_sell, sliders_buy, labels, labels_value_sell, labels_value_buy, DISPLAY_FORMAT, isPositive, font_nokia, color_title, color_display, sprite_slider_bar, sprite_slider_knob, SLIDER_VALUE_MIN, SLIDER_VALUE_MAX, SLIDER_VALUE_STEP, IS_VERTICAL, SLIDER_VALUE_SPEED);

        return createTableSliderAndLabel(TABLE_PAD_TOP, labels, labels_value_sell, labels_value_buy, sliders_sell, sliders_buy);
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
