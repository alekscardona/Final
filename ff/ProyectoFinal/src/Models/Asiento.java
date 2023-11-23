package Models;

import java.io.Serializable;

public class Asiento implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int numeroAsiento;
	public String letraAsiento;
	private TipoClase tipoClase;
	private boolean ocupado;



	public boolean isOcupado() {
		return ocupado;
	}



	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}



	public TipoClase getTipoClase() {
		return tipoClase;
	}



	public void setTipoClase(TipoClase tipoClase) {
		this.tipoClase = tipoClase;
	}



	public int getNumeroAsiento() {
		return numeroAsiento;
	}



	public void setNumeroAsiento(int numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}



	/**
	 * @param letraAsiento
	 */
	public Asiento(String letraAsiento) {
		super();
		this.letraAsiento = letraAsiento;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Asiento [letraAsiento=" + letraAsiento + "]";
	}



	/**
	 * @return the letraAsiento
	 */
	public String getLetraAsiento() {
		return letraAsiento;
	}



	/**
	 * @param letraAsiento the letraAsiento to set
	 */
	public void setLetraAsiento(String letraAsiento) {
		this.letraAsiento = letraAsiento;
	}



	/**
	 * constructor vacio
	 */
	 public Asiento() {
		// TODO Auto-generated constructor stub
	}
}
