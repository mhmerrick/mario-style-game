package com.mitchmerrick.app;

import com.mitchmerrick.app.controller.Controller;
import com.mitchmerrick.app.model.Model;
import com.mitchmerrick.app.view.View;
import org.apache.commons.io.FileUtils;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.io.*;

public class Game extends JFrame {

	Controller controller;
	View view;
	Model model;
	static Sounds themeMusic;

	// TODO: fix music
	public Game() throws IOException {
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

//		InputStream is = getClass().getResourceAsStream("/sounds/Overworld.wav");
//		File Overworld = File.createTempFile("Overworld", ".wav");
//		Overworld.deleteOnExit();
//		FileUtils.copyInputStreamToFile(is, Overworld);
//		themeMusic = new Sounds(Overworld, 2);
		themeMusic = new Sounds("/sounds/Overworld.wav", 2);
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



	public static void main(String[] args) throws IOException {
		Game g = new Game();
		g.run();
	}
}
