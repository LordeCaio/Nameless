package com.engine.entities.ENTITY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.entities.Entity;
import com.engine.world.Camera;

public class Gun extends Entity{
	
	public BufferedImage gun;

	public Gun(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);		
		gun = Data.spr_Collectible_01.getSprite(32*2, 32*0, 32, 32);

		
	}
	
	public void update() {
		depth = -1;
		setCollMask(2, 8, 26, 24);
		checkCollisionWithPlayer();		
	}
	
	public void checkCollisionWithPlayer() {
		for(int i = 0; i<Data.Entity.size(); i++) {
			Entity gun = Data.Entity.get(i);
			if(gun instanceof Gun) {
				if(isColliding(gun, Data.Player)) {
					Data.Player.hasGun = true;
					Data.Entity.remove(gun);										
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(gun, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
