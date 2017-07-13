package com.jsl.babytrader.Data;

import com.badlogic.gdx.graphics.Texture;
import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent customers.
 */

public class Customer extends Person {
    // TODO: only produce positive attribute and make the attribute max random number
    final private static int ATTRIBUTE_MAX = 1;
    final private static int AGE_MIN = 30;
    final private static int AGE_MAX = 60;

    final private static float RATE_MIN = 0.8f;
    final private static float RATE_MAX = 1.334f;

    private float rate_sell = 0f;
    private float rate_buy = 0f;

    private boolean isSelling = false;

    private Baby baby = null;

    public Customer(boolean isSelling) {
        super(AGE_MIN, AGE_MAX, ATTRIBUTE_MAX, true);

        this.rate_sell = CommonUtilities.getRandomFloat(RATE_MIN, RATE_MAX);
        this.rate_buy = CommonUtilities.getRandomFloat(RATE_MIN, RATE_MAX);

        this.isSelling = isSelling;

        // if buying situation, keep a baby
        if (!isSelling) {
            baby = new Baby();
        }
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
                "sprites/customer_m_006_163x170.png"
        };

        return CommonUtilities.getRandomString(sprites);
    }

    @Override
    protected String getFemaleTexture() {
        String[] sprites = {
                "sprites/customer_f_001_163x170.png",
                "sprites/customer_f_002_163x170.png",
                "sprites/customer_f_003_163x170.png"
        };

        return CommonUtilities.getRandomString(sprites);
    }

    // TODO: think of a better number for customer instead of random number between 1 and 100
    @Override
    public int getSellPrice() {
        int result = 0;

        for (int i = 0; i < ATTRIBUTE_MAX; i++) {
            result += CommonUtilities.getRandomInteger(1, Attribute.MAX);
        }

        return result;
    }

    // TODO: take a baby as a param?
    @Override
    public int getBuyPrice() {
        return getSellPrice();
    }
}
