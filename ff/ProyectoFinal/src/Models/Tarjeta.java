package Models;

import java.io.Serializable;

public class Tarjeta implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public double saldo;
	String codigo, nombre, numeros;
	public TipoTarjeta tipo;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeros() {
		return numeros;
	}
	public void setNumeros(String numeros) {
		this.numeros = numeros;
	}
	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	/**
	 * @return the tipo
	 */
	public TipoTarjeta getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoTarjeta tipo) {
		this.tipo = tipo;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tarjeta [saldo=" + saldo + ", tipo=" + tipo + "]";
	}
	/**
	 * @param saldo
	 * @param tipo
	 */
	public Tarjeta(double saldo, String codigo, String nombre, String numeros, TipoTarjeta tipo) {
		super();
		this.saldo = saldo;
		this.codigo = codigo;
		this.nombre = nombre;
		this.numeros = numeros;
		this.tipo = tipo;
	}



}
