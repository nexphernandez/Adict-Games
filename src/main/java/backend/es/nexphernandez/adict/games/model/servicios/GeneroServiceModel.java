package backend.es.nexphernandez.adict.games.model.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import backend.es.nexphernandez.adict.games.model.GeneroEntitty;
import backend.es.nexphernandez.adict.games.model.abstractas.Conexion;

public class GeneroServiceModel extends Conexion{
    /**
     * Constructor vacio
     */
    public GeneroServiceModel(){
    }

    /**
     * Constructor con el path de la bbdd
     * @param unaRutaBD path de la bbdd
     */
    public GeneroServiceModel(String unaRutaBD){
        super(unaRutaBD);
    }

    /**
     * Funcion que aplica una sentencia en la tabla juego
     * @param sql sentencia a aplicar
     * @return lista de juegos
     */
    public HashSet<GeneroEntitty> leerSentenciaGenero(String sql){
        HashSet<GeneroEntitty> generos = new HashSet<>();
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String nombreStr = resultado.getString("nombre");
                GeneroEntitty genero = new GeneroEntitty(nombreStr);
                generos.add(genero);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            cerrar();
        }
        return generos;
    }

    /**
     * Funcion que actualiza los datos segun la secuencia introducida en la tabla genero
     * @param sql sentencia a realizar
     * @param genero genero con los datos para actualizar
     * @return true/false
     */
    public boolean actualizarDatosGenero(String sql, GeneroEntitty genero){
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            sentencia.setString(1, genero.getGenero());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return false;
    }

    /**
     * Funcion para aniadir un genero a la bbdd
     * @param genero a aniadir
     * @return true/false
     */
    public boolean aniadirGenero(GeneroEntitty genero){
        if (genero == null) {
            return false;
        }
        String sql = "INSERT INTO generos (nombre) Values (?)";
        return actualizarDatosGenero(sql, genero);
    }

    /**
     * Funcion que te devuleve todos los generos de la base de datos
     * @return lista de generos
     */
    public HashSet<GeneroEntitty> obtenerTodosLosGeneros(){
        String sql = "SELECT * FROM generos";
        return leerSentenciaGenero(sql);
    }

    /**
     * Metodo para borrar un genero
     * @param user genero a borrar
     * @return true/false
     */
    public boolean borrarGenero(GeneroEntitty genero){
        String sql = "DELETE FROM generos where nombre ='"+ genero + "'";
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            sentencia.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
