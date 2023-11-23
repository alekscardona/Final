package Models;

import java.io.Serializable;

public class Ciudad implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public String nombre;

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public Ciudad(String nombre) {
		super();
		this.nombre = nombre;
	}
	/**
	 * constructor vacio
	 */
	public Ciudad() {
		// TODO Auto-generated constructor stub
	}


}
