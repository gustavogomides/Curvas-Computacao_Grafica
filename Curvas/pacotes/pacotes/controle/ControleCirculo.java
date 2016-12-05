package pacotes.controle;

import java.awt.Graphics;

import pacotes.modelo.*;

public class ControleCirculo {

	// DECLARACAO DA VARIAVEL

	private Util util = new Util();// VARIAVEL PARA A CLASSE UTIL

	// *************************************************************************************
	// METODO QUE CALCULA E O DESENHA UM CIRCULO UTILIZANDO METODO DDA
	public void desenharCirculo(Graphics g, Circulo circulo) {
		int r, x, y, novoX, novoY, erroRaio, xc, yc;
		xc = (int) circulo.getXc();
		yc = (int) circulo.getYc();
		r = circulo.getRaio();
		x = r;
		y = 0;
		novoX = 1 - 2 * r;
		novoY = 1;
		erroRaio = 0;
		while (x >= y) {
			desenhar8pontos(g, xc, yc, x, y);
			y++;
			erroRaio += novoY;
			novoY += 2;
			if ((2 * erroRaio + novoX) > 0) {
				x--;
				erroRaio += novoX;
				novoX += 2;
			}
		}
	}

	// *************************************************************************************
	// DESENHAR PONTOS SIMÉTRICOS
	private void desenhar8pontos(Graphics g, int xc, int yc, int x, int y) {
		util.plotarPonto(g, xc + x, yc + y, true);
		util.plotarPonto(g, xc - x, yc + y, true);
		util.plotarPonto(g, xc - x, yc - y, true);
		util.plotarPonto(g, xc + x, yc - y, true);
		util.plotarPonto(g, xc + y, yc + x, true);
		util.plotarPonto(g, xc - y, yc + x, true);
		util.plotarPonto(g, xc - y, yc - x, true);
		util.plotarPonto(g, xc + y, yc - x, true);
	}

}
