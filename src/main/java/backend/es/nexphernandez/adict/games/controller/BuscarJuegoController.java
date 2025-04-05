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
public class BuscarJuegoController extends AbstractController{
   
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaBuscarJuego();
    }

    /**
     * Funcion para ir la pagina principal
     */
    @FXML
    protected void onCerrarSesionClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) cerrarSesionLabel.getScene().getWindow();
            stage.setTitle("Pantalla principal");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion para ir la pagina seleccionar
     */
    @FXML
    protected void onSeleccionarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/seleccionarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) seleccionarLabel1.getScene().getWindow();
            stage.setTitle("Pantalla de seleccion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion para ir la pagina perfil
     */
    @FXML
    protected void onPerfilClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/perfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) perfilLHyperlink.getScene().getWindow();
            stage.setTitle("Pantalla del perfil");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
