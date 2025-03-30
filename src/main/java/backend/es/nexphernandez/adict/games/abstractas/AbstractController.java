package backend.es.nexphernandez.adict.games.abstractas;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class AbstractController {

    @FXML
    public Image imagenJuego ;

    @FXML
    public Label iniciarSesionLabel;

    @FXML
    public MenuButton menuButton;


    @FXML
    public TextField searchTextField;

    @FXML
    public Label juegoLabel;

    @FXML
    public Label nuevoUsuarioLabel;
    

    @FXML 
    public Button registrarButton;

}