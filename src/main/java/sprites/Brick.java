package src.main.java.sprites;

import src.main.java.model.Model;
import src.main.java.view.View;
import src.main.java.Json;

import java.awt.*;

public class Brick extends Sprite {
	Model model;
	
	public Brick(Model m, int xx, int yy, int ww, int hh) {
		model = m;
		x = xx;
		y = yy;
		w = ww;  
		h = hh;
	}
	
	public void update() { }
  
	public void draw(Graphics g)
	{
		g.drawImage(View.brick, x - model.scrollPos, y, w, h, null);
	}
	
	boolean isBrick() { return true; }

	public Brick(Json ob, Model m) {
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
		ob.add("type", "src.main.java.sprites.Brick");
		return ob;
	}
}
