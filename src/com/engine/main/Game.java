package com.engine.main;

import java.awt.Graphics;
import java.util.Collections;

import com.engine.data.Data;
import com.engine.data.Save;
import com.engine.entities.Enemy;
import com.engine.entities.Entity;
import com.engine.entities.Player;
import com.engine.entities.ENTITY.Bullet;
import com.engine.world.MiniMap;
import com.engine.world.World;

public class Game{
	
	//Class:	

	public static Input input;	

	public Game() {

		//Inicialização das Classes:
		input = new Input();


		//Inicialização dos Objetos;		
		Data.Player = new Player(25, 25, 32, 32,Data.spr_Player.getSprite(0, 0, 32, 32));
		Data.Entity.add(Data.Player);
		
		
		//Inicialização do Mundo:
		
		//Data.World = new World("/Maps/map0"+Data.CUR_LEVEL+".png");
		Data.World = new World("/Maps/map01_0.png");
		Data.CUR_STATE = Data.GAME_STATE[0]; 		
		
	}
	
	public void update() {
		if(Data.CUR_STATE.equals(Data.GAME_STATE[0]) || Data.CUR_STATE.equals(Data.GAME_STATE[1])) {
			//===Menu==//
			Data.MENU.update();
		}		
		else if(Data.CUR_STATE.equals(Data.GAME_STATE[2])) {
			//===Normal===//
			if(Data.saveGame) {
				Save.createSave();
			}
		
			
			if(Data.Enemy.size() == 0) {
				Data.CUR_LEVEL++;
				if(Data.CUR_LEVEL > Data.MAX_LEVEL) {
					Data.CUR_LEVEL = 1;
				}
				String newWorld = "/Maps/map0"+Data.CUR_LEVEL+".png";
				Data.World.newWorld(newWorld);
			}
			
			
			Data.Entity.sort(Entity.nodeSorter);
			for(int i = 0; i<Data.Entity.size(); i++) {
				Entity e = Data.Entity.get(i);
				e.update();
			}
			
			Data.Enemy.sort(Enemy.nodeSorter);
			for(int i = 0; i<Data.Bullet.size(); i++) {
				Bullet e = Data.Bullet.get(i);
				e.update();
			}
		}
		else if(Data.CUR_STATE.equals(Data.GAME_STATE[3])) {
			//===Game Over==//
			if(Data.restartGame) {
				Data.restartGame = false;
				Data.CUR_LEVEL = 1;
				String newWorld = "/Maps/map0"+Data.CUR_LEVEL+".png";
				Data.World.newWorld(newWorld);
				Data.CUR_STATE = Data.GAME_STATE[2];
			}
		}
		

	}
	
	public void inRender(Graphics g) {
		Data.World.render(g);		
		
		for(int i = 0; i<Data.Entity.size(); i++) {
			Entity e = Data.Entity.get(i);
			e.render(g);
		}
		
		for(int i = 0; i<Data.Bullet.size(); i++) {
			Bullet e = Data.Bullet.get(i);
			e.render(g);
		}		
		
		if(Data.debug) {
			Debug.intRender(g);
		}
	}
	
	public void outRender(Graphics g) {
		if(Data.CUR_STATE == Data.GAME_STATE[0] || Data.CUR_STATE == Data.GAME_STATE[1]) {
			//===Game_Over===///	
			Data.MENU.render(g);
			
		}
		else if(Data.CUR_STATE == Data.GAME_STATE[2]) { 
			//===Normal===//
			if(Data.Entity.contains(Data.Player)) {
				Data.UI.render(g);
			}
		}
		else if(Data.CUR_STATE == Data.GAME_STATE[3]) {
			//===Game_Over===///	
			Data.GAME_OVER.render(g);			
		}
		
		if(Data.debug) {
			Debug.outRender(g);
		}
		
		MiniMap.miniMapRender(g);
	}


}
