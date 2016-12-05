package pacotes.controle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import pacotes.modelo.Circulo;
import pacotes.view.MontarPainelInicial;

public class ControleAplicativo implements ActionListener {

	// DECLARACAO DAS VARIAVEIS

	private MontarPainelInicial pnCenario; // VARIAVEL PARA A CLASSE
											// MontarPainelInicial

	private Graphics g; // VARIAVEL DO TIPO GRAPHICS

	// VARIAVEIS PARA AS CLASSES CONTROLES
	private ControleBezier controleBezier = new ControleBezier();
	private ControleHermite controleHermite = new ControleHermite();
	private ControleCirculo controleCirculo = new ControleCirculo();
	private Util util = new Util();

	// ARRAYS COM OS PONTOS DAS FIGURAS
	public ArrayList<Point> pontosClique = new ArrayList<>();

	private boolean animacao = false;

	// ****************************************************************************
	// CONSTRUTOR DA CLASSE ControlarAplicativo
	public ControleAplicativo() {
		pnCenario = new MontarPainelInicial(this);
		g = pnCenario.iniciarGraphics();
		pnCenario.showPanel();
		pnCenario.btLimpar.setEnabled(false);
		pnCenario.btExcluirPontos.setEnabled(false);
	}

	// ****************************************************************************
	// ACTION PERFORMED - CAPTURAR E TRATAR CLIQUE DOS BOTÕES
	public void actionPerformed(ActionEvent e) {
		String comando;
		comando = e.getActionCommand();

		// BEZIER
		if (comando.equals("bezier")) {
			if (pontosClique.size() != 0) {
				g = pnCenario.iniciarGraphics();
				animacao = pnCenario.checkAnimacao.isSelected();
				bezier(Color.CYAN);
				pnCenario.btBezier.setBackground(Color.CYAN);
			}
		}

		// HERMITE
		if (comando.equals("hermite")) {
			if (pontosClique.size() != 0) {
				g = pnCenario.iniciarGraphics();
				animacao = pnCenario.checkAnimacao.isSelected();
				hermite(Color.GREEN);
				pnCenario.btHermite.setBackground(Color.GREEN);
			}
		}

		// AMBAS AS CURVAS
		if (comando.equals("ambas")) {
			if (pontosClique.size() != 0) {
				g = pnCenario.iniciarGraphics();
				ambas();
			}
		}

		// LIMPAR A TELA
		if (comando.equals("limpar")) {
			pnCenario.ocultarDesenho();
			pontosClique.clear();
		}
	}

	private void bezier(Color cor) {
		Point P, antigo;
		Util u = new Util();
		int qtdPontos = pontosClique.size();

		g.setColor(cor);
		if (qtdPontos == 0) {
			pnCenario.labelInformativo.setText("Clique em pelo menos mais dois pontos!");
		} else if (qtdPontos == 1) {
			pnCenario.labelInformativo.setText("Clique em pelo menos mais um ponto!");
		} else if (qtdPontos == 2) {
			pnCenario.btExcluirPontos.setEnabled(true);
			pnCenario.btLimpar.setEnabled(true);
			Point a = pontosClique.get(0);
			Point b = pontosClique.get(1);

			antigo = a;

			for (double t = 0.001; t < 1.0; t = t + 0.001) {
				P = controleBezier.curvaBezierLinear(a, b, t);
				u.plotarPonto(g, antigo.x, antigo.y, false);
				u.plotarPonto(g, P.x, P.y, false);
				antigo = P;
				if (animacao) {
					util.sleep(5);
				}
			}

		} else if (qtdPontos == 3) {
			pnCenario.btExcluirPontos.setEnabled(true);
			pnCenario.btLimpar.setEnabled(true);
			Point a = pontosClique.get(0);
			Point b = pontosClique.get(2);
			Point c = pontosClique.get(1);

			antigo = a;

			for (double t = 0.001; t < 1.0; t = t + 0.001) {
				P = controleBezier.curvaBezierQuadratica(a, b, c, t);
				u.plotarPonto(g, antigo.x, antigo.y, false);
				u.plotarPonto(g, P.x, P.y, false);
				antigo = P;
				if (animacao) {
					util.sleep(5);
				}
			}

		} else if (qtdPontos == 4) {
			pnCenario.btExcluirPontos.setEnabled(true);
			pnCenario.btLimpar.setEnabled(true);

			Point a = pontosClique.get(0);
			Point b = pontosClique.get(2);
			Point c = pontosClique.get(3);
			Point d = pontosClique.get(1);
			antigo = a;
			for (double t = 0.001; t < 1.0; t = t + 0.001) {
				P = controleBezier.curvaBezierCubica(a, b, c, d, t);
				u.plotarPonto(g, antigo.x, antigo.y, false);
				u.plotarPonto(g, P.x, P.y, false);
				antigo = P;
				if (animacao) {
					util.sleep(5);
				}
			}
		}

	}

	private void hermite(Color cor) {
		int qtdPontos = pontosClique.size();

		if (qtdPontos == 0) {
			pnCenario.labelInformativo.setText("Clique em quatro pontos!");
		} else if (qtdPontos == 1) {
			pnCenario.labelInformativo.setText("Clique em mais três pontos!");
		} else if (qtdPontos == 2) {
			pnCenario.labelInformativo.setText("Clique em mais dois pontos!");
		} else if (qtdPontos == 3) {
			pnCenario.labelInformativo.setText("Clique em mais um ponto!");
		} else if (qtdPontos == 4) {
			pnCenario.btExcluirPontos.setEnabled(true);
			pnCenario.btLimpar.setEnabled(true);
			g.setColor(cor);
			Point p1 = pontosClique.get(0);
			Point t1 = pontosClique.get(2);
			Point p2 = pontosClique.get(1);
			Point t2 = pontosClique.get(3);
			controleHermite.desenhoHermite(p1, p2, t1, t2, g, animacao, false, this);
		}
	}

	private void ambas() {
		pnCenario.btBezier.setBackground(Color.RED);
		pnCenario.btHermite.setBackground(Color.YELLOW);
		bezier(Color.RED);
		hermite(Color.YELLOW);
	}

	// ****************************************************************************
	// METODO QUE DESENHA UM CIRCULO NO PONTO CLICADO NA TELA
	public void desenharCirculoMarcacao(Point p, int raio, Graphics g) {
		controleCirculo.desenharCirculo(g, new Circulo(p.getX(), p.getY(), raio));
	}
}
