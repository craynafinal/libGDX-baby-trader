package com.jsl.babytrader.Data;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Data shared by runnables.
 */

public class SharedData {
    // static data for default setup
    final private static int DEFAULT_STARTING_MONEY = 5000;

    // data for hud
    private static int money = DEFAULT_STARTING_MONEY;

    // persons
    private static List<Baby> babies = new ArrayList<Baby>();
    private static List<Customer> customers_selling = new ArrayList<Customer>();
    private static List<Customer> customers_buying = new ArrayList<Customer>();

    // latest persons
    private static Customer customer_selling_latest = null;
    private static Customer customer_buying_latest = null;

    // pause state
    private static boolean isPaused = false;

    synchronized public static void initialize() {
        money = DEFAULT_STARTING_MONEY;
        babies = new ArrayList<Baby>();
        customers_selling = new ArrayList<Customer>();
        customers_buying = new ArrayList<Customer>();
        customer_selling_latest = null;
        customer_buying_latest = null;
        isPaused = false;
    }

    synchronized public static boolean isPaused() {
        return isPaused;
    }

    synchronized public static void pause() {
        isPaused = true;
    }

    synchronized public static void resume() {
        isPaused = false;
    }

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

    synchronized public static Baby getBabyWithoutRemoval(int index) {
        return babies.get(index);
    }

    synchronized public static Customer getCustomerSellingLatest() { return customer_selling_latest; }
    synchronized public static Customer getCustomerBuyingLatest() { return customer_buying_latest; }

    synchronized public static Customer getCustomerSellWithoutRemoval(int index) {
        return customers_selling.get(index);
    }

    synchronized public static Customer getCustomerBuyWithoutRemoval(int index) {
        return customers_buying.get(index);
    }

    public static Customer getCustomerBuying() {
        Gdx.app.log("getCustomerBuying", "customer taken");
        return getCustomer(false);
    }

    public static Customer getCustomerSelling() {
        Gdx.app.log("getCustomer", "customer taken");
        return getCustomer(true);
    }

    private static Customer getCustomer(boolean isSelling) {
        Customer customer = null;

        synchronized (SharedData.class) {
            if (isSelling) {
                customer = customers_selling.get(0);
                customers_selling.remove(customer);
                customer_selling_latest = customer;
            } else {
                customer = customers_buying.get(0);
                customers_buying.remove(customer);
                customer_buying_latest = customer;
            }
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
        // money = DEFAULT_STARTING_MONEY;
        /*
        // TODO: temporarily adding some babies to list
        for (int i = 0; i < 5; i++) {
            babies.add(new Baby());
        }*/
    }
}
