package com.jsl.babytrader.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jsl.babytrader.BabyTrader;

/**
 * Created by crayna on 6/26/17.
 */

public class B2WorldCreator {
    public B2WorldCreator (World world, TiledMap map) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for (int i = 2; i < 6; i++) {
            // index is from the order of layers of map editor
            // the bottom one starts with 0
            for (MapObject object: map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                // three types of body
                // dynamic body = moves around
                // static body = don't move
                // kinematic body
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((rect.getX() + rect.getWidth() / 2) / BabyTrader.PPM, (rect.getY() + rect.getHeight() / 2) / BabyTrader.PPM);

                body = world.createBody(bdef);

                shape.setAsBox(rect.getWidth() / 2 / BabyTrader.PPM, rect.getHeight() / 2 / BabyTrader.PPM);
                fdef.shape = shape;
                body.createFixture(fdef);
            }
        }

    }
}
