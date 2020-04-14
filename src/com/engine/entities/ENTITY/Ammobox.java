package com.engine.entities.ENTITY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.entities.Entity;
import com.engine.world.Camera;

public class Ammobox extends Entity{
	
	public BufferedImage ammobox;

	public Ammobox(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);		
		ammobox = Data.spr_Collectible_01.getSprite(32*1, 32*0, 32, 32);
	}
	
	public void update() {
		depth = -1;
		setCollMask(2, 2, 28, 28);
		checkCollisionWithPlayer();
	}
	
	public void checkCollisionWithPlayer(){
		for(int i = 0; i<Data.Ammobox.size(); i++) {
			Entity ammo = Data.Ammobox.get(i);
			if(ammo instanceof Ammobox) {
				if(isColliding(ammo, Data.Player)) {
					if(Data.Player.ammo >= Data.Player.maxAmmo) {
						continue;
					}else {
						Data.Player.ammo += 30;
						Data.Ammobox.remove(ammo);
						Data.Entity.remove(ammo);
					}					
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(ammobox, this.getX() - Camera.x, this.getY() - Camera.y, null);		
	}

}
