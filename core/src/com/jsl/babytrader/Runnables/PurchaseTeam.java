package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 7/4/17.
 */

public class PurchaseTeam extends Team {
    private static int sleepTime = 1000;

    @Override
    public void run() {
        while (true) {
            if (!isPaused()) {
                sleep(sleepTime);

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // proceed if there is any customer
                        if (SharedData.getCustomerBuyingSize() > 0) {
                            Customer customer = SharedData.getCustomerBuying();
                            Gdx.app.log("getting a buying customer", customer.getName());

                            Baby baby = customer.getBaby();

                            Gdx.app.log("buying price comparision", "user " + baby.getBuyPrice() + " customer " + customer.getBuyPrice());

                            // buy if price range is good
                            if (baby.getBuyPrice() >= customer.getBuyPrice()) {
                                SharedData.addMoney(-baby.getBuyPrice());
                                SharedData.addBaby(baby);

                                Gdx.app.log("baby purchased", "remaining babies " + SharedData.getBabySize());
                            } else {
                                Gdx.app.log("baby is too cheap", "customer wants " + (customer.getBuyPrice() - baby.getBuyPrice()) + " more");
                            }

                        } else {
                            // Gdx.app.log("getting a selling customer", "not found");
                        }
                    }
                });
            }
        }
    }
}
