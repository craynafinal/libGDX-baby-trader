package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

import static com.jsl.babytrader.Data.SharedData.isEnded;

/**
 * Purchase team for buying babies.
 */

public class PurchaseTeam extends Team {
    final private static int SLEEP_TIME_MIN = 500;
    final private static int SLEEP_TIME_MAX = 1000;

    @Override
    public void run() {
        while (!isEnded()) {
            sleep(getWaitTime(SLEEP_TIME_MIN, SLEEP_TIME_MAX, Configuration.getLevelBuyer()));

            if (!isPaused()) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                    // proceed if there is any customer
                    if (SharedData.getCustomerBuyingSize() > 0) {
                        Customer customer = SharedData.getCustomerBuying();
                        Baby baby = customer.getBaby();

                        // buy if price range is good
                        if (baby.getBuyPrice() >= customer.getBuyPrice()) {
                            SharedData.addMoney(-baby.getBuyPrice());
                            // buying and adding baby to stock
                            SharedData.addBaby(baby);
                            Configuration.increaseBabyPurchased();
                        }
                    }
                    }
                });
            }
        }
    }
}
