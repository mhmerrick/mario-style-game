package com.mitchmerrick.app.model;

import com.mitchmerrick.app.sprites.*;
import com.mitchmerrick.app.Json;

import java.util.ArrayList;

public class Model
{
	public ArrayList<Sprite> sprites; //Creating array list of sprites
	public int scrollPos;
	public int prev_scrollPos;
	public int back_x; // Background x value
	public Mario mario;
	private FlagPole flagPole;
	public Sprite sprite;

	public Model() {
		mario = new Mario(this);
		flagPole = new FlagPole(this);
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
		sprites.add(flagPole);
		unmarshall(); // Loads previously saved game
	}

	public void update() {
		for(int i = 0; i < sprites.size(); i++) {
			Sprite s = sprites.get(i);
			s.update();
		}
	}

	//---------------------------------------------------
	// Add methods
	//---------------------------------------------------
	public void addBrick(int xx, int yy, int ww, int hh) {
		Sprite s = new Brick(this, xx, yy, ww, hh); // Create new sprite of type brick
		sprites.add(s); // Add to array of sprites
	}

	public void addCoinBlock(int xx, int yy, int ww, int hh) {
		Sprite s = new CoinBlock(this, xx, yy, ww, hh); // Create new sprite of type coinBlock
		sprites.add(s); // Add to array of sprites
	}

	public void addCoin(int xx, int yy, int ww, int hh) {
		Sprite s = new Coin(this, xx, yy, ww, hh); // Create new sprite of type coin
		sprites.add(s); // Add to array of sprites
	}
	
	public void addTurtle(int xx, int yy) {
		Sprite s = new Turtle(this, xx, yy); // Create new sprite of type coinBlock
		sprites.add(s); // Add to array of sprites
	}
	
	public void addGoomba(int xx, int yy) {
		Sprite s = new Goomba(this, xx, yy);
		sprites.add(s);
	}
	//---------------------------------------------------
	//---------------------------------------------------

	public void unmarshall() {
		sprites.clear(); // Clear all current sprites
		Json ob = Json.load("src/main/resources/map.json"); // Load file into src.main.java.Json object
		Json json_sprites = ob.get("sprites"); // Put sprites into src.main.java.Json object
		for(int i = 0; i < json_sprites.size(); i++) {
			Json j = json_sprites.get(i); // Put sprite dimensions into src.main.java.Json object
			String str = j.getString("type");
			if(str.equals("src.main.java.sprites.Mario"))
			{
				mario = new Mario(j, this);
				sprites.add(mario);
			}
			else if(str.equals("src.main.java.sprites.CoinBlock"))
				sprites.add(new CoinBlock(j, this));
			else if(str.equals("src.main.java.sprites.Brick"))
				sprites.add(new Brick(j, this));
			else if(str.equals("src.main.java.sprites.Coin"))
				sprites.add(new Coin(j, this));
			else if(str.equals("src.main.java.sprites.FlagPole"))
				sprites.add(new FlagPole(j, this));
			else if(str.equals("Turtle"))
				sprites.add(new Turtle(j, this));
			else if(str.equals("src.main.java.sprites.Goomba"))
				sprites.add(new Goomba(j, this));
		}
	}

	private Json marshall() {
	    Json ob = Json.newObject(); // Create src.main.java.Json object
		Json json_sprites = Json.newList(); // Create src.main.java.Json list
		ob.add("sprites", json_sprites); // Add sprites to list
		for(int i = 0; i < sprites.size(); i++) {
			Sprite spr = sprites.get(i); // Put sprite into new sprite object
			Json j = spr.marshall(); // Marshall sprite and put into src.main.java.Json object
			json_sprites.add(j); // Add marshalled sprite to json_sprites
		}
		return ob; // Returns src.main.java.Json object
	}

	public void save(String filename) {
		Json ob = marshall();
		ob.save(filename);
	}


}
