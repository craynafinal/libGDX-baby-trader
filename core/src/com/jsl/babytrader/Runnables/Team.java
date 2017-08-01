package com.jsl.babytrader.Runnables;

import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Utilities.CommonUtilities;

/**
 * Base class for team components.
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

    protected static int getWaitTime(int min, int max, int level) {
        int modifier = Configuration.MAX_LEVEL - level + 1;

        return CommonUtilities.getRandomInteger(min, max) * modifier;
    }
}
