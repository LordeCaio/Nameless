package com.engine.entities.ENTITY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.entities.Entity;
import com.engine.world.Camera;

public class Explosion extends Entity{
	
	public BufferedImage[] explosion;
	public int frames = 0, index = 0, maxFrames = 5, maxIndex = 10;
	
	public Explosion(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		explosion = new BufferedImage[10];
		
		for(int i = 0; i < explosion.length; i++) {
			explosion[i] = Data.spr_Explosion.getSprite(32*i, 0, 32, 32);
		}
		
	}
	
	public void update() {
		playAnimation();
	}
	
	public void playAnimation() {
		this.frames++;
		if(this.frames == this.maxFrames){
			this.index++;
			this.frames = 0;
			if(index == maxIndex) {
				index = 0;
				Data.Entity.remove(this);
			}
		}
	}	
	
	public void render(Graphics g) {
		g.drawImage(explosion[index], this.getX() - Camera.x, this.getY() - Camera.y, 16, 16,null);
	}
	
}
