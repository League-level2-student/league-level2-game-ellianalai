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
	BufferedImage[] idleImages;
	int walkCurrent;
	int idleCurrent;
	int direction;
	boolean keepDirection;
	int walkNum = 1;
	int idleNum = 1;
	boolean isJumping;
	boolean isFalling;
	Image fall;
	int yspeed;
	boolean onSurface = false;
	boolean isLeft;
	boolean isIdle;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		move = 5;
		isWalking = false;
		isJumping = false;
		isFalling = false;
		isIdle = true;
		walkCurrent = 0;
		idleCurrent = 0;
		direction = 0;
		walkImages = new BufferedImage[36];
		jumpImages = new BufferedImage[7];
		idleImages = new BufferedImage[55];
		

		walk();
		jump();
		fall();
		idle();


	}

	void draw(Graphics g) {
		System.out.println(isFalling);

		//		g.setColor(Color.BLUE);
		//		g.fillRect(x, y, width, height);

		if (isWalking == true && direction == 1) {
			g.drawImage(walkImages[walkCurrent], x, y, -width, height, null);
			walkCurrent += 1;
			walkCurrent %= 36;
			keepDirection = true;

		} else if (isWalking == true && direction == 2) {
			g.drawImage(walkImages[walkCurrent], x, y, width, height, null);
			walkCurrent += 1;
			walkCurrent %= 36;

		} else if (isFalling == true && isLeft == false) {
			g.drawImage(fall, x, y, width, height, null);

		} else if (isFalling == true && isLeft) {
			g.drawImage(fall, x, y, -width, height, null);

		} else if (isJumping == true && isLeft == false) {
			g.drawImage(jumpImages[0], x, y, width, height, null);
			//			currentImage += 1;
			//			currentImage %= 36;

		} else if (isJumping == true && isLeft) {
			g.drawImage(jumpImages[0], x, y, -width, height, null);
			//			currentImage += 1;
			//			currentImage %= 36;
			
		if(isJumping == true && isWalking == true && isLeft) {
			g.drawImage(jumpImages[0], x, y, -width, height, null);
		}
		
		}
		
		else {

			if(isLeft && isIdle) {
				g.drawImage(idleImages[idleCurrent], x, y, -width, height, null);
				idleCurrent += 1;
				idleCurrent %= 55;
				keepDirection = true;
			}

			else if (isIdle && isLeft == false){
				g.drawImage(idleImages[idleCurrent], x, y, width, height, null);
				idleCurrent += 1;
				idleCurrent %= 55;
			}


		}

	}

	//	public void up() {
	//			yspeed+=5;
	//			System.out.println(yspeed);
	//			y-= yspeed;
	//			if(yspeed <=0) {
	//				down();
	//		}
	//		
	//		
	//		
	//		
	//		if (y <= 0) {
	//			y = 0;
	//		}
	//		super.update();
	//	}
	//
	//	public void down() {
	//		for(int i =0; i <6; i++) {
	//			yspeed +=i;
	//			System.out.println(yspeed);
	//			y += yspeed;
	//		}
	//		if (y >= 525) {
	//			y = 525;
	//		}
	//		super.update();
	//	}

	public void jumping() {
		if(onSurface) {
			yspeed = 0;
			isFalling = false;
			isJumping = false;
			isIdle = true;
			
			
		}
		else {
			yspeed+=1;
			isIdle = false;
			isFalling = true;
		}
		y+=yspeed;

		if(yspeed>0) {
			isIdle = false;
			isFalling = true;
		}
		
		
		if(y>=523) {
			yspeed = 0;
			y = 523;
			isJumping = false;
			isFalling = false;
			isIdle = true;
		}

		if(y<=0) {
			y = 0;
		}
	}

	public void left() {
		x -= move;
		if (x <= 50) {
			x = 50;
		}
		super.update();
	}

	public void right() {
		x += move;
		if (x >= 910) {
			x = 910;
		}
		super.update();
	}

	void update() {
		//		if (direction == 0 && isJumping == true) {
		//			up();} 
		if (direction == 1 && isWalking == true) {
			left();
		}
		//		if (direction == 2 && isWalking == true) {
		//			down();
		//		}
		else if (direction == 2 && isWalking == true) {
			right();
		}

		if (isJumping == true) {
			jumping();
		}

	}



	void walk() {

		for (int i = 1; i < 37; i++) {

			try {
				walkImages[i - 1] = ImageIO.read(new File(
						"src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Frog (" + walkNum + ").png"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out
				.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Frog (" + walkNum + ").png");
				e.printStackTrace();
			}

			if (i % 3 == 0) {
				walkNum += 1;
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
			fall = ImageIO.read(new File ("src/Pixel Adventure 1/Main Characters/Ninja Frog/Fall (32x32).png"));
		}

		catch (Exception e) {

		}
	}

	void idle() {

		for (int i = 1; i < 56; i++) {

			try {
				idleImages[i - 1] = ImageIO.read(new File(
						"src/Pixel Adventure 1/Main Characters/Ninja Frog/Idle/Idle (" + idleNum + ").png"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Idle/Idle (" + idleNum + ").png");
				e.printStackTrace();
			}

			if (i % 5 == 0) {
				idleNum += 1;
			}
		}
	}

}
