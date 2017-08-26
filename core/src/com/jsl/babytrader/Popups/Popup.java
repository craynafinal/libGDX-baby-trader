package com.jsl.babytrader.Popups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jsl.babytrader.Screens.BaseScreen;

/**
 * Popup window implementation.
 */
public abstract class Popup {
    protected Table table_dialog = null;

    public Popup(Texture background) {
        table_dialog = new Table();
        table_dialog.setWidth(background.getWidth());
        table_dialog.setHeight(background.getHeight());
        table_dialog.setPosition((BaseScreen.SCREEN_WIDTH / 2) - (table_dialog.getWidth() / 2), (BaseScreen.SCREEN_HEIGHT / 2) - (table_dialog.getHeight() / 2));

        Image backgroundImage = new Image(background);
        backgroundImage.setPosition(0, 0);

        table_dialog.setBackground(backgroundImage.getDrawable());
        table_dialog.setVisible(false);
    }

    public void setVisible(boolean visible) {
        table_dialog.setVisible(visible);
    }

    abstract public void addElements(Actor... actors);

    public Table getTable() {
        return table_dialog;
    }
}
