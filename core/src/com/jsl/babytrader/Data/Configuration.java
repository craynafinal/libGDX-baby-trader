package com.jsl.babytrader.Data;

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
        team_seller = new ArrayList<Thread>();
        team_buyer = new ArrayList<Thread>();

        for (int i = 0; i < MAX_SELLER_THREADS; i++) {
            team_seller.add(new Thread(new SalesTeam()));
        }

        for (int i = 0; i < MAX_BUYER_THREADS; i++) {
            team_buyer.add(new Thread(new PurchaseTeam()));
        }

        team_promotion = new Thread(new PromotionTeam());
        team_research = new Thread(new ResearchTeam());

        startSeller();
        startBuyer();
        team_promotion.start();
        team_research.start();
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
