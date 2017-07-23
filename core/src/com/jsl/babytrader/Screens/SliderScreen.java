package com.jsl.babytrader.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.Attribute;
import com.jsl.babytrader.Data.ConstData;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains common members and methods for slider screens.
 */

public abstract class SliderScreen extends BaseScreen {
    // title
    private Texture sprite_title = null;

    // next button
    protected Texture sprite_button_next_up = new Texture("sprites/sliders_nextButton_234x45.png");
    protected Texture sprite_button_next_down = new Texture("sprites/sliders_nextButton_inv_234x45.png");
    protected ImageButton button_next = null;

    // TODO: sprites are place holders
    // slider sprites
    private Texture sprite_slider_bar = new Texture("sprites/slider_bar_141x8.png");
    private Texture sprite_slider_knob = new Texture("sprites/slider_knob_24x24.png");

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
    private final static int FONT_NOKIA_SIZE = 20;
    private final static Color FONT_NOKIA_COLOR = Color.valueOf("2F3A42");

    public SliderScreen(final BabyTrader game, String title_sprite, String bgm, boolean loop) {
        super(game);

        // setup title
        sprite_title = new Texture(title_sprite);

        // font setup
        font_nokia = generateFont(FONT_NOKIA_PATH, FONT_NOKIA_SIZE, FONT_NOKIA_COLOR);

        // bgm setup
        setupMusic(bgm, loop);
    }

    // create a slider using textures, attribute and integer variables needed for libgdx method
    private Slider createSlider(int attributeValue, final Label textLabel, final Attribute attribute, final boolean isSell) {
        int slider_min = 0;
        int slider_max = 100;
        int slider_step = 1;
        float slider_speed = 0.3f;

        final Slider result = generateSlider(sprite_slider_bar, sprite_slider_knob, slider_min, slider_max, slider_step, false);
        result.setValue(attributeValue); // initial value
        result.setAnimateDuration(slider_speed);
        result.setWidth(sprite_slider_bar.getWidth());

        result.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (isSell) {
                    attribute.setSellValue((int) result.getValue());
                    textLabel.setText("$" + ((int) result.getValue()));
                    Gdx.app.log("Slider", textLabel.getText() + ": " + result.getValue() + " / internal value: " + attribute.getSellValue());
                } else {
                    attribute.setBuyValue((int) result.getValue());
                    textLabel.setText("$" + ((int) result.getValue()));
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
                String format = "%03d";
                Color color_title = Color.valueOf("2F3A42");
                Color color_display = Color.WHITE;

                // create label
                String name = attribute.getName();
                Label label = new Label(String.format(name), new Label.LabelStyle(font, color_title));
                labels_title.add(index, label);

                Label label_display_sell = new Label("$" + String.format(format, attribute.getSellValue()), new Label.LabelStyle(font, color_display));
                label_display_sell.setText("$" + attribute.getSellValue());
                labels_display_sell.add(index, label_display_sell);

                Label label_display_buy = new Label("$" + String.format(format, attribute.getBuyValue()), new Label.LabelStyle(font, color_display));
                label_display_buy.setText("$" + attribute.getBuyValue());
                labels_display_buy.add(index, label_display_buy);

                labels_sell.add(index, new Label(String.format("Sell"), new Label.LabelStyle(font, color_display)));
                labels_buy.add(index, new Label(String.format("Buy"), new Label.LabelStyle(font, color_display)));

                // create slider
                sliders_sell.add(index, createSlider(attribute.getSellValue(), label_display_sell, attribute, true));
                sliders_buy.add(index, createSlider(attribute.getBuyValue(), label_display_buy, attribute, false));
            }
        }
    }

    // generate a table and insert a set of labels_title and sliders
    private Table addSlidersAndLabelsToTable() {
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        int spacing_title = (int) (ConstData.SCREEN_WIDTH * 0.2);
        int spacing_slider = (int) (ConstData.SCREEN_WIDTH * 0.2);
        int spacing_display = (int) (ConstData.SCREEN_WIDTH * 0.075);
        int spacing_miniTitle = (int) (ConstData.SCREEN_WIDTH * 0.05);

        int margin_left = 10;
        int margin_top = 20;
        int margin_table_top = 110;

        // for top x elements
        table.add(labels_title.get(0)).width(spacing_title).padTop(margin_table_top).padLeft(margin_left);
        table.add(labels_sell.get(0)).width(spacing_miniTitle).padTop(margin_table_top).padLeft(margin_left);
        table.add(sliders_sell.get(0)).width(spacing_slider).padTop(margin_table_top).padLeft(margin_left);
        table.add(labels_display_sell.get(0)).width(spacing_display).padTop(margin_table_top).padLeft(margin_left);
        table.add(labels_buy.get(0)).width(spacing_miniTitle).padTop(margin_table_top).padLeft(margin_left);
        table.add(sliders_buy.get(0)).width(spacing_slider).padTop(margin_table_top).padLeft(margin_left);
        table.add(labels_display_buy.get(0)).width(spacing_display).padTop(margin_table_top).padLeft(margin_left);

        // the rest of elements
        for (int i = 1; i < labels_title.size(); i++) {
            table.row();

            table.add(labels_title.get(i)).width(spacing_title).padTop(margin_top).padLeft(margin_left);
            table.add(labels_sell.get(i)).width(spacing_miniTitle).padTop(margin_top).padLeft(margin_left);
            table.add(sliders_sell.get(i)).width(spacing_slider).padTop(margin_top).padLeft(margin_left);
            table.add(labels_display_sell.get(i)).width(spacing_display).padTop(margin_top).padLeft(margin_left);
            table.add(labels_buy.get(i)).width(spacing_miniTitle).padTop(margin_top).padLeft(margin_left);
            table.add(sliders_buy.get(i)).width(spacing_slider).padTop(margin_top).padLeft(margin_left);
            table.add(labels_display_buy.get(i)).width(spacing_display).padTop(margin_top).padLeft(margin_left);
        }

        return table;
    }

    // this will take care of complicated methods above at once
    protected Table generateSliderLabelTable(boolean isPositive) {
        // iterative way to initialize labels_title and sliders using Attribute enum
        labels_title = new ArrayList<Label>();
        labels_display_sell = new ArrayList<Label>();
        labels_display_buy = new ArrayList<Label>();
        sliders_sell = new ArrayList<Slider>();
        sliders_buy = new ArrayList<Slider>();
        labels_buy = new ArrayList<Label>();
        labels_sell = new ArrayList<Label>();

        createLabelsAndSliders(isPositive, font_nokia);

        return addSlidersAndLabelsToTable();
    }

    @Override
    public void render(float delta) {
        clearingScreen();
        viewportRender();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        game.batch.begin();
        game.batch.draw(sprite_title, (ConstData.SCREEN_WIDTH / 2) - (sprite_title.getWidth() / 2), (ConstData.SCREEN_HEIGHT - sprite_title.getHeight() - 30));
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
