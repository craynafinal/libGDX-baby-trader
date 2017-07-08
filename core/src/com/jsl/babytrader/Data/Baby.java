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
    final private static int ATTRIBUTE_MAX = 5;
    final private static int AGE_MIN = 1;
    final private static int AGE_MAX = 6;

    public Baby() {
        super(AGE_MIN, AGE_MAX, ATTRIBUTE_MAX);
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
    public int getBuyValue() {
        int result = 0;

        for (Attribute attribute : attributes) {
            result += attribute.getBuyValue();
        }

        return result;
    }
}
