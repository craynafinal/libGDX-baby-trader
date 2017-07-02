package com.jsl.babytrader.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jsl.babytrader.BabyTrader;
import com.jsl.babytrader.Data.Attribute;
import com.jsl.babytrader.Data.ConstData;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a stage where it handles sliders for a user to save sales values for each positive attributes.
 */

public class SliderPositiveScreen extends BaseScreen {

    // TODO: this is a placeholder sprite, replace with next button
    private Texture sprite_button_next_up = new Texture("sprites/startPage_creditsButton_191x463.png");
    private Texture sprite_button_next_down = new Texture("sprites/startPage_creditsButton_inv_191x463.png");

    private ImageButton button_next = null;

    // slider_smart_sell
    // TODO: sprites are place holders
    private Texture sprite_slider_bar = new Texture("sprites/startPage_creditsButton_inv_191x463.png");
    private Texture sprite_slider_knob = new Texture("sprites/temp_slider_knob.png");
    private Slider slider_smart_sell = null;
    Label slider_smart_sell_value_display = null;

    private List<Slider> sliders_sell = null;
    private List<Slider> sliders_buy = null;
    private List<Label> labels = null;
    private List<Label> labels_value_sell = null;
    private List<Label> labels_value_buy = null;

    private static Slider setupSlider(Texture bar, Texture knob, int min, int max, int step, boolean vertical, int connectedValue, float speed, final Label textLabel, final Attribute attribute, final boolean isSell) {
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

    private static void createLabelsAndSliders(List<Slider> sliders_sell, List<Slider> sliders_buy, List<Label> labels, List<Label> labels_display_sell, List<Label> labels_display_buy, String label_display_format, boolean isPositive, BitmapFont font, Color color_title, Color color_display, Texture bar, Texture knob, int min, int max, int step, boolean vertical, float speed) {
        for (Attribute attribute : Attribute.values()) {
            if (attribute.isPositive() == isPositive) {
                // assign index to avoid unexpected placement
                int index = attribute.getIndex();

                // create label
                String name = attribute.name();
                Label label = new Label(String.format(name), new Label.LabelStyle(font, color_title));
                labels.add(index, label);

                Label label_display_sell = new Label(String.format(label_display_format, attribute.getSellValue()), new Label.LabelStyle(font, color_display));
                label_display_sell.setText(attribute.getSellValue() + "");
                labels_display_sell.add(index, label_display_sell);

                Label label_display_buy = new Label(String.format(label_display_format, attribute.getBuyValue()), new Label.LabelStyle(font, color_display));
                label_display_buy.setText(attribute.getBuyValue() + "");
                labels_display_buy.add(index, label_display_buy);

                // create slider
                sliders_sell.add(index, setupSlider(bar, knob, min, max, step, vertical, attribute.getSellValue(), speed, label_display_sell, attribute, true));
                sliders_buy.add(index, setupSlider(bar, knob, min, max, step, vertical, attribute.getBuyValue(), speed, label_display_buy, attribute, false));
            }
        }
    }

    protected static Table generateTableSliderAndLabel(int pad, List<Label> labels_title, List<Label> labels_sell, List<Label> labels_buy, List<Slider> sliders_sell, List<Slider> sliders_buy) {

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

    public SliderPositiveScreen(final BabyTrader game) {
        super(game);

        // bgm setup
        setupMusic("music/bgm_rihujin.wav", true);

        // TODO: maybe make an array and find a way to easily add listener
        // slider_smart_sell setup

        // TODO: organize label and slider_smart_sell_value_display
        // labels
        /*
        Label label = new Label(String.format("TIME"), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        slider_smart_sell_value_display = new Label(String.format("%03d", Attribute.Smart.getSellValue()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        slider_smart_sell = setupSlider(sprite_slider_bar, sprite_slider_knob, 0, 100, 1, false, Attribute.Smart.getSellValue(), 0.3f, slider_smart_sell_value_display, Attribute.Smart, true);
        */

        // iterative way to initialize labels and sliders using Attribute enum
        labels = new ArrayList<Label>();
        labels_value_sell = new ArrayList<Label>();
        labels_value_buy = new ArrayList<Label>();
        sliders_sell = new ArrayList<Slider>();
        sliders_buy = new ArrayList<Slider>();
        createLabelsAndSliders(sliders_sell, sliders_buy, labels, labels_value_sell, labels_value_buy, "%03d", true, new BitmapFont(), Color.WHITE, Color.WHITE, sprite_slider_bar, sprite_slider_knob, 0, 100, 1, false, 0.3f);

        Table table = generateTableSliderAndLabel(50, labels, labels_value_sell, labels_value_buy, sliders_sell, sliders_buy);

        // next button setup
        button_next = generateButton(sprite_button_next_up, sprite_button_next_down);
        button_next.setPosition(100, 100);

        button_next.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Clicking Next Button", "Activated");
                sound_buttonClick.play();
                stopMusic();
                //switchScreen(new CreditScreen(game));
            }
        });

        //addElementsToStage(button_next, slider_smart_sell);
        addElementsToStage(button_next, table);

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
        stage.draw();

        game.batch.begin();

        // update slider_smart_sell value texts
        //slider_smart_sell_value_display.setText(testInteger.toString());

        game.batch.end();
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

        sprite_button_next_up.dispose();
        sprite_button_next_down.dispose();
    }
}
