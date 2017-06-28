package com.jsl.babytrader.Data;

/**
 * Created by crayna on 6/27/17.
 */

public class SharedData {
    private static int gameState;

    static {
        gameState = 0;
    }

    public static int getGameState() {
        return gameState;
    }


}
