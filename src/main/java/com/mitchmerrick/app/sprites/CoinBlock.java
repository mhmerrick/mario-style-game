package com.mitchmerrick.app.sprites;

import com.mitchmerrick.app.Sounds;
import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;
import com.mitchmerrick.app.Json;

import java.awt.*;

public class CoinBlock extends Sprite {
	Model model;
	
	int blockIMG;
	int coins;
	static Sounds coinSound = new Sounds("/sounds/coinSound.wav", 3);
	
	public CoinBlock(Model m, int xx, int yy, int ww, int hh) {
		model = m;
		coins = 0;
		x = xx;
		y = yy;
		w = ww;
		h = hh;
	}
	
	boolean isCoinBlock() { return true; }
	
	public void update() {
		// Change image to show coin block is out of coins
		if(coins < 5)
			blockIMG = 0;
		else
			blockIMG = 1;	
	}
	
	public void draw(Graphics g) {
		g.drawImage(View.coinBlocks[blockIMG], x - model.scrollPos, y, w, h, null);
	}
	
	public CoinBlock(Json ob, Model m) {
		model = m;
		// Change values back to ints
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		coins = (int)ob.getLong("coins");
	}
	
	public Json marshall() {
		// Put values into Jason object 
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("coins", coins);
		ob.add("type", "src.main.java.sprites.CoinBlock");
		return ob;
	}
}
