package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Ground extends GameObject{

	Image block;




	public Ground(int x, int y, int width, int height) {
		super(x, y, width, height);
		ground();




	}

	void draw(Graphics g) {
		System.out.println();
		g.drawImage(block, x, y, width, height, null);
		g.drawRect(x, y, width, height);

	}




	void ground() {

		try {
			block = ImageIO.read(new File("src/Pixel Adventure 1/Terrain/ground.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//	void addBlocks() {
	//		terrain.add(new Terrain(ran.nextInt(Game.WIDTH), 300-height/2+(y-GamePanel.player.y), 80, 50));
	//	}

//	@Override
	void update() {
		
		super.update();
//		collisionBox.setBounds(x, y, width, height);
		//		for(Terrain t: terrain) {
		////			t.update();
		////			System.out.println("h");
		//
		//	}


	}



}









