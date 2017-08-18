package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Utilities.CommonUtilities;

/**
 * Tracking information.
 */

public class EventTracker implements Runnable {
    protected Sound sound_cash = Gdx.audio.newSound(Gdx.files.internal("sounds/se_cash.mp3"));

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
                    sound_cash.play();

                    sleep(500);
                    Configuration.setBabyTraderFace(true);
                    Configuration.setBuyerPurchased(false);
                } else if (newSize < babyStackCount) {
                    // new size is smaller => baby sold
                    babyStackCount = newSize;
                    Configuration.setBabyTraderFace(false);
                    Configuration.setSellerSold(true);
                    sound_cash.play();

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
