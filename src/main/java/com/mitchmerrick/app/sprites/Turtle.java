package com.mitchmerrick.app.sprites;
import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;
import com.mitchmerrick.app.Json;

import java.awt.Graphics;

public class Turtle extends Sprite {
	int turtleFrame;
	boolean killed;
	int groundLevel = 575;

	Model model;

	public Turtle(Model m, int xx, int yy) {
	    model = m;
	    x = xx;
	    y = yy;
	    w = 60;
	    h = 60;
	    vert_vel = 0;
	    turtleFrame = 0;
	    direction = Sprite.Directions.RIGHT; // 1 = right; 0 = left
		killed = false;
	}
	
	boolean isTurtle() 	{ return true; }

	public void draw(Graphics g) {
		// Draw current turtle
		g.drawImage(View.turtleImages[turtleFrame], x - model.scrollPos, y, w, h, null);
	}

	public void update() {
		prevLocation();
		
		// Gravity
		vert_vel += 5;
		y += vert_vel;

		// Floor
		if(y >= groundLevel) {
			vert_vel = 0.0;
			y = groundLevel; // Send back to ground
		}

		// Movement and Picture change
		Move();

		// Is turtle colliding with a brick
		for(int i = 0; i < model.sprites.size(); i++) {
			Sprite s = model.sprites.get(i);
			if((s.isBrick() || s.isCoinBlock() || s.isFlagPole()) && doesCollide(0, this, s)) {
				Barrier(s);
			}
			else if (s.isMario() && doesCollide(model.scrollPos, s, this))
				Barrier(model.scrollPos, model.prev_scrollPos, this, s);
		}
	}

	private void Move() {
		if(direction == Sprite.Directions.RIGHT) {
			if(!killed) {
				x += 3;
				if (turtleFrame != 2 && turtleFrame != 3)
					turtleFrame = 2;
				if (turtleFrame == 2)
					turtleFrame++;
				else
					turtleFrame--;
			} else {
				x += 20;
				turtleFrame = 4;
			}
		}
		else if(direction == Sprite.Directions.LEFT){
			if(!killed) {
				x -= 3;
				if (turtleFrame != 0 && turtleFrame != 1)
					turtleFrame = 0;
				if (turtleFrame == 0)
					turtleFrame++;
				else
					turtleFrame--;
			} else {
				x -= 20;
				turtleFrame = 4;
			}
		}
		else if(direction == Directions.STOPPED) {
			turtleFrame = 4;
		}
	}
	
	private void prevLocation() {
		prev_x = x;
		prev_y = y;
	}
	
	// Stops passing through sprite
	private void Barrier(Sprite s) {
		// From left
		if(x + w >= s.x && prev_x + w < s.x) {
			direction = Sprite.Directions.LEFT;
		}
		// From right
		else if(x <= s.x + s.w && prev_x > s.x + s.w) {
			direction = Sprite.Directions.RIGHT;
		}
		// From top
		else if(y + h >= s.y && prev_y + h < s.y) {
			y = s.y - h - 1;
			vert_vel = 0;
		}
		// From bottom
		else if(y <= s.y + s.h && prev_y > s.y + s.h) {
			y = s.y + s.h + 1;
			vert_vel = 0;
		}
	}

	public void squish(Mario mario) {
		if(!killed) {
			// account for squished turtle image
			groundLevel += 20;
			w -= 15;
			h -= 20;

			killed = true;
			Mario.bump.play();
		}
		direction = Directions.STOPPED;
	}
	
	public Turtle(Json ob, Model m) {
		model = m;
		// Change values back to ints
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		vert_vel = (int)ob.getDouble("vert_vel");
		turtleFrame = (int)ob.getLong("turtleFrame");
		direction = Sprite.Directions.RIGHT;
		killed = false;
	}

	public Json marshall() {
		// Put values into Jason object
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("vert_vel", vert_vel);
		ob.add("turtleFrame", turtleFrame);
		ob.add("type", "Turtle");
		return ob;
	}
}
