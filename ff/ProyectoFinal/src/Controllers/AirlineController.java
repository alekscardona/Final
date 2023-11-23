package Controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import Chat.ejecutables.ChatApp;
import ListasGenericas.ListaSimple;
import Models.Aerolinea;
import Models.Aeronave;
import Models.Asiento;
import Models.CarroEmbarque;
import Models.Cliente;
import Models.Equipaje;
import Models.Mascota;
import Models.Ruta;
import Models.Tarjeta;
import Models.TipoClase;
import Models.TipoModalidad;
import Models.TipoTarjeta;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Clase controlador de la clase Aerolinea que permite la conexion entre
 * la interfaz y las funcionalidades de la aplicacion ademas navegacion
 * de las clases, ejecucion e metodos, etc
 * @author Cardona
 *
 */
public class AirlineController {

	Aerolinea aerolinea = new Aerolinea();
	Ruta rutaSeleccionada;
	Aeronave aeronaveElegida;
	TipoModalidad tipoModalidad;
	Boolean compraReciente = false;
	List<CarroEmbarque> listaEmbarque = new ArrayList<>();
	private TreeSet<Cliente> listaUsuarios = new TreeSet<>();//Treeset para que se orden por numero de indentidad
	int precio;

//////////////////////////////////////////////////////////chat////////////////////////////////////////
	@FXML
	private void chat() {
		ChatApp a = new ChatApp();
		a.main(null);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	
	
	@FXML
    private TextField apellidoCliente;



    @FXML
    private Button aniadirTarjetaCliente;

    @FXML
    private Button aniadirTarjetaClienteBTN;

    @FXML
    private Button btnAceptarEquipaje;

    @FXML
    private Button btnAplicarPersonas;

    @FXML
    private Button btnAsientos;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCompraTiquetes;

    @FXML
    private Button btnEmbarqueEquipaje;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnMasPersonas;

    @FXML
    private Button btnMenosPersonas;

    @FXML
    private Button btnMenuPrincipal;

    @FXML
    private Button btnPersonas;

    @FXML
    private Button btnPortalADM;

    @FXML
    private TextField clienteCodigo;

    @FXML
    private ComboBox<String> comBoxClase;

    @FXML
    private ComboBox<String> comBoxDestino;

    @FXML
    private ComboBox<String> comBoxOrigen;

    @FXML
    private TextField correoCliente;

    @FXML
    private DatePicker datePIda;

    @FXML
    private DatePicker datePVuelta;

    @FXML
    private TextField direccionCliente;

    @FXML
    private DatePicker fechaNCliente;

    @FXML
    private Button finalizarCompra;

    @FXML
    private CheckBox idaYVuelta;

    @FXML
    private CheckBox mascotaInte;

    @FXML
    private TextField mascotaPeso;

    @FXML
    private TextField noTarjetaCliente;

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField nombreTarjetaCliente;

    @FXML
    private Pane paneCompraTiq;

    @FXML
    private Pane paneEquipaje;

    @FXML
    private Pane paneMascota;

    @FXML
    private Pane paneMenuP;
    @FXML
    private Pane embarque1;
    @FXML
    private Pane embarque2;
    @FXML
    private Pane embarque3;

    @FXML
    private Pane panePersonas;

    @FXML
    private Pane panePortalADM;

    @FXML
    private Pane paneRutas;

    @FXML
    private Pane paneTarjeta;

    @FXML
    private TextField personasCantidadTXT;

    @FXML
    private TextField peso;

    @FXML
    private Label precioLabel;

    @FXML
    private ChoiceBox<String> seleccionTarjetaCliente;

    @FXML
    private CheckBox soloIda;

    @FXML
    private TableColumn<Ruta, String> tabDestino;

    @FXML
    private TableColumn<Ruta, String> tabDuracion;

    @FXML
    private TableColumn<Ruta, String> tabHoraSalida;

    @FXML
    private TableColumn<Ruta, String> tabOrigen;

    @FXML
    private TableView<Ruta> tableRutas;

    @FXML
    private AnchorPane ventana;

    @FXML
    private Pane panePago;

    @FXML
    private GridPane gridEmbarque;

    @FXML
    private TextField anchoPeso;
    @FXML
    private TextField altoPeso;
    @FXML
    private TextField largoPeso;
    @FXML
    private TextField noIdentificacion;

	private Main main;

	ObservableList<Ruta> listaRutasData = FXCollections.observableArrayList();

	
	public void pagar() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AirbusA320view.fxml"));
    	Parent root1 = (Parent) fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));
    	SeleccionAsientoController asiento = fxmlLoader.getController();
    	asiento.initAirbusA320_1(Integer.parseInt(personasCantidadTXT.getText()), obtenerTipoClase(comBoxClase.getValue()));
    	stage.getIcons().add(new Image("/source/Logo.png"));
    	stage.initStyle(StageStyle.UNDECORATED);
    	stage.showAndWait();

		ListaSimple<Asiento> listaAsientos = new ListaSimple<>();
		for (int i = 0; i < asiento.getAsientosSeleccionados().size(); i++) {
			System.out.println(asiento.getAsientosSeleccionados().get(i).getLetraAsiento()+ asiento.getAsientosSeleccionados().get(i).getNumeroAsiento());
			for (int j = 0; j < listaAsientos.getTamanio(); j++) {

				if(asiento.getAsientosSeleccionados().get(i).getLetraAsiento().equalsIgnoreCase(listaAsientos.obtenerValorNodo(j).getLetraAsiento())
						&& asiento.getAsientosSeleccionados().get(i).getNumeroAsiento() == (listaAsientos.obtenerValorNodo(j).getNumeroAsiento())
						&& asiento.getAsientosSeleccionados().get(i).getTipoClase() == listaAsientos.obtenerValorNodo(j).getTipoClase()){
				}
			}
		}

		cerrarPanes();

		tipoModalidad = TipoModalidad.NACIONAL;
		pagarTiquetes();
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, "fallo");
	}
		}


	/**
	 * Metodo que retorna la accion al presionar clic en el boton de seleccion de asientos,

	 * valida las flotas, la ruta, el destino, lista de asientos, de aviones, etc la clase que se selecciona al volar y
	 *  establece los parametros de peso de equipaje con o sin mascota
	 *
	 * @param event
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NumberFormatException
	 */
	 @SuppressWarnings("unchecked")
    @FXML
    void actnAsientos(ActionEvent event) throws IOException, ClassNotFoundException, NumberFormatException {
    	   
    	

    	    if ((idaYVuelta.isSelected() && datePVuelta.getValue() == null) || comBoxClase.getValue() == null || datePIda.getValue() == null
    	            || (mascotaInte.isSelected() && mascotaPeso.getText().isEmpty()) || Integer.parseInt(personasCantidadTXT.getText()) < 1 || rutaSeleccionada == null
    	            || (Integer.parseInt(altoPeso.getText()) + Integer.parseInt(anchoPeso.getText()) + Integer.parseInt(altoPeso.getText()) > 170 && comBoxClase.getValue().equals("Ejecutiva"))
    	            || (Integer.parseInt(altoPeso.getText()) + Integer.parseInt(anchoPeso.getText()) + Integer.parseInt(altoPeso.getText()) > 110 && comBoxClase.getValue().equals("Economica"))) {

    	        if ((Integer.parseInt(altoPeso.getText()) + Integer.parseInt(anchoPeso.getText()) + Integer.parseInt(altoPeso.getText()) > 170 && comBoxClase.getValue().equals("Ejecutiva"))
    	                || (Integer.parseInt(altoPeso.getText()) + Integer.parseInt(anchoPeso.getText()) + Integer.parseInt(altoPeso.getText()) > 110 && comBoxClase.getValue().equals("Economica"))) {
    	            JOptionPane.showMessageDialog(null, "Las dimensiones del peso superan el límite");
    	        } else
    	            JOptionPane.showMessageDialog(null, "Seleccione toda la información que se solicita");

    		}else{
    		System.out.println(rutaSeleccionada.getDestino().getNombre());
    		if(rutaSeleccionada.getDestino().getNombre().equalsIgnoreCase("Monterrey")){
    			pagar();
    		}
    		if(rutaSeleccionada.getDestino().getNombre().equalsIgnoreCase("Cancun")){
    			pagar();
    		}
    		if(rutaSeleccionada.getDestino().getNombre().equalsIgnoreCase("Buenos Aires")){
    			pagar();
    			}
    		if(rutaSeleccionada.getDestino().getNombre().equalsIgnoreCase("Los Angeles")){
    			pagar();

    		}
    		if(rutaSeleccionada.getDestino().getNombre().equalsIgnoreCase("Bogota")){
    			pagar();
    		}
    	}
    }

    /**
     * Precio que de acuerdo a la modalidad de viaje, clase, mascota,
     * peso equipaje calcula y determina
     * el precio y el proceso de pago de tiquetes
     * @throws NumberFormatException
     */
    private void pagarTiquetes() throws NumberFormatException{
    	precio = 0;
    	panePago.setVisible(true);
    	paneTarjeta.setVisible(false);
    	if(tipoModalidad == TipoModalidad.NACIONAL && comBoxClase.getValue()=="Economica"){
    		int pesoA = Integer.parseInt(peso.getText());
    		if(pesoA>24){
    			precio += (pesoA-24)*8 +(((pesoA-24)*8)/6.75);
    			precioLabel.setText(precioLabel.getText()+"\n - Peso adicional: "+ (pesoA-24)+" kilos adicionales = "+((pesoA-24)*8)+"$");
    			precioLabel.setText(precioLabel.getText()+"\n - Impuestos por peso: "+ (((pesoA-24)*8)/6.75));
    		}
    	}
    	if(tipoModalidad == TipoModalidad.NACIONAL && comBoxClase.getValue()=="Ejecutiva"){
    		int pesoA = Integer.parseInt(peso.getText());
    		if(pesoA>68){
    			precio += (pesoA-68)*8+(((pesoA-68)*8)/6.75);
    			precioLabel.setText(precioLabel.getText()+"\n - Peso adicional: "+ (pesoA-68)+" kilos adicionales = "+((pesoA-68)*8)+"$");
    			precioLabel.setText(precioLabel.getText()+"\n - Impuestos por peso: "+ (((pesoA-68)*8)/6.75));
    		}
    	}
    	if(tipoModalidad == TipoModalidad.INTERNACIONAL && comBoxClase.getValue()=="Economica"){
    		int pesoA = Integer.parseInt(peso.getText());
    		if(pesoA>48){
    			precio += (pesoA-48)*8+(((pesoA-48)*8)/6.75);
    			precioLabel.setText(precioLabel.getText()+"\n - Peso adicional: "+ (pesoA-48)+" kilos adicionales = "+((pesoA-48)*8)+"$");
    			precioLabel.setText(precioLabel.getText()+"\n - Impuestos por peso: "+ (((pesoA-48)*8)/6.75));
    		}
    	}
    	if(tipoModalidad == TipoModalidad.INTERNACIONAL && comBoxClase.getValue()=="Ejecutiva"){
    		int pesoA = Integer.parseInt(peso.getText());
    		if(pesoA>68){
    			precio += (pesoA-68)*8+(((pesoA-68)*8)/6.75);
    			precioLabel.setText(precioLabel.getText()+"\n - Peso adicional: "+ (pesoA-68)+" kilos adicionales = "+((pesoA-68)*8)+"$");
    			precioLabel.setText(precioLabel.getText()+"\n - Impuestos por peso: "+ (((pesoA-68)*8)/6.75));
    		}

    	}

    	if(mascotaInte.isSelected()){
    		int pesoM = Integer.parseInt(mascotaPeso.getText());
    		precio+=48;
    		precioLabel.setText(precioLabel.getText()+"\n - Pago por mascotas: 48$");
    		if(pesoM>9){
    			precio+= (pesoM-9)*2;
    			precioLabel.setText(precioLabel.getText()+"\n - Pago por peso adicional mascotas: " + (pesoM-9)*2+"$");
    		}
    	}
    	if(tipoModalidad == TipoModalidad.NACIONAL){
    		precio+=800;
    		precioLabel.setText(precioLabel.getText()+"\n - Precio de vuelo nacional: 800$");
    		if(Integer.parseInt(personasCantidadTXT.getText())>1){
    			precio+=800*(Integer.parseInt(personasCantidadTXT.getText())-1);
    			precioLabel.setText(precioLabel.getText()+"\n - Precio de vuelo por persona adicional: "+(800*(Integer.parseInt(personasCantidadTXT.getText())-1)));
    		}
    	}
    	if(tipoModalidad == TipoModalidad.INTERNACIONAL){
    		precio+=1200;
    		precioLabel.setText(precioLabel.getText()+"\n - Precio de vuelo nacional: 1200$");
    		if(Integer.parseInt(personasCantidadTXT.getText())>1){
    			precio+=1200*(Integer.parseInt(personasCantidadTXT.getText())-1);
    			precioLabel.setText(precioLabel.getText()+"\n - Precio de vuelo por persona adicional: "+(1200*(Integer.parseInt(personasCantidadTXT.getText())-1)));
    		}
    	}
    	if(idaYVuelta.isSelected() && tipoModalidad == TipoModalidad.NACIONAL){
    		precio+=800;
    		precioLabel.setText(precioLabel.getText()+"\n - Precio por vuelta: 800$");
    		if(Integer.parseInt(personasCantidadTXT.getText())>1){
    			precio+=800*(Integer.parseInt(personasCantidadTXT.getText())-1);
    			precioLabel.setText(precioLabel.getText()+"\n - Precio de vuelo por persona adicional de vuelta: "+(800*(Integer.parseInt(personasCantidadTXT.getText())-1)));
    		}
    	}
    	if(tipoModalidad == TipoModalidad.INTERNACIONAL){
    		precio+=1200;
    		precioLabel.setText(precioLabel.getText()+"\n - Precio por vuelta: 1200$");
    		if(Integer.parseInt(personasCantidadTXT.getText())>1){
    			precio+=1200*(Integer.parseInt(personasCantidadTXT.getText())-1);
    			precioLabel.setText(precioLabel.getText()+"\n - Precio de vuelo por persona adicional de vuelta: "+(1200*(Integer.parseInt(personasCantidadTXT.getText())-1)));
    		}
    	}
    	precioLabel.setText(precioLabel.getText()+"\n - PRECIO TOTAL: "+precio+"$");

	}


    /**
     * Metodo para obtener el tipo de clase de acuerdo a lo que se seleccione
     * @param s
     * @return
     */
	private TipoClase obtenerTipoClase(String s){
    	if(s == "Economica"){
    		return TipoClase.ECONOMICA;
    	}else{
    		return TipoClase.EJECUTIVA;
    	}
    }


	/**
	 * boton salir
	 * @param event
	 */
    @FXML
    void ctnBtnExit(ActionEvent event) {
    	main.close();
    }


    @FXML
    void ctnAplicarPersonas(ActionEvent event) {
    	panePersonas.setVisible(false);
    }


    /**
     * metodo que por medio de la interfaz obtiene la lista de rutas
     * y busca en la tabla
     * @param event
     */
    @FXML
    void ctnBuscar(ActionEvent event) {
    	paneRutas.setVisible(true);
    	tableRutas.getItems().clear();
    	for (int i = 0; i < aerolinea.getListaRutas().size(); i++) {
			if(aerolinea.getListaRutas().get(i).getDestino().getNombre()== comBoxDestino.getValue()){
				listaRutasData.add(aerolinea.getListaRutas().get(i));
			}
		}
    	this.tabOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
    	this.tabDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
    	this.tabHoraSalida.setCellValueFactory(new PropertyValueFactory<>("horaSalida"));
    	this.tabDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
    	tableRutas.setItems(listaRutasData);

    }


    /**
     * metodo que hace visible la lista de rutas del archivo
     * @return
     */
    public ObservableList<Ruta> getListaRutasData() {
    	for (int i = 0; i < aerolinea.getListaRutas().size(); i++) {
			if(aerolinea.getListaRutas().get(i).getDestino().getNombre()== comBoxDestino.getValue()){
				listaRutasData.addAll(aerolinea.getListaRutas().get(i));
			}
		}
		return listaRutasData;
	}

    /**
     * boton interfaz compra tiquetes
     * @param event
     */
    @FXML
    void ctnCompraTiquetes(ActionEvent event) {
    	cerrarPanes();
    	paneCompraTiq.setVisible(true);
    }

    /**
     * interfaz para cambio a embarque de equipaje
     * @param event
     * @throws InterruptedException
     */

    @FXML
    void ctnEmbarqueEquipaje(ActionEvent event) throws InterruptedException {

        cerrarPanes();
        panePortalADM.setVisible(true);

        if (!listaEmbarque.isEmpty()) {
            List<Button> botones = new ArrayList<>();

            for (int i = 0; i < listaEmbarque.size() || i < 11; i++) {
                Button boton = new Button(" ");
                boton.setStyle("-fx-background-color: RED;");
                final NodeOrientation INHERIT = NodeOrientation.INHERIT;
                boton.nodeOrientationProperty().set(INHERIT);
                botones.add(boton);

                if (!listaEmbarque.isEmpty()) {
                    CarroEmbarque carro = listaEmbarque.remove(listaEmbarque.size() - 1);
                    botones.get(i).setUserData(carro); // Almacena el carro en el UserData del botï¿½n
                    gridEmbarque.add(botones.get(i), i, 0);
                }
            }

            for (int i = 0; i < botones.size(); i++) {
                Button boton = botones.get(i);
                CarroEmbarque carro = (CarroEmbarque) boton.getUserData();

                if (carro != null) {
                    boton.setStyle("-fx-background-color: WHITE;");
                    Thread.sleep(3000); // Espera de 3 segundos
                }
            }

            gridEmbarque.getChildren().removeAll(botones);
            botones.clear();
        }
    }
    /**
     *Boton para aumentar la cantidad de personas
     * @param event
     */
    @FXML
    void ctnMasPersonas(ActionEvent event) {
    	try{
    	if(Integer.parseInt(personasCantidadTXT.getText())>0 && Integer.parseInt(personasCantidadTXT.getText())<99){
    		int cantidad = Integer.parseInt(personasCantidadTXT.getText())+1;
        	personasCantidadTXT.setText(String.valueOf(cantidad));
    	}else{
    		personasCantidadTXT.setText("1");
    	}
    	}catch(NumberFormatException e){
    		personasCantidadTXT.setText("1");
    	}

    }

    /**
     * boton para disminutir la cantidad de personas
     * @param event
     */
    @FXML
    void ctnMenosPersonas(ActionEvent event) {
    	try{
        	if(Integer.parseInt(personasCantidadTXT.getText())>0 && Integer.parseInt(personasCantidadTXT.getText())<99){
        		int cantidad = Integer.parseInt(personasCantidadTXT.getText())-1;
            	personasCantidadTXT.setText(String.valueOf(cantidad));
        	}else{
        		personasCantidadTXT.setText("1");
        	}
        	}catch(NumberFormatException e){
        		personasCantidadTXT.setText("1");
        	}
    }


    /**
     * boton para volver al menu principal
     * @param event
     */
    @FXML
    void ctnMenuPrincipal(ActionEvent event) {
    	cerrarPanes();
    	paneMenuP.setVisible(true);


    }

    /**
     * boton para la carga de
     * seleccion de personas
     * @param event
     */
    @FXML
    void ctnPersonas(ActionEvent event) {

    	panePersonas.setVisible(true);
    }

    /**
     * metodo para limpiar interfaz
     */
    private void cerrarPanes(){
    	panePortalADM.setVisible(false);
    	panePersonas.setVisible(false);
    	paneCompraTiq.setVisible(false);
    	paneRutas.setVisible(false);
    	paneMenuP.setVisible(false);
    	paneMascota.setVisible(false);
    	paneTarjeta.setVisible(false);
    	paneEquipaje.setVisible(false);
    	btnAsientos.setVisible(false);
    	panePago.setVisible(false);
    }

    @FXML
    void actnPortalADM(ActionEvent event) {

    }

    /**
     * boton para aï¿½aadir tarjeta
     * @param event
     * @throws IOException
     * @throws ClassNotFoundException
     */
	@FXML
    void aniadirTarjeta(ActionEvent event) throws IOException, ClassNotFoundException {
    	 paneTarjeta.setVisible(true);
    }

	/**
	 * boton para settear el equipaje
	 * @param event
	 */
    @FXML
    void ctnAceptarEquipaje(ActionEvent event) {
    	btnAsientos.setVisible(true);
    }
    
    /**
     * metodo para buscar tarjetas del cliente
     * @param event
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NullPointerException
     */
    @FXML
    void buscarTarjetas(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException {
    	try{
		 for (Cliente cliente : listaUsuarios) {
			if(cliente.getIdentificacion().equalsIgnoreCase(noIdentificacion.getText())){
				for (int i = 0; i < cliente.getTarjeta().size(); i++) {
					seleccionTarjetaCliente.getItems().add(cliente.getTarjeta().get(i).getNumeros());
				}
			}
		}
    	}catch(NullPointerException e){

    	}
    }


    /**
     *
     * @param event
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NullPointerException
     */
    @FXML
    void ctnAniadirClienteTarjeta(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException {
    	try{
    	Boolean encontrado = true;
    	String fecha = fechaNCliente.getValue().getDayOfMonth()+"/"+fechaNCliente.getValue().getMonthValue()+"/"+fechaNCliente.getValue().getYear();

    	Tarjeta tarjeta = new Tarjeta(10000, clienteCodigo.getText(), nombreTarjetaCliente.getText(), noTarjetaCliente.getText(), TipoTarjeta.CREDITO);

		if(clienteCodigo.getText() == null || clienteCodigo.getText() == "" || nombreCliente.getText() == null || nombreCliente.getText() == ""
				|| noTarjetaCliente.getText()==null || noTarjetaCliente.getText() == ""){
			JOptionPane.showMessageDialog(null, "Rellene toda la informacion de la tarjeta");
		}else{
			for (Cliente cliente : listaUsuarios) {
				if(cliente.getIdentificacion().equalsIgnoreCase(noIdentificacion.getText())){
					encontrado = true;
					cliente.getTarjeta().add(tarjeta);
					break;
				}else{
					encontrado = false;
				}
			}
			if(encontrado == false){
				System.out.println("Se creara un cliente nuevo");
				if(noIdentificacion.getText()==null || nombreCliente.getText() == null){
					JOptionPane.showMessageDialog(null, "Como no esta registrado en el sistema, es necesario que rellene su informacion");
				}else{
					ArrayList<Tarjeta> listaTarjetasCliente = new ArrayList<>();
					listaTarjetasCliente.add(tarjeta);
					Cliente cliente = new Cliente(nombreCliente.getText(), apellidoCliente.getText(),direccionCliente.getText(), correoCliente.getText(), noIdentificacion.getText(),
							fecha, listaTarjetasCliente);
					listaUsuarios.add(cliente);
				}
			}
			JOptionPane.showMessageDialog(null, "Tarjeta añadida con exito");
			paneTarjeta.setVisible(false);
		}
		ObjectOutputStream clienteEscribir = new ObjectOutputStream(new FileOutputStream("src/archivos/clientes.dat"));
		clienteEscribir.writeObject(listaUsuarios);
		clienteEscribir.close();
    	}catch(NullPointerException e){

    	}
    }


    /**
     * metodo para finalizar la compra, validando la informacion del cliente
     * @param event
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws NullPointerException
     */
    @FXML
    void ctnFinalizarCompra(ActionEvent event) throws ClassNotFoundException, IOException, NullPointerException{
    	try{
    	if(noIdentificacion.getText()!=""|| (seleccionTarjetaCliente.getValue()!=null)){
    		Boolean encontrado = true;
    		int noTiquete = 0;
    		String fecha = fechaNCliente.getValue().getDayOfMonth()+"/"+fechaNCliente.getValue().getMonthValue()+"/"+fechaNCliente.getValue().getYear();
    		Cliente clienteEncontrado = null;

    		for (Cliente cliente : listaUsuarios) {
				if(cliente.getIdentificacion().equalsIgnoreCase(noIdentificacion.getText())){
					encontrado = true;
					clienteEncontrado = cliente;
					break;
				}else{
					encontrado = false;
				}
			}
    		if(encontrado == false){
    			ArrayList<Tarjeta> listaTarjetasCliente = new ArrayList<>();
    			Cliente cliente = new Cliente(nombreCliente.getText(), apellidoCliente.getText(),direccionCliente.getText(), correoCliente.getText(), noIdentificacion.getText(),
						fecha, listaTarjetasCliente);
				listaUsuarios.add(cliente);
				clienteEncontrado = cliente;
				
    		}


    		ArrayList<Tarjeta> busquedaTarjeta = new ArrayList<>();
    		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();

            Date dateObj = calendar.getTime();
            String formattedDate = dtf.format(dateObj);

            

          
    		busquedaTarjeta = clienteEncontrado.getTarjeta();

    		double saldoNuevo = 0;

    		for (int i = 0; i < busquedaTarjeta.size(); i++) {
				if(busquedaTarjeta.get(i).getNumeros().equalsIgnoreCase(seleccionTarjetaCliente.getValue()) ){
					clienteEncontrado.getTarjeta().get(i).setSaldo(clienteEncontrado.getTarjeta().get(i).getSaldo()-precio);
					saldoNuevo = clienteEncontrado.getTarjeta().get(i).getSaldo()-precio;
				}
			}

    		for (Cliente cliente : listaUsuarios) {
				if(cliente.getIdentificacion().equalsIgnoreCase(noIdentificacion.getText())){
					cliente = clienteEncontrado;
					break;
				}
			}


    		ObjectOutputStream clienteEscribir = new ObjectOutputStream(new FileOutputStream("src/archivos/clientes.dat"));
    		clienteEscribir.writeObject(listaUsuarios);
    		clienteEscribir.close();

    	
    		JOptionPane.showMessageDialog(null, "Gracias por usar nuestros servicios señor "+clienteEncontrado.getNombre());
    		cerrarPanes();
    		paneMenuP.setVisible(true);

    		if(listaEmbarque.isEmpty()){

    			CarroEmbarque carroEmbarque;
    			if(mascotaInte.isSelected()){
    				Mascota mascota = new Mascota(Double.valueOf(mascotaPeso.getText()));
    				Equipaje equipaje = new Equipaje(Double.valueOf(peso.getText()), Double.valueOf(altoPeso.getText()),
    				Double.valueOf(largoPeso.getText()), Double.valueOf(anchoPeso.getText()), mascota);
    				ListaSimple<Equipaje> equipajeL = new ListaSimple<>();
    				equipajeL.agregarfinal(equipaje);
    				carroEmbarque = new CarroEmbarque("00", 500, equipajeL);
    			}else{
    					Equipaje equipaje = new Equipaje(Double.valueOf(peso.getText()), Double.valueOf(altoPeso.getText()),
    					Double.valueOf(largoPeso.getText()), Double.valueOf(anchoPeso.getText()));
    	    			ListaSimple<Equipaje> equipajeL = new ListaSimple<>();
    	    			equipajeL.agregarfinal(equipaje);
    	   				carroEmbarque = new CarroEmbarque("00", 500, equipajeL);
    			}
    
    		}else{
    
	 			int pesoTotal = 0;
    	
    			if((pesoTotal+Integer.parseInt(peso.getText()))>500){
    				CarroEmbarque nuevoCarro;
    				if(mascotaInte.isSelected()){
        				Mascota mascota = new Mascota(Double.valueOf(mascotaPeso.getText()));
        				Equipaje equipaje = new Equipaje(Double.valueOf(peso.getText()), Double.valueOf(altoPeso.getText()),
        				Double.valueOf(largoPeso.getText()), Double.valueOf(anchoPeso.getText()), mascota);
        				ListaSimple<Equipaje> equipajeL = new ListaSimple<>();
        				equipajeL.agregarfinal(equipaje);
        				nuevoCarro = new CarroEmbarque("00", 500, equipajeL);
        			}else{
        					Equipaje equipaje = new Equipaje(Double.valueOf(peso.getText()), Double.valueOf(altoPeso.getText()),
        					Double.valueOf(largoPeso.getText()), Double.valueOf(anchoPeso.getText()));
        	    			ListaSimple<Equipaje> equipajeL = new ListaSimple<>();
        	    			equipajeL.agregarfinal(equipaje);
        	   				nuevoCarro = new CarroEmbarque("00", 500, equipajeL);
        			}
        	   
    			}else{
    				if(mascotaInte.isSelected()){
    					Mascota mascota = new Mascota(Double.valueOf(mascotaPeso.getText()));
        				Equipaje equipaje = new Equipaje(Double.valueOf(peso.getText()), Double.valueOf(altoPeso.getText()),
        				Double.valueOf(largoPeso.getText()), Double.valueOf(anchoPeso.getText()), mascota);

    				}else{
    						Equipaje equipaje = new Equipaje(Double.valueOf(peso.getText()), Double.valueOf(altoPeso.getText()),
    	        			Double.valueOf(largoPeso.getText()), Double.valueOf(anchoPeso.getText()));
    				}
    			
    			
    			}
    		}
    	}else{
    		JOptionPane.showMessageDialog(null, "Porfavor rellene todos los datos");
    	}
    	}catch(NullPointerException e){

    	}
    }


    /**
     * campos en la interfaza para seleccion de ida y vuelta
     * @param event
     */
    @FXML
    void ctnIdaYVuelta(ActionEvent event) {
    	soloIda.setSelected(false);
		datePVuelta.setVisible(true);
		if(!idaYVuelta.isSelected() && !soloIda.isSelected()){
    		soloIda.setSelected(true);
    		datePVuelta.setVisible(false);
    	}
    }

    /**
     * campos para seleccion de solo ida
     * @param event
     */
    @FXML
    void ctnSoloIda(ActionEvent event) {
    	idaYVuelta.setSelected(false);
		datePVuelta.setVisible(false);
		if(!idaYVuelta.isSelected() && !soloIda.isSelected()){
    		soloIda.setSelected(true);
    		datePVuelta.setVisible(false);
    	}
    }


    /**
     * campo para validar seleccion de mascota
     * @param event
     */
    @FXML
    void ctnMascotaInte(ActionEvent event) {
    	if(mascotaInte.isSelected()){
    		paneMascota.setVisible(true);
    	}else{
    		paneMascota.setVisible(false);
    	}
    }



    @FXML
    void mouseCompraT(MouseDragEvent event) {

    }

    @FXML
    void mouseEmbarque(MouseDragEvent event) {

    }

    @FXML
    void mouseMenuP(MouseDragEvent event) {

    }

    @FXML
    void mousePortal(MouseDragEvent event) {

    }

    @FXML
    void noMouseCompraT(MouseDragEvent event) {

    }

    @FXML
    void noMouseEmbarque(MouseDragEvent event) {

    }

    @FXML
    void noMouseMenuP(MouseDragEvent event) {

    }

    @FXML
    void noMousePortal(MouseDragEvent event) {

    }

    /**
     * Método de inicialización para el controlador de la interfaz de usuario de la aplicación.
     * Se ejecuta automáticamente al cargar la interfaz gráfica.
     */
    @FXML
    void initialize() {
        // Inicialización de datos de la aerolínea
        aerolinea.inicializarDatos();
        
        // Obtención de la lista de usuarios desde la aerolínea
        listaUsuarios = aerolinea.getListaUsuarios();
        
        // Impresión de la lista de usuarios en la consola
        System.out.println(listaUsuarios);
        
        // Configuración de opciones en ComboBox para la clase de vuelo (Economica, Ejecutiva)
        comBoxClase.getItems().addAll("Economica", "Ejecutiva");
        
        // Configuración de opciones en ComboBox para el origen del vuelo (CDMX, BGTA)
        comBoxOrigen.getItems().add("CDMX");
        comBoxOrigen.getItems().add("BGTA");
        
        // Obtención de la lista de rutas desde la aerolínea
        ArrayList<Ruta> destinos = aerolinea.getListaRutas();
        
        // Configuración de opciones en ComboBox para el destino del vuelo utilizando la lista de rutas
        for (int i = 0; i < destinos.size(); i++) {
            comBoxDestino.getItems().add(destinos.get(i).getDestino().getNombre());
        }

        // Configuración de un listener para la selección de elementos en la tabla de rutas
        tableRutas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // Actualización de la ruta seleccionada y visibilidad de un panel relacionado con el equipaje
            rutaSeleccionada = newSelection;
            paneEquipaje.setVisible(true);
        });

        // Asertos para verificar que los elementos de la interfaz hayan sido inyectados correctamente
        assert btnAplicarPersonas != null : "fx:id=\"btnAplicarPersonas\" was not injected: check your FXML file 'CaribbeanAirlinesView.fxml'.";
        assert btnAsientos != null : "fx:id=\"btnAsientos\" was not injected: check your FXML file 'CaribbeanAirlinesView.fxml'.";
        // (se repite para otros botones y elementos de la interfaz)
        assert tableRutas != null : "fx:id=\"tableRutas\" was not injected: check your FXML file 'CaribbeanAirlinesView.fxml'.";
    }

	public void setAplicacion(Main main) {
		this.main = main;

	}

}
