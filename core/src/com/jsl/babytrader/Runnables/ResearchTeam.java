package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Controls.Configuration;
import com.jsl.babytrader.Data.Attribute;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.SharedData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jsl.babytrader.Data.SharedData.isEnded;

/**
 * Represents a research team.
 */

public class ResearchTeam extends Team {
    final private static int SLEEP_TIME_MIN = 5000;
    final private static int SLEEP_TIME_MAX = 10000;

    @Override
    public void run() {
        while (!isEnded()) {
            sleep(getWaitTime(SLEEP_TIME_MIN, SLEEP_TIME_MAX, Configuration.getLevelResearch()));

            if (!isPaused()) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (this) {
                            List<Baby> babies = SharedData.getBabies();

                            // find attributes for each baby
                            for (Baby baby : babies) {
                                Set<Attribute> attributes = baby.getAttributes();

                                // save size for later
                                int size = attributes.size();

                                Set<Attribute> negativeAttribute = new HashSet<Attribute>();

                                // search for negative attributes and change them to positive
                                for (Attribute attribute : attributes) {
                                    if (!attribute.isPositive()) {
                                        negativeAttribute.add(attribute);
                                    }
                                }

                                // remove all negative ones
                                attributes.removeAll(negativeAttribute);

                                // add positive ones
                                while (attributes.size() < size) {
                                    attributes.addAll(Attribute.getRandomAttributesPositive(1));
                                }

                                // only change the first one with negative talents
                                if (negativeAttribute.size() > 0) {
                                    break;
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
