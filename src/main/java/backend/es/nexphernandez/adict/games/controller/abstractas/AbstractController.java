package backend.es.nexphernandez.adict.games.controller.abstractas;
import backend.es.nexphernandez.adict.games.config.ConfigManager;
import backend.es.nexphernandez.adict.games.model.servicios.GeneroServiceModel;
import backend.es.nexphernandez.adict.games.model.servicios.JuegoServiceModel;
import backend.es.nexphernandez.adict.games.model.servicios.RolServiceModel;
import backend.es.nexphernandez.adict.games.model.servicios.UsuarioServiceModel;
import javafx.fxml.FXML;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
public class AbstractController {

    private final String pathFichero="src/main/resources/idiomas/";
    private final String ficheroStr= "idioma-";
    private static String idiomaActual = "es"; 

    static final String PATH_DB = "src/main/resources/adictGames.db";

    GeneroServiceModel generoServiceModel;
    JuegoServiceModel juegoServiceModel;
    RolServiceModel rolServiceModel;
    UsuarioServiceModel usuarioServiceModel;
    @FXML
    public Hyperlink iniciarSesionLink;

    @FXML
    public Hyperlink olvidarContraseniaLabel;

    @FXML
    public Hyperlink cerrarSesionLabel;

    @FXML
    public Hyperlink comprarLabel;

    @FXML
    public Hyperlink comprarLabel2;
    
    @FXML
    public Hyperlink seleccionarLabel1;

    @FXML
    public Hyperlink seleccionarLabel2;

    @FXML
    public Hyperlink perfilLHyperlink;

    @FXML
    public Label iniciarSesionLabel;

    @FXML
    public Label contraseniaLabel;

    @FXML
    public Label contraseniaLabel1;

    @FXML
    public Label juegoLabel;

    @FXML
    public Label emailLabel;

    @FXML
    public Label repetirEmailLabel;

    @FXML
    public Label comprarLabel1;

    @FXML
    public Label nuevoUsuarioLabel;

    @FXML
    public Label registoLabel;

    @FXML
    public Label nombreJuegoLabel1;

    @FXML
    public Label nombreJuegoLabel2;

    @FXML
    public Label olvidarContraseniaLabel1;

    @FXML
    public Label numeroCuentaLabel;

    @FXML
    public Label nombreLabel;

    @FXML
    public Label codigoLabel;

    @FXML
    public Label nombreUsuarioLabel;
    
    @FXML
    public Label perfilLabel;

    @FXML
    public Label generarCodigoLabel;

    @FXML
    public Label requerimientosLabel;

    @FXML
    public Button registrarButton;

    @FXML
    public Button entrarButton;

    @FXML
    public Button atrasButton;

    @FXML
    public Button inicioButton;

    @FXML
    public MenuBar barra;

    @FXML
    public Menu idiomaMenu;

    @FXML
    public MenuItem esMenuItem;

    @FXML
    public MenuItem enMenuItem;

    @FXML
    public MenuItem generoMenu;

    @FXML
    public TextField searchTextField;

    @FXML
    public TextField textFieldUsuario;

    @FXML
    public TextField textFieldNumeroCuenta;

    @FXML
    public TextField textFieldNombre;

    @FXML
    public TextField emailField;

    @FXML
    public TextField emailField1;

    @FXML
    public TextField nombreField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField passwordField1;

    @FXML
    public ImageView imagenJuego;

    @FXML
    public ImageView imagenJuego1;

    @FXML
    public ImageView imagenJuego2;

    @FXML
    public ImageView imagenLogo;

    @FXML
    public TextArea referencias;


    public AbstractController(){
        usuarioServiceModel = new UsuarioServiceModel();
        generoServiceModel = new GeneroServiceModel();
        rolServiceModel = new RolServiceModel();
        juegoServiceModel = new JuegoServiceModel();
    }

    /**
     * Funcion para cargar el idioma
     * @param idioma a cargar
     */
    protected void cargarIdiomaActual() {
        if (idiomaActual == null || idiomaActual.isEmpty()) {
            idiomaActual = "es"; 
        }
        
        String path = pathFichero+ficheroStr + idiomaActual + ".properties";
        ConfigManager.ConfigProperties.setPath(path);
    }

    /**
     * Método para obtener el idioma actual.
     */
    public static String getIdioma() {
        return idiomaActual;
    }

    /**
     * Método para cambiar el idioma.
     */
    public static void setIdioma(String idioma) {
        idiomaActual = idioma;
    }

    /**
     * Metodo para seleccionar el idioma ingles
     */
    @FXML
    public void seleccionarIdiomaIngles() {
        String idioma = "en"; 
        setIdioma(idioma);
        cargarIdiomaActual();
        cambiarIdiomaPrincipal();

    }

    /**
     * Metodo para seleccionar el idioma Español
     */
    @FXML
    public void seleccionarIdiomaEspañol() {
        String idioma = "es"; 
        setIdioma(idioma);
        cargarIdiomaActual();
        cambiarIdiomaPrincipal();

    }

    /**
     * Funcion para cambiar el idioma de la pantalla principal
     */
    public void cambiarIdiomaPrincipal(){
        nuevoUsuarioLabel.setText(ConfigManager.ConfigProperties.getProperty("nuevoUsuarioLabel"));
        iniciarSesionLink.setText(ConfigManager.ConfigProperties.getProperty("iniciarSesionLink"));
        searchTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("searchTextField"));
        idiomaMenu.setText(ConfigManager.ConfigProperties.getProperty("idiomaMenu"));
        esMenuItem.setText(ConfigManager.ConfigProperties.getProperty("esMenuItem"));
        enMenuItem.setText(ConfigManager.ConfigProperties.getProperty("enMenuItem"));
        generoMenu.setText(ConfigManager.ConfigProperties.getProperty("generoMenu")); 
        registrarButton.setText(ConfigManager.ConfigProperties.getProperty("registrarButton")); 

    }

    /**
     * Funcion para cambiar el idioma de la pantalla de inicio sesion
     */
    public void cambiarIdiomaInicioSesion(){
        iniciarSesionLabel.setText(ConfigManager.ConfigProperties.getProperty("iniciarSesionLabel"));
        nombreUsuarioLabel.setText(ConfigManager.ConfigProperties.getProperty("nombreUsuarioLabel"));
        contraseniaLabel.setText(ConfigManager.ConfigProperties.getProperty("contraseniaLabel"));
        textFieldUsuario.setText(ConfigManager.ConfigProperties.getProperty("textFieldUsuario")); 
        entrarButton.setText(ConfigManager.ConfigProperties.getProperty("entrarButton")); 
        olvidarContraseniaLabel.setText(ConfigManager.ConfigProperties.getProperty("olvidarContraseniaLabel")); 
        nuevoUsuarioLabel.setText(ConfigManager.ConfigProperties.getProperty("nuevoUsuarioLabel")); 
        registrarButton.setText(ConfigManager.ConfigProperties.getProperty("registrarButton")); 
        atrasButton.setText(ConfigManager.ConfigProperties.getProperty("atrasButton")); 
    }

    /**
     * Funcion para cambiar el idioma de la pantalla de registro
     */
    public void cambiarIdiomaRegistro(){
        registoLabel.setText(ConfigManager.ConfigProperties.getProperty("registoLabel"));
        nombreUsuarioLabel.setText(ConfigManager.ConfigProperties.getProperty("nombreUsuarioLabel"));
        contraseniaLabel.setText(ConfigManager.ConfigProperties.getProperty("contraseniaLabel"));
        textFieldUsuario.setPromptText(ConfigManager.ConfigProperties.getProperty("textFieldUsuario")); 
        entrarButton.setText(ConfigManager.ConfigProperties.getProperty("entrarButton")); 
        contraseniaLabel1.setText(ConfigManager.ConfigProperties.getProperty("contraseniaLabel1")); 
        emailField1.setPromptText(ConfigManager.ConfigProperties.getProperty("emailField1")); 
        emailField.setPromptText(ConfigManager.ConfigProperties.getProperty("emailField")); 
        repetirEmailLabel.setText(ConfigManager.ConfigProperties.getProperty("repetirEmailLabel")); 
        atrasButton.setText(ConfigManager.ConfigProperties.getProperty("atrasButton")); 
        nombreLabel.setText(ConfigManager.ConfigProperties.getProperty("nombreLabel")); 
        nombreField.setPromptText(ConfigManager.ConfigProperties.getProperty("nombreField")); 
        passwordField.setPromptText(ConfigManager.ConfigProperties.getProperty("passwordField")); 
        passwordField1.setPromptText(ConfigManager.ConfigProperties.getProperty("passwordField1")); 
    }

    /**
     * Funcion para cambiar el idioma de la pantalla buscar juegos
     */
    public void cambiarIdiomaBuscarJuego(){
        searchTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("searchTextField"));
        seleccionarLabel1.setText(ConfigManager.ConfigProperties.getProperty("seleccionarLabel1"));
        seleccionarLabel2.setText(ConfigManager.ConfigProperties.getProperty("seleccionarLabel2"));        
        cerrarSesionLabel.setText(ConfigManager.ConfigProperties.getProperty("cerrarSesionLabel")); 
    }

    /**
     * Funcion para cambiar el idioma de la pantalla perfil
     */
    public void cambiarIdiomaPerfil(){
        perfilLabel.setText(ConfigManager.ConfigProperties.getProperty("perfilLabel"));
        nombreUsuarioLabel.setText(ConfigManager.ConfigProperties.getProperty("nombreUsuarioLabel"));
        contraseniaLabel.setText(ConfigManager.ConfigProperties.getProperty("contraseniaLabel"));
        entrarButton.setText(ConfigManager.ConfigProperties.getProperty("entrarButton")); 
        atrasButton.setText(ConfigManager.ConfigProperties.getProperty("atrasButton")); 
        nombreLabel.setText(ConfigManager.ConfigProperties.getProperty("nombreLabel")); 
    }

    /**
     * Funcion para cambiar el idioma de la pantalla seleccionar juego
     */
    public void cambiarIdiomaSeleccionarJuego(){
        searchTextField.setPromptText(ConfigManager.ConfigProperties.getProperty("searchTextField"));
        comprarLabel.setText(ConfigManager.ConfigProperties.getProperty("comprarLabel"));
        atrasButton.setText(ConfigManager.ConfigProperties.getProperty("atrasButton"));        
        cerrarSesionLabel.setText(ConfigManager.ConfigProperties.getProperty("cerrarSesionLabel")); 
        requerimientosLabel.setText(ConfigManager.ConfigProperties.getProperty("requerimientosLabel")); 
    }  

    /**
     * Funcion para cambiar el idioma de la pantalla compra
     */
    public void cambiarIdiomaComprar(){
        comprarLabel1.setText(ConfigManager.ConfigProperties.getProperty("comprarLabel"));
        numeroCuentaLabel.setText(ConfigManager.ConfigProperties.getProperty("numeroCuentaLabel"));
        textFieldNumeroCuenta.setText(ConfigManager.ConfigProperties.getProperty("textFieldNumeroCuenta"));
        nombreLabel.setText(ConfigManager.ConfigProperties.getProperty("nombreLabel")); 
        textFieldNombre.setText(ConfigManager.ConfigProperties.getProperty("textFieldNombre")); 
        emailField.setText(ConfigManager.ConfigProperties.getProperty("emailField")); 
        entrarButton.setText(ConfigManager.ConfigProperties.getProperty("entrarButton")); 
        atrasButton.setText(ConfigManager.ConfigProperties.getProperty("atrasButton")); 
    }

    /**
     * Funcion para cambiar el idioma de la pantalla generar codigo
     */
    public void cambiarIdiomaGenerarCodigo(){
        generarCodigoLabel.setText(ConfigManager.ConfigProperties.getProperty("generarCodigoLabel"));
        codigoLabel.setText(ConfigManager.ConfigProperties.getProperty("codigoLabel"));
        atrasButton.setText(ConfigManager.ConfigProperties.getProperty("atrasButton"));        
        inicioButton.setText(ConfigManager.ConfigProperties.getProperty("inicioButton")); 
    }
}