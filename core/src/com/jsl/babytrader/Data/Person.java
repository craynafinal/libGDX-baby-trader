package com.jsl.babytrader.Data;

import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person.
 */

public abstract class Person {
    protected List<Attribute> attributes = null;
    protected String name = null;
    protected int age = 0;
    protected boolean isMale = false;

    final private static List<String> NAMES_MALE = new ArrayList<String>();
    final private static List<String> NAMES_FEMALE = new ArrayList<String>();

    public Person(int age_min, int age_max, int attribute_max) {
        this.age = CommonUtilities.getRandomNumber(age_min, age_max);
        this.attributes = Attribute.getRandomAttributes(attribute_max);
        this.isMale = CommonUtilities.getRandomBoolean() ? true : false;

        // name should appear later than isMale
        this.name = isMale ? NAMES_MALE.get(CommonUtilities.getRandomNumber(0, NAMES_MALE.size()))
                : NAMES_FEMALE.get(CommonUtilities.getRandomNumber(0, NAMES_FEMALE.size()));
    }

    protected static void addNames(List<String> nameList, String ... names) {
        for(String name : names) {
            nameList.add(name);
        }
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public abstract int getSellPrice();

    public abstract int getBuyPrice();

    static {
        addNames(NAMES_MALE,
            "Jackson",
            "Aiden",
            "Lucas",
            "Liam",
            "Noah",
            "Ethan",
            "Mason",
            "Caden",
            "Oliver",
            "Elijah",
            "Grayson",
            "Jacob",
            "Michael",
            "Benjamin",
            "Carter",
            "James",
            "Jayden",
            "Logan",
            "Alexander",
            "Caleb",
            "Ryan",
            "Luke",
            "Daniel",
            "Jack",
            "William",
            "Owen",
            "Gabriel",
            "Matthew",
            "Connor",
            "Jayce",
            "Isaac",
            "Sebastian",
            "Henry",
            "Muhammad",
            "Cameron",
            "Wyatt",
            "Dylan",
            "Nathan",
            "Nicholas",
            "Julian",
            "Eli",
            "Levi",
            "Isaiah",
            "Landon",
            "David",
            "Christian",
            "Andrew",
            "Brayden",
            "John",
            "Lincoln"
        );
        addNames(NAMES_FEMALE,
            "Sophia",
            "Emma",
            "Olivia",
            "Ava",
            "Mia",
            "Isabella",
            "Riley",
            "Aria",
            "Zoe",
            "Charlotte",
            "Lily",
            "Layla",
            "Amelia",
            "Emily",
            "Madelyn",
            "Aubrey",
            "Adalyn",
            "Madison",
            "Chloe",
            "Harper",
            "Abigail",
            "Aaliyah",
            "Avery",
            "Evelyn",
            "Kaylee",
            "Ella",
            "Ellie",
            "Scarlett",
            "Arianna",
            "Hailey",
            "Nora",
            "Addison",
            "Brooklyn",
            "Hannah",
            "Mila",
            "Leah",
            "Elizabeth",
            "Sarah",
            "Eliana",
            "Mackenzie",
            "Peyton",
            "Maria",
            "Grace",
            "Adeline",
            "Elena",
            "Anna",
            "Victoria",
            "Camilla",
            "Lillian",
            "Natalie"
        );
    }
}
