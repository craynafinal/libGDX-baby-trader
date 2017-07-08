package com.jsl.babytrader.Data;

import com.jsl.babytrader.Utilities.CommonUtilities;

/**
 * Represent customers.
 */

public class Customer extends Person {
    final private static int ATTRIBUTE_MAX = 5;
    final private static int AGE_MIN = 30;
    final private static int AGE_MAX = 60;

    final private static float RATE_MIN = 0.8f;
    final private static float RATE_MAX = 1.334f;

    private float rate_sell = 0f;
    private float rate_buy = 0f;

    private boolean isSelling = false;

    public Customer(boolean isSelling) {
        super(AGE_MIN, AGE_MAX, ATTRIBUTE_MAX);

        this.rate_sell = CommonUtilities.getRandomFloat(RATE_MIN, RATE_MAX);
        this.rate_buy = CommonUtilities.getRandomFloat(RATE_MIN, RATE_MAX);

        this.isSelling = isSelling;
    }

    public boolean isSelling() {
        return isSelling;
    }

    // TODO: think of a better number for customer instead of random number between 1 and 100
    @Override
    public int getSellPrice() {
        int result = 0;

        for (int i = 0; i < ATTRIBUTE_MAX; i++) {
            result += CommonUtilities.getRandomNumber(1, Attribute.MAX);
        }

        return result;
    }

    @Override
    public int getBuyValue() {
        return getSellPrice();
    }
}
