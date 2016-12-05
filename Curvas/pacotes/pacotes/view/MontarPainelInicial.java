package pacotes.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pacotes.controle.ControleAplicativo;
import pacotes.controle.Util;

public class MontarPainelInicial implements MouseListener, MouseMotionListener {

	// DECLARACOES DAS VARIAVIES

	// FRAME PRINCIPAL
	public JFrame baseFrame;

	// PANELS UTILIZADOS
	private JPanel basePanel;
	public JPanel outputPanel;
	private JPanel buttonPanel;

	// BOTOES
	public JButton btBezier;
	public JButton btHermite;
	public JButton btAmbas;
	public JButton btLimpar;
	public JButton btExcluirPontos;

	// LABELS
	private JLabel labelVisor;
	public JLabel labelInformativo;

	// GRAPHICSG
	private Graphics desenho;

	// OBJETO PARA A CLASSE ControleAplicativo
	private ControleAplicativo controlePrograma;

	// ARRAYLIST PARA OS PONTOS CLICADOS NA TELA
	public ArrayList<Point> pontos = new ArrayList<>();

	// BOOLEANOS PARA O CONTROLE DO CLIQUE DO MOUSE
	private boolean clique1 = true, clique2 = false, clique3 = false, clique4 = false;

	public JCheckBox checkAnimacao;

	// ******************************************************************************************************************
	// MONTAR PAINEL INICIAL
	public MontarPainelInicial(ControleAplicativo controlePrograma) {
		this.controlePrograma = controlePrograma;
		Util u = new Util();

		// LAYOUT
		baseFrame = new JFrame();
		baseFrame.setLayout(new BoxLayout(baseFrame.getContentPane(), BoxLayout.Y_AXIS));
		baseFrame.setTitle("Curvas - 30818");
		baseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // FITS PANEL TO THE
															// ACTUAL MONITOR

		baseFrame.setBackground(Color.BLACK);

		basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		basePanel.setBackground(Color.BLACK);

		// OUTPUT PANEL
		outputPanel = new JPanel();
		outputPanel.setLayout(new BorderLayout());
		outputPanel.setBackground(Color.BLACK);

		// BUTTON PANEL
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0, 50));
		buttonPanel.setBackground(Color.BLUE);

		// LABEL DA VISUALIZACAO DO PONTO NA TELA
		JPanel panel = new JPanel(new BorderLayout());
		labelVisor = new JLabel("");
		labelVisor.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelVisor, BorderLayout.NORTH);
		labelVisor.setBackground(Color.WHITE);
		labelVisor.setForeground(Color.BLACK);
		labelVisor.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonPanel.add(panel);

		// BOTOES
		btBezier = u.addAButton("Bezier", "bezier", buttonPanel, 0, null);
		btBezier.addActionListener(controlePrograma);
		btBezier.setBackground(Color.CYAN);
		btBezier.setForeground(Color.BLACK);

		btHermite = u.addAButton("Hermite", "hermite", buttonPanel, 0, null);
		btHermite.addActionListener(controlePrograma);
		btHermite.setBackground(Color.GREEN);
		btHermite.setForeground(Color.BLACK);

		btAmbas = u.addAButton("Ambas as curvas", "ambas", buttonPanel, 0, null);
		btAmbas.addActionListener(controlePrograma);
		btAmbas.setBackground(Color.LIGHT_GRAY);
		btAmbas.setForeground(Color.BLACK);
		btAmbas.setEnabled(false);

		btLimpar = u.addAButton("Limpar Desenho", "limpar", buttonPanel, 0, null);
		btLimpar.addActionListener(controlePrograma);
		btLimpar.setBackground(Color.LIGHT_GRAY);
		btLimpar.setForeground(Color.BLACK);

		btExcluirPontos = u.addAButton("Excluir pontos clicados", "excluirpontos", buttonPanel, 0, null);
		btExcluirPontos.addActionListener(controlePrograma);
		btExcluirPontos.setBackground(Color.LIGHT_GRAY);
		btExcluirPontos.setForeground(Color.BLACK);

		checkAnimacao = new JCheckBox("Animação");
		checkAnimacao.setPreferredSize(new Dimension(90, 25));
		checkAnimacao.setBackground(Color.LIGHT_GRAY);
		checkAnimacao.setForeground(Color.BLACK);
		buttonPanel.add(checkAnimacao);

		// LABEL INFORMATIVO
		labelInformativo = new JLabel("");
		labelInformativo.setFont(new Font("Times", Font.BOLD, 12));
		labelInformativo.setForeground(Color.WHITE);
		labelInformativo.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonPanel.add(labelInformativo);

		// OUVINTES DO MOUSE
		outputPanel.addMouseListener(this);
		outputPanel.addMouseMotionListener(this);

		// VISIBLE PANELS
		baseFrame.add(basePanel);
		basePanel.add(outputPanel, BorderLayout.CENTER);
		basePanel.add(buttonPanel, BorderLayout.PAGE_END);

		baseFrame.setVisible(true);
		basePanel.setVisible(true);
	}

	// ******************************************************************************************************************
	// METODO PARA MOSTRAR O FRAME BASICO
	public void showPanel() {
		// basePanel.setVisible(true);
	}

	// ******************************************************************************************************************
	public void mouseClicked(MouseEvent evento) {
		desenho = iniciarGraphics();
		Point P = new Point(evento.getX(), evento.getY());

		if (clique1) {
			clique1 = false;
			clique2 = true;
			controlePrograma.pontosClique.add(P);
			controlePrograma.desenharCirculoMarcacao(P, 3, desenho);
		} else if (clique2) {
			clique2 = false;
			clique3 = true;
			controlePrograma.pontosClique.add(P);
			controlePrograma.desenharCirculoMarcacao(P, 3, desenho);
		} else if (clique3) {
			clique3 = false;
			clique4 = true;
			controlePrograma.pontosClique.add(P);
			controlePrograma.desenharCirculoMarcacao(P, 3, desenho);
		} else if (clique4) {
			btAmbas.setEnabled(true);
			clique4 = false;
			clique1 = true;
			controlePrograma.pontosClique.add(P);
			controlePrograma.desenharCirculoMarcacao(P, 3, desenho);
		}
	}

	// ******************************************************************************************************************
	public void mouseEntered(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseMoved(MouseEvent e) {
		Point P = new Point(e.getX(), e.getY());
		this.labelVisor.setText("Ponto: ( " + (int) P.getX() + " ; " + (int) P.getY() + " )");
	}

	// ******************************************************************************************************************
	public void mouseExited(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mousePressed(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseReleased(MouseEvent evento) {
	}

	// ******************************************************************************************************************
	public void mouseDragged(MouseEvent e) {
	}

	// ******************************************************************************************************************
	// METODO QUE INICIA O COMPONENTE GRAFICO
	// RETORNO -> VARIAVEL DO TIPO GRAPHICS
	public Graphics iniciarGraphics() {
		desenho = outputPanel.getGraphics();
		return (desenho);
	}

	// ******************************************************************************************************************
	// OCULTAR O DESENHO
	public void ocultarDesenho() {
		desenho.clearRect(0, 0, outputPanel.getWidth(), outputPanel.getHeight());
		desenho.setColor(Color.BLACK);
	}

	// ******************************************************************************************************************

}
