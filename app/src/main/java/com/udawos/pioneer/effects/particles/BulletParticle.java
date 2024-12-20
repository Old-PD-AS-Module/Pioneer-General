/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.udawos.pioneer.effects.particles;

import com.udawos.noosa.particles.Emitter;
import com.udawos.noosa.particles.PixelParticle;
import com.udawos.noosa.particles.Emitter.Factory;

public class BulletParticle extends PixelParticle.Shrinking {

    public static final Emitter.Factory MISSILE = new Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((BulletParticle)emitter.recycle( BulletParticle.class )).reset( x, y );
        }
    };

    public static final Emitter.Factory FACTORY = new Factory() {
        @Override
        public void emit( Emitter emitter, int index, float x, float y ) {
            ((BulletParticle)emitter.recycle( BulletParticle.class )).reset( x, y );
        }
        @Override
        public boolean lightMode() {
            return true;
        };
    };

    public BulletParticle() {
        super();

        color( 0x007722 );
        lifespan = 1f;

        acc.set( 0, -80 );
    }

    public void reset( float x, float y ) {
        revive();

        this.x = x;
        this.y = y;

        left = lifespan;

        size = 1;
        speed.set( 1000 );
    }

    @Override
    public void update() {
        super.update();
        float p = left / lifespan;
        am = p > 0.8f ? (1 - p) * 5 : 1;
    }
}