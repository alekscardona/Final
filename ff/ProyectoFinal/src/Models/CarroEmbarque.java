package Models;

import ListasGenericas.ListaSimple;

public class CarroEmbarque {

	public String numero;
	public double capacidadCarga;

	public ListaSimple<Equipaje> listaEquipajeCarroEmbarque = new ListaSimple<>();

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the capacidadCarga
	 */
	public double getCapacidadCarga() {
		return capacidadCarga;
	}

	/**
	 * @param capacidadCarga the capacidadCarga to set
	 */
	public void setCapacidadCarga(double capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}

	/**
	 * @return the listaEquipajeCarroEmbarque
	 */
	public ListaSimple<Equipaje> getListaEquipajeCarroEmbarque() {
		return listaEquipajeCarroEmbarque;
	}

	/**
	 * @param listaEquipajeCarroEmbarque the listaEquipajeCarroEmbarque to set
	 */
	public void setListaEquipajeCarroEmbarque(ListaSimple<Equipaje> listaEquipajeCarroEmbarque) {
		this.listaEquipajeCarroEmbarque = listaEquipajeCarroEmbarque;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarroEmbarque [numero=" + numero + ", capacidadCarga=" + capacidadCarga
				+ ", listaEquipajeCarroEmbarque=" + listaEquipajeCarroEmbarque + "]";
	}

	/**
	 * @param numero
	 * @param capacidadCarga
	 * @param listaEquipajeCarroEmbarque
	 */
	public CarroEmbarque(String numero, double capacidadCarga, ListaSimple<Equipaje> listaEquipajeCarroEmbarque) {
		super();
		this.numero = numero;
		this.capacidadCarga = capacidadCarga;
		this.listaEquipajeCarroEmbarque = listaEquipajeCarroEmbarque;
	}




}
