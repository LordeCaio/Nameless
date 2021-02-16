package com.engine.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.engine.entities.Enemy;
import com.engine.entities.Entity;
import com.engine.entities.Player;
import com.engine.entities.ENTITY.Ammobox;
import com.engine.entities.ENTITY.Bullet;
import com.engine.entities.ENTITY.Medkit;
import com.engine.graphics.Sprite_Entity;
import com.engine.ui.GAME_OVER;
import com.engine.ui.MENU;
import com.engine.ui.UI;
import com.engine.world.World;

public abstract class Data {
	
	public static Player Player;	
	
	
	public static World World;
	
	public static UI UI = new UI();
	public static MENU MENU = new MENU();
	public static GAME_OVER GAME_OVER= new GAME_OVER();	

	public static Random Random = new Random();

//====================================================================================================================================//
	
	//Level Variables:
	public static int CUR_LEVEL = 1, MAX_LEVEL = 2; 

	//Game States:	
	public static String[] GAME_STATE = {"MAIN_MENU", "GAME_MENU", "NORMAL", "GAME_OVER"};
	public static String CUR_STATE = "";
	public static boolean restartGame = false;
	
	//Global Keys:	
	public static boolean saveGame = false;
	public static boolean debug = false;
	
//====================================================================================================================================//

	public static List<Entity> Entity = new ArrayList<Entity>();  				//Lista de Entidades
	public static List<Enemy> Enemy = new ArrayList<Enemy>();					//Lista de Inimigos
	
	public static List<Ammobox> Ammobox = new ArrayList<Ammobox>();				//Lista de Ammobox
	public static List<Medkit> Medkit = new ArrayList<Medkit>();				//Lista de Medkits
	public static List<Bullet> Bullet = new ArrayList<Bullet>();				//Lista de Bullets
	
	
//====================================================================================================================================//
	
	
	
//====================================================================================================================================//
	//PLAYER
	public static Sprite_Entity spr_Player = new Sprite_Entity("/Entity/spr_Player.png");
		
	//COLLECTIBLES:
	public static Sprite_Entity spr_Collectible_01 = new Sprite_Entity("/Entity/spr_Collectible01.png");
	
	//BULLETS:
	public static Sprite_Entity spr_Bullet_01 = new Sprite_Entity("/Entity/spr_Bullet01.png");
	public static Sprite_Entity spr_Explosion = new Sprite_Entity("/Entity/spr_Explosion.png");
	
	//ENEMIES:
	public static Sprite_Entity spr_Enemy_01 = new Sprite_Entity("/Enemy/spr_Enemy01.png");
	
//====================================================================================================================================//
		

}
