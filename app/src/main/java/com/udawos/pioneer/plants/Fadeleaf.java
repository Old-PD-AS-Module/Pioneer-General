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
package com.udawos.pioneer.plants;

import com.udawos.pioneer.Dungeon;
import com.udawos.pioneer.actors.Char;
import com.udawos.pioneer.actors.hero.Hero;
import com.udawos.pioneer.actors.mobs.Mob;
import com.udawos.pioneer.effects.CellEmitter;
import com.udawos.pioneer.effects.Speck;
import com.udawos.pioneer.items.potions.PotionOfMindVision;
import com.udawos.pioneer.items.scrolls.ScrollOfTeleportation;
import com.udawos.pioneer.sprites.ItemSpriteSheet;

public class Fadeleaf extends Plant {

	private static final String TXT_DESC = 
		"Touching a Fadeleaf will teleport any creature " +
		"to a random place on the current level.";
	
	{
		image = 6;
		plantName = "Fadeleaf";
	}
	
	@Override
	public void activate( Char ch ) {
		super.activate( ch );
		
		if (ch instanceof Hero) {
			
			ScrollOfTeleportation.teleportHero( (Hero)ch );
			((Hero)ch).curAction = null;
			
		} else if (ch instanceof Mob) {
			
			int count = 10;
			int newPos;
			do {
				newPos = Dungeon.level.randomRespawnCell();
				if (count-- <= 0) {
					break;
				}
			} while (newPos == -1);
			
			if (newPos != -1) {
			
				ch.pos = newPos;
				ch.sprite.place( ch.pos );
				ch.sprite.visible = Dungeon.visible[pos];
				
			}
						
		}
		
		if (Dungeon.visible[pos]) {
			CellEmitter.get( pos ).start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
		}
	}
	
	@Override
	public String desc() {
		return TXT_DESC;
	}
	
	public static class Seed extends Plant.Seed {
		{
			plantName = "Fadeleaf";
			
			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_FADELEAF;
			
			plantClass = Fadeleaf.class;
			alchemyClass = PotionOfMindVision.class;
		}
		
		@Override
		public String desc() {
			return TXT_DESC;
		}
	}
}
