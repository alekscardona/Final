package Models;

import java.io.Serializable;

public enum TipoTarjeta implements Serializable{

	DEBITO(0), CREDITO(1);

	private int tipoTarjeta;											//Declaracion de la variable local para escoger el Tipo 0/1

	/**
	 * @return the tipoTarjeta
	 */
	public int getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * @param tipoTarjeta the tipoTarjeta to set
	 */
	public void setTipoTarjeta(int tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	/**
	 * @param tipoTarjeta
	 */
	private TipoTarjeta(int tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public TipoTarjeta obtenerTipoTarjeta(int index){

		switch (index) {
		case 0: return TipoTarjeta.DEBITO;
		case 2: return TipoTarjeta.CREDITO;

		default: return null;
		}
	}


}
