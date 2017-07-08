package com.jsl.babytrader.Data;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Data shared by runnables.
 */

public class SharedData {
    // data for hud
    private static int money = 0;
    // TODO: timer

    // persons
    private static List<Baby> babies = new ArrayList<Baby>();
    private static List<Customer> customers_selling = new ArrayList<Customer>();
    private static List<Customer> customers_buying = new ArrayList<Customer>();

    // static data for default setup
    final private static int DEFAULT_STARTING_money = 5000;

    synchronized public static int getBabySize() {
        return babies.size();
    }

    synchronized public static int getCustomerSellingSize() {
        return customers_selling.size();
    }

    synchronized public static int getCustomerBuyingSize() {
        return customers_buying.size();
    }

    synchronized public static List<Baby> getBabies() {
        return babies;
    }

    public static Customer getCustomerBuying() {
        Gdx.app.log("getCustomerBuying", "customer taken");
        return getCustomer(customers_buying);
    }

    public static Customer getCustomerSelling() {
        Gdx.app.log("getCustomer", "customer taken");
        return getCustomer(customers_selling);
    }

    private static Customer getCustomer(List<Customer> customers) {
        Customer customer = null;

        synchronized (SharedData.class) {
            customer = customers.get(0);
            customers.remove(customer);
        }

        return customer;
    }

    public static void addBaby(Baby baby) {
        Gdx.app.log("addBaby", "started");

        synchronized (SharedData.class) {
            babies.add(baby);
        }

        Gdx.app.log("addBaby", "finished");
    }

    public static void addCustomerSelling(Customer customer) {
        Gdx.app.log("addCustomerSelling", "started");

        synchronized (SharedData.class) {
            customers_selling.add(customer);
        }

        Gdx.app.log("addCustomerSelling", "finished");
    }

    public static void addCustomerBuying(Customer customer) {
        Gdx.app.log("addCustomerBuying", "started");

        synchronized (SharedData.class) {
            customers_buying.add(customer);
        }

        Gdx.app.log("addCustomerBuying", "finished");
    }

    public static int getMoney() {
        synchronized (SharedData.class) {
            Gdx.app.log("getMoney", "current balance - " + money);
            return money;
        }
    }

    public static void addMoney(int value) {
        synchronized (SharedData.class) {
            money += value;
            Gdx.app.log("getMoney", "current balance - " + money);
        }
    }

    public static Baby getBabyByAttribute(Set<Attribute> attributes) {
        Gdx.app.log("getBabyByAttribute", attributes.toString());

        Baby result = null;

        synchronized (SharedData.class) {
            for (Baby baby : babies) {
                if (baby.getAttributes().containsAll(attributes)) {
                    result = baby;
                }
            }

            // remove should happen outside for loop due to multi-threading issue
            if (result != null) {
                babies.remove(result);
            }
        }

        return result;
    }

    // initialize
    static {
        money = DEFAULT_STARTING_money;

        // TODO: temporarily adding some babies to list
        for (int i = 0; i < 5; i++) {
            babies.add(new Baby());
        }
    }
}
