package com.engine.world;

import com.engine.data.Data;
import com.engine.data.Functions;
import com.engine.main.Loop;

public class Camera {
	
	public static int x, y;	
	public static int wZoom = 2, hZoom = 2;
	public static int follow_x, follow_y, viewport_x, viewport_y;

	public static void cameraUpdate() {
		wZoom = Functions.clamp(wZoom, 2, 4);
		hZoom = Functions.clamp(hZoom, 2, 4);
		
		follow_x = (Data.Player.getX() + Data.Player.getW()/2) - (Loop.WIDTH/(2*Camera.wZoom));
		follow_y = (Data.Player.getY() + Data.Player.getH()/2) - (Loop.HEIGHT/(2*Camera.hZoom));
		
		viewport_x = (World.WIDTH*World.TILE_SIZE) - (Loop.WIDTH/Camera.wZoom);
		viewport_y = (World.HEIGHT*World.TILE_SIZE) - (Loop.HEIGHT/Camera.hZoom);
		
		x = Functions.clamp(follow_x, 0, viewport_x);
		y = Functions.clamp(follow_y, 0, viewport_y);		
		
	}

}
