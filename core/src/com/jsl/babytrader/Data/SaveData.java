package com.jsl.babytrader.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Class that saves data for later use.
 */

public class SaveData {
    final static public String TOKEN_PREFERENCE = "BabyTrader";
    final static public String TOKEN_HIGHSCORE = "HighScore";
    final static private Preferences prefs = Gdx.app.getPreferences(TOKEN_PREFERENCE);

    public static int saveHighScore(int newScore) {
        int highScore = prefs.getInteger(TOKEN_HIGHSCORE, 0);
        System.out.println("last data" + highScore);
        if (highScore < newScore) {
            prefs.putInteger(TOKEN_HIGHSCORE, newScore);
            prefs.flush();
            highScore = newScore;
        }

        return highScore;
    }
}
