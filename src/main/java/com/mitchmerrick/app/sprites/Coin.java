package com.mitchmerrick.app.sprites;

import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;
import com.mitchmerrick.app.Json;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

public class Coin extends Sprite {
	Model model;
	double vert_vel;
	int coinFrame;
	int randNum;
	
	Random rand = new Random(System.currentTimeMillis());
	
	// Add coin above block and play coin sound 
	public Coin(Model m, int xx, int yy, int ww, int hh) {
		model = m;
		vert_vel = -30;
		coinFrame = 0;
		x = (int) (xx + (0.4 * ww));
		y = yy - 30;
		w = 30;
		h = 30;
		
		randNum = rand.nextInt(100) + 1;

		CoinBlock.coinSound.play();
	}
	
	boolean isCoin() { return true; }
	
	public void update() {
		// Gravity
		vert_vel += 5;
		y += vert_vel;
		
		// src.main.java.sprites.Coin image
		coinFrame++;
		if(coinFrame > 5)
			coinFrame = 0;
		
		
		// src.main.java.sprites.Coin direction
		if(randNum < 50)
			x += 6;
		else
			x -= 6;
		
		// Remove coin from list after it falls off screen
		Iterator<Sprite> it = model.sprites.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			if(s.isCoin() && s.y > 700)
				it.remove();
		}
			
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(View.coins[coinFrame], x - model.scrollPos, y, 30, 30, null);
	}
	
	public Coin(Json ob, Model m) {
		model = m;
		// Change values back to ints
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		vert_vel = (int)ob.getDouble("vert_vel");
	}
	
	public Json marshall() {
		// Put values into Jason object 
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("vert_vel", vert_vel);
		ob.add("type", "src.main.java.sprites.Coin");
		return ob;
	}
}
