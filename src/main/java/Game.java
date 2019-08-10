package src.main.java;

import src.main.java.controller.Controller;
import src.main.java.model.Model;
import src.main.java.view.View;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{

	Controller controller;
	View view;
	Model model;
	static Sounds themeMusic;

	public Game() {
		themeMusic  = new Sounds("src/main/resources/sounds/Overworld.wav", 2);
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
		themeMusic.play();

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

	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
}
