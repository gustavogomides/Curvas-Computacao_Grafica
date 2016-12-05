package pacotes.controle;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class ControleHermite {

	// DECLARACAO DA VARIAVEL
	public ArrayList<Point> pontosHermite = new ArrayList<>(); // ARRAY COM OS
																// PONTOS DA
																// FIGURA

	// *************************************************************************************
	// METODO QUE CALCULA E DESENHA A CURVA
	// PARAMETROS:
	// -> inicio: ponto inicial da curva
	// -> fim: ponto final da curva
	// -> tangente1: ponto da tangente 1
	// -> tangente2: ponto da tangente2
	// -> g: variavel do tipo Graphics
	// -> animacao: se true anima o desenho da figura
	// -> inserir: se true insere no array de pontos
	// -> controlePrograma: variavel do tipo ControleAplicativo
	public void desenhoHermite(Point inicio, Point fim, Point tangente1, Point tangente2, Graphics g, boolean animacao,
			boolean inserir, ControleAplicativo controlePrograma) {

		Util util = new Util();

		for (int i = 0; i < 1000; i++) {
			int x = 0, y = 0;
			double t = (double) i / (double) 1000;
			double hp1 = 0.0, hp2 = 0.0, ht1 = 0.0, ht2 = 0.0;
			hp1 = (2 * t * t * t) - (3 * t * t) + 1;

			hp2 = (-2 * t * t * t) + (3 * t) * t;

			ht1 = (t * t * t) - (2 * t * t) + t;

			ht2 = (t * t * t) - (t * t);

			x = ((int) (hp1 * inicio.getX() + ht1 * (tangente1.getX() - inicio.getX())
					+ ht2 * (tangente2.getX() - fim.getX()) + hp2 * fim.getX()));

			y = ((int) (hp1 * inicio.getY() + ht1 * (tangente1.getY() - inicio.getY())
					+ ht2 * (tangente2.getY() - fim.getY()) + hp2 * fim.getY()));

			util.plotarPonto(g, (int) x, (int) y, false);

			if (animacao) {
				util.sleep(5);
			}
			if (inserir) {
				pontosHermite.add(new Point((int) x, (int) y));
			}

		}
	}

	// *************************************************************************************
	// METODO QUE RETORNA O ARRAY DE PONTOS DA FIGURA
	// RETORNO -> ARRAY DE PONTOS
	public ArrayList<Point> getArrayDesenhoHermite() {
		return pontosHermite;
	}

	// *************************************************************************************
}
