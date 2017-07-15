package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 7/7/17.
 */
// TODO: maybe rename this to ad team (and implement its features)
public class PromotionTeam extends Team {
    private static int sleepTime = 500;

    @Override
    public void run() {
        while (true) {
            sleep(sleepTime);

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    addCustomer(true);
                    addCustomer(false);
                    Gdx.app.log("test", "finished");
                }
            });
        }
    }

    private static void addCustomer(boolean isSelling) {
        int size = 0;
        Customer customer = new Customer(isSelling);

        if(isSelling) {
            SharedData.addCustomerSelling(customer);
            size = SharedData.getCustomerSellingSize();
        } else {
            SharedData.addCustomerBuying(customer);
            size = SharedData.getCustomerBuyingSize();
        }

        Gdx.app.log("adding a customer", "customer added " + customer.getName() + " size = " + size);
    }
}
