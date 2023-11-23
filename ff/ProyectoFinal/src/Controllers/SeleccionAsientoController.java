package Controllers;

import java.io.FileInputStream;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controllers.Auxiliar.BotonAsiento;
import ListasGenericas.ListaSimple;
import Models.Aeronave;
import Models.Asiento;
import Models.Ruta;
import Models.TipoClase;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Clase controller especifica para el manejo de la seleccion de asientos
 * por medio de interfaz grafica
 * @author manur
 *
 */
public class SeleccionAsientoController {

	
//// Declaracion de botones y elementos de la interfaz 
	
	 @FXML
	 private Button btnSeleccionar;

	 @FXML
	 private ResourceBundle resources;

	 @FXML
	 private URL location;

	 @FXML
	 private GridPane gridEconomicoA;

	 @FXML
     private GridPane gridEconomicoB;

	 @FXML
	 private GridPane gridEjecutivoA;

	 @FXML
	 private GridPane gridEjecutivoB;

	 @FXML
     private GridPane gridEconomico1;
	 @FXML
     private GridPane gridEconomico2;
	 @FXML
     private GridPane gridEconomico3;

	 @FXML
     private GridPane gridEconomico;
	 @FXML
	 private GridPane gridEjecutivo;

	 
	ListaSimple<Asiento> listaAsientos; //uso de ListaSimple para la lista de asientos

	ArrayList<Asiento> asientosSeleccionados = new ArrayList<>();

	/**
	 * Declaracion de variables
	 */
	int contadorPersonas;
	int maxPersonas;

	private Main main;

	/**
	 * metodo para inicializar interfaz y datos
	 */
	@FXML
	public
	  void initialize() {
		try{
	        assert gridEconomicoA != null : "fx:id=\"gridEconomicoA\" was not injected: check your FXML file 'seleccionAsientoView.fxml'.";
	        assert gridEconomicoB != null : "fx:id=\"gridEconomicoB\" was not injected: check your FXML file 'seleccionAsientoView.fxml'.";
	        assert gridEjecutivoA != null : "fx:id=\"gridEjecutivoA\" was not injected: check your FXML file 'seleccionAsientoView.fxml'.";
	        assert gridEjecutivoB != null : "fx:id=\"gridEjecutivoB\" was not injected: check your FXML file 'seleccionAsientoView.fxml'.";
		}catch(NullPointerException e){

		}
	  }

	public void setAplicacion(Main main) {
		this.main = main;

	}
	
	/**
	 * Metodo que lanza el evento del boton seleccionar
	 * @param event
	 */
	@FXML
	void actnBtnSeleccionar(ActionEvent event) {
		Stage stage = (Stage) this.btnSeleccionar.getScene().getWindow();
		stage.close();
	}

	
	/**
	 * Metodo para la creacion de una eronave y sus asientos para seleccionarlos
	 * hace uso de las flotas, lista de asientos
	 *  y valida la clase 
	 * @param personas
	 * @param tipoClase
	 */
	@SuppressWarnings("unchecked")
	public void initAirbusA320_1(int personas, TipoClase tipoClase){
		try {
			ArrayList<Aeronave> listaFlotas = new ArrayList<>();


			ObjectInputStream flotasArchivo = new ObjectInputStream(new FileInputStream("src/archivos/flotas.dat"));
			listaFlotas = (ArrayList<Aeronave>) flotasArchivo.readObject();
			flotasArchivo.close();

			listaAsientos = listaFlotas.get(0).getListaAsientosAeronave();


		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contadorPersonas = personas;
		maxPersonas = personas;
		int contador = 0;
		ArrayList<BotonAsiento> botonAsientoLista = new ArrayList<>();
		try{

			boolean disponibilidad;
			for (int i = 0; i < listaAsientos.getTamanio(); i++) {

				Button boton = new Button("  ");

				Asiento asiento = listaAsientos.obtenerValorNodo(i);

				if(asiento.isOcupado()){
					disponibilidad=false;
				}else{
					disponibilidad=true;
				}

				BotonAsiento botonAsiento = new BotonAsiento(boton, asiento.getTipoClase(), disponibilidad, asiento.getLetraAsiento(),asiento.getNumeroAsiento());
				botonAsientoLista.add(botonAsiento);
				if(asiento.getLetraAsiento().equalsIgnoreCase("A")){

					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoA.add(botonAsientoLista.get(i).getBoton(), contador,0);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoA.add(botonAsientoLista.get(i).getBoton(), contador,0);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("B")){
					if(contador>5){
						contador=0;
					}
						gridEconomicoA.add(botonAsientoLista.get(i).getBoton(), contador,1);
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("C")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoA.add(botonAsientoLista.get(i).getBoton(), contador,1);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoA.add(botonAsientoLista.get(i).getBoton(), contador,2);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("D")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,0);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoB.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("E")){
					if(contador>5){
						contador=0;
					}
					gridEconomicoB.add(botonAsientoLista.get(i).getBoton(), contador,1);
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("F")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,1);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoB.add(botonAsientoLista.get(i).getBoton(), contador,2);
						}
					}

				}

				for (int j = 0; j < botonAsientoLista.size(); j++) {
					int o=j;
					int auxO=o;
					botonAsientoLista.get(j).getBoton().setOnAction(a -> {
						  if(botonAsientoLista.get(auxO).isDisponibilidad()&&contadorPersonas>0&&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
								  &&!botonAsientoLista.get(auxO).isSeleccionado()&&contadorPersonas<=maxPersonas){

							  botonAsientoLista.get(auxO).setSeleccionado(true);
							  botonAsientoLista.get(auxO).cambiarColor();
							  this.contadorPersonas--;
							  System.out.println(auxO);

							  Asiento asientoSeleccionado = listaAsientos.obtenerValorNodo(auxO);
							  this.asientosSeleccionados.add(asientoSeleccionado);

						  }else{
							  if(botonAsientoLista.get(auxO).isDisponibilidad() &&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
									  &&botonAsientoLista.get(auxO).isSeleccionado()){

								  botonAsientoLista.get(auxO).setSeleccionado(false);
							  	  botonAsientoLista.get(auxO).cambiarColor();
							  	  this.contadorPersonas++;
							  	  for (int k = 0; k < asientosSeleccionados.size(); k++) {
									if(listaAsientos.obtenerValorNodo(auxO)==asientosSeleccionados.get(k)){
										this.asientosSeleccionados.remove(k);
									}
								}

							  }
						  }

						  });
				}

				contador++;
			}

		}catch(IllegalArgumentException e){

		}
	}
	
	
	/**
	 * Metodo para la creacion de una eronave y sus asientos para seleccionarlos
	 * hace uso de las flotas, lista de asientos
	 *  y valida la clase 
	 * @param personas
	 * @param tipoClase
	 */
	/*
	 * se utiliza en Java para indicar al compilador que ignore las advertencias relacionadas con 
	 * operaciones de unchecked (sin comprobación de tipo) que pueden ocurrir durante la ejecución 
	 * de un programa. En este contexto, "unchecked" se refiere a las advertencias que surgen cuando 
	 * se realiza una operación que no puede ser verificada en tiempo de compilación en términos de tipos.
	 */
	@SuppressWarnings("unchecked")
	public void initAirbusA320_2(int personas, TipoClase tipoClase){
		try {
			ArrayList<Aeronave> listaFlotas = new ArrayList<>();


			ObjectInputStream flotasArchivo = new ObjectInputStream(new FileInputStream("src/archivos/flotas.dat"));
			listaFlotas = (ArrayList<Aeronave>) flotasArchivo.readObject();
			flotasArchivo.close();

			listaAsientos = listaFlotas.get(1).getListaAsientosAeronave();


		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contadorPersonas = personas;
		maxPersonas = personas;
		int contador = 0;
		ArrayList<BotonAsiento> botonAsientoLista = new ArrayList<>();
		try{

			boolean disponibilidad;
			for (int i = 0; i < listaAsientos.getTamanio(); i++) {

				Button boton = new Button("  ");

				Asiento asiento = listaAsientos.obtenerValorNodo(i);

				if(asiento.isOcupado()){
					disponibilidad=false;
				}else{
					disponibilidad=true;
				}

				BotonAsiento botonAsiento = new BotonAsiento(boton, asiento.getTipoClase(), disponibilidad, asiento.getLetraAsiento(),asiento.getNumeroAsiento());
				botonAsientoLista.add(botonAsiento);
				if(asiento.getLetraAsiento().equalsIgnoreCase("A")){

					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoA.add(botonAsientoLista.get(i).getBoton(), contador,0);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoA.add(botonAsientoLista.get(i).getBoton(), contador,0);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("B")){
					if(contador>5){
						contador=0;
					}
						gridEconomicoA.add(botonAsientoLista.get(i).getBoton(), contador,1);
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("C")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoA.add(botonAsientoLista.get(i).getBoton(), contador,1);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoA.add(botonAsientoLista.get(i).getBoton(), contador,2);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("D")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,0);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoB.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("E")){
					if(contador>5){
						contador=0;
					}
					gridEconomicoB.add(botonAsientoLista.get(i).getBoton(), contador,1);
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("F")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>3){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,1);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>5){
								contador=0;
							}
							gridEconomicoB.add(botonAsientoLista.get(i).getBoton(), contador,2);
						}
					}

				}

				for (int j = 0; j < botonAsientoLista.size(); j++) {
					int o=j;
					int auxO=o;
					botonAsientoLista.get(j).getBoton().setOnAction(a -> {
						  if(botonAsientoLista.get(auxO).isDisponibilidad()&&contadorPersonas>0&&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
								  &&!botonAsientoLista.get(auxO).isSeleccionado()&&contadorPersonas<=maxPersonas){

							  botonAsientoLista.get(auxO).setSeleccionado(true);
							  botonAsientoLista.get(auxO).cambiarColor();
							  this.contadorPersonas--;
							  System.out.println(auxO);

							  Asiento asientoSeleccionado = listaAsientos.obtenerValorNodo(auxO);
							  this.asientosSeleccionados.add(asientoSeleccionado);

						  }else{
							  if(botonAsientoLista.get(auxO).isDisponibilidad() &&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
									  &&botonAsientoLista.get(auxO).isSeleccionado()){

								  botonAsientoLista.get(auxO).setSeleccionado(false);
							  	  botonAsientoLista.get(auxO).cambiarColor();
							  	  this.contadorPersonas++;
							  	  for (int k = 0; k < asientosSeleccionados.size(); k++) {
									if(listaAsientos.obtenerValorNodo(auxO)==asientosSeleccionados.get(k)){
										this.asientosSeleccionados.remove(k);
									}
								}

							  }
						  }

						  });
				}

				contador++;
			}

		}catch(IllegalArgumentException e){

		}
	}
	/**
	 * metodo get de la lista de asientos seleccionados
	 * @return
	 */
	public ArrayList<Asiento> getAsientosSeleccionados() {
		return asientosSeleccionados;
	}
	
	/**
	 * metodo set de la lista de asientos seleccionados
	 * @return
	 */
	public void setAsientosSeleccionados(ArrayList<Asiento> asientosSeleccionados) {
		this.asientosSeleccionados = asientosSeleccionados;
	}

	/**
	 * Metodo que establece los asientos y la seleccion de estos
	 * para la tercer aeronave
	 * @param personas
	 * @param tipoClase
	 */
	@SuppressWarnings("unchecked")
	public void initAirbusA330_1(int personas, TipoClase tipoClase) {
		try {
			ArrayList<Aeronave> listaFlotas = new ArrayList<>();


			ObjectInputStream flotasArchivo = new ObjectInputStream(new FileInputStream("src/archivos/flotas.dat"));
			listaFlotas = (ArrayList<Aeronave>) flotasArchivo.readObject();
			flotasArchivo.close();

			listaAsientos = listaFlotas.get(2).getListaAsientosAeronave();


		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contadorPersonas = personas;
		maxPersonas = personas;
		int contador = 0;
		ArrayList<BotonAsiento> botonAsientoLista = new ArrayList<>();
		try{

			boolean disponibilidad;
			for (int i = 0; i < listaAsientos.getTamanio(); i++) {

				Button boton = new Button("  ");

				Asiento asiento = listaAsientos.obtenerValorNodo(i);

				if(asiento.isOcupado()){
					disponibilidad=false;
				}else{
					disponibilidad=true;
				}

				BotonAsiento botonAsiento = new BotonAsiento(boton, asiento.getTipoClase(), disponibilidad, asiento.getLetraAsiento(),asiento.getNumeroAsiento());
				botonAsientoLista.add(botonAsiento);
				if(asiento.getLetraAsiento().equalsIgnoreCase("A")){

					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,0);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico1.add(botonAsientoLista.get(i).getBoton(), contador,0);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("C")){
					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,1);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico1.add(botonAsientoLista.get(i).getBoton(), contador,1);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("D")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador, 2);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico2.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("F")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,3);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico2.add(botonAsientoLista.get(i).getBoton(), contador,1);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("H")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,4);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico3.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("K")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,5);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico3.add(botonAsientoLista.get(i).getBoton(), contador,1);
						}
					}

				}

				for (int j = 0; j < botonAsientoLista.size(); j++) {
					int o=j;
					int auxO=o;
					botonAsientoLista.get(j).getBoton().setOnAction(a -> {
						  if(botonAsientoLista.get(auxO).isDisponibilidad()&&contadorPersonas>0&&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
								  &&!botonAsientoLista.get(auxO).isSeleccionado()&&contadorPersonas<=maxPersonas){

							  botonAsientoLista.get(auxO).setSeleccionado(true);
							  botonAsientoLista.get(auxO).cambiarColor();
							  this.contadorPersonas--;
							  System.out.println(auxO);

							  Asiento asientoSeleccionado = listaAsientos.obtenerValorNodo(auxO);
							  this.asientosSeleccionados.add(asientoSeleccionado);

						  }else{
							  if(botonAsientoLista.get(auxO).isDisponibilidad() &&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
									  &&botonAsientoLista.get(auxO).isSeleccionado()){

								  botonAsientoLista.get(auxO).setSeleccionado(false);
							  	  botonAsientoLista.get(auxO).cambiarColor();
							  	  this.contadorPersonas++;
							  	  for (int k = 0; k < asientosSeleccionados.size(); k++) {
									if(listaAsientos.obtenerValorNodo(auxO)==asientosSeleccionados.get(k)){
										this.asientosSeleccionados.remove(k);
									}
								}

							  }
						  }

						  });
				}

				contador++;
			}

		}catch(IllegalArgumentException e){

		}
	}
	
	
	/**
	 * Metodo que establece los asientos y la seleccion de estos
	 * para la cuarta aeronave
	 * @param personas
	 * @param tipoClase
	 */
	@SuppressWarnings("unchecked")
	public void initAirbusA330_2(int personas, TipoClase tipoClase) {
		try {
			ArrayList<Aeronave> listaFlotas = new ArrayList<>();


			ObjectInputStream flotasArchivo = new ObjectInputStream(new FileInputStream("src/archivos/flotas.dat"));
			listaFlotas = (ArrayList<Aeronave>) flotasArchivo.readObject();
			flotasArchivo.close();

			listaAsientos = listaFlotas.get(3).getListaAsientosAeronave();


		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contadorPersonas = personas;
		maxPersonas = personas;
		int contador = 0;
		ArrayList<BotonAsiento> botonAsientoLista = new ArrayList<>();
		try{

			boolean disponibilidad;
			for (int i = 0; i < listaAsientos.getTamanio(); i++) {

				Button boton = new Button("  ");

				Asiento asiento = listaAsientos.obtenerValorNodo(i);

				if(asiento.isOcupado()){
					disponibilidad=false;
				}else{
					disponibilidad=true;
				}

				BotonAsiento botonAsiento = new BotonAsiento(boton, asiento.getTipoClase(), disponibilidad, asiento.getLetraAsiento(),asiento.getNumeroAsiento());
				botonAsientoLista.add(botonAsiento);
				if(asiento.getLetraAsiento().equalsIgnoreCase("A")){

					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,0);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico1.add(botonAsientoLista.get(i).getBoton(), contador,0);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("C")){
					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,1);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico1.add(botonAsientoLista.get(i).getBoton(), contador,1);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("D")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador, 2);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico2.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("F")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,3);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico2.add(botonAsientoLista.get(i).getBoton(), contador,1);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("H")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,4);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico3.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("K")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,5);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico3.add(botonAsientoLista.get(i).getBoton(), contador,1);
						}
					}

				}

				for (int j = 0; j < botonAsientoLista.size(); j++) {
					int o=j;
					int auxO=o;
					botonAsientoLista.get(j).getBoton().setOnAction(a -> {
						  if(botonAsientoLista.get(auxO).isDisponibilidad()&&contadorPersonas>0&&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
								  &&!botonAsientoLista.get(auxO).isSeleccionado()&&contadorPersonas<=maxPersonas){

							  botonAsientoLista.get(auxO).setSeleccionado(true);
							  botonAsientoLista.get(auxO).cambiarColor();
							  this.contadorPersonas--;
							  System.out.println(auxO);

							  Asiento asientoSeleccionado = listaAsientos.obtenerValorNodo(auxO);
							  this.asientosSeleccionados.add(asientoSeleccionado);

						  }else{
							  if(botonAsientoLista.get(auxO).isDisponibilidad() &&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
									  &&botonAsientoLista.get(auxO).isSeleccionado()){

								  botonAsientoLista.get(auxO).setSeleccionado(false);
							  	  botonAsientoLista.get(auxO).cambiarColor();
							  	  this.contadorPersonas++;
							  	  for (int k = 0; k < asientosSeleccionados.size(); k++) {
									if(listaAsientos.obtenerValorNodo(auxO)==asientosSeleccionados.get(k)){
										this.asientosSeleccionados.remove(k);
									}
								}

							  }
						  }

						  });
				}

				contador++;
			}

		}catch(IllegalArgumentException e){

		}
	}
	
	/**
	 * Metodo que establece los asientos y la seleccion de estos
	 * para la quinta aeronave
	 * @param personas
	 * @param tipoClase
	 */
	@SuppressWarnings("unchecked")
	public void initBoeing787_1(int personas, TipoClase tipoClase) {
		try {
			ArrayList<Aeronave> listaFlotas = new ArrayList<>();

			ObjectInputStream flotasArchivo = new ObjectInputStream(new FileInputStream("src/archivos/flotas.dat"));
			listaFlotas = (ArrayList<Aeronave>) flotasArchivo.readObject();
			flotasArchivo.close();

			listaAsientos = listaFlotas.get(4).getListaAsientosAeronave();


		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		contadorPersonas = personas;
		maxPersonas = personas;
		int contador = 0;
		ArrayList<BotonAsiento> botonAsientoLista = new ArrayList<>();
		try{

			boolean disponibilidad;
			for (int i = 0; i < listaAsientos.getTamanio(); i++) {

				Button boton = new Button("  ");

				Asiento asiento = listaAsientos.obtenerValorNodo(i);

				if(asiento.isOcupado()){
					disponibilidad=false;
				}else{
					disponibilidad=true;
				}

				BotonAsiento botonAsiento = new BotonAsiento(boton, asiento.getTipoClase(), disponibilidad, asiento.getLetraAsiento(),asiento.getNumeroAsiento());
				botonAsientoLista.add(botonAsiento);
				if(asiento.getLetraAsiento().equalsIgnoreCase("A")){

					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,0);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico1.add(botonAsientoLista.get(i).getBoton(), contador,0);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("C")){
					if(asiento.getTipoClase().equals(TipoClase.EJECUTIVA)){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,1);

					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico1.add(botonAsientoLista.get(i).getBoton(), contador,1);

						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("D")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador, 2);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico2.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("F")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,3);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico2.add(botonAsientoLista.get(i).getBoton(), contador,1);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("H")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,4);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico3.add(botonAsientoLista.get(i).getBoton(), contador,0);
						}
					}
				}
				if(asiento.getLetraAsiento().equalsIgnoreCase("K")){
					if(asiento.getTipoClase() == TipoClase.EJECUTIVA){
						if(contador>5){
							contador=0;
						}
						gridEjecutivoB.add(botonAsientoLista.get(i).getBoton(), contador,5);
					}else{
						if(asiento.getTipoClase() == TipoClase.ECONOMICA){
							if(contador>8){
								contador=0;
							}
							gridEconomico3.add(botonAsientoLista.get(i).getBoton(), contador,1);
						}
					}

				}

				for (int j = 0; j < botonAsientoLista.size(); j++) {
					int o=j;
					int auxO=o;
					botonAsientoLista.get(j).getBoton().setOnAction(a -> {
						  if(botonAsientoLista.get(auxO).isDisponibilidad()&&contadorPersonas>0&&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
								  &&!botonAsientoLista.get(auxO).isSeleccionado()&&contadorPersonas<=maxPersonas){

							  botonAsientoLista.get(auxO).setSeleccionado(true);
							  botonAsientoLista.get(auxO).cambiarColor();
							  this.contadorPersonas--;
							  System.out.println(auxO);

							  Asiento asientoSeleccionado = listaAsientos.obtenerValorNodo(auxO);
							  this.asientosSeleccionados.add(asientoSeleccionado);

						  }else{
							  if(botonAsientoLista.get(auxO).isDisponibilidad() &&botonAsientoLista.get(auxO).getTipoClase().equals(tipoClase)
									  &&botonAsientoLista.get(auxO).isSeleccionado()){

								  botonAsientoLista.get(auxO).setSeleccionado(false);
							  	  botonAsientoLista.get(auxO).cambiarColor();
							  	  this.contadorPersonas++;
							  	  for (int k = 0; k < asientosSeleccionados.size(); k++) {
									if(listaAsientos.obtenerValorNodo(auxO)==asientosSeleccionados.get(k)){
										this.asientosSeleccionados.remove(k);
									}
								}

							  }
						  }

						  });
				}

				contador++;
			}

		}catch(IllegalArgumentException e){

		}
	}


}
