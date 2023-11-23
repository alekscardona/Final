package Models;

import java.io.Serializable;

import ListasGenericas.ListaSimple;

public class Aeronave implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public String nombre;
	public double capacidadCarga;
	public ListaSimple<Asiento> listaAsientosAeronave = new ListaSimple<>();


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getCapacidadCarga() {
		return capacidadCarga;
	}
	public void setCapacidadCarga(double capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}
	public ListaSimple<Asiento> getListaAsientosAeronave() {
		return listaAsientosAeronave;
	}
	public void setListaAsientosAeronave(ListaSimple<Asiento> listaAsientosAeronave) {
		this.listaAsientosAeronave = listaAsientosAeronave;
	}

	@Override
	public String toString() {
		return "Aeronave [nombre=" + nombre + ", capacidadCarga=" + capacidadCarga +", listaAsientosAeronave=" + listaAsientosAeronave + "]";
	}
	/**
	 * @param nombre
	 * @param capacidadCarga
	 * @param capacidadAsientos
	 * @param listaAsientosAeronave
	 * @param listaVuelosAeronave
	 */
	public Aeronave(String nombre, double capacidadCarga, ListaSimple<Asiento> listaAsientosAeronave) {
		super();
		this.nombre = nombre;
		this.capacidadCarga = capacidadCarga;
		this.listaAsientosAeronave = listaAsientosAeronave;
	}
}
