package com.jsl.babytrader.Runnables;

import com.badlogic.gdx.Gdx;
import com.jsl.babytrader.Data.Attribute;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.SharedData;

import java.util.List;
import java.util.Set;

/**
 * Created by crayna on 7/8/17.
 */

public class ResearchTeam extends Team {
    @Override
    public void run() {
        while (true) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        List<Baby> babies = SharedData.getBabies();

                        // find attributes for each baby
                        for (Baby baby : babies) {
                            Set<Attribute> attributes = baby.getAttributes();

                            // search for negative attributes and change them to positive
                            for (Attribute attribute : attributes) {

                            }
                        }
                    }


                }
            });
        }
    }
}
