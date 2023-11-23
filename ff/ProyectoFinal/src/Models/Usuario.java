package Models;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;

import Controllers.LoginController;
import application.Main;

public class Usuario {
    private String user;
    private String password;
    private static Stage stg;

    private HashMap<String, String> usuarios = new HashMap<>();

    public void usuarios() {
        usuarios.put("Camilo", "Lealtad");
        usuarios.put("Aleks", "Sorda");
    }

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return this.user;
    }
    public Set<String> getUsuarios() {
        return usuarios.keySet();
    }


    public String getPassword() {
        return this.password;
    }

    public void validacionIngreso(LoginController controlador) {
    	try {
        usuarios();
        Main inicio = new Main();
        // Recorre el HashMap y compara las credenciales
        for (String username : usuarios.keySet()) {
            String clave = usuarios.get(username);
            
            if (user.equals(username) && password.equals(clave)) {       
                     inicio.mostarPrincipal();
                     controlador.cerrar();
                    
                    

                return;
            }
        }
        } catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar");
		}
      
        controlador.mostrarAlerta("Credenciales incorrectas", Alert.AlertType.ERROR);
    }

    
}
