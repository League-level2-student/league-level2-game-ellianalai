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

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	static Player player;
	Timer timer;
	Image blue;
	int x;
	int y;
	Platform platform;
	Platform p2;
	Ground ground;
	ArrayList<Platform> platforms = new ArrayList <Platform>();
	ArrayList<Items> item = new ArrayList<Items>();

	GamePanel(){
		player = new Player(200,200,50,50);
		timer = new Timer(1000/60, this);
		timer.start();
		platform = new Platform(100,100,80,50);
		p2 = new Platform(300, 400, 80, 50);
		ground = new Ground(0, 508, 960, 64);
		
		background();
		addPlatform();

	}	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		player.update();
		checkCollision();
		
		
		
		
		repaint();
		
		
				
	}


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

		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.isFalling = true;
			player.isIdle = false;
		}

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
		System.out.println(player.onSurface);

		if((player.collisionBox.intersects(ground.collisionBox))) {
			
			if(player.collisionBoxes[2].intersects(ground.collisionBox)) {
				player.y = 459;
				player.isFalling = false;
				player.isIdle = true;
			}
			
			else {
		
			}
			
//			if(player.collisionBoxes[2].intersects(ground.collisionBox)&& player.isJumping == true) {
//				System.out.println("jump");
//				player.onSurface = false;
//				
//			}
//			
//			else {
//				player.onSurface = true;
//				player.y = 459;
//			}
		
		}
		
		else {
			player.onSurface = false;
		}
		
		for(int i = 0; i<platforms.size(); i++) {
			Platform p = platforms.get(i);
			
			if(player.collisionBox.intersects(p.collisionBox)) {
				
				
				if(player.collisionBox.intersects(p.collisionBox)&& player.isJumping == true) {
					System.out.println("jump");
					player.onSurface = false;
					
				}
				
				else if(player.collisionBox.intersects(platform.collisionBox)&& player.isJumping == false) {
					player.onSurface = true;
					player.y = 51;
					
					
				}
				
				else if(player.collisionBox.intersects(p2.collisionBox)&& player.isJumping == false) {
					player.onSurface = true;
					player.y = 351;
					
					
				}
				
			}
			
			else {
				player.isFalling = true;
//				player.onSurface= false;
			}
		}
		

	}
	
	void addPlatform() {
		platforms.add(p2);
		platforms.add(platform);
		
	}


}
