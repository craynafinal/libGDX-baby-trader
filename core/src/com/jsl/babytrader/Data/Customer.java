package com.jsl.babytrader.Data;

import com.badlogic.gdx.graphics.Texture;
import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent customers.
 */

public class Customer extends Person {
    final public static int ATTRIBUTE_MIN = 1;
    final public static int ATTRIBUTE_MAX = 5;
    final public static int AGE_MIN = 30;
    final public static int AGE_MAX = 60;

    final public static float RATE_MIN = 0.8f;
    final public static float RATE_MAX = 1.334f;

    private float rate_sell = 0f;
    private float rate_buy = 0f;

    private boolean isSelling = false;

    private Baby baby = null;

    public Customer(boolean isSelling) {
        super(AGE_MIN, AGE_MAX, CommonUtilities.getRandomInteger(ATTRIBUTE_MIN, ATTRIBUTE_MAX), true);

        this.rate_sell = CommonUtilities.getRandomFloat(RATE_MIN, RATE_MAX);
        this.rate_buy = CommonUtilities.getRandomFloat(RATE_MIN, RATE_MAX);

        this.isSelling = isSelling;

        // if buying situation, keep a baby
        if (!isSelling) {
            baby = new Baby();
        }
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }

    public Baby getBaby() {
        return baby;
    }

    public boolean isSelling() {
        return isSelling;
    }

    @Override
    protected String getMaleTexture() {
        String[] sprites = {
                "sprites/customer_m_001_163x170.png",
                "sprites/customer_m_002_163x170.png",
                "sprites/customer_m_003_163x170.png",
                "sprites/customer_m_004_163x170.png",
                "sprites/customer_m_005_163x170.png",
                "sprites/customer_m_006_163x170.png",
        };

        return CommonUtilities.getRandomString(sprites);
    }

    @Override
    protected String getFemaleTexture() {
        String[] sprites = {
                "sprites/customer_f_001_163x170.png",
                "sprites/customer_f_002_163x170.png",
                "sprites/customer_f_003_163x170.png",
        };

        return CommonUtilities.getRandomString(sprites);
    }

    // TODO: think of a better number for customer instead of random number between 1 and 100
    @Override
    public int getSellPrice() {
        int result = 0;

        if (baby != null) {
            for (Attribute attribute : baby.getAttributes()) {
                if (attribute.isPositive()) {
                    result += CommonUtilities.getRandomInteger(50, 100);
                } else {
                    result += CommonUtilities.getRandomInteger(1, 75);
                }
            }
        }

        return result;
    }

    // TODO: take a baby as a param?
    @Override
    public int getBuyPrice() {
        return getSellPrice();
    }
}
