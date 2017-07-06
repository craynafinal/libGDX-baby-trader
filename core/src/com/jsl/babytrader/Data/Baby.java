package com.jsl.babytrader.Data;

import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents babies.
 */

public class Baby {
    private List<Attribute> attributes = null;
    private String name = null;
    private int age = 0;
    private boolean isMale = false;

    final private static int ATTRIBUTE_MAX = 5;
    final private static int AGE_MIN = 1;
    final private static int AGE_MAX = 6;

    final private static List<String> NAMES_MALE = new ArrayList<String>();
    final private static List<String> NAMES_FEMALE = new ArrayList<String>();

    static {
        addNames(NAMES_MALE, "Chris", "Scott", "John", "Taylor", "Jeff", "Doug", "Gale", "Jimmy");
        addNames(NAMES_FEMALE, "Andrea", "Amy", "Jenny", "Tess", "Rachael", "Christine", "Lisa", "Beth");
    }

    private static void addNames(List<String> nameList, String ... names) {
        for(String name : names) {
            nameList.add(name);
        }
    }

    public Baby() {
        this.age = CommonUtilities.getRandomNumber(AGE_MIN, AGE_MAX);
        this.attributes = Attribute.getRandomAttributes(ATTRIBUTE_MAX);
        this.isMale = CommonUtilities.getRandomBoolean() ? true : false;

        // name should appear later than isMale
        this.name = isMale ? NAMES_MALE.get(CommonUtilities.getRandomNumber(0, NAMES_MALE.size()))
                : NAMES_FEMALE.get(CommonUtilities.getRandomNumber(0, NAMES_FEMALE.size()));
    }

    public int getSellPrice() {
        int result = 0;

        for (Attribute attribute : attributes) {
            result += attribute.getSellValue();
        }

        return result;
    }

    public int getBuyValue() {
        int result = 0;

        for (Attribute attribute : attributes) {
            result += attribute.getBuyValue();
        }

        return result;
    }
}
