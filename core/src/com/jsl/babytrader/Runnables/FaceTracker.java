package com.jsl.babytrader.Runnables;

import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 8/5/17.
 */

public class FaceTracker implements Runnable {
    private static int babyStackCount = 0;

    public FaceTracker() {
        babyStackCount = SharedData.getBabySize();
    }

    @Override
    public void run() {
        while(!SharedData.isEnded()) {
            if (!SharedData.isPaused()) {
                int newSize = SharedData.getBabySize();
                if (newSize != babyStackCount) {
                    babyStackCount = newSize;
                    Configuration.setBabyTraderFace(false);

                    sleep(500);
                    Configuration.setBabyTraderFace(true);
                }
            }
        }
    }

    protected static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
