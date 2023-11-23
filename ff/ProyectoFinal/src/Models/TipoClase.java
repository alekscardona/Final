package Models;

import java.io.Serializable;

public enum TipoClase  implements Serializable{

	ECONOMICA(0), EJECUTIVA(1);

	private int tipoClase;

	/**
	 * @return the tipoClase
	 */
	public int getTipoClase() {
		return tipoClase;
	}

	/**
	 * @param tipoClase the tipoClase to set
	 */
	public void setTipoClase(int tipoClase) {
		this.tipoClase = tipoClase;
	}

	/**
	 * @param tipoClase
	 */
	private TipoClase(int tipoClase) {
		this.tipoClase = tipoClase;
	}


	public TipoClase obtenerTipoEstado(int index){

		switch (index) {
		case 0: return TipoClase.ECONOMICA;
		case 1: return TipoClase.EJECUTIVA;
		default: return null;
		}
	}
}
