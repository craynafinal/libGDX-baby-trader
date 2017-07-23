package com.jsl.babytrader.Runnables;

import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 6/3/17.
 */

public abstract class Team implements Runnable {

    protected static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected static boolean isPaused() {
        return SharedData.isPaused();
    }
}
