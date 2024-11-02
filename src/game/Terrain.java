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

public class Terrain extends GameObject{

	Image grass;
	ArrayList<Terrain> terrain = new ArrayList <Terrain>();
	Random ran = new Random();

	public Terrain(int x, int y, int width, int height) {
		super(x, y, width, height);
		blocks();
//		addBlocks();




	}

	void draw(Graphics g) {
		System.out.println(terrain.size());
		g.drawImage(grass, x, y , width, height, null);
		g.drawRect(x, 300-height/2+(y-GamePanel.player.y), width, height);

		
//		for(Terrain t: terrain) {
//			t.draw(g);
//		}

		

	}
	void blocks() {

		try {
			grass = ImageIO.read(new File("src/Pixel Adventure 1/Terrain/Grass.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
//	void addBlocks() {
//		terrain.add(new Terrain(ran.nextInt(Game.WIDTH), 300-height/2+(y-GamePanel.player.y), 80, 50));
//	}

	@Override
	void update() {
		
		
		
		collisionBox.setBounds(x, y, width, height/2);
//		for(Terrain t: terrain) {
////			t.update();
////			System.out.println("h");
//
//	}
	
	
	}

}
	
	
	






