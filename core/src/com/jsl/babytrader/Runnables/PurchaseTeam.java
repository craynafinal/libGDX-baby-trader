package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

import static com.jsl.babytrader.Data.SharedData.isEnded;

/**
 * Created by crayna on 7/4/17.
 */

public class PurchaseTeam extends Team {
    private static int sleepTime = 1000;

    @Override
    public void run() {
        while (!isEnded()) {
            if (!isPaused()) {
                sleep(sleepTime);

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
                                SharedData.addBaby(baby);
                            }
                        }
                    }
                });
            }
        }
    }
}
