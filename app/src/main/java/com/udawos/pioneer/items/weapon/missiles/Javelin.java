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
import com.udawos.pioneer.actors.buffs.Cripple;
import com.udawos.pioneer.items.Item;
import com.udawos.pioneer.sprites.ItemSpriteSheet;
import com.udawos.utils.Random;

public class Javelin extends MissileWeapon {

	{
		name = "javelin";
		image = ItemSpriteSheet.JAVELIN;
		
		STR = 15;
		
		MIN = 2;
		MAX = 15;
	}
	
	public Javelin() {
		this( 1 );
	}
	
	public Javelin( int number ) {
		super();
		quantity = number;
	}
	
	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		super.proc( attacker, defender, damage );
		Buff.prolong( defender, Cripple.class, Cripple.DURATION );
	}
	
	@Override
	public String desc() {
		return 
			"This length of metal is weighted to keep the spike " +
			"at its tip foremost as it sails through the air.";
	}
	
	@Override
	public Item random() {
		quantity = Random.Int( 5, 15 );
		return this;
	}
	
	@Override
	public int price() {
		return 15 * quantity;
	}
}
