package com.engine.entities.ENEMY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.data.Functions;
import com.engine.entities.Enemy;
import com.engine.entities.Entity;
import com.engine.graphics.Sprite;
import com.engine.world.Camera;
import com.engine.world.World;

public class Slime extends Enemy{
	
	public double speed = 0.35;	
	public int dir = 0;		
	
	public double health = 10, maxHealth = 10;	
	
	public BufferedImage[][] slime;	
	public int[] center_point = {width/2, height/2};
	public boolean isMirrored, isDamaged;
	
	public int frames = 0, index = 0, maxFrames = 15, maxIndex = 4, animationIndex = 0;
	public int dmgFrames = 0;
	
	
	public Slime(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);		
		slime = new BufferedImage[4][2];
		
		for(int i = 0; i<4; i++) {
			for(int ii = 0; ii<2;ii++) {			
				slime[i][ii] = Data.spr_Enemy_01.getSprite(32*(i+(6*ii)), 32*(0*ii), width, height);
			}			
		}		

	}
	
	public void moveEvent() {
		if(isCollidingWithPlayer() == false) {				
			if((int)x < Data.Player.getX() & World.isFreeMask(nextRIGHT,this.getY(), this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
					&& !isColliding(nextRIGHT, this.getY())) {
				isMirrored = false;
				x+=speed;							
				dir = 0;
			}	
			else if((int)x > Data.Player.getX() & World.isFreeMask(nextLEFT,this.getY(), this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
					&& !isColliding(nextLEFT, this.getY())) {		
				isMirrored = true;		
				x-=speed;							
				dir = 1;
			}
			if((int)y < Data.Player.getY() & World.isFreeMask(this.getX(),nextDOWN, this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
					&& !isColliding(this.getX(),nextDOWN)) {		
				y+=speed;
				dir = 2;
			}		
			else if((int)y > Data.Player.getY() & World.isFreeMask(this.getX(),nextUP, this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
					&& !isColliding(this.getX(),nextUP)) {		
				y-=speed;
				dir = 3;
			}
	}else {
		if(Data.Player.z <= 0) {
			int dmgChance = Functions.random.nextInt(100-1) +1;
			if(dmgChance <= 10) {
				Data.Player.health -= Functions.choose(1, 2, 3);
				Data.Player.isDamaged = true;
			}
		}			
	}
	}
	
	public void playAnimation() {
		this.frames++;
		if(this.frames == this.maxFrames){
			this.index++;
			this.frames = 0;
			if(index == maxIndex) {
				index = 0;
			}
		}
	}
	
	public void dmgFeedback(int timer) {
		animationIndex = 1;
		dmgFrames++;
		if(dmgFrames > timer) {
			dmgFrames = 0;
			animationIndex = 0;
			isDamaged = false;
		}
	}
	
	public void isCollidingWithBullet() {
		for(int i = 0; i < Data.Bullet.size(); i++) {
			Entity bullet = Data.Bullet.get(i);
			if(isColliding(this, bullet)) {
				health--;
				isDamaged = true;				
				Data.Bullet.remove(bullet);
			}
		}
	}
	
	public void destroySelf() {
		if(health <= 0) {
			Data.Entity.remove(this);
			Data.Enemy.remove(this);
		}
	}
	
	public void update() {
		
		depth = (int)y;			
		
		moveEvent();
		playAnimation();
		
		if(isDamaged) {			
			dmgFeedback(10);
		}						
		
		///===COLLISIONS===///
		isCollidingWithBullet();
		///===COLLISIONS===///
		
		destroySelf();	
		
		///===OTHERS===///
		setFootMask(4, 14, 24, 10);
		setCollMask(4, 8, 24, 16);
		setNext((int)(y-speed), (int)(y+speed), (int)(x-speed), (int)(x+speed));	
		///===OTHERS===///
		
	}
		
	public void render(Graphics g) {
		if(!isMirrored) {
			g.drawImage(slime[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else {
			Sprite.mirror(slime[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y, this.getW(), this.getH(), g);
		}
		
	}

}
