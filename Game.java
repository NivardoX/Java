package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	//ATRIBUTOS
	public final static int WIDTH = 400;
	public final static int HEIGHT = 200;
	public final static int SCALE = 3;
	public static double DIF;
	public static int frameCount;

	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	
	//METODO CONSTRUTOR GAME
	public Game(int count_player, int count_enemy, double dif) {
	
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		
		Game.DIF=  dif;
		
		player = new Player((WIDTH/2) - 20, HEIGHT - 5, count_player);
		enemy = new Enemy(0 - 20, 0, count_enemy);
		ball = new Ball(0 , HEIGHT /2 - 1);
	}
	
	public void tick() {
		frameCount++;
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics gph = layer.getGraphics();
		gph.setColor(Color.DARK_GRAY);
		gph.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(gph);
		enemy.render(gph);
		ball.render(gph);
		
		
		gph = bs.getDrawGraphics();
		gph.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bs.show();	
	}
	
	public void run() {
		
		//GAME LOOP
		while(true) {
			tick();
			render();
			try {
				Thread.sleep((long) (1000/(60*Game.DIF)));
			}catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
			
		}
			
	}

	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}

	
	public static void score(Graphics gph) {
		
		gph.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		gph.setColor(Color.WHITE);
		gph.drawString("PLAYER SCORE:" + Game.player.count_player, 0, (Game.WIDTH/6));
		gph.drawString("IA SCORE:" + Game.enemy.count_enemy, Game.WIDTH - 60 , (Game.WIDTH/6));
		gph.setFont(new Font("Arial", Font.PLAIN, 7));
		gph.drawString("Frames: " +  Game.frameCount, 0 , 5);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}