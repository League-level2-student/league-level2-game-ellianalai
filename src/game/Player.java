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
	BufferedImage[] walkRight;
	BufferedImage[] walkLeft;
	BufferedImage[] idleImages;
	Rectangle [] collisionBoxes;
	int walkCurrent_l;
	int walkCurrent_r;
	int idleCurrent;
	int direction;
	//	boolean keepDirection;
	int walkNum_l = 1;
	int walkNum_r = 1;
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
		walkCurrent_l = 0;
		walkCurrent_r = 0;
		idleCurrent = 0;
		direction = 0;
		walkRight = new BufferedImage[36];
		walkLeft = new BufferedImage[36];
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



		
		walk_r();
		walk_l();
		jump();
		fall();
		idle();


	}

	void draw(Graphics g) {
		System.out.println();
		g.drawRect(x, y, width, height);

		//		g.setColor(Color.BLUE);
		//		g.fillRect(x, y, width, height);

		if (isWalking == true &&  isLeft == true) {
			g.drawImage(walkLeft[walkCurrent_l], x, y, width, height, null);
			walkCurrent_l += 1;
			walkCurrent_l %= 36;
			//			keepDirection = true;

		} else if (isWalking == true && isLeft == false) {
			g.drawImage(walkRight[walkCurrent_r], x, y, width, height, null);
			walkCurrent_r += 1;
			walkCurrent_r %= 36;

		} else if (isJumping == true && isLeft == false) {
			g.drawImage(jumpImage, x, y, width, height, null);
			//			currentImage += 1;
			//			currentImage %= 36;

		} else if (isJumping == true && isLeft) {
			g.drawImage(jumpImage, x, y, -width, height, null);
			//			currentImage += 1;
			//			currentImage %= 36;

			//		if(isJumping == true && isWalking == true && isLeft) {
			//			g.drawImage(jumpImages[0], x, y, -width, height, null);
			//		}

		} else if (isFalling == true && isLeft == false) {
			g.drawImage(fall, x, y, width, height, null);

		} else if (isFalling == true && isLeft) {
			g.drawImage(fall, x, y, -width, height, null);

		} 

		else {

			if(isLeft && isIdle) {
				g.drawImage(idleImages[idleCurrent], x, y, -width, height, null);
				idleCurrent += 1;
				idleCurrent %= 55;
				//				keepDirection = true;
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
			isIdle = true;


		}
		else {
			yspeed+=1;

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

	}



	void walk_r() {

			for (int i = 1; i < 37; i++) {

				try {
					walkRight[i - 1] = ImageIO.read(new File(
							"src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Right/Frog (" + walkNum_r + ").png"));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out
					.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Right/Frog (" + walkNum_r + ").png");
					e.printStackTrace();
				}

				if (i % 3 == 0) {
					walkNum_r += 1;
				}
			}
		
	}

	void walk_l () {


			for (int k = 1; k < 37; k++) {

				try {
					walkLeft[k - 1] = ImageIO.read(new File(
							"src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Left/Frog_left (" + walkNum_l + ").png"));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out
					.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Run/Left/Frog_left (\" + walkNum + \").png");
					e.printStackTrace();
				}

				if (k % 3 == 0) {
					walkNum_l += 1;
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
						"src/Pixel Adventure 1/Main Characters/Ninja Frog/Idle/Idle/Right (" + idleNum + ").png"));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("src/Pixel Adventure 1/Main Characters/Ninja Frog/Idle/Idle/Right (" + idleNum + ").png");
				e.printStackTrace();
			}

			if (i % 5 == 0) {
				idleNum += 1;
			}
		}
	}

}
