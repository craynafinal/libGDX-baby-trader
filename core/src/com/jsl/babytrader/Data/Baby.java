package com.jsl.babytrader.Data;

import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents babies.
 */
public class Baby extends Person {
    final public static int ATTRIBUTE_MAX = 5;
    final public static int AGE_MIN = 1;
    final public static int AGE_MAX = 6;

    final static public List<String> TEXTURE_MALE = new ArrayList<String>();
    final static public List<String> TEXTURE_FEMALE = new ArrayList<String>();

    static {
        TEXTURE_MALE.add("sprites/baby_m_001_270x387.png");
        TEXTURE_MALE.add("sprites/baby_m_002_270x387.png");
        TEXTURE_MALE.add("sprites/baby_m_003_270x387.png");
        TEXTURE_MALE.add("sprites/baby_m_004_270x387.png");

        TEXTURE_FEMALE.add("sprites/baby_f_001_270x387.png");
        TEXTURE_FEMALE.add("sprites/baby_f_002_270x387.png");
        TEXTURE_FEMALE.add("sprites/baby_f_003_270x387.png");
        TEXTURE_FEMALE.add("sprites/baby_f_004_270x387.png");
    }

    public Baby() {
        super(AGE_MIN, AGE_MAX, ATTRIBUTE_MAX, false);
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

        for (Attribute attribute : attributes) {
            result += attribute.getSellValue();
        }

        return result;
    }

    @Override
    public int getBuyPrice() {
        int result = 0;

        for (Attribute attribute : attributes) {
            result += attribute.getBuyValue();
        }

        return result;
    }
}
