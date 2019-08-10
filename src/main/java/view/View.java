package src.main.java.view;

import src.main.java.model.Model;
import src.main.java.sprites.Sprite;

import javax.swing.JPanel;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.*;

public class View extends JPanel {
	Model model;

	public static Image background;
	public static Image brick;
	public static Image floorBlock;
	public static Image[] turtleImages;
	public static Image[] goombaImages;
	public static Image[] coins;
	public static Image[] coinBlocks;
	public static Image[] flagPole;
	public static Image[] marioImages = null;
	public static Image[] marioImagesBackwards = null;

	public View(Model m) {
		if(marioImages == null) {
			model = m;
			marioImages = new Image[5];
			marioImagesBackwards = new Image[5];
			turtleImages = new Image[5];
			goombaImages = new Image[3];
			coinBlocks = new Image[2];
			coins = new Image[6];
			flagPole = new Image[5];

			try {
				background = ImageIO.read(new File("src/main/resources/png/background1.png"));
				brick = ImageIO.read(new File("src/main/resources/png/blocks/brick.png"));
				floorBlock = ImageIO.read(new File("src/main/resources/png/floorBlock.png"));

				flagPole[0] = ImageIO.read(new File("src/main/resources/png/flagpole/FlagPole1.png"));
				flagPole[1] = ImageIO.read(new File("src/main/resources/png/flagpole/FlagPole2.png"));
				flagPole[2] = ImageIO.read(new File("src/main/resources/png/flagpole/FlagPole3.png"));
				flagPole[3] = ImageIO.read(new File("src/main/resources/png/flagpole/FlagPole4.png"));
				flagPole[4] = ImageIO.read(new File("src/main/resources/png/flagpole/FlagPole5.png"));

				coins[0] = ImageIO.read(new File("src/main/resources/png/coins/coin1.png"));
				coins[1] = ImageIO.read(new File("src/main/resources/png/coins/coin2.png"));
				coins[2] = ImageIO.read(new File("src/main/resources/png/coins/coin3.png"));
				coins[3] = ImageIO.read(new File("src/main/resources/png/coins/coin4.png"));
				coins[4] = ImageIO.read(new File("src/main/resources/png/coins/coin5.png"));
				coins[5] = ImageIO.read(new File("src/main/resources/png/coins/coin6.png"));

				coinBlocks[0] = ImageIO.read(new File("src/main/resources/png/blocks/coinBlock1.png"));
				coinBlocks[1] = ImageIO.read(new File("src/main/resources/png/blocks/coinBlock2.png"));

				turtleImages[0] = ImageIO.read(new File("src/main/resources/png/turtle/turtle0.png"));
				turtleImages[1] = ImageIO.read(new File("src/main/resources/png/turtle/turtle1.png"));
				turtleImages[2] = ImageIO.read(new File("src/main/resources/png/turtle/turtle2.png"));
				turtleImages[3] = ImageIO.read(new File("src/main/resources/png/turtle/turtle3.png"));
				turtleImages[4] = ImageIO.read(new File("src/main/resources/png/turtle/turtle4.png"));
				
				goombaImages[0] = ImageIO.read(new File("src/main/resources/png/goomba/goomba0.png"));
				goombaImages[1] = ImageIO.read(new File("src/main/resources/png/goomba/goomba1.png"));
				goombaImages[2] = ImageIO.read(new File("src/main/resources/png/goomba/goomba2.png"));

				marioImages[0] = ImageIO.read(new File("src/main/resources/png/mario/mario1.png"));
				marioImages[1] = ImageIO.read(new File("src/main/resources/png/mario/mario2.png"));
				marioImages[2] = ImageIO.read(new File("src/main/resources/png/mario/mario3.png"));
				marioImages[3] = ImageIO.read(new File("src/main/resources/png/mario/mario4.png"));
				marioImages[4] = ImageIO.read(new File("src/main/resources/png/mario/mario5.png"));
				
				marioImagesBackwards[0] = ImageIO.read(new File("src/main/resources/png/mario/marioB5.png"));
				marioImagesBackwards[1] = ImageIO.read(new File("src/main/resources/png/mario/marioB4.png"));
				marioImagesBackwards[2] = ImageIO.read(new File("src/main/resources/png/mario/marioB3.png"));
				marioImagesBackwards[3] = ImageIO.read(new File("src/main/resources/png/mario/marioB2.png"));
				marioImagesBackwards[4] = ImageIO.read(new File("src/main/resources/png/mario/marioB1.png"));
			}
			catch(IOException e) {
				e.printStackTrace(System.err);
				System.out.println("Could not load an image.");
				System.exit(1);
			}
		}
	}

	public void paintComponent(Graphics g) {
		int floorX = 0;

		// Draw background
		g.drawImage(background, model.back_x, -10, 1920, 710, null);
		
		// Draw floor
		for(int i = 0; i < 100; i++) {
			g.drawImage(floorBlock, floorX-model.scrollPos, 634, 60, 60, null);
			floorX += 60;
		}
		floorX = 0;

		// Draw sprites
		for(int i = 0; i < model.sprites.size(); i++) {
			Sprite s = model.sprites.get(i);
			s.draw(g);
		}

	}

}
