package com.engine.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.engine.data.Data;
import com.engine.entities.Enemy;
import com.engine.entities.Entity;
import com.engine.entities.Player;
import com.engine.entities.ENEMY.Slime;
import com.engine.entities.ENTITY.Ammobox;
import com.engine.entities.ENTITY.Gun;
import com.engine.entities.ENTITY.Medkit;
import com.engine.main.Loop;
import com.engine.tile.Solid_Tile;
import com.engine.tile.Tile;
import com.engine.tile.UnSolid_Tile;

public class World {
	
	public static final int TILE_SIZE = 32;
	public static int WIDTH, HEIGHT;
	public static Tile[] tiles;
	
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int pixels[] = new int[map.getWidth() * map.getHeight()];
			tiles = new Tile[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int xx = 0; xx<map.getWidth(); xx++) {
				for(int yy = 0; yy<map.getHeight(); yy++) {
					int currentPixel = pixels[xx + (yy*map.getWidth())];
					if(currentPixel == 0xFF000000) {
						tiles[xx + (yy * WIDTH)] = new UnSolid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, UnSolid_Tile.Grass01);						
					}
					if(currentPixel == 0xFFFFFFFF) {
						tiles[xx + (yy * WIDTH)] = new Solid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, Solid_Tile.Rock01);
						
					}					
					if(currentPixel == 0xFF3cff00) {
						tiles[xx + (yy * WIDTH)] = new UnSolid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, UnSolid_Tile.Grass01);
						Data.Player.setX(xx*TILE_SIZE);
						Data.Player.setY(yy*TILE_SIZE);
					}
					if(currentPixel == 0xFFdca6ff) {
						tiles[xx + (yy * WIDTH)] = new UnSolid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, UnSolid_Tile.Grass01);						
						Slime slime = new Slime(xx*TILE_SIZE, yy*TILE_SIZE, 32, 32, Data.spr_Enemy_01.getSprite(0, 0, 32, 32));					
						Data.Entity.add(slime);
						Data.Enemy.add(slime);						
					}
					if(currentPixel == 0xffffff48) {
						tiles[xx + (yy * WIDTH)] = new UnSolid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, UnSolid_Tile.Grass01);
						Medkit pack = new Medkit(xx*TILE_SIZE, yy*TILE_SIZE, 32, 32, Data.spr_Collectible_01.getSprite(32*0, 32*0, 32, 32));
						Data.Entity.add(pack);
						Data.Medkit.add(pack);
					}
					if(currentPixel == 0xff065700) {
						tiles[xx + (yy * WIDTH)] = new UnSolid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, UnSolid_Tile.Grass01);
						Ammobox box = new Ammobox(xx*TILE_SIZE, yy*TILE_SIZE, 32, 32, Data.spr_Collectible_01.getSprite(32*1, 32*1, 32, 32));
						Data.Entity.add(box);
						Data.Ammobox.add(box);
					}
					if(currentPixel == 0xffe6b686) {
						tiles[xx + (yy * WIDTH)] = new UnSolid_Tile(xx*TILE_SIZE, yy*TILE_SIZE, UnSolid_Tile.Grass01);
						Gun gun = new Gun(xx*TILE_SIZE, yy*TILE_SIZE, 32, 32, Data.spr_Collectible_01.getSprite(32*2, 0, 32, 32));
						Data.Entity.add(gun);
					}

					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void newWorld(String path) {
		Data.Entity = new ArrayList<Entity>();
		Data.Enemy = new ArrayList<Enemy>();
		Data.Ammobox = new ArrayList<Ammobox>();
		Data.Medkit = new ArrayList<Medkit>();
		Data.Entity.add(Data.Player = new Player(25, 25, 32, 32,Data.spr_Player.getSprite(0, 0, 32, 32)));
		Data.World = new World(path);
		return;
	}
	
	public static boolean isFree(int xNext, int yNext){
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		return  !(tiles[x1 + (y1*WIDTH)] instanceof Solid_Tile ||
				  tiles[x2 + (y2*WIDTH)] instanceof Solid_Tile ||
				  tiles[x3 + (y3*WIDTH)] instanceof Solid_Tile ||
				  tiles[x4 + (y4*WIDTH)] instanceof Solid_Tile);
		
	}
	
	public static boolean isFreeMask(int xnext, int ynext,int maskX,int maskY,int maskW,int maskH) {
		int x1 = (xnext + maskX) / TILE_SIZE;
		int y1 = (ynext + maskY) / TILE_SIZE;
		
		int x2 = (xnext + maskX + (maskW)) / TILE_SIZE;
		int y2 = (ynext + maskY) / TILE_SIZE;
		
		int x3 = (xnext + maskX) / TILE_SIZE;
		int y3 = (ynext + maskY + (maskH)) / TILE_SIZE;
		
		int x4 = (xnext + maskX + (maskW)) / TILE_SIZE;
		int y4 = (ynext + maskY + (maskH)) / TILE_SIZE;
		
		return  !(tiles[x1 + (y1*WIDTH)] instanceof Solid_Tile ||
				  tiles[x2 + (y2*WIDTH)] instanceof Solid_Tile ||
				  tiles[x3 + (y3*WIDTH)] instanceof Solid_Tile ||
				  tiles[x4 + (y4*WIDTH)] instanceof Solid_Tile);
	}
	
	public void render(Graphics g) {
		int xStart = Camera.x/TILE_SIZE;
		int yStart = Camera.y/TILE_SIZE;
		
		int xFinal = xStart + (Loop.WIDTH/TILE_SIZE);
		int yFinal = yStart + (Loop.HEIGHT/TILE_SIZE);
		
		
		for(int xx = xStart; xx<=xFinal; xx++) {
			for(int yy = yStart; yy<=yFinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx +(yy * WIDTH)];
				tile.render(g);				
			}
		}
		
	}

}
