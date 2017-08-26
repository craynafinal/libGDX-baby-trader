package com.jsl.babytrader.Data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.jsl.babytrader.Utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents a person.
 */
public abstract class Person implements Disposable {
    protected Set<Attribute> attributes = null;
    protected String name = null;
    protected int age = 0;
    protected boolean isMale = false;
    private Texture sprite = null;

    final private static List<String> NAMES_MALE = new ArrayList<String>();
    final private static List<String> NAMES_FEMALE = new ArrayList<String>();

    public Person(int age_min, int age_max, int attribute_max, boolean isPositive) {
        this.age = CommonUtilities.getRandomInteger(age_min, age_max);
        this.attributes = isPositive ? Attribute.getRandomAttributesPositive(attribute_max) : Attribute.getRandomAttributesRandom(attribute_max);
        this.isMale = CommonUtilities.getRandomBoolean() ? true : false;

        // name should appear later than isMale
        this.name = isMale ? NAMES_MALE.get(CommonUtilities.getRandomInteger(0, NAMES_MALE.size()))
                : NAMES_FEMALE.get(CommonUtilities.getRandomInteger(0, NAMES_FEMALE.size()));

        sprite = isMale ? new Texture(getMaleTexture()) : new Texture(getFemaleTexture());
    }

    abstract protected String getMaleTexture();
    abstract protected String getFemaleTexture();

    public Texture getSprite() {
        return sprite;
    }

    protected static void addNames(List<String> nameList, String ... names) {
        for(String name : names) {
            nameList.add(name);
        }
    }

    public int getAge() { return age; }

    public boolean isMale() { return isMale; }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public abstract int getSellPrice();

    public abstract int getBuyPrice();

    @Override
    public void dispose() {
        if (sprite != null) {
            sprite.dispose();
        }
    }

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
