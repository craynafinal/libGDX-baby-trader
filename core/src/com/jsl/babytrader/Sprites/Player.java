package com.jsl.babytrader.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jsl.babytrader.BabyTrader;

/**
 * Created by crayna on 6/26/17.
 */

public class Player extends Sprite {
    public World world;
    public Body b2body;

    public Player(World world) {
        this.world = world;
        defineBabyTrader();
    }

    private void defineBabyTrader() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / BabyTrader.PPM, 32 / BabyTrader.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / BabyTrader.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
