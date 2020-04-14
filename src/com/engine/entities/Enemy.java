package com.engine.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.engine.data.Data;

public class Enemy extends Entity{
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

	}	
	
	public boolean isColliding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + footMaskX, ynext + footMaskY, footMaskW, footMaskH);
		for(int i = 0; i < Data.Enemy.size(); i++) {
			Enemy e = Data.Enemy.get(i);
			if(e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX() + footMaskX, e.getY() + footMaskY, footMaskW, footMaskH);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}		
		return false;		
	}
		
	
	public boolean isCollidingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + footMaskX, this.getY() + footMaskY, footMaskW, footMaskH);
		Rectangle player = new Rectangle(Data.Player.getX() + footMaskX, Data.Player.getY() + footMaskY, + footMaskW, + footMaskH);
		
		return enemyCurrent.intersects(player);
	}	
	
	public void update() {
		super.update();

	}
	

	
	public void render(Graphics g) {
		super.render(g);
	}

}
