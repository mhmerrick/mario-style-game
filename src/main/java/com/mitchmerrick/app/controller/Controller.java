package com.mitchmerrick.app.controller;

import com.mitchmerrick.app.model.Model;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Controller implements ActionListener, MouseListener, KeyListener {
	private Model model;
	private String rightClick = "";
	private boolean leftClick = false;
	private boolean keyLeft = false;
	private boolean keyRight = false;
	private boolean keyUp = false;
	private boolean keyDown = false;
	private boolean keySpace = false;
	private int mouseDownX;
	private int mouseDownY;

	public Controller(Model m) {
		model = m;
	}

	public void mousePressed(MouseEvent e) {
		// Get current X and Y position on mouse click
		mouseDownX = e.getX();
		mouseDownY = e.getY();
		
		switch(e.getButton()) {
			case MouseEvent.BUTTON1: leftClick = true; break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		// Add sprite however the user draws it
		int x1 = mouseDownX;
		int x2 = e.getX();
		int y1 = mouseDownY;
		int y2 = e.getY();
		int left = Math.min(x1, x2);
		int right = Math.max(x1, x2);
		int top = Math.min(y1, y2);
		int bottom = Math.max(y1, y2);
		
		// Add brick or coin block 
		if(leftClick)
			model.addBrick(left + model.scrollPos, top, right - left, bottom - top);
		else {
			switch(rightClick) {
				case "coinBlock": model.addCoinBlock(x1 + model.scrollPos, top, 60, 60); break;
				case "turtle": model.addTurtle(left + model.scrollPos, top); break;
				case "goomba": model.addGoomba(left + model.scrollPos, top); break;
			}
		}
		
		// Reset variables
		switch(e.getButton()) {
			case MouseEvent.BUTTON1: leftClick = false; break;
		}
	}

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;
			case KeyEvent.VK_S: model.save("src/main/resources/map.json"); break;
			case KeyEvent.VK_L: model.unmarshall(); break;
			case KeyEvent.VK_T: rightClick = "turtle"; break;
			case KeyEvent.VK_G: rightClick = "goomba"; break;
			case KeyEvent.VK_B: rightClick = "coinBlock"; break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: keyRight = false; 	break;
			case KeyEvent.VK_LEFT: 	keyLeft = false; 	break;
			case KeyEvent.VK_UP: 	keyUp = false; 		break;
			case KeyEvent.VK_DOWN: 	keyDown = false; 	break;
			case KeyEvent.VK_SPACE: keySpace = false; 	break;
			case KeyEvent.VK_T:
			case KeyEvent.VK_B:
			case KeyEvent.VK_G:
				rightClick = ""; 	break;
		}
	}

	public void update() {
		model.mario.prevLocation();
		// Scroll left or right and cycle though mario_images 
		if(keyRight) {
			model.mario.right();
		}
		if(keyLeft) {
			model.mario.left();
		}
		if(keySpace)
			model.mario.jump();
	}

	public void keyTyped(KeyEvent e) 			{ 	}
	public void mouseEntered(MouseEvent e) 		{   }
	public void mouseExited(MouseEvent e) 		{   }
	public void mouseClicked(MouseEvent e) 		{   }
	public void actionPerformed(ActionEvent e) 	{	}
	
	
}
