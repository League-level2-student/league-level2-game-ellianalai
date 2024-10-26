package game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Terrain extends GameObject{
	
	ArrayList <Image> platform;
	
	Image grass;
	
	int rand_x;
	int rand_y;

	public Terrain(int x, int y, int width, int height) {
		super(x, y, width, height);
		platform = new ArrayList<Image>();
		blocks();
		
		for(int i = 0; i <5; i++) {
			platform.add(grass);
		}
		
		
	}
	
	void draw(Graphics g) {
			System.out.println(platform.size());
			
			for(int i = 0; i<platform.size(); i ++) {
				g.drawImage(grass, x, 300-height/2+(y-GamePanel.player.y), width, height, null);
				g.drawRect(x, 300-height/2+(y-GamePanel.player.y), width, height);
			}
			
		
	}
	
	void blocks() {
		try {
			grass = ImageIO.read(new File("src/Pixel Adventure 1/Terrain/Grass.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	void update() {
		collisionBox.setBounds(x, y, width, height/2);
//		int rand_x = random.nextInt(GamePanel.player.y+300) + GamePanel.player.y-300;
		
	}
	
	

}
