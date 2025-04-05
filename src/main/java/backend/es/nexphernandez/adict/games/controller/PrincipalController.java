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
public class PrincipalController extends AbstractController {

    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cargarIdiomaActual();
        cambiarIdiomaPrincipal();
    }

    /**
     * Funcion para ir la pagina iniciar sesion
     */
    @FXML
    protected void OnClickIniciarSesion() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/iniciarSesion.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) iniciarSesionLink.getScene().getWindow();
            stage.setTitle("Pantalla inicio sesion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void buscarJuegos() {

    }

    /**
     * Funcion para ir la pagina registrar
     */
    @FXML
    protected void onClickRegistrar() {
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
    protected void onClickMenu() {

    }

}
