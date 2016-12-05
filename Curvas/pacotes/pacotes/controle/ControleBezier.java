package pacotes.controle;

import java.awt.Point;
import java.util.ArrayList;

public class ControleBezier {

	// DECLARACAO DAS VARIAVEIS

	public ArrayList<Point> pontosBezier = new ArrayList<>(); // ARRAY COM OS
																// PONTOS DA
																// FIGURA

	// METODO QUE CALCULA A CURVA DE BEZIER LINEAR
	// PARAMETROS:
	// -> inicio: ponto inicial da curva
	// -> fim: ponto final da curva
	// -> t: instante t
	// -> controleAnimacao: variavel para a classe ControleAnimacao
	public Point curvaBezierLinear(Point inicio, Point fim, double t) {
		// c(t) = (1-t)P0 + tP1
		int x, y;

		x = (int) ((1 - t) * inicio.x + t * fim.x);
		y = (int) ((1 - t) * inicio.y + t * fim.y);

		return new Point(x, y);
	}

	// *************************************************************************************
	// METODO QUE CALCULA A CURVA DE BEZIER QUADRATICA
	// PARAMETROS:
	// -> inicio: ponto inicial da curva
	// -> controle: ponto de controle
	// -> fim: ponto final da curva
	// -> t: instante t
	// -> controleAnimacao: variavel para a classe ControleAnimacao
	public Point curvaBezierQuadratica(Point inicio, Point controle, Point fim, double t) {
		// c(t) = (1-t)²P0 + 2t(1-t)P1 + t²P2
		int x, y;

		x = (int) ((1 - t) * (1 - t) * inicio.x + 2 * t * (1 - t) * controle.x + t * t * fim.x);
		y = (int) ((1 - t) * (1 - t) * inicio.y + 2 * t * (1 - t) * controle.y + t * t * fim.y);

		return new Point(x, y);

	}

	// *************************************************************************************
	// METODO QUE CALCULA A CURVA DE BEZIER CUBICA
	// PARAMETROS:
	// -> inicio: ponto inicial da curva
	// -> controle1: ponto de controle 1
	// -> controle2: ponto de controle 2
	// -> fim: ponto final da curva
	// -> t: instante t
	// -> controleAnimacao: variavel para a classe ControleAnimacao
	public Point curvaBezierCubica(Point inicio, Point controle1, Point controle2, Point fim, double t) {
		// c(t) = (1-t)³P0 + 3t(1-t)²P1 + 3t²(1-t)P2 + t³P3
		int x, y;
		x = (int) ((1 - t) * (1 - t) * (1 - t) * inicio.x + 3 * t * (1 - t) * (1 - t) * controle1.x
				+ 3 * t * t * (1 - t) * controle2.x + t * t * t * fim.x);

		y = (int) ((1 - t) * (1 - t) * (1 - t) * inicio.y + 3 * t * (1 - t) * (1 - t) * controle1.y
				+ 3 * t * t * (1 - t) * controle2.y + t * t * t * fim.y);

		return new Point(x, y);
	}

	// *************************************************************************************
	// METODO QUE RECUPERA OS PONTOS DA FIGURA
	// RETORNO -> ARRAY COM OS PONTOS DA FIGURA
	public ArrayList<Point> getArrayDesenhoBezier() {
		return pontosBezier;
	}

}
