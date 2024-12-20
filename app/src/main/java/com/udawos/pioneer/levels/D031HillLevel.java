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
package com.udawos.pioneer.levels;

import com.udawos.pioneer.Assets;
import com.udawos.pioneer.Bones;
import com.udawos.pioneer.actors.Actor;
import com.udawos.pioneer.items.Heap;
import com.udawos.pioneer.items.Item;
import com.udawos.pioneer.levels.painters.Painter;
import com.udawos.utils.Bundle;
import com.udawos.utils.Random;

public class D031HillLevel extends Level {

    {
        color1 = 0x4b6636;
        color2 = 0xf2f2f2;
        viewDistance = 16;
    }


    private static final int NW_CORNER_X	= 16;
    private static final int NW_CORNER_Y	= 10;
    private static final int SQUARE_WIDTH	= 16;
    private static final int SQUARE_HEIGHT	= 20;
    private static final int NE_CORNER_X	= NW_CORNER_X + SQUARE_WIDTH;

    private static final int NW_CORNER_X2	= 18;
    private static final int NW_CORNER_Y2	= 12;
    private static final int SQUARE_WIDTH2	= 16;
    private static final int SQUARE_HEIGHT2	= 16;
    private static final int NE_CORNER_X2	= NW_CORNER_X2 + SQUARE_WIDTH2;

    private static final int NW_CORNER_X3	= 20;
    private static final int NW_CORNER_Y3	= 14;
    private static final int SQUARE_WIDTH3	= 15;
    private static final int SQUARE_HEIGHT3	= 12;
    private static final int NE_CORNER_X3	= NW_CORNER_X3 + SQUARE_WIDTH3;

    private static final int NW_CORNER_X4	= 23;
    private static final int NW_CORNER_Y4	= 16;
    private static final int SQUARE_WIDTH4	= 13;
    private static final int SQUARE_HEIGHT4	= 8;
    private static final int NE_CORNER_X4	= NW_CORNER_X4 + SQUARE_WIDTH4;

    private static final int NW_CORNER_X5	= 25;
    private static final int NW_CORNER_Y5	= 18;
    private static final int SQUARE_WIDTH5	= 25;
    private static final int SQUARE_HEIGHT5	= 4;
    private static final int NE_CORNER_X5	= NW_CORNER_X5 + SQUARE_WIDTH5;

    private static final int NW_CORNER_X6	= 27;
    private static final int NW_CORNER_Y6	= 19;
    private static final int SQUARE_WIDTH6	= 20;
    private static final int SQUARE_HEIGHT6	= 2;
    private static final int NE_CORNER_X6	= NW_CORNER_X6 + SQUARE_WIDTH6;

    private static final int NW_CORNER_X7	= 29;
    private static final int NW_CORNER_Y7	= 36;
    private static final int SQUARE_WIDTH7	= 20;
    private static final int SQUARE_HEIGHT7	= 10;
    private static final int NE_CORNER_X7	= NW_CORNER_X7 + SQUARE_WIDTH7;

    private static final int NW_CORNER_X8	= 31;
    private static final int NW_CORNER_Y8	= 38;
    private static final int SQUARE_WIDTH8	= 18;
    private static final int SQUARE_HEIGHT8	= 6;
    private static final int NE_CORNER_X8	= NW_CORNER_X8 + SQUARE_WIDTH8;

    private static final int NW_CORNER_X9	= 34;
    private static final int NW_CORNER_Y9	= 40;
    private static final int SQUARE_WIDTH9	= 15;
    private static final int SQUARE_HEIGHT9	= 2;
    private static final int NE_CORNER_X9	= NW_CORNER_X9 + SQUARE_WIDTH9;

    private static final int NW_CORNER_X10	= 40;
    private static final int NW_CORNER_Y10	= 0;
    private static final int SQUARE_WIDTH10	= 10;
    private static final int SQUARE_HEIGHT10	= 14;
    private static final int NE_CORNER_X10	= NW_CORNER_X10 + SQUARE_WIDTH10;

    private static final int NW_CORNER_X11	= 43;
    private static final int NW_CORNER_Y11	= 0;
    private static final int SQUARE_WIDTH11	= 7;
    private static final int SQUARE_HEIGHT11	= 12;
    private static final int NE_CORNER_X11	= NW_CORNER_X11 + SQUARE_WIDTH11;

    private static final int NW_CORNER_X12	= 46;
    private static final int NW_CORNER_Y12	= 0;
    private static final int SQUARE_WIDTH12	    = 4;
    private static final int SQUARE_HEIGHT12    = 10;
    private static final int NE_CORNER_X12	= NW_CORNER_X12 + SQUARE_WIDTH12;

    @Override
    public String tilesTex() { return Assets.TILES_MOUNTAIN;
    }

    @Override
    public String waterTex() {
        return Assets.WATER_CITY;
    }

    protected boolean[] water() {
        return Patch.generate( feeling == Feeling.CHASM ? 0.60f : 0.45f, 6 );
    }



    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
    }

    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle(bundle);
    }

    @Override
    protected boolean build() {

        //N block
        Painter.fill(this, NW_CORNER_X, NW_CORNER_Y , SQUARE_WIDTH+1, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X-1, NW_CORNER_Y , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X-1, NW_CORNER_Y+1 , 1, SQUARE_HEIGHT, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X -1, NW_CORNER_Y+SQUARE_HEIGHT , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X , NW_CORNER_Y+SQUARE_HEIGHT , SQUARE_WIDTH, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X , NW_CORNER_Y+SQUARE_HEIGHT , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X, NW_CORNER_Y , 1, SQUARE_HEIGHT, Terrain.MOUNTAIN_E);
        //Painter.fill(this, NE_CORNER_X, NW_CORNER_Y, 1, 1, Terrain.MOUNTAIN_CORNER_NE);
        Painter.fill(this, NW_CORNER_X+SQUARE_WIDTH, 0, 1, 10, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X+SQUARE_WIDTH, 10, 1, 1, Terrain.MOUNTAIN_ELBOW_SE);


        Painter.fill(this, NW_CORNER_X+SQUARE_WIDTH, NW_CORNER_Y+SQUARE_HEIGHT, 1, 6, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X+SQUARE_WIDTH, NW_CORNER_Y+SQUARE_HEIGHT, 1, 1, Terrain.MOUNTAIN_ELBOW_NE);


        //
        Painter.fill(this, NW_CORNER_X2, NW_CORNER_Y2 , SQUARE_WIDTH2+1, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X2-1, NW_CORNER_Y2 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X2-1, NW_CORNER_Y2+1 , 1, SQUARE_HEIGHT2, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X2 -1, NW_CORNER_Y2+SQUARE_HEIGHT2 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X2 , NW_CORNER_Y2+SQUARE_HEIGHT2 , SQUARE_WIDTH2, 1, Terrain.MOUNTAIN_S);
       // Painter.fill(this, NE_CORNER_X2 , NW_CORNER_Y2+SQUARE_HEIGHT2 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X2, NW_CORNER_Y2 , 1, SQUARE_HEIGHT2, Terrain.MOUNTAIN_E);
       // Painter.fill(this, NE_CORNER_X2, NW_CORNER_Y2, 1, 1, Terrain.MOUNTAIN_CORNER_NE);
        Painter.fill(this, NW_CORNER_X2+SQUARE_WIDTH2, 0, 1, 12, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X2+SQUARE_WIDTH2, 12, 1, 1, Terrain.MOUNTAIN_ELBOW_SE);

        Painter.fill(this, NW_CORNER_X2+SQUARE_WIDTH2, NW_CORNER_Y2+SQUARE_HEIGHT2, 1, 8, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X2+SQUARE_WIDTH2, NW_CORNER_Y2+SQUARE_HEIGHT2, 1, 1, Terrain.MOUNTAIN_ELBOW_NE);

        //
        Painter.fill(this, NW_CORNER_X3, NW_CORNER_Y3 , SQUARE_WIDTH3+1, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X3-1, NW_CORNER_Y3 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X3-1, NW_CORNER_Y3+1 , 1, SQUARE_HEIGHT3, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X3 -1, NW_CORNER_Y3+SQUARE_HEIGHT3 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X3 , NW_CORNER_Y3+SQUARE_HEIGHT3 , SQUARE_WIDTH3, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X3 , NW_CORNER_Y3+SQUARE_HEIGHT3 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
       //Painter.fill(this, NE_CORNER_X3 , NW_CORNER_Y3 , 1, SQUARE_HEIGHT3, Terrain.MOUNTAIN_E);
       // Painter.fill(this, NE_CORNER_X3, NW_CORNER_Y3, 1, 1, Terrain.MOUNTAIN_CORNER_NE);
        Painter.fill(this, NW_CORNER_X3+SQUARE_WIDTH3, 0, 1, 14, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X3+SQUARE_WIDTH3, 14, 1, 1, Terrain.MOUNTAIN_ELBOW_SE);

        Painter.fill(this, NW_CORNER_X3+SQUARE_WIDTH3, NW_CORNER_Y3+SQUARE_HEIGHT3, 1, 10, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X3+SQUARE_WIDTH3, NW_CORNER_Y3+SQUARE_HEIGHT3, 1, 1, Terrain.MOUNTAIN_ELBOW_NE);

        //
        Painter.fill(this, NW_CORNER_X4, NW_CORNER_Y4 , SQUARE_WIDTH4+1, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X4-1, NW_CORNER_Y4 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X4-1, NW_CORNER_Y4+1 , 1, SQUARE_HEIGHT4, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X4 -1, NW_CORNER_Y4+SQUARE_HEIGHT4 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X4 , NW_CORNER_Y4+SQUARE_HEIGHT4 , SQUARE_WIDTH4, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X4 , NW_CORNER_Y4+SQUARE_HEIGHT4 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X4 , NW_CORNER_Y4 , 1, SQUARE_HEIGHT4, Terrain.MOUNTAIN_E);
        //Painter.fill(this, NE_CORNER_X4, NW_CORNER_Y4, 1, 1, Terrain.MOUNTAIN_CORNER_NE);
        Painter.fill(this, NW_CORNER_X4+SQUARE_WIDTH4, 0, 1, 16, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X4+SQUARE_WIDTH4, 16, 1, 1, Terrain.MOUNTAIN_ELBOW_SE);


        Painter.fill(this, NW_CORNER_X4+SQUARE_WIDTH4, NW_CORNER_Y4+SQUARE_HEIGHT4, 1, 12, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X4+SQUARE_WIDTH4, NW_CORNER_Y4+SQUARE_HEIGHT4, 1, 1, Terrain.MOUNTAIN_ELBOW_NE);

        //
        Painter.fill(this, NW_CORNER_X5, NW_CORNER_Y5 , SQUARE_WIDTH5, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X5-1, NW_CORNER_Y5 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X5-1, NW_CORNER_Y5+1 , 1, SQUARE_HEIGHT5, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X5 -1, NW_CORNER_Y5+SQUARE_HEIGHT5 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X5 , NW_CORNER_Y5+SQUARE_HEIGHT5 , SQUARE_WIDTH5, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X5 , NW_CORNER_Y5+SQUARE_HEIGHT5 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X5 , NW_CORNER_Y5 , 1, SQUARE_HEIGHT5, Terrain.MOUNTAIN_E);
        //Painter.fill(this, NE_CORNER_X5, NW_CORNER_Y5, 1, 1, Terrain.MOUNTAIN_CORNER_NE);

        //
        Painter.fill(this, NW_CORNER_X6, NW_CORNER_Y6 , SQUARE_WIDTH6, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X6-1, NW_CORNER_Y6 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X6-1, NW_CORNER_Y6+1 , 1, SQUARE_HEIGHT6, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X6 -1, NW_CORNER_Y6+SQUARE_HEIGHT6 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X6 , NW_CORNER_Y6+SQUARE_HEIGHT6 , SQUARE_WIDTH6, 1, Terrain.MOUNTAIN_S);
        Painter.fill(this, NE_CORNER_X6 , NW_CORNER_Y6+SQUARE_HEIGHT6 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        Painter.fill(this, NE_CORNER_X6 , NW_CORNER_Y6 , 1, SQUARE_HEIGHT6, Terrain.MOUNTAIN_E);
        Painter.fill(this, NE_CORNER_X6, NW_CORNER_Y6, 1, 1, Terrain.MOUNTAIN_CORNER_NE);

        //
        Painter.fill(this, NW_CORNER_X7, NW_CORNER_Y7 , SQUARE_WIDTH7, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X7-1, NW_CORNER_Y7 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X7-1, NW_CORNER_Y7+1 , 1, SQUARE_HEIGHT7, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X7 -1, NW_CORNER_Y7+SQUARE_HEIGHT7 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X7 , NW_CORNER_Y7+SQUARE_HEIGHT7 , SQUARE_WIDTH7, 1, Terrain.MOUNTAIN_S);
        Painter.fill(this, NE_CORNER_X7 , NW_CORNER_Y7+SQUARE_HEIGHT7 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        Painter.fill(this, NE_CORNER_X7, NW_CORNER_Y7 , 1, SQUARE_HEIGHT7, Terrain.MOUNTAIN_E);
        Painter.fill(this, NE_CORNER_X7, NW_CORNER_Y7, 1, 1, Terrain.MOUNTAIN_CORNER_NE);


        Painter.fill(this, NW_CORNER_X8, NW_CORNER_Y8 , SQUARE_WIDTH8, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X8-1, NW_CORNER_Y8 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X8-1, NW_CORNER_Y8+1 , 1, SQUARE_HEIGHT8, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X8 -1, NW_CORNER_Y8+SQUARE_HEIGHT8 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X8 , NW_CORNER_Y8+SQUARE_HEIGHT8 , SQUARE_WIDTH8, 1, Terrain.MOUNTAIN_S);
        Painter.fill(this, NE_CORNER_X8 , NW_CORNER_Y8+SQUARE_HEIGHT8 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        Painter.fill(this, NE_CORNER_X8, NW_CORNER_Y8 , 1, SQUARE_HEIGHT8, Terrain.MOUNTAIN_E);
        Painter.fill(this, NE_CORNER_X8, NW_CORNER_Y8, 1, 1, Terrain.MOUNTAIN_CORNER_NE);


        Painter.fill(this, NW_CORNER_X9, NW_CORNER_Y9 , SQUARE_WIDTH9, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X9-1, NW_CORNER_Y9 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X9-1, NW_CORNER_Y9+1 , 1, SQUARE_HEIGHT9, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X9 -1, NW_CORNER_Y9+SQUARE_HEIGHT9 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X9 , NW_CORNER_Y9+SQUARE_HEIGHT9 , SQUARE_WIDTH9, 1, Terrain.MOUNTAIN_S);
        Painter.fill(this, NE_CORNER_X9 , NW_CORNER_Y9+SQUARE_HEIGHT9 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        Painter.fill(this, NE_CORNER_X9 , NW_CORNER_Y9 , 1, SQUARE_HEIGHT9, Terrain.MOUNTAIN_E);
        Painter.fill(this, NE_CORNER_X9 , NW_CORNER_Y9, 1, 1, Terrain.MOUNTAIN_CORNER_NE);


        //Painter.fill(this, NW_CORNER_X10+SQUARE_WIDTH4, NW_CORNER_Y10 , SQUARE_WIDTH10-SQUARE_WIDTH4, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X10-1, NW_CORNER_Y10 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X10-1, NW_CORNER_Y10+1 , 1, SQUARE_HEIGHT10, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X10 -1, NW_CORNER_Y10+SQUARE_HEIGHT10 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X10 , NW_CORNER_Y10+SQUARE_HEIGHT10 , SQUARE_WIDTH10, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X10 , NW_CORNER_Y10+SQUARE_HEIGHT10 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X10 , NW_CORNER_Y10 , 1, SQUARE_HEIGHT10, Terrain.MOUNTAIN_E);
        //Painter.fill(this, NE_CORNER_X10 , NW_CORNER_Y10, 1, 1, Terrain.MOUNTAIN_CORNER_NE);

        //Painter.fill(this, NW_CORNER_X11+SQUARE_WIDTH5, NW_CORNER_Y11 , SQUARE_WIDTH11-SQUARE_WIDTH5, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X11-1, NW_CORNER_Y11 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X11-1, NW_CORNER_Y11+1 , 1, SQUARE_HEIGHT11, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X11 -1, NW_CORNER_Y11+SQUARE_HEIGHT11 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X11 , NW_CORNER_Y11+SQUARE_HEIGHT11 , SQUARE_WIDTH11, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X11 , NW_CORNER_Y11+SQUARE_HEIGHT11 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X11 , NW_CORNER_Y11+2 , 1, SQUARE_HEIGHT11-2, Terrain.MOUNTAIN_E);
        //Painter.fill(this, NE_CORNER_X11 , NW_CORNER_Y11+1, 1, 1, Terrain.MOUNTAIN_CORNER_NE);

        //Painter.fill(this, NW_CORNER_X12+SQUARE_WIDTH6, NW_CORNER_Y12 , SQUARE_WIDTH12-SQUARE_WIDTH6, 1, Terrain.MOUNTAIN_N);
        Painter.fill(this, NW_CORNER_X12-1, NW_CORNER_Y12 , 1, 1, Terrain.MOUNTAIN_CORNER_NW);
        Painter.fill(this, NW_CORNER_X12-1, NW_CORNER_Y12+1 , 1, SQUARE_HEIGHT12, Terrain.MOUNTAIN_W);
        Painter.fill(this, NW_CORNER_X12 -1, NW_CORNER_Y12+SQUARE_HEIGHT12 , 1, 1, Terrain.MOUNTAIN_CORNER_SW);
        Painter.fill(this, NW_CORNER_X12 , NW_CORNER_Y12+SQUARE_HEIGHT12 , SQUARE_WIDTH12, 1, Terrain.MOUNTAIN_S);
        //Painter.fill(this, NE_CORNER_X12 , NW_CORNER_Y12+SQUARE_HEIGHT12 , 1, 1, Terrain.MOUNTAIN_CORNER_SE);
        //Painter.fill(this, NE_CORNER_X12 , NW_CORNER_Y12 , 1, SQUARE_HEIGHT12, Terrain.MOUNTAIN_E);
        //Painter.fill(this, NE_CORNER_X12 , NW_CORNER_Y12, 1, 1, Terrain.MOUNTAIN_CORNER_NE);


        west = 1151;
        map[west] = Terrain.WEST;

        north = 75;
        map[north] = Terrain.NORTH;

        south =  2425;
        map[south] = Terrain.SOUTH;

        return true;
    }

    @Override
    protected void decorate() {
        for (int i=WIDTH + 1; i < LENGTH - WIDTH; i++) {
            if (map[i] == Terrain.EMPTY) {
                int n = 0;
                if (map[i + 1] == Terrain.EMPTY) {
                    n++;
                }
                if (map[i - 1] == Terrain.EMPTY) {
                    n++;
                }
                if (map[i + WIDTH] == Terrain.EMPTY) {
                    n++;
                }
                if (map[i - WIDTH] == Terrain.EMPTY) {
                    n++;
                }
                if (Random.Int( 35 ) <= n) {
                    map[i] = Terrain.PEDESTAL;
                }
            }
        }

    }


    @Override
    protected void createMobs() {

    }

    public Actor respawner() {
        return null;
    }

    @Override
    protected void createItems() {
        Item item = Bones.get();
        if (item != null) {
            int pos;
            do {
                pos =
                        Random.IntRange( 6, 10 - 2 ) +
                                Random.IntRange( 7 + 1, 12 ) * WIDTH;
            } while (pos == east || map[pos] == Terrain.SIGN);
            drop( item, pos ).type = Heap.Type.SKELETON;
        }
    }

    @Override
    public int randomRespawnCell() {
        return -1;
    }

   /* @Override
    public void press( int cell, Char hero ) {

        super.press(cell, hero);

        if (hero == Dungeon.hero) {

            Mob boss = Bestiary.mob(Dungeon.depth);
            boss.state = boss.HUNTING;
            int count = 0;
            do {
                boss.pos = Random.Int( LENGTH );
            } while (
                    !passable[boss.pos] ||
                            (Dungeon.visible[boss.pos] && count++ < 20));
            GameScene.add(boss);

            if (Dungeon.visible[boss.pos]) {
                boss.notice();
                boss.sprite.alpha( 0 );
                boss.sprite.parent.add( new AlphaTweener( boss.sprite, 1, 0.1f ) );
            }

            Dungeon.observe();
        }
    }*/

    @Override
    public String tileName( int tile ) {
        switch (tile) {
            case Terrain.WATER:
                return "Suspiciously colored water";
            case Terrain.HIGH_GRASS:
                return "High blooming flowers";
            default:
                return super.tileName( tile );
        }
    }

    @Override
    public String tileDesc(int tile) {
        switch (tile) {
            case Terrain.ENTRANCE:
                return "A ramp leads up to the upper depth.";
            case Terrain.EXIT:
                return "A ramp leads down to the lower depth.";
            case Terrain.WALL_DECO:
            case Terrain.HILL:
                return "Several tiles are missing here.";
            case Terrain.EMPTY_SP:
                return "Thick carpet covers the floor.";
            case Terrain.STATUE:
            case Terrain.STATUE_SP:
                return "The statue depicts some dwarf standing in a heroic stance.";
            case Terrain.BOOKSHELF:
                return "The rows of books on different disciplines fill the bookshelf.";
            default:
                return super.tileDesc( tile );
        }
    }

}
