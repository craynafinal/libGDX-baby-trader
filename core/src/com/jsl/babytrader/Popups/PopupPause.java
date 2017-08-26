package com.jsl.babytrader.Popups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
