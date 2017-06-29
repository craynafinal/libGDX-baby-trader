package com.jsl.babytrader.Utilities;

import java.util.Random;

/**
 * Created by crayna on 6/28/17.
 */

public class CommonUtilities {
    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max-min) + min;
    }
}
