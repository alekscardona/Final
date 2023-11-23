package application;

import java.io.IOException;

import Controllers.AirlineController;
import Controllers.SeleccionAsientoController;
import Models.Aerolinea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * Metodo main que inicializa toda la ejecucion de la aplicacion, carga archivos fxml, controllers, etc
 *
 */
public class Main extends Application {
    private Stage primaryStage;

    public Main() {
        primaryStage = new Stage();
    }

	
	private static Stage stg;
	  @Override
      public void start(Stage primaryStage)  throws Exception{
          this.primaryStage = primaryStage;
      mostarPrincipal();

  }
	
	public void mostarPrincipal() {
		try {
			stg= primaryStage;
			primaryStage.setResizable(false);
			stg.setTitle("Caribbean Airlines");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/CaribeAirlinesView.fxml"));
			AnchorPane rootLayout = (AnchorPane)loader.load();
			AirlineController airlineController = loader.getController();
			airlineController.setAplicacion(this);
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			stg.getIcons().add(new Image("/source/Logo.png"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * metodo nativo Javafx para cambios en las ventanas
	 * @param fxml
	 * @throws IOException
	 */
	public void changeScene(String fxml) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);

	}

	/**
	 * Metodo que permite cerrar la ventana de la interfaz
	 */
	public void close(){
		stg.close();
	}



	public static void main(String[] args) {
		launch(args);
	}
}
