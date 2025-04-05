package backend.es.nexphernandez.adict.games.controller;

import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class IniciarSesionController extends AbstractController{
    
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaInicioSesion();
    }

    /**
     * Funcion para ir la pagina registrar
     */
    @FXML
    protected void onRegitrarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/registrar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) registrarButton.getScene().getWindow();
            stage.setTitle("Pantalla de registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRecuperarClick(){
        
    }

    /**
     * Funcion para ir la pagina buscar juego
     */
    @FXML
    protected void onAceptarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/buscarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) entrarButton.getScene().getWindow();
            stage.setTitle("Pantalla para buscar juegos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion para ir la pagina principal
     */
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            stage.setTitle("Pantalla principal");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
