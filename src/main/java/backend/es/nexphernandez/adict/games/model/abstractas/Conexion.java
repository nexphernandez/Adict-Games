package backend.es.nexphernandez.adict.games.model.abstractas;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String rutaBD;
    private Connection connection;
    
    /**
     * Constructor vacio
     */
    public Conexion() {
    }

    /**
     * Contructor con el path de la bbdd
     * @param unarutaBD ruta de la bbdd
     */
    public Conexion(String unarutaBD) {
        try {
            if (unarutaBD == null || unarutaBD.isEmpty()) {
                throw new SQLException("El fichero no existe");
            }
            File file = new File(unarutaBD);
            if (!file.exists()) {
                throw new SQLException("No existe la bbdd: " + unarutaBD);
            }
            rutaBD = unarutaBD;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRutaBD(){
        return this.rutaBD;
    }

    /**
     * Metodo para abrir la conexion con la bbdd 
     * @return conexion con la bbdd
     */
    public Connection conectar(){
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:" + rutaBD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * Metod para cerrar la conexion con la bbdd 
     */
    public void cerrar(){
        try {
            if (connection == null) {
                return;
            }
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
