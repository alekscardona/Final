package Controllers.Auxiliar;

import Models.TipoClase;
import javafx.scene.control.Button;

/**
 * clase para los asientos seleccionados
 *cambia de color de a cuerdo a la seleccion
 * @author manur
 *
 */
public class BotonAsiento {
	private Button boton;
	private TipoClase tipoClase;
	boolean disponibilidad;
	boolean seleccionado;
	String letra;
	int numero;

	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public Button getBoton() {
		return boton;
	}
	public void setBoton(Button boton) {
		this.boton = boton;
	}

	public TipoClase getTipoClase() {
		return tipoClase;
	}
	public void setTipoClase(TipoClase tipoClase) {
		this.tipoClase = tipoClase;
	}

	public BotonAsiento(Button boton, TipoClase tipoClase, boolean disponibilidad, String letra,
			int numero) {
		super();
		this.boton = boton;
		this.tipoClase = tipoClase;
		this.disponibilidad = disponibilidad;
		this.letra = letra;
		this.numero = numero;
		cambiarColor();
	}
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	/**
	 * Metodo que permite el cambio de color de acuerdo a a la seleccion
	 */
	public void cambiarColor(){
		if(disponibilidad == true && seleccionado == false){
			this.boton.setStyle("-fx-background-color: "+buscarColor()+";");
		}
		if(seleccionado == true){
			this.boton.setStyle("-fx-background-color: YELLOW;");
		}
		if(disponibilidad==false){
			this.boton.setStyle("-fx-background-color: RED;");
		}
	}
	
	/**
	 * metodo para obtener el color de acuerdo a el tipo de clase seleccionada
	 * @return
	 */
	private String buscarColor(){
		if(tipoClase.equals(TipoClase.ECONOMICA)){
			return "BROWN";
		}
		if(tipoClase.equals(TipoClase.EJECUTIVA)){
			return "BLUE";
		}
		return null;

	}
	
	/**
	 * Metodo para seleccionar asientos
	 * cambia a color rojo
	 */
	public void seleccionarSientos(){
		if(seleccionado==true){
			boton.setStyle("-fx-background-color: RED;");
			disponibilidad = false;
		}
	}
}
