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
 * Created by crayna on 7/8/17.
 */

public class ResearchTeam extends Team {
    final private static int SLEEP_TIME_MIN = 1000;
    final private static int SLEEP_TIME_MAX = 2000;

    @Override
    public void run() {
        while (!isEnded()) {
            if (!isPaused()) {
                sleep(getWaitTime(SLEEP_TIME_MIN, SLEEP_TIME_MAX, Configuration.getLevelResearch()));

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
                                // TODO: this is going to convert all negative ones to positive ones, maybe need to make a better logic
                                while (attributes.size() < size) {
                                    attributes.addAll(Attribute.getRandomAttributesPositive(1));
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
