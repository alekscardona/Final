package Models;

import java.io.Serializable;

import ListasGenericas.ListaSimple;

public class Equipaje implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public double peso;
	public double dimensionAlto;
	public double dimenesionLargo;
	public double dimensionAncho;

	public Mascota listaMascotas;


	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}


	/**
	 * @return the dimensionAlto
	 */
	public double getDimensionAlto() {
		return dimensionAlto;
	}

	/**
	 * @param dimensionAlto the dimensionAlto to set
	 */
	public void setDimensionAlto(double dimensionAlto) {
		this.dimensionAlto = dimensionAlto;
	}

	/**
	 * @return the dimenesionLargo
	 */
	public double getDimenesionLargo() {
		return dimenesionLargo;
	}

	/**
	 * @param dimenesionLargo the dimenesionLargo to set
	 */
	public void setDimenesionLargo(double dimenesionLargo) {
		this.dimenesionLargo = dimenesionLargo;
	}

	/**
	 * @return the dimensionAncho
	 */
	public double getDimensionAncho() {
		return dimensionAncho;
	}

	/**
	 * @param dimensionAncho the dimensionAncho to set
	 */
	public void setDimensionAncho(double dimensionAncho) {
		this.dimensionAncho = dimensionAncho;
	}

	public Mascota getListaMascotas() {
		return listaMascotas;
	}

	public void setListaMascotas(Mascota listaMascotas) {
		this.listaMascotas = listaMascotas;
	}

	public Equipaje(double peso, double dimensionAlto, double dimenesionLargo, double dimensionAncho) {
		super();
		this.peso = peso;
		this.dimensionAlto = dimensionAlto;
		this.dimenesionLargo = dimenesionLargo;
		this.dimensionAncho = dimensionAncho;
		this.listaMascotas = null;
	}

	public Equipaje(double peso, double dimensionAlto, double dimenesionLargo, double dimensionAncho,
			Mascota listaMascotas) {
		super();
		this.peso = peso;
		this.dimensionAlto = dimensionAlto;
		this.dimenesionLargo = dimenesionLargo;
		this.dimensionAncho = dimensionAncho;
		this.listaMascotas = listaMascotas;
	}
}
