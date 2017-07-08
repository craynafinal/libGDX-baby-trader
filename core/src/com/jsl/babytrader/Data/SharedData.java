package com.jsl.babytrader.Data;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Data shared by runnables.
 */

public class SharedData {
    // data for hud
    private static int money = 0;
    // TODO: timer

    // persons
    private static List<Person> babies = new ArrayList<Person>();
    private static List<Person> customers_selling = new ArrayList<Person>();
    private static List<Person> customers_buying = new ArrayList<Person>();

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

    synchronized public static Baby getBaby() {
        Gdx.app.log("getBaby", "baby taken");
        return (Baby)getPerson(babies);
    }

    synchronized public static Customer getCustomerBuying() {
        Gdx.app.log("getCustomerBuying", "customer taken");
        return (Customer)getPerson(customers_buying);
    }

    synchronized public static Customer getCustomerSelling() {
        Gdx.app.log("getCustomer", "customer taken");
        return (Customer)getPerson(customers_selling);
    }

    synchronized private static Person getPerson(List<Person> persons) {
        Person person = persons.get(0);
        persons.remove(person);

        return person;
    }

    synchronized public static void addBaby(Baby baby) {
        babies.add(baby);
        Gdx.app.log("addBaby", "baby added " + baby.getName());
    }

    synchronized public static void addCustomerSelling(Customer customer) {
        customers_selling.add(customer);
        Gdx.app.log("addCustomerSelling", "customer added " + customer.getName());
    }

    synchronized public static void addCustomerBuying(Customer customer) {
        customers_buying.add(customer);
        Gdx.app.log("addCustomerBuying", "customer added " + customer.getName());
    }

    synchronized public static int getMoney() {
        Gdx.app.log("getMoney", "current balance - " + money);
        return money;
    }

    synchronized public static void addMoney(int value) {
        money += value;
        Gdx.app.log("getMoney", "current balance - " + money);
    }

    synchronized public static Baby getBabyByAttribute(List<Attribute> attributes) {
        Gdx.app.log("getBabyByAttribute", attributes.toString());
        return (Baby)getPersonByAttribute(babies, attributes);
    }

    synchronized private static Person getPersonByAttribute(List<Person> persons, List<Attribute> attributes) {
        Person result = null;

        for (Person person : persons) {
            if (person.getAttributes().containsAll(attributes)) {
                result = person;
            }
        }

        if (result != null) {
            persons.remove(result);
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
