package backend.es.nexphernandez.adict.games.controller;


import backend.es.nexphernandez.adict.games.PrincipalApplication;
import backend.es.nexphernandez.adict.games.controller.abstractas.AbstractController;
import backend.es.nexphernandez.adict.games.model.UsuarioEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class IniciarSesionController extends AbstractController{
    
    private UsuarioEntity usuario;
    @FXML
    private Label textLabel;
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaInicioSesion();
    }

    public IniciarSesionController(){
        super();
    }

    /**
     * Funcion para ir la pagina registrar
     */
    @FXML
    protected void onRegitrarClick(){
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
    protected void onRecuperarClick(){
        
    }

    /**
     * Funcion para ir la pagina buscar juego
     */
    @FXML
    protected void onAceptarClick(){
        if (textFieldUsuario.getText().isEmpty() || passwordField.getText().isEmpty() ||
        textFieldUsuario == null || passwordField == null) {
            textLabel.setText("Credenciales nulas o vacias");
            return;
        }
        usuario = usuarioServiceModel.obtenerUsuarioPorUser(textFieldUsuario.getText(),passwordField.getText());
        if (usuario == null) {
            usuario = usuarioServiceModel.obtenerUsuariosPorEmail(textFieldUsuario.getText(),passwordField.getText());
        }

        if (usuario == null) {
            textLabel.setText("Credenciales inválidas");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/buscarJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            BuscarJuegoController buscarJuegoController = fxmlLoader.getController();
            buscarJuegoController.setUsuario(usuario);
            Stage stage = (Stage) entrarButton.getScene().getWindow();
            stage.setTitle("Pantalla para buscar juegos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcion para ir la pagina principal
     */
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/principal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            stage.setTitle("Pantalla principal");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
