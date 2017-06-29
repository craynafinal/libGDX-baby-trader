package com.jsl.babytrader.Data;

/**
 * Created by crayna on 6/28/17.
 */

public class StaticData {

    public enum AttributeIndex {
        // positive
        Smart(0), Humorous(1), Fast(2), Self_Confidence(3), Nice_Job(4), Rich(5), Handsome(6), Tall(7),
        // negative
        Dumb(8), Boring(9), Slow(10), Selfish(11), Bad_Job(12), Poor(13), Ugly(14), Short(15);

        final private int i;

        AttributeIndex(int i) {
            this.i = i;
        }

        public int getValue() {
            return i;
        }
    }

    private class Attribute {
        private String name = null;
        private String description = null;
        private int sellValue = 0;
        private int buyValue = 0;

        public Attribute(String name, String description, int sellValue, int buyValue) {
            this.name = name;
            this.description = description;
            this.sellValue = sellValue;
            this.buyValue = buyValue;
        }


    }
}
