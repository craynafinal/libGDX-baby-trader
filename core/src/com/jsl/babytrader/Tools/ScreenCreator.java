package com.jsl.babytrader.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by crayna on 6/27/17.
 */

public abstract class ScreenCreator {
    protected static ImageButton setupButton(Texture button_up, Texture button_down) {
        return new ImageButton (
                new TextureRegionDrawable(new TextureRegion(button_up)),
                new TextureRegionDrawable(new TextureRegion(button_down))
        );
    }

    protected static void addActors(Stage stage, Button... buttons) {
        for (Button button : buttons) {
            stage.addActor(button);
        }
    }
}
