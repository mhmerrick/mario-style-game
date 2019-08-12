package com.mitchmerrick.app.sprites;

import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;
import com.mitchmerrick.app.Json;

import java.awt.Graphics;

public class FlagPole extends Sprite {
	Model model;
	
	int flagPosition;
	
	public FlagPole(Model m) {
		model = m;
		flagPosition = 0;
		
		x = 1700;
		y = 135;
		w = 60;
		h = 500;
	}
	
	boolean isFlagPole() { return true; }
	
	void changeFlagPos() {
		if(flagPosition < 4)
			flagPosition++;
	}
	
	public void draw(Graphics g) {
		g.drawImage(View.flagPole[flagPosition], x - model.scrollPos, y, w, h, null);
	}
	
	public void update() { }
	
	public FlagPole(Json ob, Model m) {
		model = m;
		// Change values back to ints
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
	}
	
	public Json marshall() {
		// Put values into Jason object 
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("type", "src.main.java.sprites.FlagPole");
		return ob;
	}
}
