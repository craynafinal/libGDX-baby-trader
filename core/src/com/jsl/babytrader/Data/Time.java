package com.jsl.babytrader.Data;

/**
 * Timer for game screen.
 */
public class Time {
    private int min = 0;
    private int sec = 0;

    final static private int DEFAULT_MIN = 5;

    public Time() {
        min = DEFAULT_MIN;
    }

    public void countDown() {
        if(sec == 0) {
            min--;
            sec = 59;
        } else {
            sec--;
        }
    }

    public String getTime() {
        String minute = ((min < 10) ? ("0" + min) : min + "");
        String second = ((sec < 10) ? ("0" + sec) : sec + "");

        return minute + ":" + second;
    }

    public boolean isTimeOver() {
        return (min <= 0 && sec <= 0);
    }
}
