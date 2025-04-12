package backend.es.nexphernandez.adict.games.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class RegistrarController extends AbstractController{
    
    @FXML
    private Label textLabel;
    /**
     * Inicializate de la clase
     */
    public void initialize() {
        cambiarIdiomaRegistro();
    }

    @FXML
    protected void onRegistrarClick(){
        if (textFieldUsuario == null || textFieldUsuario.getText().isEmpty()) {
            textLabel.setText("¡El nombre de usuario no puede ser nulo o vacio!");
            return;
        }

        if (usuarioServiceModel.verificarUsuario(textFieldUsuario.getText())) {
            textLabel.setText("El usuario ya esta registrado.");
            return;
        }

        if (passwordField == null || passwordField.getText().isEmpty()|| 
            passwordField == null || passwordField.getText().isEmpty()) {
            textLabel.setText("¡El password no puede ser nulo o vacio!");
            return;
        }

        if (nombreField == null || nombreField.getText().isEmpty()) {
            textLabel.setText("¡El nombre no puede ser nulo o vacio!");
            return;
        }

        if (emailField == null || emailField.getText().isEmpty()|| 
        emailField1 == null || emailField1.getText().isEmpty()) {
            textLabel.setText("¡El email no puede ser nulo o vacio!");
            return;
        }

        String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(emailField.getText());

        if (!m.matches()) {
            textLabel.setText("El correo electrónico no es válido.");
            return;
        }

        if (usuarioServiceModel.verificarEmail(emailField1.getText())) {
            textLabel.setText("El email ya esta registrado.");
            return;
        }

        if (!passwordField.getText().equals(passwordField1.getText())) {
            textLabel.setText("¡La passwords no son iguales!");
            return;
        }
        UsuarioEntity  usuarioARegistrar = new UsuarioEntity(textFieldUsuario.getText(), 
        emailField.getText(), nombreField.getText(), passwordField.getText());
        boolean registro = usuarioServiceModel.aniadirUsuario(usuarioARegistrar);
        if (registro == false) {
            textLabel.setText("Ya hay una cuenta con ese usuario e email");
        } else{
            textLabel.setText("¡Usuario registrado!");
        }
    }

    /**
     * Funcion para ir la pagina iniciar sesion
     */
    @FXML
    protected void onAtrasClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PrincipalApplication.class.getResource("/view/iniciarSesion.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) atrasButton.getScene().getWindow();
            stage.setTitle("Pantalla de Busqueda");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
