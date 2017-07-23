package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 6/3/17.
 */

public class SalesTeam extends Team {
    // TODO: sleep time must be a little more random?
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
                        if (SharedData.getCustomerSellingSize() > 0) {
                            Customer customer = SharedData.getCustomerSelling();
                            Gdx.app.log("getting a selling customer", customer.getName());

                            Baby baby = SharedData.getBabyByAttribute(customer.getAttributes());

                            if (baby != null) {
                                Gdx.app.log("selling price comparision", "user " + baby.getSellPrice() + " customer " + customer.getSellPrice());

                                // sell if price range is good
                                if (baby.getSellPrice() <= customer.getSellPrice()) {
                                    SharedData.addMoney(baby.getSellPrice());

                                    Gdx.app.log("baby sold", "remaining babies " + SharedData.getBabySize());
                                } else {
                                    SharedData.addBaby(baby);

                                    Gdx.app.log("baby is too expensive", "user wants " + (baby.getSellPrice() - customer.getSellPrice()) + " more");
                                }
                            } else {
                                Gdx.app.log("trying to find a baby to sell", "not found");
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
