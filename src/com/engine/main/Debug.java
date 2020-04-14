package com.engine.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.engine.data.Data;
import com.engine.entities.Entity;
import com.engine.entities.Player;
import com.engine.entities.ENEMY.Slime;
import com.engine.entities.ENTITY.Ammobox;
import com.engine.entities.ENTITY.Bullet;
import com.engine.entities.ENTITY.Gun;
import com.engine.entities.ENTITY.Medkit;
import com.engine.graphics.Sprite;
import com.engine.world.Camera;

public class Debug {
	
	public static int FPS;
	
	public static void intRender(Graphics g) {		
		for(int i = 0; i < Data.Entity.size(); i++) {
			Entity e = Data.Entity.get(i);
			
			if(e instanceof Slime) {
				g.setColor(Color.MAGENTA);
			}else if(e instanceof Ammobox) {
				g.setColor(Color.GREEN);
			}else if(e instanceof Gun) {
				g.setColor(Color.ORANGE);
			}else if(e instanceof Medkit) {
				g.setColor(Color.PINK);
			}else if(e instanceof Player) {
				g.setColor(Color.RED);
			}
			g.drawRect(e.getX() + e.getCollMask('x') - Camera.x, e.getY() + e.getCollMask('y') - Camera.y, e.getCollMask('w'), e.getCollMask('h'));
			g.setColor(Color.WHITE);
			if(e.getFootMask('x') != 0 | e.getFootMask('y') != 0) {
				g.drawRect(e.getX() + e.getFootMask('x') - Camera.x, e.getY() + e.getFootMask('y') - Camera.y, e.getFootMask('w'), e.getFootMask('h'));
			}			
		}
		
		for(int i = 0; i < Data.Bullet.size(); i++) {
			Bullet e = Data.Bullet.get(i);
			
			g.setColor(Color.RED);
			g.drawRect(e.getX() + e.getCollMask('x') - Camera.x, e.getY() + e.getCollMask('y') - Camera.y, e.getCollMask('w'), e.getCollMask('h'));
			g.setColor(Color.WHITE);
			if(e.getFootMask('x') != 0 | e.getFootMask('y') != 0) {
				g.drawRect(e.getX() + e.getFootMask('x') - Camera.x, e.getY() + e.getFootMask('y') - Camera.y, e.getFootMask('w'), e.getFootMask('h'));
			}			
		}
		
	}
	
	public static void outRender(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 15));
		Sprite.drawStringOutline("FPS: "+FPS, 25, 300, Color.WHITE, Color.BLACK, g);
		Sprite.drawStringOutline("Entities: "+Data.Entity.size(), 25, 315, Color.WHITE, Color.BLACK, g);
		Sprite.drawStringOutline("Enemies: "+Data.Enemy.size(), 25, 330, Color.WHITE, Color.BLACK, g);
		Sprite.drawStringOutline("Bullets: "+Data.Bullet.size(), 25, 345, Color.WHITE, Color.BLACK, g);
		
		g.setFont(new Font("Arial", Font.BOLD | Font.CENTER_BASELINE, 10));
		for(int i = 0; i < Data.Entity.size(); i++) {
			Entity e = Data.Entity.get(i);
			
			Sprite.drawStringOutline("Depth: "+e.depth, e.getX()*2 - Camera.x*2, e.getY()*2+e.getH()*2 + 32 - Camera.y*2, Color.WHITE, Color.BLACK, g);
			Sprite.drawStringOutline("X: "+e.getX(), e.getX()*2 - Camera.x*2, e.getY()*2+e.getH()*2 + 22 - Camera.y*2, Color.WHITE, Color.BLACK, g);
			Sprite.drawStringOutline("Y: "+e.getY(), e.getX()*2 - Camera.x*2, e.getY()*2+e.getH()*2 + 12 - Camera.y*2, Color.WHITE, Color.BLACK, g);			
		}
	
	}

}
