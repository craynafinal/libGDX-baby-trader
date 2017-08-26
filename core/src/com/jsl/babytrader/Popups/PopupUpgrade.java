package com.jsl.babytrader.Popups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Popup for asking a player if he/she wants to upgrade a feature.
 */
public class PopupUpgrade extends Popup {
    private Label type = null;
    private Label level = null;
    private Label cost = null;
    private Label description = null;

    // text data for each upgrade
    final public static String TYPE_SELLER = "Seller".toUpperCase();
    final public static String TYPE_BUYER = "Buyer".toUpperCase();
    final public static String TYPE_PROMOTION = "Promotion".toUpperCase();
    final public static String TYPE_RESEARCH = "Research".toUpperCase();

    final public static String DESCRIPTION_SELLER =
        ("Seller upgrade will add\n" +
        "another sales agent\n" +
        "to sell babies faster.").toUpperCase();

    final public static String DESCRIPTION_BUYER =
        ("Buyer upgrade will add\n" +
        "another sales agent\n" +
        "to buy babies faster.").toUpperCase();

    final public static String DESCRIPTION_PROMOTION =
        ("Promotion upgrade will\n" +
        "bring customers\n" +
        "to your store more often.").toUpperCase();

    final public static String DESCRIPTION_RESEARCH =
        ("Research upgrade will\n" +
        "switch negative talents to\n" +
        "positive ones more often.").toUpperCase();

    public PopupUpgrade(Texture background, Label.LabelStyle labelStyle) {
        super(background);
        table_dialog.align(Align.top);
        labelSetup(labelStyle);
    }

    public static String getLevelText(int prevLevel, int nextLevel, boolean isMax) {
        return prevLevel + " -> " + nextLevel + (isMax ? "(Max)".toUpperCase() : "");
    }

    public static String getMoneyText(int money) {
        return "$" + money;
    }

    private void labelSetup(Label.LabelStyle labelStyle) {
        type = new Label("", labelStyle);
        table_dialog.add(type).align(Align.left).padLeft(190).padTop(90).width(300);
        table_dialog.row();

        level = new Label("", labelStyle);
        table_dialog.add(level).align(Align.left).padLeft(190).padTop(-5).width(300);
        table_dialog.row();

        cost = new Label("", labelStyle);
        table_dialog.add(cost).align(Align.left).padLeft(190).padTop(-5).width(300);
        table_dialog.row();

        description = new Label("", labelStyle);
        description.setAlignment(Align.center);
        description.setWidth(table_dialog.getWidth() - 30);
        table_dialog.add(description).center().minHeight(100).maxHeight(100).padTop(24).padBottom(14);
        table_dialog.row();

    }

    public void setTextType(String text) {
        type.setText(text);
    }

    public void setTextLevel(String text) {
        level.setText(text);
    }

    public void setTextCost(String text) {
        cost.setText(text);
    }

    public void setTextDescription(String text) {
        description.setText(text);
    }

    public String getTextType() {
        return type.getText().toString();
    }

    @Override
    public void addElements(Actor... actors) {
        Table elementContainer = new Table();

        for (Actor actor : actors) {
            elementContainer.add(actor).padRight(8);
        }

        table_dialog.add(elementContainer);
    }
}
