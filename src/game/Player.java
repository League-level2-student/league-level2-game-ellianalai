package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject{

	int move;
	boolean isWalking;
	BufferedImage[] images;
	int currentImage;
	int direction;
	boolean keepDirection;
	
	public Player(int x, int y, int width, int height) {
		super(x,y,width,height);
		move = 2;
		isWalking = false;
		currentImage = 0;
		direction = 0;
		images = new BufferedImage[15];

		
		for(int i =1; i<16; i++) {
			try {
				images[i-1] = ImageIO.read(new File("src/game/png/Walk (" + i + ").png"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	void draw(Graphics g) {
	
//		g.setColor(Color.BLUE);
//		g.fillRect(x, y, width, height);
		
		if(isWalking == true && direction ==1) {
			g.drawImage(images[currentImage], x, y, -width, height, null);
			currentImage+=1;
			currentImage %= 15;
			keepDirection = true;

		}
		else if(isWalking == true && direction ==3) {
			g.drawImage(images[currentImage], x, y, width, height, null);
			currentImage+=1;
			currentImage %= 15;

		}
		else if(isWalking == true && direction ==2) {
			g.drawImage(images[currentImage], x, y, width, height, null);
			currentImage+=1;
			currentImage %= 15;

		}
		else if(isWalking == true && direction ==0) {
			g.drawImage(images[currentImage], x, y, width, height, null);
			currentImage+=1;
			currentImage %= 15;

		}
		else {
			g.drawImage(images[0], x, y, width, height, null);

		}
		
	}

	public void up() {
		y-=move;
		if(y<=0) {
			y=0;
		}
		super.update();
	}

	public void down() {
		y+=move;
		if(y>=525) {
			y=525;
		}
		super.update();
	}

	public void left() {
		x-=move;
		if(x<=0) {
			x=0;
		}
		super.update();
	}

	public void right() {
		x+=move;
		if(x>=750) {
			x=750;
		}
		super.update();
	}
	
	void update() {
		if(direction ==0 && isWalking == true) {
			up();
		}
		else if(direction ==1 && isWalking == true) {
			left();
		}
		if(direction ==2 && isWalking == true) {
			down();
		}
		if(direction ==3 && isWalking == true) {
			right();
		}
	}


}
