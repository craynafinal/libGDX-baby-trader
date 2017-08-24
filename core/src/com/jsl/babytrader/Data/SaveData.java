package com.jsl.babytrader.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Class that saves data for later use.
 */

public class SaveData {
    final static public String TOKEN_PREFERENCE = "BabyTrader";
    final static public String TOKEN_HIGHSCORE = "HighScore";
    final static public String TOKEN_BABYSOLD = "BabySold";
    final static public String TOKEN_BABYPURCHASED = "BabyPurchased";
    final static public String TOKEN_CUSTOMERVISITED = "CustomerVisited";
    final static public String TOKEN_MONEY = "Money";

    final static private Preferences prefs = Gdx.app.getPreferences(TOKEN_PREFERENCE);

    public static HighScore saveHighScore(int newScore, int money, int babySold, int babyPurchased, int customerVisited) {
        int highScore = prefs.getInteger(TOKEN_HIGHSCORE, 0);
        int highScoreMoney = prefs.getInteger(TOKEN_MONEY, 0);
        int highScoreBabySold = prefs.getInteger(TOKEN_BABYSOLD, 0);
        int highScoreBabyPurchased = prefs.getInteger(TOKEN_BABYPURCHASED, 0);
        int highScoreCustomerVisited = prefs.getInteger(TOKEN_CUSTOMERVISITED, 0);

        if (highScore < newScore) {
            prefs.putInteger(TOKEN_HIGHSCORE, newScore);
            prefs.putInteger(TOKEN_MONEY, money);
            prefs.putInteger(TOKEN_BABYSOLD, babySold);
            prefs.putInteger(TOKEN_BABYPURCHASED, babyPurchased);
            prefs.putInteger(TOKEN_CUSTOMERVISITED, customerVisited);

            prefs.flush();
            highScore = newScore;
            highScoreMoney = money;
            highScoreBabySold = babySold;
            highScoreBabyPurchased = babyPurchased;
            highScoreCustomerVisited = customerVisited;
        }

        return new HighScore(highScore, highScoreMoney, highScoreBabySold, highScoreBabyPurchased, highScoreCustomerVisited);
    }

    public static class HighScore {
        public int highScore = 0;
        public int money = 0;
        public int babySold = 0;
        public int babyPurchased = 0;
        public int customerVisited = 0;

        public HighScore(int highScore, int money, int babySold, int babyPurchased, int customerVisited) {
            this.highScore = highScore;
            this.money = money;
            this.babySold = babySold;
            this.babyPurchased = babyPurchased;
            this.customerVisited = customerVisited;
        }
    }
}
