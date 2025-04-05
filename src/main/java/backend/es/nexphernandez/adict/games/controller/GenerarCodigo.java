package backend.es.nexphernandez.adict.games.controller;

import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.controller.abstractas.AbstractController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenerarCodigo extends AbstractController{
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/buscarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) inicioButton.getScene().getWindow();
            stage.setTitle("Pantalla de Busqueda");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
