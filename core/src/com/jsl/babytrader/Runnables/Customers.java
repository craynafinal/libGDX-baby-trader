package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Customer;
import com.jsl.babytrader.Data.SharedData;

/**
 * Created by crayna on 7/7/17.
 */

public class Customers implements Runnable {
    private static int sleepTime = 10000;

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
                    addCustomer(true);
                    addCustomer(false);
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
