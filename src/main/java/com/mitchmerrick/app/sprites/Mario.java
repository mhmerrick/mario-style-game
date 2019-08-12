package com.mitchmerrick.app.sprites;

import com.mitchmerrick.app.Sounds;
import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;
import com.mitchmerrick.app.Json;

import java.awt.Graphics;

public class Mario extends Sprite {
	int marioFrame;
	int count; // Counts when mario is in the air
	public boolean forward; // Walking direction

	Model model;
	static Sounds bump = new Sounds("src/main/resources/sounds/bump.wav", 5);

	public Mario(Model m) {
		model = m;
		x = 200;
		y = -200;
		w = 60;
		h = 95;
		vert_vel = 0;
		marioFrame = 0;
		forward = true;
		count = 0;
	}

	boolean isMario() { return true; }

	public void draw(Graphics g) {
		// Draw current mario
		//int marioFrame = (Math.abs(model.scrollPos) / 8) % 5; // 20 = run speed; 5 = total number of frames we have to work with
		if(forward)
			g.drawImage(View.marioImages[marioFrame], x, y, null);
		else
			g.drawImage(View.marioImagesBackwards[marioFrame], x, y, null);
	}

	public void update() {
		// Gravity
		vert_vel += 5;
		y += vert_vel;
		count++;

		// Floor
		if(y >= 540) {
			count = 0;
			vert_vel = 0.0;
			y = 540; // Send back to ground
		}

		// Is mario colliding with anything
		for(int i = 0; i < model.sprites.size(); i++) {
			Sprite s = model.sprites.get(i);
			if((s.isBrick() || s.isCoinBlock() || s.isTurtle() || s.isGoomba()) && doesCollide(model.scrollPos, this, s))
				Barrier(s);
			else if(s.isFlagPole() && doesCollide(model.scrollPos, this, s)) {
				if(this.x + this.w + model.scrollPos >= s.x + 10) {
					model.scrollPos -= 10;
					model.back_x += 8;
					((FlagPole) s).changeFlagPos();
				}
			}
		}
	}

	public void prevLocation() {
		prev_x = x;
		prev_y = y;
		model.prev_scrollPos = model.scrollPos;
	}

	// Stops passing through sprite
	void Barrier(Sprite s) {
		// From left
		if(x + model.scrollPos + w >= s.x && prev_x + model.prev_scrollPos + w < s.x) {
			model.scrollPos -= 10;
			model.back_x += 8;
			s.beKicked(Directions.RIGHT);
		}
		// From right
		else if(x + model.scrollPos <= s.x + s.w && prev_x + model.prev_scrollPos > s.x + s.w) {
			model.scrollPos += 10;
			model.back_x -= 8;
			s.beKicked(Directions.LEFT);
		}
		// From top
		else if(y + h >= s.y && prev_y + h < s.y) {
			y = s.y - h - 1;
			count = 0;
			vert_vel = 0;
			s.squish(this);
		}
		// From bottom
		else if(y <= s.y + s.h && prev_y > s.y + s.h) {
			y = s.y + s.h + 1;
			vert_vel = 0;

			if(s.isCoinBlock() && ((CoinBlock) s).coins < 5) {
				((CoinBlock)s).coins++;
				model.addCoin(s.x, s.y, s.w, s.h);
			}
		}
	}

	//---------------------------------------------------
	// Controller methods
	//---------------------------------------------------
	public void right() {
		model.scrollPos += 10;
		model.back_x -= 8;
		marioFrame++;

		if(marioFrame > 4)
			marioFrame = 0;
	}

	public void left() {
		model.scrollPos -= 10;
		model.back_x += 8;
		marioFrame--;

		if(marioFrame < 0)
			marioFrame = 4;
	}

	public void jump() {
		if(count < 4)
			vert_vel -= 14;
	}
	//---------------------------------------------------
	//---------------------------------------------------


	public Mario(Json ob, Model m) {
		model = m;
		// Change values back to numbers
		x = (int)ob.getLong("x");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		vert_vel = (int)ob.getDouble("vert_vel");
		marioFrame = (int)ob.getLong("marioFrame");
		model.scrollPos = (int)ob.getLong("model.scrollPos");
		model.back_x = (int)ob.getLong("model.back_x");
		forward = true;
	}

	public Json marshall() {
		// Put values into Jason object
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("vert_vel", vert_vel);
		ob.add("marioFrame", marioFrame);
		ob.add("model.scrollPos", model.scrollPos);
		ob.add("model.back_x", model.back_x);
		ob.add("type", "src.main.java.sprites.Mario");
		return ob;
	}
}