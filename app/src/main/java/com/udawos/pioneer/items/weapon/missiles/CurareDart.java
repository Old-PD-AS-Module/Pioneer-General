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
package com.udawos.pioneer.items.weapon.missiles;

import com.udawos.pioneer.actors.Char;
import com.udawos.pioneer.actors.buffs.Buff;
import com.udawos.pioneer.actors.buffs.Paralysis;
import com.udawos.pioneer.items.Item;
import com.udawos.pioneer.sprites.ItemSpriteSheet;
import com.udawos.utils.Random;

public class CurareDart extends MissileWeapon {

	public static final float DURATION	= 3f;
	
	{
		name = "curare dart";
		image = ItemSpriteSheet.CURARE_DART;
		
		STR = 14;
		
		MIN = 1;
		MAX = 3;
	}
	
	public CurareDart() {
		this( 1 );
	}
	
	public CurareDart( int number ) {
		super();
		quantity = number;
	}
	
	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		Buff.prolong( defender, Paralysis.class, DURATION );
		super.proc( attacker, defender, damage );
	}
	
	@Override
	public String desc() {
		return 
			"These little evil darts don't do much damage but they can paralyze " +
			"the target leaving it helpless and motionless for some time.";
	}
	
	@Override
	public Item random() {
		quantity = Random.Int( 2, 5 );
		return this;
	}
	
	@Override
	public int price() {
		return 12 * quantity;
	}
}
