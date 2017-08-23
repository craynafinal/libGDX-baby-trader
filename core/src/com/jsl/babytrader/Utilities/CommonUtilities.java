package com.jsl.babytrader.Utilities;

import java.util.Random;

public class CommonUtilities {

    // Simple random number generator that the result is between min and max.
    public static int getRandomInteger(int min, int max) {
        return new Random().nextInt(max-min) + min;
    }

    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static float getRandomFloat(float min, float max) {
        return new Random().nextFloat() * (max - min) + min;
    }

    public static String getRandomString(String ... strings) {
        String result = "";

        if (strings.length > 0) {
            result = strings[getRandomInteger(0, strings.length - 1)];
        }

        return result;
    }

    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
