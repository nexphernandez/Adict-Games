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
public class ComprarController extends AbstractController{
    
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaComprar();
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
        emailField.setText(usuario.getEmail());
        textFieldNombre.setText(usuario.getNombre());
    }

    /**
     * Funcion para ir la pagina seleccionar juego
     */
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/seleccionarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            SeleccionarJuego seleccionarJuego = fxmlLoader.getController();
            seleccionarJuego.setUsuario(usuario);
            stage.setTitle("Pantalla de seleccion");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion para ir la pagina generar codigo
     */
    @FXML
    protected void onComprarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/generarCodigo.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            GenerarCodigo generarCodigo = fxmlLoader.getController();
            generarCodigo.setUsuario(usuario);
            Stage stage = (Stage) entrarButton.getScene().getWindow();
            stage.setTitle("Pantalla de generarCodigo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
