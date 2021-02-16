package com.engine.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.data.Functions;
import com.engine.entities.ENTITY.Bullet;
import com.engine.world.Camera;
import com.engine.world.World;

public class Player extends Entity{	
	//Movement and Stattes:
	public boolean key_up, key_down, key_left, key_right;
	
	public int initialDir = 2, dir = initialDir;
	public double speed = 1.15;	
	
	public boolean isMoving, isDamaged, hasGun, isShooting;
	public boolean isMovable;
	
	public int dmgFrames = 0;
	 
	//Player Stats:
	public double health = 100, maxHealth = 100;
	public int ammo = 0, maxAmmo = 30;
	
	//Player Fake Jump:
	public boolean jump, isJumping, jumpUp, jumpDown;
	public int z, jumpCur, jumpFrames = 30;
	

	//Animation & Sprite//	
	public BufferedImage[][] upPlayer, downPlayer, leftPlayer, rightPlayer;
	public BufferedImage[][] upPlayerGun, downPlayerGun, leftPlayerGun, rightPlayerGun;	
	
	public int frames = 0, index = 0, maxFrames = 10, maxIndex = 4, animationIndex = 0;	
	
	public Player(int x, int y, int width, int height,  BufferedImage sprite) {
		super(x, y, width, height, sprite);
		upPlayer = new BufferedImage[4][2];
		downPlayer = new BufferedImage[4][2];
		leftPlayer = new BufferedImage[4][2];
		rightPlayer = new BufferedImage[4][2];		
		
		upPlayerGun = new BufferedImage[4][2];
		downPlayerGun = new BufferedImage[4][2];
		leftPlayerGun = new BufferedImage[4][2];
		rightPlayerGun = new BufferedImage[4][2];
				
		for(int i = 0; i<4; i++) {
			for(int ii = 0; ii<2;ii++) {				
				downPlayer[i][ii] = Data.spr_Player.getSprite(32*i, 32*(0+(ii*maxIndex)), width, height);
				upPlayer[i][ii] = Data.spr_Player.getSprite(32*i, 32*(1+(ii*maxIndex)), width, height);
				rightPlayer[i][ii] = Data.spr_Player.getSprite(32*i, 32*(2+(ii*maxIndex)), width, height);
				leftPlayer[i][ii] = Data.spr_Player.getSprite(32*i, 32*(3+(ii*maxIndex)), width, height);				

				downPlayerGun[i][ii] = Data.spr_Player.getSprite(32*(6+i), 32*(0+(ii*maxIndex)), width, height);
				upPlayerGun[i][ii] = Data.spr_Player.getSprite(32*(6+i), 32*(1+(ii*maxIndex)), width, height);			
				rightPlayerGun[i][ii] = Data.spr_Player.getSprite(32*(6+i), 32*(2+(ii*maxIndex)), width, height);
				leftPlayerGun[i][ii] = Data.spr_Player.getSprite(32*(6+i), 32*(3+(ii*maxIndex)), width, height);
				
				upPlayer[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				downPlayer[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				leftPlayer[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				rightPlayer[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);		

				upPlayerGun[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				downPlayerGun[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				leftPlayerGun[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				rightPlayerGun[i][ii].setRGB(0, 0, BufferedImage.TYPE_INT_ARGB);
				
			}
		}		
		
	}
	
	public void moveEvent() {
		if(isMoving) {
			playAnimation();
		}		
		else {
			stopAnimation(); 
			x = Math.floor(x); 
			y = Math.floor(y);
		}
		
		health = 100;
		ammo = 30;
		
		isMoving = false;
		if(key_right && World.isFreeMask(nextRIGHT, this.getY(), this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
				&& !isSolid(nextRIGHT, this.getY())) {
			isMoving = true;			
			dir = 0;
			x+=speed;
		}
		else if(key_left && World.isFreeMask(nextLEFT, this.getY(), this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
				&& !isSolid(nextLEFT, this.getY())) {
			isMoving = true;		
			dir = 1;
			x-=speed;
		}		
		if(key_down && World.isFreeMask(this.getX(), nextDOWN, this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
				&& !isSolid(this.getX(), nextDOWN)) {
			isMoving = true;			
			dir = 2;
			y+=speed;
		}
		else if(key_up && World.isFreeMask(this.getX(), nextUP, this.footMaskX, this.footMaskY, this.footMaskW, this.footMaskH)
				&& !isSolid(this.getX(), nextUP)) {
			isMoving = true;			
			dir = 3;
			y-=speed;			
		} 
	}
			
	public void jumpEvent() {	
		if(jump) {
			if(isJumping == false) {
				isJumping = true;				
				jumpUp = true;
				
			}
		}		
		if(isJumping == true) {
			if(jumpUp == true) {
				jumpCur+=2;
			}else if(jumpDown) {
				jumpCur-=2;
				if(jumpCur <= 0) {
					jump = false;
					isJumping = false;
					jumpUp = false;
					jumpDown = false;
				}
			}
			z = jumpCur;
			if(jumpCur >= jumpFrames) {
				jumpUp = false;
				jumpDown = true;
			}
				
		}
	}
	
	public void shootEvent() {
		if(isShooting && hasGun) {
			isShooting = false;
			int dx = 0, dy = 0;
			if(ammo > 0) {
				ammo--;				
				if(dir == 0) {dx = 1;}else if(dir == 1) {dx = -1;}
				if(dir == 2) {dy = 1;}else if(dir == 3) {dy = -1;}
				Data.Bullet.add(new Bullet(this.getX() + 8, this.getY() + 8, 32, 32, null, dx, dy));
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
	
	public void stopAnimation() {
		index = 0;
	}	
	
	public void dmgFeedback(int timer) {
		animationIndex = 1;
		dmgFrames++;
		if(dmgFrames >= timer) {
			dmgFrames = 0;
			animationIndex = 0;
			isDamaged = false;
		}
	}	
	
	public void destroySelf() {
		if(health <= 0) {			
			Data.CUR_STATE = Data.GAME_STATE[3]; 
		}
	}	

	
	public void update() {
		depth = Integer.MAX_VALUE;	
		
		moveEvent();
		jumpEvent();
		shootEvent();	
		
		if(isDamaged) {			
			dmgFeedback(10);
		}		
		
		destroySelf();	
		
		
		///===OTHERS===///
		health = Functions.clamp(health, 0, maxHealth);
		ammo = Functions.clamp(ammo, 0, maxAmmo);
		
		Camera.cameraUpdate();		
		setCollMask(8, 0, 16, 32);
		setFootMask(8, 24, 16, 8);
		setNext((int)(y-speed), (int)(y+speed), (int)(x-speed), (int)(x+speed));	
		///===OTHERS===///
		
	}	
	
	public void render(Graphics g) {		
		if(isJumping) {
			g.setColor(Color.BLACK);
			g.fillOval(this.getX() + 4 - Camera.x, this.getY() + 28 - Camera.y, 24, 8);
		}	
		
		if(!hasGun) {			
			if(dir == 3) {g.drawImage(upPlayer[index][animationIndex] , this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
			if(dir == 2) {g.drawImage(downPlayer[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
			if(dir == 1) {g.drawImage(leftPlayer[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
			if(dir == 0) {g.drawImage(rightPlayer[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
		}
		else {		
			if(dir == 3) {g.drawImage(upPlayerGun[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
			if(dir == 2) {g.drawImage(downPlayerGun[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
			if(dir == 1) {g.drawImage(leftPlayerGun[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
			if(dir == 0) {g.drawImage(rightPlayerGun[index][animationIndex], this.getX() - Camera.x, this.getY() - Camera.y - z, null);}
		}

	
	}

}
