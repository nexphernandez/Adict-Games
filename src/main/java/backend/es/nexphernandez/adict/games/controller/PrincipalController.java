package backend.es.nexphernandez.adict.games.controller;
import java.util.ArrayList;
import java.util.List;

import backend.es.nexphernandez.adict.games.abstractas.AbstractController;
import backend.es.nexphernandez.adict.games.config.ConfigManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class PrincipalController extends AbstractController{
    private final String pathFichero="src/main/resources/idiomas";
    private final String ficheroStr= "idioma-";

    public void initialize(){
        List<String> listaIdiomas = new ArrayList<>();
        listaIdiomas.add("es");
        listaIdiomas.add("en");
        cargarIdioma("es");
    }

    private void cargarIdioma(String idioma){
        String path = pathFichero+ficheroStr+idioma+".properties";
        ConfigManager.ConfigProperties.setPath(path);
    }
}
