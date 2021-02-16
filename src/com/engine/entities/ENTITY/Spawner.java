package com.engine.entities.ENTITY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.engine.data.Data;
import com.engine.entities.Entity;
import com.engine.entities.ENEMY.Slime;
import com.engine.world.Camera;

public class Spawner extends Entity{
	
	public BufferedImage spawner;
	
	public int spawnTimer, spawnEnemyTimer,spawnedEnemy;
	public boolean spawnable;

	public Spawner(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		spawner = Data.spr_Collectible_01.getSprite(32*0, 32*1, 32, 32);		
	}
	
	public void update() {
		if(spawnable) {
			spawnEnemy(2, 60);
		}else {
			spawnerTimer(3);
		}		
	}
	
	public void spawnerTimer(int sec) {
		spawnTimer++;
		if(spawnTimer >= (sec*60/*fazer a conta sec*fps nesse caso 60*/)) {
			spawnTimer = 0;
			spawnable = true;
		}
	}
	
	public void spawnEnemy(int toSpawn /*Inimigos para Spawnar*/, int interval/*Intervalo entre o Spawn*/) {		
		spawnEnemyTimer++;
		if(spawnEnemyTimer >= interval) {
			spawnEnemyTimer = 0;
			spawnedEnemy++;
			//Criação do Inimigo
			Slime slime = new Slime(this.getX(), this.getY(), 32, 32, null);
			Data.Entity.add(slime);
			Data.Enemy.add(slime);	
		}
		//Reseta o spawner caso tenha criado a quantidade certa de inimigos
		if(spawnedEnemy >= toSpawn) {
			spawnerTimer(3); //reinicia o tempo do spawner com 3 segundos
			spawnable = false;	
			spawnedEnemy = 0;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(spawner, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	

}
