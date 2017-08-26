package com.jsl.babytrader.Utilities;

import java.util.List;
import java.util.Random;

public class CommonUtilities {

    // Simple random number generator that the result is between min and max.
    public static int getRandomInteger(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static float getRandomFloat(float min, float max) {
        return new Random().nextFloat() * (max - min) + min;
    }

    public static String getRandomString(List<String> strings) {
        String result = "";

        if (strings.size() > 0) {
            result = strings.get(getRandomInteger(0, strings.size()));
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
