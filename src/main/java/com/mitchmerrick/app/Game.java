package com.mitchmerrick.app;

import com.mitchmerrick.app.controller.Controller;
import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame {

	Controller controller;
	View view;
	Model model;

	public Game() {
		model = new Model();
		controller = new Controller(model);
		view = new View(model);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
		this.setTitle("Play!");
		this.setSize(900, 700);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run() {
		while(true) {
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// when 40 -> 25fps
			try {
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	// TODO: remove target from github

	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
}
