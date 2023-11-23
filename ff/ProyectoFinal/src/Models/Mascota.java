package Models;

import java.io.Serializable;

public class Mascota implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public double pesoMascota;

	/**
	 * @return the pesoMascota
	 */
	public double getPesoMascota() {
		return pesoMascota;
	}

	/**
	 * @param pesoMascota the pesoMascota to set
	 */
	public void setPesoMascota(double pesoMascota) {
		this.pesoMascota = pesoMascota;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mascota [pesoMascota=" + pesoMascota + "]";
	}

	/**
	 * @param pesoMascota
	 */
	public Mascota(double pesoMascota) {
		super();
		this.pesoMascota = pesoMascota;
	}

}


