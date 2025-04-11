package backend.es.nexphernandez.adict.games.model.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import backend.es.nexphernandez.adict.games.model.RolEntity;
import backend.es.nexphernandez.adict.games.model.abstractas.Conexion;

public class RolServiceModel extends Conexion{
    /**
     * Constructor vacio
     */
    public RolServiceModel(){
    }

    /**
     * Constructor con el path de la bbdd
     * @param unaRutaBD path de la bbdd
     */
    public RolServiceModel(String unaRutaBD){
        super(unaRutaBD);
    }

    /**
     * Funcion que aplica una sentencia en la tabla rol
     * @param sql sentencia a aplicar
     * @return lista de roles
     */
    public HashSet<RolEntity> leerSentenciaRol(String sql){
        HashSet<RolEntity> roles = new HashSet<>();
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String nombreStr = resultado.getString("nombre");
                RolEntity rol = new RolEntity(nombreStr);
                roles.add(rol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            cerrar();
        }
        return roles;
    }

    /**
     * Funcion que actualiza los datos segun la secuencia introducida en la tabla rol
     * @param sql sentencia a realizar
     * @param rol rol con los datos para actualizar
     * @return true/false
     */
    public boolean actualizarDatosRol(String sql, RolEntity rol){
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            sentencia.setString(1, rol.getRol());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return false;
    }

    /**
     * Funcion que te devuleve todos los roles de la base de datos
     * @return lista de roles
     */
    public HashSet<RolEntity> obtenerTodosLosRoles(){
        String sql = "SELECT * FROM rol";
        return leerSentenciaRol(sql);
    }
    
}
