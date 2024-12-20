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

import com.udawos.pioneer.Bones;
import com.udawos.pioneer.Dungeon;
import com.udawos.pioneer.actors.Actor;
import com.udawos.pioneer.actors.mobs.Bestiary;
import com.udawos.pioneer.actors.mobs.Mob;
import com.udawos.pioneer.items.Generator;
import com.udawos.pioneer.items.Heap;
import com.udawos.pioneer.items.Item;
import com.udawos.pioneer.levels.Room.Type;
import com.udawos.pioneer.levels.painters.Painter;
import com.udawos.utils.Bundle;
import com.udawos.utils.Graph;
import com.udawos.utils.Random;
import com.udawos.utils.Rect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public abstract class RegularLevel extends Level {

    protected HashSet<Room> rooms;

    protected Room roomEntrance;
    protected Room roomExit;

    protected ArrayList<Room.Type> specials;

    public int secretDoors;

    @Override
    protected boolean build() {

        if (!initRooms()) {
            return false;
        }

        int distance;
        int retry = 0;
        int minDistance = (int)Math.sqrt( rooms.size() );
        do {
            do {
                roomEntrance = Random.element( rooms );
            } while (roomEntrance.width() < 4 || roomEntrance.height() < 4);

            do {
                roomExit = Random.element( rooms );
            } while (roomExit == roomEntrance || roomExit.width() < 4 || roomExit.height() < 4);

            Graph.buildDistanceMap( rooms, roomExit );
            distance = roomEntrance.distance();

            if (retry++ > 10) {
                return false;
            }

        } while (distance < minDistance);

        roomEntrance.type = Type.ENTRANCE;
        roomExit.type = Type.EXIT;

        HashSet<Room> connected = new HashSet<Room>();
        connected.add( roomEntrance );

        Graph.buildDistanceMap( rooms, roomExit );
        List<Room> path = Graph.buildPath( rooms, roomEntrance, roomExit );

        Room room = roomEntrance;
        for (Room next : path) {
            room.connect( next );
            room = next;
            connected.add( room );
        }

        Graph.setPrice( path, roomEntrance.distance );

        Graph.buildDistanceMap( rooms, roomExit );
        path = Graph.buildPath( rooms, roomEntrance, roomExit );

        room = roomEntrance;
        for (Room next : path) {
            room.connect( next );
            room = next;
            connected.add( room );
        }

        int nConnected = (int)(rooms.size() * Random.Float( 0.5f, 0.7f ));
        while (connected.size() < nConnected) {
            Room cr = Random.element( connected );
            Room or = Random.element( cr.neigbours );
            if (!connected.contains( or )) {
                cr.connect( or );
                connected.add( or );
            }
        }

        if (Dungeon.shopOnLevel()) {
            Room shop = null;
            for (Room r : roomEntrance.connected.keySet()) {
                if (r.connected.size() == 1 && r.width() >= 5 && r.height() >= 5) {
                    shop = r;
                    break;
                }
            }

            if (shop == null) {
                return false;
            } else {
                shop.type = Room.Type.SHOP;
            }
        }

        specials = new ArrayList<Room.Type>( Room.SPECIALS );
        if (Dungeon.bossLevel( Dungeon.depth + 1 )) {
            specials.remove( Room.Type.WEAK_FLOOR );
        }
        assignRoomType();

        paint();
        paintWater();
        paintGrass();

        return true;
    }

    protected boolean initRooms() {
        rooms = new HashSet<Room>();
        split( new Rect( 0, 0, WIDTH - 1, HEIGHT - 1 ) );

        if (rooms.size() < 8) {
            return false;
        }

        Room[] ra = rooms.toArray( new Room[0] );
        for (int i=0; i < ra.length-1; i++) {
            for (int j=i+1; j < ra.length; j++) {
                ra[i].addNeigbour( ra[j] );
            }
        }

        return true;
    }

    protected void assignRoomType() {

        int specialRooms = 0;

        for (Room r : rooms) {
            if (r.type == Type.NULL &&
                    r.connected.size() == 1) {

                if (specials.size() > 0 &&
                        r.width() > 3 && r.height() > 3 &&
                        Random.Int( specialRooms * specialRooms + 2 ) == 0) {

                    if (pitRoomNeeded) {

                        r.type = Type.PIT;
                        pitRoomNeeded = false;

                        specials.remove( Type.ARMORY );
                        specials.remove( Type.CRYPT );
                        specials.remove( Type.LABORATORY );
                        specials.remove( Type.LIBRARY );
                        specials.remove( Type.TREASURY );
                        specials.remove( Type.VAULT );
                        specials.remove( Type.WEAK_FLOOR );

                    } else if (Dungeon.depth % 5 == 2 && specials.contains( Type.LABORATORY )) {

                        r.type = Type.LABORATORY;

                    } else if (Dungeon.depth >= Dungeon.transmutation && specials.contains( Type.MAGIC_WELL )) {

                        r.type = Type.MAGIC_WELL;

                    } else {

                        int n = specials.size();
                        r.type = specials.get( Math.min( Random.Int( n ), Random.Int( n ) ) );
                        if (r.type == Type.WEAK_FLOOR) {
                            weakFloorCreated = true;
                        }

                    }

                    Room.useType( r.type );
                    specials.remove( r.type );
                    specialRooms++;

                } else if (Random.Int( 2 ) == 0){

                    HashSet<Room> neigbours = new HashSet<Room>();
                    for (Room n : r.neigbours) {
                        if (!r.connected.containsKey( n ) &&
                                !Room.SPECIALS.contains( n.type ) &&
                                n.type != Type.PIT) {

                            neigbours.add( n );
                        }
                    }
                    if (neigbours.size() > 1) {
                        r.connect( Random.element( neigbours ) );
                    }
                }
            }
        }

    }

    protected void paintWater() {
        boolean[] lake = water();
        for (int i=0; i < LENGTH; i++) {
            if (map[i] == Terrain.EMPTY && lake[i]) {
                map[i] = Terrain.WATER;
            }
        }
    }

    protected void paintGrass() {
        boolean[] grass = grass();


        for (int i=WIDTH+1; i < LENGTH-WIDTH-1; i++) {
            if (map[i] == Terrain.EMPTY && grass[i]) {
                int count = 1;
                for (int n : NEIGHBOURS8) {
                    if (grass[i + n]) {
                        count++;
                    }
                }
                map[i] = (Random.Float() < count / 12f) ? Terrain.HIGH_GRASS : Terrain.GRASS;
            }
        }
    }

    protected abstract boolean[] water();
    protected abstract boolean[] grass();


    protected int nTraps() {
        return Dungeon.depth <= 1 ? 0 : Random.Int( 1, rooms.size() + Dungeon.depth );
    }

    protected float[] trapChances() {
        float[] chances = { 1, 1, 1, 1, 1, 1, 1, 1 };
        return chances;
    }

    protected int minRoomSize = 7;
    protected int maxRoomSize = 9;

    protected void split( Rect rect ) {

        int w = rect.width();
        int h = rect.height();

        if (w > maxRoomSize && h < minRoomSize) {

            int vw = Random.Int( rect.left + 3, rect.right - 3 );
            split( new Rect( rect.left, rect.top, vw, rect.bottom ) );
            split( new Rect( vw, rect.top, rect.right, rect.bottom ) );

        } else
        if (h > maxRoomSize && w < minRoomSize) {

            int vh = Random.Int( rect.top + 3, rect.bottom - 3 );
            split( new Rect( rect.left, rect.top, rect.right, vh ) );
            split( new Rect( rect.left, vh, rect.right, rect.bottom ) );

        } else
        if ((Math.random() <= (minRoomSize * minRoomSize / rect.square()) && w <= maxRoomSize && h <= maxRoomSize) || w < minRoomSize || h < minRoomSize) {

            rooms.add( (Room)new Room().set( rect ) );

        } else {

            if (Random.Float() < (float)(w - 2) / (w + h - 4)) {
                int vw = Random.Int( rect.left + 3, rect.right - 3 );
                split( new Rect( rect.left, rect.top, vw, rect.bottom ) );
                split( new Rect( vw, rect.top, rect.right, rect.bottom ) );
            } else {
                int vh = Random.Int( rect.top + 3, rect.bottom - 3 );
                split( new Rect( rect.left, rect.top, rect.right, vh ) );
                split( new Rect( rect.left, vh, rect.right, rect.bottom ) );
            }

        }
    }

    protected void paint() {

        for (Room r : rooms) {
            if (r.type != Type.NULL) {
                placeDoors( r );
                r.type.paint( this, r );
            } else {

            }
        }

        for (Room r : rooms) {
            paintDoors( r );
        }
    }

    private void placeDoors( Room r ) {
        for (Room n : r.connected.keySet()) {
            Room.Door door = r.connected.get( n );
            if (door == null) {

                Rect i = r.intersect( n );
                if (i.width() == 0) {
                    door = new Room.Door(
                            i.left,
                            Random.Int( i.top + 1, i.bottom ) );
                } else {
                    door = new Room.Door(
                            Random.Int( i.left + 1, i.right ),
                            i.top);
                }

                r.connected.put( n, door );
                n.connected.put( r, door );
            }
        }
    }

    protected void paintDoors( Room r ) {
        for (Room n : r.connected.keySet()) {

            if (joinRooms( r, n )) {
                continue;
            }

            Room.Door d = r.connected.get( n );
            int door = d.x + d.y * WIDTH;

            switch (d.type) {
                case EMPTY:
                    map[door] = Terrain.EMPTY;
                    break;
                case REGULAR:
                    if (Dungeon.depth <= 1) {
                        map[door] = Terrain.DOOR;
                    } else {
                        boolean secret = (Dungeon.depth < 6 ? Random.Int( 12 - Dungeon.depth ) : Random.Int( 6 )) == 0;
                        map[door] = secret ? Terrain.EMPTY : Terrain.DOOR;
                        if (secret) {
                            secretDoors++;
                        }
                    }
                    break;
                case UNLOCKED:
                    map[door] = Terrain.DOOR;
                    break;
                case HIDDEN:
                    map[door] = Terrain.EMPTY;
                    break;
                case BARRICADE:
                    map[door] = Random.Int( 3 ) == 0 ? Terrain.BOOKSHELF : Terrain.RUBBLE;
                    break;
                case LOCKED:
                    map[door] = Terrain.LOCKED_DOOR;
                    break;
            }
        }
    }

    protected boolean joinRooms( Room r, Room n ) {

        if (r.type != Room.Type.STANDARD || n.type != Room.Type.STANDARD) {
            return false;
        }

        Rect w = r.intersect( n );
        if (w.left == w.right) {

            if (w.bottom - w.top < 3) {
                return false;
            }

            if (w.height() == Math.max( r.height(), n.height() )) {
                return false;
            }

            if (r.width() + n.width() > maxRoomSize) {
                return false;
            }

            w.top += 1;
            w.bottom -= 0;

            w.right++;

            Painter.fill( this, w.left, w.top, 1, w.height(), Terrain.EMPTY );

        } else {

            if (w.right - w.left < 3) {
                return false;
            }

            if (w.width() == Math.max( r.width(), n.width() )) {
                return false;
            }

            if (r.height() + n.height() > maxRoomSize) {
                return false;
            }

            w.left += 1;
            w.right -= 0;

            w.bottom++;

            Painter.fill( this, w.left, w.top, w.width(), 1, Terrain.EMPTY );
        }

        return true;
    }

    @Override
    public int nMobs() {
        return 2 + Dungeon.depth % 5 + Random.Int( 3 );
    }

    @Override
    protected void createMobs() {
        int nMobs = nMobs();
        for (int i=0; i < nMobs; i++) {
            Mob mob = Bestiary.mob( Dungeon.depth );
            do {
                mob.pos = randomRespawnCell();
            } while (mob.pos == -1);
            mobs.add( mob );
            Actor.occupyCell( mob );
        }
    }

    @Override
    public int randomRespawnCell() {
        int count = 0;
        int cell = -1;

        while (true) {

            if (++count > 10) {
                return -1;
            }

            Room room = randomRoom( Room.Type.STANDARD, 10 );
            if (room == null) {
                continue;
            }

            cell = room.random();
            if (!Dungeon.visible[cell] && Actor.findChar( cell ) == null && Level.passable[cell]) {
                return cell;
            }

        }
    }

    @Override
    public int randomDestination() {

        int cell = -1;

        while (true) {

            Room room = Random.element( rooms );
            if (room == null) {
                continue;
            }

            cell = room.random();
            if (Level.passable[cell]) {
                return cell;
            }

        }
    }

    @Override
    protected void createItems() {

        int nItems = 3;
        while (Random.Float() < 0.4f) {
            nItems++;
        }

        for (int i=0; i < nItems; i++) {
            Heap.Type type = null;
            switch (Random.Int( 20 )) {
                case 0:
                    type = Heap.Type.SKELETON;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    type = Heap.Type.CHEST;
                    break;
                case 5:
                    type = Dungeon.depth > 1 ? Heap.Type.MIMIC : Heap.Type.CHEST;
                    break;
                default:
                    type = Heap.Type.HEAP;
            }
            drop( Generator.random(), randomDropCell() ).type = type;
        }


        Item item = Bones.get();
        if (item != null) {
            drop( item, randomDropCell() ).type = Heap.Type.SKELETON;
        }
    }

    protected Room randomRoom( Room.Type type, int tries ) {
        for (int i=0; i < tries; i++) {
            Room room = Random.element( rooms );
            if (room.type == type) {
                return room;
            }
        }
        return null;
    }

    public Room room( int pos ) {
        for (Room room : rooms) {
            if (room.type != Type.NULL && room.inside( pos )) {
                return room;
            }
        }

        return null;
    }

    protected int randomDropCell() {
        while (true) {
            Room room = randomRoom( Room.Type.STANDARD, 1 );
            if (room != null) {
                int pos = room.random();
                if (passable[pos]) {
                    return pos;
                }
            }
        }
    }

    @Override
    public int pitCell() {
        for (Room room : rooms) {
            if (room.type == Type.PIT) {
                return room.random();
            }
        }

        return super.pitCell();
    }

    @Override
    public void storeInBundle( Bundle bundle ) {
        super.storeInBundle( bundle );
        bundle.put( "rooms", rooms );
    }

    @SuppressWarnings("unchecked")
    @Override
    public void restoreFromBundle( Bundle bundle ) {
        super.restoreFromBundle( bundle );

        rooms = new HashSet<Room>( (Collection<Room>) ((Collection<?>) bundle.getCollection( "rooms" )) );
        for (Room r : rooms) {
            if (r.type == Type.WEAK_FLOOR) {
                weakFloorCreated = true;
                break;
            }
        }
    }

}
