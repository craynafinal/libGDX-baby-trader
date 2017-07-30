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

    public PopupUpgrade(Texture background, Label.LabelStyle labelStyle) {
        super(background);
        table_dialog.align(Align.top);
        labelSetup(labelStyle);
    }

    private void labelSetup(Label.LabelStyle labelStyle) {
        type = new Label("", labelStyle);
        table_dialog.add(type).align(Align.left).padLeft(190).padTop(90);
        table_dialog.row();

        level = new Label("", labelStyle);
        table_dialog.add(level).align(Align.left).padLeft(190).padTop(-5);
        table_dialog.row();

        cost = new Label("", labelStyle);
        table_dialog.add(cost).align(Align.left).padLeft(190).padTop(-5);
        table_dialog.row();

        description = new Label("", labelStyle);
        description.setAlignment(Align.center);
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

    @Override
    public void addElements(Actor... actors) {
        Table elementContainer = new Table();

        for (Actor actor : actors) {
            elementContainer.add(actor).padRight(8);
        }

        table_dialog.add(elementContainer);
    }
}
