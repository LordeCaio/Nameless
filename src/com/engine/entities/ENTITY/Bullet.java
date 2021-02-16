package com.engine.entities.ENTITY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.entities.Entity;
import com.engine.tile.Solid_Tile;
import com.engine.tile.UnSolid_Tile;
import com.engine.world.Camera;
import com.engine.world.World;

public class Bullet extends Entity{
	
	private int dx, dy;
	private double speed = 2.5;
	
	private int life = 50, lifeSpam = 0;

	public BufferedImage bullet;
	
	public Bullet(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		if(Data.Player.dir == 0 || Data.Player.dir == 1) {
			bullet = Data.spr_Bullet_01.getSprite(32*0, 32*0, 32, 32);
		}else if (Data.Player.dir == 2 || Data.Player.dir == 3) {
			bullet = Data.spr_Bullet_01.getSprite(32*1, 32*0, 32, 32);
		}
				
	}
	
	public void update() {
		depth = 0;
		setCollMask(4, 4, 6, 6);
		if(World.isFreeMask((int)(x + (dx*speed)), (int)(y + (dy*speed)), collMaskX, collMaskY, collMaskW, collMaskH)) {
			x+=dx*speed;
			y+=dy*speed;		
		}else {					
			//Método de destruição da parede
			int bulletX = (this.getX() + this.collMaskX) / World.TILE_SIZE + dx; //Conversão da posição X
			int bulletY = (this.getY() + this.collMaskY) /World.TILE_SIZE + dy;	 //Conversão da posição Y
			if(World.tiles[bulletX + (bulletY * World.WIDTH)] instanceof Solid_Tile) {  //Verifica se o a Bullet Colidiu com uma parede
				//Substituição da parede por uma tile de chão
				World.tiles[bulletX + (bulletY * World.WIDTH)] = new UnSolid_Tile(bulletX*World.TILE_SIZE, bulletY*World.TILE_SIZE, UnSolid_Tile.Grass01);
			}
			Data.Bullet.remove(this);
		}
		
		destroySelf();
		
	}
	
	public void destroySelf() {	
		lifeSpam++;
		if(lifeSpam >= life) {
			Data.Entity.add(new Explosion((int)x, (int)y, 32, 32, null));			
			Data.Bullet.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(bullet, this.getX() - Camera.x, this.getY() - Camera.y, 16, 16,null);

	}

}
