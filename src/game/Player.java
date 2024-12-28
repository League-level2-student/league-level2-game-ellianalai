package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject {

	int move;
	boolean isWalking;
	BufferedImage[] walkImage;
	BufferedImage[] idleImages;
	Rectangle [] collisionBoxes;
	int walkCurrent;
	int idleCurrent;
	int direction;
	//	boolean keepDirection;
	int walkNum = 1;
	int idleNum = 1;
	boolean isJumping;
	boolean isFalling;
	Image fall;
	Image jumpImage;
	int yspeed;
	int xspeed;
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
		walkImage = new BufferedImage[36];
		idleImages = new BufferedImage[55];
		collisionBoxes = new Rectangle[4];

		
		for(int i = 0; i<collisionBoxes.length; i++ ) {

			if(i==0) {
				collisionBoxes[i] = new Rectangle(collisionBox.x, collisionBox.y, width, height/10);

			}

			if(i==1) {
				collisionBoxes[i] = new Rectangle(collisionBox.x-width/10 + width, collisionBox.y, width/10, height);
			}

			if(i==2) {
				collisionBoxes[i] = new Rectangle(collisionBox.x, collisionBox.y - height/10 + height, width, height/10);
			}

			if(i==3) {
				collisionBoxes[i] = new Rectangle(collisionBox.x, collisionBox.y, width/10, height);
			}
		}

		
		walk();
		jump();
		fall();
		idle();


	}

	void draw(Graphics g) {
		
		
		
		System.out.println();
		g.drawRect(collisionBox.x, collisionBox.y, width, height/10);
		g.drawRect(collisionBox.x-width/10 + width, collisionBox.y, width/10, height);
		g.drawRect(collisionBox.x, collisionBox.y - height/10 + height, width, height/10);
		g.drawRect(collisionBox.x, collisionBox.y, width/10, height);
		g.drawRect(x, y, width, height);

		//		g.setColor(Color.BLUE);
		//		g.fillRect(x, y, width, height);

		if (isWalking == true &&  isLeft == true) {
			g.drawImage(walkImage[walkCurrent], collisionBox.x+width, collisionBox.y, -width, height, null);
			walkCurrent += 1;
			walkCurrent %= 36;
			//			keepDirection = true;

		} else if (isWalking == true && isLeft == false) {
			g.drawImage(walkImage[walkCurrent], collisionBox.x, collisionBox.y, width, height, null);
			walkCurrent += 1;
			walkCurrent %= 36;

		} else if (isJumping == true && isLeft == false) {
			g.drawImage(jumpImage, collisionBox.x, collisionBox.y, width, height, null);
			//			currentImage += 1;
			//			currentImage %= 36;

		} else if (isJumping == true && isLeft) {
			g.drawImage(jumpImage, collisionBox.x+width, collisionBox.y, -width, height, null);
			//			currentImage += 1;
			//			currentImage %= 36;

			//		if(isJumping == true && isWalking == true && isLeft) {
			//			g.drawImage(jumpImages[0], x, y, -width, height, null);
			//		}

		} else if (isFalling == true && isLeft == false) {
			g.drawImage(fall, collisionBox.x, collisionBox.y, width, height, null);

		} else if (isFalling == true && isLeft) {
			g.drawImage(fall, collisionBox.x+width, collisionBox.y, -width, height, null);

		} 

		else {

			if(isLeft && isIdle) {
				g.drawImage(idleImages[idleCurrent], collisionBox.x+width, collisionBox.y, -width, height, null);
				idleCurrent += 1;
				idleCurrent %= 55;
				//				keepDirection = true;
			}

			else if (isIdle && isLeft == false){
				g.drawImage(idleImages[idleCurrent], collisionBox.x, collisionBox.y, width, height, null);
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
			isIdle = true;


		}
		else {
			yspeed+=1;
			isFalling = true;

			if(yspeed<0) {
				isJumping = true;
				isFalling = false;
				isIdle = false;
			}

			else if(yspeed>0) {
				isFalling = true;
				isJumping = false;
				isIdle = false;

			}

		}
		y+=yspeed;
		

	}

	public void left() {
		x -= move;
		if (x <= 0) {
			x = 0;
		}
	}

	public void right() {
		x += move;
		if (x >= 462) {
			x = 462;
		}
	}

	void update() {
		super.update();
		//		if (direction == 0 && isJumping == true) {
		//			up();} 
		if (isLeft == true && isWalking == true) {

			left();
		}
		//		if (direction == 2 && isWalking == true) {
		//			down();
		//		}
		else if (isLeft == false && isWalking == true) {
			right();
		}

		if (isJumping == true || isFalling == true) {
			jumping();
		}
		
		for(int i = 0; i<collisionBoxes.length; i++ ) {

			if(i==0) {
				collisionBoxes[0].setBounds(collisionBox.x, collisionBox.y, width, height/10);

			}

			if(i==1) {
				collisionBoxes[1].setBounds(collisionBox.x-width/10 + width, collisionBox.y, width/10, height);
			}

			if(i==2) {
				collisionBoxes[2].setBounds(collisionBox.x, collisionBox.y - height/10 + height, width, height/10);
			}

			if(i==3) {
				collisionBoxes[3].setBounds(collisionBox.x, collisionBox.y, width/10, height);
			}
		}

	}



	void walk() {

			for (int i = 1; i < 37; i++) {

				try {
					walkImage[i - 1] = ImageIO.read(new File(
							"src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Frog (" + walkNum + ").png"));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out
					.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Right/Frog (" + walkNum + ").png");
					e.printStackTrace();
				}

				if (i % 3 == 0) {
					walkNum += 1;
				}
			}
		
	}


	void jump() {

		try {
			jumpImage = ImageIO.read(new File ("src/Pixel Adventure 1/Main Characters/Ninja Frog/Jump (32x32).png"));
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
