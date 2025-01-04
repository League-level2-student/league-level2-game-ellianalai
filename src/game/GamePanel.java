package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	static Player player;
	Timer timer;
	Timer fruit_spawn;
	Timer bomb_spawn;
	Timer platform_spawn;
	Image blue;
	int x;
	int y;
	Platform f;
	Platform p2;
	Ground ground;
	Random ran = new Random();
	ArrayList<Platform> platforms = new ArrayList <Platform>();
	ArrayList<Bomb> bombs = new ArrayList <Bomb>();
	ArrayList<Fruit> fruits = new ArrayList <Fruit>();
	int platformHeight = 850;
	int currentState = 0;
	

	GamePanel(){
		player = new Player(200,800,50,50);
//		platform = new Platform(100,100,80,50);
//		p2 = new Platform(300, 400, 80, 50);
		ground = new Ground(0, 836, 512, 64);
		timer = new Timer(1000/60, this);
		timer.start();
		spawnFruit();
		spawnBomb();
		background();

		
		
	}	

	ActionListener a = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			addFruit();
			
		}
	};
	
	ActionListener b = new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			addBomb();
			
		}
	};


	public void actionPerformed(ActionEvent arg0) {
//		for(int i = 0; i<platforms.size(); i++) {
//			Platform p =platforms.get(i);
//			
//			System.out.println(p.y);
//		}
		System.out.println(player.isFalling);
		checkHeight();
		update();		
		checkCollision();
		repaint();
		
	}

	void checkHeight() {
		if(player.y<=-100) {
			currentState =1;
			System.out.println("hi");
			for(int i = 0;i<platforms.size(); i++) {
				platforms.remove(i);
				i--;
			
			player.y = 750;
			platformHeight = 725;
			
			}
			platforms.add(new Platform(player.x, 840, 70, 40));
		}
	}


	@Override
	public void paintComponent(Graphics g) {
		
		for(int i = 0; i<112; i++) {
			x = (i*64)%512;
			y = ((int)(i*64)/512) * 64;
			g.drawImage(blue, x, y, 64, 64, null);

		}
		
	if(currentState ==0) {
			drawFirst(g);	
			}
		
		else if (currentState == 1) {
			drawOther(g);
		}
		
		


	}
	
	void drawFirst(Graphics g) {
		

			player.draw(g);
			ground.draw(g);

			for(Platform p: platforms) {
				p.draw(g);
			}

			for(Bomb b: bombs) {
				b.draw(g);
			}

			for(Fruit f: fruits) {

				f.draw(g);
				
				
			}
	}
	
	void update() {
		
		player.update();
		addPlatform();
		
		for(Fruit f: fruits) {
			f.update();
		}
		
		for(Bomb b: bombs) {
			b.update();
		}
		
		
		for(Platform p: platforms) {
			
			p.update();
		}
	}
	
	void drawOther(Graphics g) {
		

		for(Platform p: platforms) {
			p.draw(g);
		}

		player.draw(g);
		
		for(Bomb b: bombs) {
			b.draw(g);
		}

		for(Fruit f: fruits) {

			f.draw(g);
		}
		
		

		
	}
//	
//	void updateOther() {
//		player.update();
//		addPlatform();
//		
//		for(Fruit f: fruits) {
//			f.update();
//		}
//		
//		for(Bomb b: bombs) {
//			b.update();
//		}
//		
//		
//		for(Platform p: platforms) {
//			
//			p.update();
//		}
//		
//
//	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_SPACE) {
			player.isJumping = true;
			player.isFalling = false;
			player.yspeed = -17;
			player.isIdle = false;


		}

		//		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
		//			player.isFalling = true;
		//			player.isIdle = false;
		//		}

		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.isLeft = true;
			player.isWalking = true;
			player.isIdle = false;
		}

		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.isLeft = false;
			player.isWalking = true;
			player.isIdle = false;
		}


	}
	@Override
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.isFalling = false;
			player.isIdle = true;
		}

		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.isWalking = false;
			player.isFalling = false;
			player.isIdle = true;
		}

		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.isWalking = false;
			player.isFalling = false;
			player.isIdle = true;
		}

	}


	void background() {
		try {
			blue = ImageIO.read(new File ("src/Pixel Adventure 1/Background/Blue.png"));
		}

		catch (Exception e) {

		}
	}
	

	
	void checkCollision() {
		if(currentState == 0) {
			
			if((player.collisionBox.intersects(ground.collisionBox))) {

				if(player.collisionBoxes[2].intersects(ground.collisionBox)&& player.isJumping == true) {
					player.jumping();
				}

				else {
					player.y = ground.y-player.height+1;
					player.isFalling = false;
					player.isIdle = true;
					player.yspeed = 0;
				}

			}
		}

		


		for(int i = 0; i<platforms.size(); i++) {
			Platform p = platforms.get(i);

//			if(player.collisionBox.intersects(p.collisionBox)) {

			
				if(player.collisionBoxes[2].intersects(p.collisionBox)&& player.isJumping == true) {
					System.out.println("jump");
					player.jumping();

				}
				
				else if(player.collisionBoxes[2].intersects(p.collisionBox)&& player.isJumping == false) {
					player.y = p.y-player.height+1;
					player.isIdle = true;
					player.isFalling = false;
					player.yspeed = 0;


				}

//				else if(player.collisionBoxes[2].intersects(platform.collisionBox)&& player.isJumping == false) {
//					player.y = platform.y-player.height+1;
//					player.isFalling = false;
//					player.isIdle = true;
//					player.yspeed = 0;
//
//
//				}
//
//				else if(player.collisionBoxes[2].intersects(p2.collisionBox)&& player.isJumping == false) {
//					player.y = 351;
//					player.isFalling = false;
//					player.isIdle = true;
//					player.yspeed = 0;
//
//				}


//			}

//			else {
				if(player.collisionBox.intersects(p.collisionBox)|| player.collisionBox.intersects(ground.collisionBox)) {
					player.isIdle = true;
					player.isFalling = false;
				}
				else {
					player.isFalling = true;
				}
//			}
		}
		
		
			
			for(int i = 0; i<fruits.size(); i++) {
				Fruit f = fruits.get(i);
				
				if(currentState == 0) {
					if(f.collisionBox.intersects(ground.collisionBox)) {
						f.y+=0;
					}
					
					else {
						f.y+=2;
					}
					
				}
				
				else if(currentState == 1) {
					if(f.y>900) {
						purgeFruit();
					}
					
					else {
						f.y+=2;
					}
				}
				
				
				
				
				if(f.collisionBox.intersects(player.collisionBox)) {
					f.fruit_got = true;
					if(f.collectCurrent>=15) {
						purgeFruit();
					}

				}
				else {
					f.fruit_got = false;
				}
			}
			
			for(int i = 0; i<bombs.size(); i++) {
				Bomb b = bombs.get(i);
				
				if(currentState == 0) {
					if(b.collisionBox.intersects(ground.collisionBox)) {
						b.y+=0;
						b.isExploded = true;
						if(b.collectCurrent>=16) {
							purgeBomb();
						}
					}
					else {
						b.y+=2;
					}
				}
				
				else if (currentState == 1) {
					if(b.y>900) {
						purgeBomb();
					}
					else{
						b.y+=2;
					}
				}
				
				if(b.collisionBox.intersects(player.collisionBox)) {
					b.isExploded = true;
					if(b.collectCurrent>=16) {
						purgeBomb();
					}
				}	

			}
		
		
		
		


	}

	void addPlatform() {
		if(platforms.size()<5) {
			platforms.add(new Platform(ran.nextInt(0, 440), platformHeight-=160 , 70, 40));
		}
		
		

	}

	void addBomb() {
		bombs.add(new Bomb(ran.nextInt(0, 440), 0, 30, 26));
	}

	void addFruit() {
		
		fruits.add(new Fruit(ran.nextInt(Game.WIDTH), 0, 20, 23));
		
	}

	//	void addItem() {
	//		item.add(new Items(ran.nextInt(Game.WIDTH), 0, 32, 32));	
	//	}

	void spawnFruit() {
		fruit_spawn = new Timer(3000, a);
		fruit_spawn.start();
	}
	
	void spawnBomb() {
		bomb_spawn = new Timer (2000, b);
		bomb_spawn.start();
	}
	
	
	
	
	void purgeFruit( ) {
		for(int i = 0; i<fruits.size(); i++) {
			Fruit f = fruits.get(i);
			
			if(f.fruit_got == true) {
				fruits.remove(i);
				i--;
			}
		}
	}
	
	void purgeBomb( ) {
		for(int i = 0; i<bombs.size(); i++) {
			Bomb b = bombs.get(i);
				if(b.isExploded == true) {
					bombs.remove(i);
					i--;
				}
					
				
			}
		}
	}






