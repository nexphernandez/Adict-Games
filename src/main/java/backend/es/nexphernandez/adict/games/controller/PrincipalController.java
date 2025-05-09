package backend.es.nexphernandez.adict.games.controller;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.controller.abstractas.AbstractController;
import backend.es.nexphernandez.adict.games.model.JuegoEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        obtenerJuegoRandom();
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

    public void obtenerJuegoRandom(){
        HashSet<JuegoEntity> juegos = juegoServiceModel.obtenerTodosLosJuegos();
        List<JuegoEntity> juegoList = new ArrayList<>();
        juegoList.addAll(juegos);
        Random random = new Random(); 
        JuegoEntity juego = juegoList.get(random.nextInt(juegoList.size()));
        String urlIMagen = juego.getUrlImangen();
        String nombreJuego = juego.getNombre();
        juegoLabel.setText(nombreJuego);
        imagenJuego.setImage(new Image("file:"+urlIMagen));
    }

}
