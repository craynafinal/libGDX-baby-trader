package com.jsl.babytrader.Popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.Data.ConstData;

/**
 * Created by crayna on 7/23/17.
 */

public class PopupPause {
    private Table table_dialog = null;

    public PopupPause(Texture background) {
        table_dialog = new Table();
        table_dialog.setWidth(background.getWidth());
        table_dialog.setHeight(background.getHeight());
        table_dialog.setPosition((ConstData.SCREEN_WIDTH / 2) - (table_dialog.getWidth() / 2), (ConstData.SCREEN_HEIGHT / 2) - (table_dialog.getHeight() / 2));

        Image testImage = new Image(background);
        testImage.setPosition(0, 0);

        table_dialog.setBackground(testImage.getDrawable());
        table_dialog.setVisible(false);
    }

    public void setVisible(boolean visible) {
        table_dialog.setVisible(visible);
    }

    public void addElements(Actor ... actors) {
        for (Actor actor : actors) {
            table_dialog.add(actor);
            table_dialog.row();
        }
    }

    public Table getTable() {
        return table_dialog;
    }
}
