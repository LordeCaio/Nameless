package com.engine.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;

import com.engine.astar.Node;
import com.engine.astar.Vector2i;
import com.engine.data.Data;
import com.engine.world.Camera;
import com.engine.world.World;


public class Entity {
	
	protected double x, y, speed;	
	protected int width, height;	
	protected float xscale, yscale;
	protected boolean solid;
	
	protected int z;	
	
	protected int collMaskX, collMaskY, collMaskW, collMaskH;	//Collision Mask Points.
	protected int footMaskX, footMaskY, footMaskW, footMaskH;	//Foot Mask Points.
	
	protected int nextUP , nextDOWN , nextLEFT , nextRIGHT;		//Next Collision XY;

	protected List<Node> path;
	
	public int depth = 0;	
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.xscale = 1;
		this.yscale = 1;
		
		this.z = 0;
		setCollMask(x, y, width, height);
	}
	
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}	
	
	public int getW() {
		return this.width;
	}
		
	public int getH() {
		return this.height;
	}	
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}

	
	/**
	 * @param letter - Compatible Letters: X, Y, W, H.
	 * @return The Collision Box Point.
	 */
	public int getCollMask(char letter) {
		int toReturn = 0;
		if(letter == 'x' || letter == 'X') {
			toReturn = this.collMaskX;
		}else if(letter == 'y' || letter == 'Y') {
			toReturn = this.collMaskY;
		}else if(letter == 'w' || letter == 'W') {
			toReturn = this.collMaskW;
		}else if(letter == 'h' || letter == 'H') {
			toReturn = this.collMaskH;
		}
		return toReturn;
	}
	
	/**
	 * @param letter - Compatible Letters: X, Y, W, H.
	 * @return The Collision Box Point.
	 */
	public int getFootMask(char letter) {
		int toReturn = 0;
		if(letter == 'x' || letter == 'X') {
			toReturn = this.footMaskX;
		}else if(letter == 'y' || letter == 'Y') {
			toReturn = this.footMaskY;
		}else if(letter == 'w' || letter == 'W') {
			toReturn = this.footMaskW;
		}else if(letter == 'h' || letter == 'H') {
			toReturn = this.footMaskH;
		}
		return toReturn;
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {		
		@Override
		public int compare(Entity e0, Entity e1) {
			if(e1.depth < e0.depth) {return+1;}
			if(e1.depth > e0.depth) {return-1;}
			return 0;
		}
	};
	
	public void setCollMask(int maskX, int maskY, int maskW, int maskH) {
		this.collMaskX = maskX;
		this.collMaskY = maskY;
		this.collMaskW = maskW;
		this.collMaskH = maskH;
		
	}
	
	public void setFootMask(int footMaskX, int footMaskY, int footMaskW, int footMaskH) {
		this.footMaskX = footMaskX;
		this.footMaskY = footMaskY;
		this.footMaskW = footMaskW;
		this.footMaskH = footMaskH;
	}
	
	public void setNext(int UP,int DOWN,int LEFT,int RIGHT) {
		this.nextUP = UP;
		this.nextDOWN = DOWN;
		this.nextLEFT = LEFT;
		this.nextRIGHT = RIGHT;
	}

	public boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.collMaskX, e1.getY() + e1.collMaskY, e1.collMaskW, e1.collMaskH);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.collMaskX, e2.getY() + e2.collMaskY, e2.collMaskW, e2.collMaskH);
		if(e1Mask.intersects(e2Mask) && e1.z == e2.z) {
			return true;
		}
		return false;		
	}
		
	public void followPath(List<Node> path) {		
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size()-1).tile;
				if(x < (target.x*World.TILE_SIZE)) {
					x++;
				}else if(x > (target.x*World.TILE_SIZE)) {
					x--;
				}				
				if(y < (target.y*World.TILE_SIZE)) {
					y++;
				}else if(y > (target.y*World.TILE_SIZE)) {
					y--;
				}				
				if(x == (target.x * World.TILE_SIZE) && y == (target.y * World.TILE_SIZE)) {
					path.remove(path.size()-1);					
				}
				
			}
		}
	}
	
	public boolean isSolid(int xnext, int ynext) {
		Rectangle current = new Rectangle(xnext + collMaskX, ynext + collMaskY, collMaskW, collMaskH);
		for(int i = 0; i < Data.Entity.size(); i++) {
			Entity e = Data.Entity.get(i);
			if(e instanceof Entity) {
				Rectangle target = new Rectangle(e.getX() + e.collMaskX, e.getY() + e.collMaskY, e.collMaskW, e.collMaskH);
				if(current.intersects(target) && e.solid == true) {
					System.out.println("Exemplo de Solido");
					return true;
				}
			}
		}		
		return false;		
	}
		
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, this.getW(), this.getH(), null);		

	}


}
