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

    // thread state
    private static boolean isPaused = false;
    private static boolean isEnded = false;

    synchronized public static void initialize() {
        money = DEFAULT_STARTING_MONEY;
        babies = new ArrayList<Baby>();
        customers_selling = new ArrayList<Customer>();
        customers_buying = new ArrayList<Customer>();
        customer_selling_latest = null;
        customer_buying_latest = null;
        isPaused = false;
        isEnded = false;
    }

    synchronized public static boolean isEnded() { return isEnded; }

    synchronized public static void endThreads() { isEnded = true; }

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
        return getCustomer(false);
    }

    public static Customer getCustomerSelling() {
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

    synchronized public static void addBaby(Baby baby) {
        babies.add(baby);
    }

    synchronized public static void addCustomerSelling(Customer customer) {
        customers_selling.add(customer);
    }

    synchronized public static void addCustomerBuying(Customer customer) {
        customers_buying.add(customer);
    }

    synchronized public static int getMoney() {
        return money;
    }

    synchronized public static void spendMoney(int value) {
        money -= value;
    }

    synchronized public static void addMoney(int value) {
        money += value;
    }

    public static Baby getBabyByAttribute(Set<Attribute> attributes) {

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
}
