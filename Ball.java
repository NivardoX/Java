package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x,y;
	public int wid, hei;
	public double dx, dy;
	public double speed = 1.3;
	public int count_player;
	public int count_enemy;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.wid = (int) (4/(Game.DIF == 1?1:(Game.DIF - 1)));
		this.hei = (int) (4/(Game.DIF == 1?1:(Game.DIF - 1)));
		
		int angle = -(new Random().nextInt(120 - 45) + 45);
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		
		x += dx * speed;
		y += dy * speed;
		
		//LIMITES DA BOLA NO EIXO X
		if(x + (dx * speed) + wid >= Game.WIDTH) {
			dx *= -1;
			
		}else if(x + (dx * speed) < 0) {
			dx *= -1;
		}
		
		//PONTUAÇÕES
		if(y >= Game.HEIGHT) {
			//INIMIGO PONTUA

			System.out.println("Enemy scores!");
			Game.enemy.count_enemy++;
			System.out.println("Enemy: " + Game.enemy.count_enemy);
			
			new Game(Game.player.count_player, Game.enemy.count_enemy,Game.DIF);
			return;
			
		}else if(y < 0) {
			//PONTO DO PLAYER
			//int count_player = 0;
			System.out.println("Player scores!");
			
			Game.player.count_player++;
			System.out.println("Player: " + Game.player.count_player);
			
			new Game(Game.player.count_player, Game.enemy.count_enemy,Game.DIF);
			return;
		}
		
		//FAZENDO COLISÕES COM OS JOGADORES
		
		Rectangle bounds = new Rectangle((int)(x + (dx * speed)), (int)(y + (dy * speed)), wid, hei);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.wid, Game.player.hei);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.wid, Game.enemy.hei);
	
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 45;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) 
				dy *= -1;
			
		}else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 45;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0) 
				dy *= -1;
			
		}
	}
	
	public void render(Graphics gph) {
		gph.setColor(Color.GREEN);
		gph.fillRect((int)x, (int)y, wid, hei);
		Game.score(gph);
	}
	
	
}