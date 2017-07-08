package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 6/3/17.
 */

public class SalesTeam extends Team {
    private static int sleepTime = 1000;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    // proceed if there is any customer
                    if (SharedData.getCustomerSellingSize() > 0) {
                        Customer customer = SharedData.getCustomerSelling();
                        Gdx.app.log("getting a selling customer", customer.getName());
                    } else {
                        Gdx.app.log("getting a selling customer", "not found");
                    }
                }
            });
        }
    }
}
