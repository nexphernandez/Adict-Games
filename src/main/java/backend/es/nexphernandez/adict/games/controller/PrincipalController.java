package backend.es.nexphernandez.adict.games.controller;


import java.util.HashSet;

import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.controller.abstractas.AbstractController;
import backend.es.nexphernandez.adict.games.model.GeneroEntity;
import backend.es.nexphernandez.adict.games.model.JuegoEntity;
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
        HashSet<GeneroEntity> generoEntities = new HashSet<>();
        GeneroEntity genero = new GeneroEntity(8,"papa");
        GeneroEntity genero2 = new GeneroEntity(9,"pepe");
        generoEntities.add(genero);
        generoEntities.add(genero2);
        JuegoEntity juegoEntity = new JuegoEntity("getIdioma", "getIdioma", "getIdioma", generoEntities);
        System.out.println(juegoServiceModel.aniadirJuego(juegoEntity));
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



}
