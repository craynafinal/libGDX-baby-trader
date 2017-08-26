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

    final static public List<String> TEXTURE_MALE = new ArrayList<String>();
    final static public List<String> TEXTURE_FEMALE = new ArrayList<String>();

    private boolean isSelling = false;
    private Baby baby = null;

    static {
        TEXTURE_MALE.add("sprites/customer_m_001_163x170.png");
        TEXTURE_MALE.add("sprites/customer_m_002_163x170.png");
        TEXTURE_MALE.add("sprites/customer_m_003_163x170.png");
        TEXTURE_MALE.add("sprites/customer_m_004_163x170.png");
        TEXTURE_MALE.add("sprites/customer_m_005_163x170.png");
        TEXTURE_MALE.add("sprites/customer_m_006_163x170.png");

        TEXTURE_FEMALE.add("sprites/customer_f_001_163x170.png");
        TEXTURE_FEMALE.add("sprites/customer_f_002_163x170.png");
        TEXTURE_FEMALE.add("sprites/customer_f_003_163x170.png");
    }

    public Customer(boolean isSelling) {
        super(AGE_MIN, AGE_MAX, CommonUtilities.getRandomInteger(ATTRIBUTE_MIN, ATTRIBUTE_MAX), true);

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
        return CommonUtilities.getRandomString(TEXTURE_MALE);
    }

    @Override
    protected String getFemaleTexture() {
        return CommonUtilities.getRandomString(TEXTURE_FEMALE);
    }

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

    @Override
    public int getBuyPrice() {
        return getSellPrice();
    }
}
