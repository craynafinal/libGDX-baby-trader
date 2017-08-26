package com.jsl.babytrader.Data;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.HashSet;
import java.util.Set;

/**
 * This enum represents attributes of babies and they also save sales values.
 */
public enum Attribute {
    // positive
    Smart(0, true),
    Humorous(1, true),
    Active(2, true),
    Self_Confidence(3, true),
    Nice_Job(4, true),
    Long_Life(5, true),
    Fashionable(6, true),
    Tall(7, true),

    // negative
    Geek(8, false),
    Perverted(9, false),
    Poor(10, false),
    Selfish(11, false),
    Ugly(12, false),
    Slow(13, false),
    Lazy(14, false),
    Single_Forever(15, false);

    private int index = 0;
    private int sellValue = 0;
    private int buyValue = 0;
    private boolean isPositive = false;

    private static final int DEFAULT_BUY_VALUE_POSITIVE = 50;
    private static final int DEFAULT_SELL_VALUE_POSITIVE = 60;
    private static final int DEFAULT_BUY_VALUE_NEGATIVE = 40;
    private static final int DEFAULT_SELL_VALUE_NEGATIVE = 50;
    private static final int DEFAULT_VARIATION = 20;

    public static final int MAX = 100;

    // depending on the type, it will assign default sale values
    Attribute(int index, boolean isPositive) {
        this.index = index;
        this.isPositive = isPositive;

        reroll();
    }

    Attribute(int index, int initSell, int initBuy) {
        this.index = index;
        sellValue = initSell;
        buyValue = initBuy;
    }

    public static void rerollAll() {
        for (Attribute attribute : Attribute.values()) {
            attribute.reroll();
        }
    }

    public void reroll() {
        if (isPositive) {
            sellValue = DEFAULT_SELL_VALUE_POSITIVE + CommonUtilities.getRandomInteger(-DEFAULT_VARIATION, DEFAULT_VARIATION);
            buyValue = DEFAULT_BUY_VALUE_POSITIVE + CommonUtilities.getRandomInteger(-DEFAULT_VARIATION, DEFAULT_VARIATION);
        } else {
            sellValue = DEFAULT_SELL_VALUE_NEGATIVE + CommonUtilities.getRandomInteger(-DEFAULT_VARIATION, DEFAULT_VARIATION);
            buyValue = DEFAULT_BUY_VALUE_NEGATIVE + CommonUtilities.getRandomInteger(-DEFAULT_VARIATION, DEFAULT_VARIATION);
        }
    }

    public int getSellValue() {
        return sellValue;
    }

    public int getBuyValue() {
        return buyValue;
    }

    public void setSellValue(int sellValue) {
        this.sellValue = sellValue;
    }

    public void setBuyValue(int buyValue) {
        this.buyValue = buyValue;
    }

    public int getIndex() {
        return index;
    }

    public boolean isPositive() {
        return isPositive;
    }

    // get max number of enum elements
    public static int getMaxNum() {
        return Attribute.values().length;
    }

    // this will return a name of instance but replaces underscore with blank space
    public String getName() {
        return this.name().replace("_", " ");
    }

    // this will return a set of attributes in list format
    public static Set<Attribute> getRandomAttributesRandom(int max) {
        return getRandomAttributes(max, false);
    }

    public static Set<Attribute> getRandomAttributesPositive(int max) {
        return getRandomAttributes(max, true);
    }

    private static Set<Attribute> getRandomAttributes(int max, boolean getPositive) {
        Set<Attribute> attributes = new HashSet<Attribute>();

        while (attributes.size() < max) {
            Attribute elem = Attribute.values()[getRandomIndex()];

            // if this is for positive attribute, make sure elem is positive attribute
            while (getPositive && !elem.isPositive) {
                elem = Attribute.values()[getRandomIndex()];
            }

            attributes.add(elem);
        }

        return attributes;
    }

    private static int getRandomIndex() {
        return CommonUtilities.getRandomInteger(0, Attribute.values().length);
    }
}
