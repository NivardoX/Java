package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	// ATRIBUTOS
	public double x, y;
	public int wid, hei;
	public int count_enemy;

	// METODO CONSTRUTOR
	public Enemy(double x, double y, int count_enemy) {
		this.x = 0;
		this.y = 0;
		this.wid = 40;
		this.hei = 5;
		this.count_enemy = count_enemy;
	}

	public void tick() {

		x += (Game.ball.x - (x+20)) * 0.2 *(Game.DIF == 1?1:(int)(1 + (Game.DIF / 14)));
		
	}

	public void render(Graphics gph) {

		gph.setColor(Color.blue);
		gph.fillRect((int) x, (int) y, wid, hei);
	}
}