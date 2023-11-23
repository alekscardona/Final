package Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Models.Usuario;



public class LoginController {
	@FXML
	private AnchorPane visual;
    @FXML
    private PasswordField password;

    @FXML
    private TextField user;


    private Usuario usuario;

    @FXML
    private void ingresar() {
    	 usuario = new Usuario(user.getText(), password.getText());
         usuario.usuarios();
          usuario.validacionIngreso(this);

          
     }
    
    public void cerrar() {
    	Stage stage = (Stage) visual.getScene().getWindow();
        stage.close();
    }
       
    
   
    @FXML
    private void registrar() {}
  
    public void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
