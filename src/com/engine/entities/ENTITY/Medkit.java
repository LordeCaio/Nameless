package com.engine.entities.ENTITY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.data.Functions;
import com.engine.entities.Entity;
import com.engine.world.Camera;

public class Medkit extends Entity{
		
	public BufferedImage lifepack;

	public Medkit(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);		
		lifepack = Data.spr_Collectible_01.getSprite(32*0, 32*0, 32, 32);
		
	}
	
	public void update() {
		depth = -1;
		setCollMask(4, 5, 22, 22);
		checkCollisionWithPlayer();
	}
	
	public void checkCollisionWithPlayer() {
		for(int i = 0; i<Data.Medkit.size(); i++) {
			Entity life = Data.Medkit.get(i);
			if(life instanceof Entity) {
				if(isColliding(life, Data.Player)) {
					if(Data.Player.health >= Data.Player.maxHealth) {
						continue;
					}else {
						Data.Player.health+=Functions.choose(3, 5, 7, 9);
						Data.Medkit.remove(life);
						Data.Entity.remove(life);
					}					
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(lifepack, this.getX() - Camera.x, this.getY() - Camera.y, null);		
	}

}
