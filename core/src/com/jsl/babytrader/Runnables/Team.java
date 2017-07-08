package com.jsl.babytrader.Runnables;

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
}
