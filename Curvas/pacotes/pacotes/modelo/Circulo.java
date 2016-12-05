package pacotes.modelo;

public class Circulo {

	// DECLARACAO DAS VARIAVEIS
	private double xc; // COORDENADA X DO CENTRO
	private double yc; // COORDENADA Y DO CENTRO
	private int raio; // RAIO

	// ****************************************************************************************
	// CONSTRUTOR DA CLASSE CIRCULO
	public Circulo(double xc, double yc, int raio) {
		super();
		this.xc = xc;
		this.yc = yc;
		this.raio = raio;
	}

	// ****************************************************************************************
	// RECUPERAR O VALOR DA COORDENADA X DO CENTRO
	public double getXc() {
		return xc;
	}

	// ****************************************************************************************
	// SETAR A COORDENADA X DO CENTRO
	public void setXc(double xc) {
		this.xc = xc;
	}

	// ****************************************************************************************
	// RECUPERAR O VALOR DA COORDENADA Y DO CENTRO
	public double getYc() {
		return yc;
	}

	// ****************************************************************************************
	// SETAR A COORDENADA Y DO CENTRO
	public void setYc(double yc) {
		this.yc = yc;
	}

	// ****************************************************************************************
	// RECUPERAR O VALOR DO RAIO
	public int getRaio() {
		return raio;
	}

	// ****************************************************************************************
	// SETAR O VALOR DO RAIO
	public void setRaio(int raio) {
		this.raio = raio;
	}
	// ****************************************************************************************
}
