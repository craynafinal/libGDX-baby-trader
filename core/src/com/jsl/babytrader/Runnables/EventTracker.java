package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Utilities.CommonUtilities;

/**
 * Created by crayna on 8/5/17.
 */

public class EventTracker implements Runnable {
    private static int babyStackCount = 0;

    public EventTracker() {
        babyStackCount = SharedData.getBabySize();
    }

    @Override
    public void run() {
        while(!SharedData.isEnded()) {
            if (!SharedData.isPaused()) {
                int newSize = SharedData.getBabySize();
                if (newSize > babyStackCount) {
                    // new size is bigger => baby purchased
                    babyStackCount = newSize;
                    Configuration.setBabyTraderFace(false);
                    Configuration.setBuyerPurchased(true);

                    sleep(500);
                    Configuration.setBabyTraderFace(true);
                    Configuration.setBuyerPurchased(false);
                } else if (newSize < babyStackCount) {
                    // new size is smaller => baby sold
                    babyStackCount = newSize;
                    Configuration.setBabyTraderFace(false);
                    Configuration.setSellerSold(true);

                    sleep(500);
                    Configuration.setBabyTraderFace(true);
                    Configuration.setSellerSold(false);
                }
            }
        }
    }

    protected static void sleep(int sleepTime) {
        CommonUtilities.sleep(sleepTime);
    }
}
