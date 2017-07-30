package com.jsl.babytrader.Popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jsl.babytrader.Data.ConstData;

/**
 * Popup for pause window.
 */

public class PopupPause extends Popup {
    public PopupPause(Texture background) {
        super(background);
    }

    @Override
    public void addElements(Actor ... actors) {
        table_dialog.padTop(45);

        for (Actor actor : actors) {
            table_dialog.add(actor).padTop(8);
            table_dialog.row();
        }
    }
}
