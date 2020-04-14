package com.engine.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.engine.data.Data;

public class Input implements KeyListener{
	
	public boolean ShiftPress = false;
	
	///KEYBOARD:
	public void keyPressed(KeyEvent e) {
		if(Data.CUR_STATE == Data.GAME_STATE[0] || Data.CUR_STATE == Data.GAME_STATE[1]) {
			//===Main Menu===//
			if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				Data.MENU.menuUp = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				Data.MENU.menuDown = true;
			}	
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Data.MENU.menuEnter = true;
			}
		}
		
		if(Data.CUR_STATE == Data.GAME_STATE[2]) {
			//===Normal===//
			if(e.getKeyCode() == KeyEvent.VK_W) {
				Data.Player.key_up = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_S) {
				Data.Player.key_down = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				Data.Player.key_left = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				Data.Player.key_right = true;
			}
			
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				Data.Player.jump = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
				if(Data.Player.hasGun && ShiftPress == false) {
					ShiftPress = true;
					Data.Player.isShooting = true;
				}else {Data.Player.isShooting = false;}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_F1) {
				Data.saveGame = true;
			}			
		
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Data.CUR_STATE = Data.GAME_STATE[1];
			}
		}
		
		if(Data.CUR_STATE == Data.GAME_STATE[3]) {
			//===Game Over===//
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				Data.restartGame = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F3) {
			Data.debug = !Data.debug;
		}
				
		
	}


	public void keyReleased(KeyEvent e) {
		if(Data.CUR_STATE == Data.GAME_STATE[0] || Data.CUR_STATE == Data.GAME_STATE[1]) {
			//===Main Menu===//
			if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				Data.MENU.menuUp = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				Data.MENU.menuDown = false;
			}
		}
		
		if(Data.CUR_STATE == Data.GAME_STATE[2]) {
			//===Normal===//
			if(e.getKeyCode() == KeyEvent.VK_W) {
				Data.Player.key_up = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_S) {
				Data.Player.key_down = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_A) {
				Data.Player.key_left = false;
			}
			else if(e.getKeyCode() == KeyEvent.VK_D) {
				Data.Player.key_right = false;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
				ShiftPress = false;
			}
			
		}
		
	}


	public void keyTyped(KeyEvent e) {
	
	}

}
