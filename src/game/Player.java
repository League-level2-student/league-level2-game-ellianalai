package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject {

	int move;
	boolean isWalking;
	BufferedImage[] walkImages;
	BufferedImage[] jumpImages;
	int currentImage;
	int direction;
	boolean keepDirection;
	int imageNumber = 1;
	boolean isJumping;
	boolean isFalling;
	Image fall;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		move = 5;
		isWalking = false;
		isJumping = false;
		isFalling = false;
		currentImage = 0;
		direction = 0;
		walkImages = new BufferedImage[36];
		jumpImages = new BufferedImage[7];
		
		walk();
		jump();
		fall();
		

	}

	void draw(Graphics g) {

//		g.setColor(Color.BLUE);
//		g.fillRect(x, y, width, height);

		if (isWalking == true && direction == 1) {
			g.drawImage(walkImages[currentImage], x, y, -width, height, null);
			currentImage += 1;
			currentImage %= 36;
			keepDirection = true;

		} else if (isWalking == true && direction == 3) {
			g.drawImage(walkImages[currentImage], x, y, width, height, null);
			currentImage += 1;
			currentImage %= 36;

		} else if (isFalling == true && direction == 2) {
			g.drawImage(fall, x, y, width, height, null);

		} else if (isJumping == true && direction == 0) {
			g.drawImage(jumpImages[0], x, y, width, height, null);
//			currentImage += 1;
//			currentImage %= 36;

		} else {
			g.drawImage(walkImages[0], x, y, width, height, null);

		}

	}

	public void up() {
		y -= 10;
		if (y <= 0) {
			y = 0;
		}
		super.update();
	}

	public void down() {
		y += 10;
		if (y >= 525) {
			y = 525;
		}
		super.update();
	}

	public void left() {
		x -= move;
		if (x <= 0) {
			x = 0;
		}
		super.update();
	}

	public void right() {
		x += move;
		if (x >= 750) {
			x = 750;
		}
		super.update();
	}

	void update() {
		if (direction == 0 && isJumping == true) {
			up();
		} else if (direction == 1 && isWalking == true) {
			left();
		}
		if (direction == 2 && isWalking == true) {
			down();
		}
		if (direction == 3 && isWalking == true) {
			right();
		}
	}
	
	void walk() {
		
		for (int i = 1; i < 37; i++) {

			try {
				walkImages[i - 1] = ImageIO.read(new File(
						"src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Frog (" + imageNumber + ").png"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out
						.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Frog (" + imageNumber + ").png");
				e.printStackTrace();
			}
			
			if (i % 3 == 0) {
				imageNumber += 1;
			}
		}
	}
	
	void jump() {
		
		try {
			jumpImages [0] = ImageIO.read(new File ("src/Pixel Adventure 1/Main Characters/Ninja Frog/Jump (32x32).png"));
		}
		
		catch (Exception e) {
			
		}
		
	}
	
	void fall() {
		try {
			fall = ImageIO.read(new File ("src/Pixel Adventure 1/Main Characters/Ninja Frog/Jump (32x32).png"));
		}
		
		catch (Exception e) {
			
		}
	}

}
