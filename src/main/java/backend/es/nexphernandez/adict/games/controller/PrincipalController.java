package backend.es.nexphernandez.adict.games.controller;

import java.util.ArrayList;
import java.util.List;

import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.config.ConfigManager;
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
    private final String pathFichero = "src/main/resources/idiomas";
    private final String ficheroStr = "idioma-";

    public void initialize() {
        List<String> listaIdiomas = new ArrayList<>();
        listaIdiomas.add("es");
        listaIdiomas.add("en");
        cargarIdioma("es");
    }

    private void cargarIdioma(String idioma) {
        String path = pathFichero + ficheroStr + idioma + ".properties";
        ConfigManager.ConfigProperties.setPath(path);
    }

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
