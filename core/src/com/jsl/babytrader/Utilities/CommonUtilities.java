package com.jsl.babytrader.Utilities;

import java.util.Random;

public class CommonUtilities {

    // Simple random number generator that the result is between min and max.
    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max-min) + min;
    }

    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }
}
