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

import android.opengl.GLES20;

import com.udawos.noosa.Game;
import com.udawos.noosa.TextureFilm;
import com.udawos.pioneer.Assets;
import com.udawos.pioneer.actors.Char;
import com.udawos.pioneer.effects.Halo;
import com.udawos.pioneer.effects.particles.ElmoParticle;
import com.udawos.utils.PointF;

import javax.microedition.khronos.opengles.GL10;

public class WandmakerSprite extends MobSprite {
	
	private Shield shield;
	
	public WandmakerSprite() {
		super();
		
		texture( Assets.MAKER );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 10, true );
		idle.frames( frames, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0 );

		run = new Animation( 5, true );
		run.frames( frames, 2,3 );
		
		die = new Animation( 20, false );
		die.frames( frames, 0 );
		
		play( idle );
	}
	
	@Override
	public void link( Char ch ) {
		super.link( ch );
		
		if (shield == null) {
			parent.add( shield = new Shield() );
		}
	}
	
	@Override
	public void die() {
		super.die();
		
		if (shield != null) {
			shield.putOut();
		}
		emitter().start( ElmoParticle.FACTORY, 0.03f, 60 );
	}
	
	public class Shield extends Halo {
		
		private float phase;
		
		public Shield() {
			
			super( 14, 0xBBAACC, 1f );
			
			am = -1;
			aa = +1;
			
			phase = 1;
		}
		
		@Override
		public void update() {
			super.update();
			
			if (phase < 1) {
				if ((phase -= Game.elapsed) <= 0) {
					killAndErase();
				} else {
					scale.set( (2 - phase) * radius / RADIUS );
					am = phase * (-1);
					aa = phase * (+1);
				}
			}
			
			if (visible = WandmakerSprite.this.visible) {
				PointF p = WandmakerSprite.this.center();
				point(p.x, p.y );
			}
		}
		
		@Override
		public void draw() {
			GLES20.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE);
			super.draw();
			GLES20.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA );
		}
		
		public void putOut() {
			phase = 0.999f;
		}
	}

}
