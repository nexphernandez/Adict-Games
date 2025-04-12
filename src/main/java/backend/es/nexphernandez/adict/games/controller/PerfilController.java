package backend.es.nexphernandez.adict.games.controller;

import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.controller.abstractas.AbstractController;
import backend.es.nexphernandez.adict.games.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class PerfilController extends AbstractController{
    
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaPerfil();
    }
    
    /**
     * Funcion para indicar de que usuario se cargan los datos
     * @param usuario a cargar los datos
     */
    public void setUsuario(UsuarioEntity usuario){
        this.usuario = usuario;
        cargarDatos();
    }

    /**
     * Funcion para cargar los datos en esta pagina
     */
    public void cargarDatos(){
        textFieldUsuario.setText(usuario.getUser());
        passwordField.setText(usuario.getContrasenia());
        nombreField.setText(usuario.getNombre());
        emailField.setText(usuario.getEmail());
    }

    /**
     * Funcion para ir la pagina buscar juego
     */
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/buscarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            BuscarJuegoController buscarJuegoController = fxmlLoader.getController();
            buscarJuegoController.setUsuario(usuario);
            stage.setTitle("Pantalla de Busqueda");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
