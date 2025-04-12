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
public class SeleccionarJuego extends AbstractController{
    
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaSeleccionarJuego();
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
        perfilLHyperlink.setText(usuario.getNombre());
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
     * Funcion para ir la pagina comprar
     */
    @FXML
    protected void oncomprarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/comprar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ComprarController comprarController = fxmlLoader.getController();
            comprarController.setUsuario(usuario);
            Stage stage = (Stage) comprarLabel.getScene().getWindow();
            stage.setTitle("Pantalla para comprar");
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
            PerfilController perfilController = fxmlLoader.getController();
            perfilController.setUsuario(usuario);
            Stage stage = (Stage) perfilLHyperlink.getScene().getWindow();
            stage.setTitle("Pantalla del perfil");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion para ir la pagina buscar juego
     */
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/buscarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BuscarJuegoController buscarJuegoController = fxmlLoader.getController();
            buscarJuegoController.setUsuario(usuario);
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            stage.setTitle("Pantalla de Busqueda");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
