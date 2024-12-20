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
package com.udawos.pioneer.sprites;

import com.udawos.noosa.TextureFilm;
import com.udawos.pioneer.Assets;

public class BearCubSprite extends MobSprite {
	
	public BearCubSprite() {
		super();
		
		texture( Assets.BEARCUB );
		
		TextureFilm frames = new TextureFilm( texture, 10, 9 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0, 0, 0, 1 );
		
		run = new Animation( 10, true );
		run.frames( frames, 2, 3 );
		
		attack = new Animation( 15, false );
		attack.frames( frames, 0 );
		
		die = new Animation( 10, false );
		die.frames( frames, 0 );
		
		play( idle );
	}
}
