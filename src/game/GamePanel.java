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
	Timer item_spawn;
	Image blue;
	int x;
	int y;
	Platform platform;
	Platform p2;
	Ground ground;
	Random ran = new Random();
	ArrayList<Platform> platforms = new ArrayList <Platform>();
	ArrayList<Items> item = new ArrayList<Items>();
	

	GamePanel(){
		player = new Player(200,200,50,50);
		timer = new Timer(1000/60, i);
		timer.start();
		platform = new Platform(100,100,80,50);
		p2 = new Platform(300, 400, 80, 50);
		ground = new Ground(0, 508, 960, 64);
		
		background();
		addPlatform();

	}	
	
	ActionListener i = new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			
			player.update();
			checkCollision();
			addItem();
			repaint();
			
			
					
		}
	};
	


	@Override
	protected void paintComponent(Graphics g) {

		for(int i = 0; i<135; i++) {
			x = (i*64)%960;
			y = ((int)(i*64)/960) * 64;
			g.drawImage(blue, x, y, 64, 64, null);
	
		}
		
		player.draw(g);
		ground.draw(g);
		
		for(Platform p: platforms) {
			p.draw(g);
		}
		
		for(Items i: item) {
			i.draw(g);
		}
		

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_SPACE) {
			player.isJumping = true;
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
			player.isIdle = true;
		}

		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.isWalking = false;
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
		
		if((player.collisionBox.intersects(ground.collisionBox))) {
			
			if(player.collisionBoxes[2].intersects(ground.collisionBox)&& player.isJumping == true) {
				player.jumping();
			}
			
			else {
				player.y = 459;
				player.isFalling = false;
				player.isIdle = true;
				player.yspeed = 0;
			}
		
		}
		
		
		for(int i = 0; i<platforms.size(); i++) {
			Platform p = platforms.get(i);
			
			if(player.collisionBox.intersects(p.collisionBox)) {
				
				
				if(player.collisionBox.intersects(p.collisionBox)&& player.isJumping == true) {
					System.out.println("jump");
					player.jumping();
					
				}
				
				else if(player.collisionBox.intersects(platform.collisionBox)&& player.isJumping == false) {
					player.y = 51;
					player.isFalling = false;
					player.isIdle = true;
					player.yspeed = 0;
					
					
				}
				
				else if(player.collisionBox.intersects(p2.collisionBox)&& player.isJumping == false) {
					player.y = 351;
					player.isFalling = false;
					player.isIdle = true;
					player.yspeed = 0;
					
				}
				
				
			}
			
			else {
				if(player.collisionBox.intersects(platform.collisionBox)|| player.collisionBox.intersects(ground.collisionBox)
					|| player.collisionBox.intersects(p2.collisionBox)) {
					player.isIdle = true;
					player.isFalling = false;
				}
				else {
					player.isFalling = true;
				}
			}
		}
		

	}
	
	void addPlatform() {
		platforms.add(p2);
		platforms.add(platform);
		
	}
	
	void addItem() {
		item.add(new Items(ran.nextInt(Game.WIDTH), 0, 32, 32));	
	}
	
	void spawnItem() {
		item_spawn = new Timer(1000, i);
		item_spawn.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
