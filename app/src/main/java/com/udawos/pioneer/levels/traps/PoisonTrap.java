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
package com.udawos.pioneer.levels.traps;

import com.udawos.pioneer.Dungeon;
import com.udawos.pioneer.actors.Char;
import com.udawos.pioneer.actors.buffs.Buff;
import com.udawos.pioneer.actors.buffs.Poison;
import com.udawos.pioneer.effects.CellEmitter;
import com.udawos.pioneer.effects.particles.PoisonParticle;

public class PoisonTrap {

	// 0xBB66EE
	
	public static void trigger( int pos, Char ch ) {
		
		if (ch != null) {
			Buff.affect( ch, Poison.class ).set( Poison.durationFactor( ch ) * (4 + Dungeon.depth / 2) );
		}
		
		CellEmitter.center( pos ).burst( PoisonParticle.SPLASH, 3 );
		
	}
}
