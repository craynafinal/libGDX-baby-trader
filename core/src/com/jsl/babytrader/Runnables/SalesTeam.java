package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

import static com.jsl.babytrader.Data.SharedData.isEnded;

/**
 * Created by crayna on 6/3/17.
 */

public class SalesTeam extends Team {
    final private static int SLEEP_TIME_MIN = 500;
    final private static int SLEEP_TIME_MAX = 1000;

    @Override
    public void run() {
        while (!isEnded()) {
            if (!isPaused()) {
                sleep(getWaitTime(SLEEP_TIME_MIN, SLEEP_TIME_MAX, Configuration.getLevelSeller()));

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // proceed if there is any customer
                        if (SharedData.getCustomerSellingSize() > 0) {
                            Customer customer = SharedData.getCustomerSelling();

                            Baby baby = SharedData.getBabyByAttribute(customer.getAttributes());

                            if (baby != null) {
                                // sell if price range is good
                                if (baby.getSellPrice() <= customer.getSellPrice()) {
                                    SharedData.addMoney(baby.getSellPrice());
                                } else {
                                    SharedData.addBaby(baby);
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
