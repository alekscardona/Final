package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Cliente extends Persona implements Serializable, Comparable<Cliente>, Comparator<Cliente>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ArrayList<Tarjeta> tarjeta;

	public Equipaje equipaje;
	/**
	 * @return the tarjeta
	 */
	public ArrayList<Tarjeta> getTarjeta() {
		return tarjeta;
	}
	/**
	 * @param tarjeta the tarjeta to set
	 */
	public void setTarjeta(ArrayList<Tarjeta> tarjeta) {
		this.tarjeta = tarjeta;
	}
	/**
	 * @return the listaEquipajeCliente
	 */

	/**
	 * @return the listaTiquetesCliente
	 */

	public Equipaje getEquipaje() {
		return equipaje;
	}
	public void setEquipaje(Equipaje equipaje) {
		this.equipaje = equipaje;
	}
	/**
	 * @param listaTiquetesCliente the listaTiquetesCliente to set
	 */
	
	public Cliente(String nombre, String apellido, String direccion, String correo, String identificacion,
			String fechaNacimiento, ArrayList<Tarjeta> tarjeta) {
		super(nombre, apellido, direccion, correo, identificacion, fechaNacimiento);
		this.tarjeta = tarjeta;
	}
	@Override
	public int compareTo(Cliente arg0) {
		int identificacionA = Integer.parseInt(this.identificacion);
		return identificacionA - Integer.parseInt(arg0.getIdentificacion());
	}
	@Override
	public int compare(Cliente arg0, Cliente arg1) {
		if(Integer.parseInt(arg0.getIdentificacion())>Integer.parseInt(arg1.getIdentificacion())){
			return 1;
		}
		if(Integer.parseInt(arg0.getIdentificacion())<Integer.parseInt(arg1.getIdentificacion())){
			return -1;
		}
		return 0;
	}


}
