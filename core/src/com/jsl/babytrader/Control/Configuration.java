package com.jsl.babytrader.Control;

import com.badlogic.gdx.utils.Timer;
import com.jsl.babytrader.Data.Baby;
import com.jsl.babytrader.Data.SharedData;
import com.jsl.babytrader.Data.Time;
import com.jsl.babytrader.Runnables.PromotionTeam;
import com.jsl.babytrader.Runnables.PurchaseTeam;
import com.jsl.babytrader.Runnables.ResearchTeam;
import com.jsl.babytrader.Runnables.SalesTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crayna on 7/19/17.
 */

public class Configuration {
    private Time time = new Time();

    private int level_seller = 1;
    private int level_buyer = 1;
    private int level_promotion = 1;
    private int level_research = 1;

    final public static int MAX_LEVEL = 3;
    final public static int UPGRADE_INTERVAL_MONEY = 3000;

    final public static int MAX_SELLER_THREADS = 5;
    final public static int MAX_BUYER_THREADS = 5;

    private List<Thread> team_seller = null;
    private List<Thread> team_buyer = null;

    private Thread team_promotion = null;
    private Thread team_research = null;

    private int team_seller_count = 0;
    private int team_buyer_count = 0;

    public Configuration() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                time.countDown();
            }
        }, 0, 1);

        //team_seller = new ArrayList<Thread>();
        //team_buyer = new ArrayList<Thread>();

        //initialize();
    }

    public void start() {
        // timer start
        startSeller();
        startBuyer();
        team_promotion.start();
        team_research.start();

        Timer.instance().start();
    }
    public void initialize() {
        SharedData.initialize();

        time = new Time();

        level_seller = 1;
        level_buyer = 1;
        level_promotion = 1;
        level_research = 1;

        team_seller = null;
        team_buyer = null;

        team_promotion = null;
        team_research = null;

        team_seller_count = 0;
        team_buyer_count = 0;

        for (int i = 0; i < 5; i++) {
            SharedData.addBaby(new Baby());
        }

        team_seller = new ArrayList<Thread>();
        team_buyer = new ArrayList<Thread>();

        for (int i = 0; i < MAX_SELLER_THREADS; i++) {
            team_seller.add(new Thread(new SalesTeam()));
            System.out.println(i);
        }

        for (int i = 0; i < MAX_BUYER_THREADS; i++) {
            team_buyer.add(new Thread(new PurchaseTeam()));
        }

        team_promotion = new Thread(new PromotionTeam());
        team_research = new Thread(new ResearchTeam());
        /*
        for (int i = 0; i < MAX_SELLER_THREADS; i++) {
            System.out.println((team_seller == null) ? "null" : "not null");
            team_seller.get(i).interrupt();
        }

        for (int i = 0; i < MAX_BUYER_THREADS; i++) {
            team_buyer.get(i).interrupt();
        }
        */

        //start();
    }

    public void interrupt() {
        for (int i = 0; i < team_seller_count; i++) {
            team_seller.get(i).interrupt();
        }

        for (int i = 0; i < team_buyer_count; i++) {
            team_buyer.get(i).interrupt();
        }

        team_seller = null;
        team_buyer = null;

        team_seller_count = 0;
        team_buyer_count = 0;

        team_promotion.interrupt();
        team_research.interrupt();

        System.gc();
    }

    public void pause() throws InterruptedException {
        Timer.instance().stop();
        SharedData.pause();
    }

    public void resume() {
        Timer.instance().start();
        SharedData.resume();
    }

    public String getTime() {
        return time.getTime();
    }

    private void startSeller() {
        if (team_seller_count < MAX_SELLER_THREADS) {
            team_seller.get(team_seller_count).start();
            team_seller_count++;
        }
    }

    private void startBuyer() {
        if (team_buyer_count < MAX_BUYER_THREADS) {
            team_buyer.get(team_buyer_count).start();
            team_buyer_count++;
        }
    }

    public void levelUpSeller() {
        if (level_seller < MAX_LEVEL) {
            level_seller++;
            startSeller();
        }
    }

    public void levelUpBuyer() {
        if (level_buyer < MAX_LEVEL) {
            level_buyer++;
            startBuyer();
        }
    }

    public void levelUpPromotion() {
        if (level_promotion < MAX_LEVEL) {
            level_promotion++;
        }
    }

    public void levelUpResearch() {
        if (level_research < MAX_LEVEL) {
            level_research++;
        }
    }

    public int getLevelSeller() {
        return level_seller;
    }

    public int getLevelBuyer() {
        return level_buyer;
    }

    public int getLevelPromotion() {
        return level_promotion;
    }

    public int getLevelResearch() {
        return level_research;
    }


}
