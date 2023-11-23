package Models;

import java.io.Serializable;

public enum TipoModalidad implements Serializable{

	NACIONAL(0), INTERNACIONAL(1);

	private int tipoModalidad;

	/**
	 * @return the tipoModalidad
	 */
	public int getTipoModalidad() {
		return tipoModalidad;
	}

	/**
	 * @param tipoModalidad the tipoModalidad to set
	 */
	public void setTipoModalidad(int tipoModalidad) {
		this.tipoModalidad = tipoModalidad;
	}

	/**
	 * @param tipoModalidad
	 */
	private TipoModalidad(int tipoModalidad) {
		this.tipoModalidad = tipoModalidad;
	}

	public TipoModalidad obtenerTipoModalidad(int index){

		switch (index) {
		case 0: return TipoModalidad.NACIONAL;
		case 2: return TipoModalidad.INTERNACIONAL;

		default: return null;
		}
	}


}
