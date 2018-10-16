package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	// VARIAVEIS
	public boolean right, left;
	public int x, y, wid, hei;
	public int count_player;

	// METODO CONSTRUTOR
	public Player(int x, int y, int count_player) {
		this.x = x;
		this.y = y;
		this.wid = 40;
		this.hei = 5;
		this.count_player = count_player;
	}

	public void tick() {

		// FAZ COM QUE MUDE A POSIÇÃO DO PLAYER DE ACORDO COM AS SETAS DO TECLADO
		if (right) {
			x = x + 2;
		} else if (left) {
			x = x - 2;
		}

		// FAZ COM QUE EXISTAM COLISÕES
		if (x + wid > Game.WIDTH) {
			x = Game.WIDTH - wid;

		} else if (x < 0) {
			x = 0;
		}
	}

	public void render(Graphics gph) {

		gph.setColor(Color.red);
		gph.fillRect(x, y, 40, 5);

	}
}