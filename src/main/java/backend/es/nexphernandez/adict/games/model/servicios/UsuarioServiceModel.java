package backend.es.nexphernandez.adict.games.model.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;


import backend.es.nexphernandez.adict.games.model.UsuarioEntity;
import backend.es.nexphernandez.adict.games.model.abstractas.Conexion;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class UsuarioServiceModel extends Conexion{
    
    private HashSet<UsuarioEntity> usuarios;
    /**
     * Constructor vacio
     */
    public UsuarioServiceModel(){
        super();
        usuarios = new HashSet<>();
    }

    /**
     * Constructor con el path de la bbdd
     * @param unaRutaBD path de la bbdd
     */
    public UsuarioServiceModel(String unaRutaBD){
        super(unaRutaBD);
        usuarios = new HashSet<>(obtenerTodosLosUsuarios());
    }

    /**
     * Funcion que aplica una sentencia en la tabla usuario
     * @param sql sentencia a aplicar
     * @return lista de usuarios
     */
    public HashSet<UsuarioEntity> leerSentenciaUser(String sql){
        HashSet<UsuarioEntity> usuarios = new HashSet<>();
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                String userStr = resultado.getString("user");
                String emailStr = resultado.getString("email");
                String nombreStr = resultado.getString("nombre");
                String contraseniaStr = resultado.getString("password");
                UsuarioEntity usuario = new UsuarioEntity(userStr, emailStr, nombreStr, contraseniaStr);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            cerrar();
        }
        return usuarios;
    }

    /**
     * Funcion que actualiza los datos segun la secuencia introducida en la tabla usuario
     * @param sql sentencia a realizar
     * @param usuario usuario con los datos para actualizar
     * @return true/false
     */
    public boolean actualizarDatosUsuario(String sql, UsuarioEntity user){
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            sentencia.setString(1, user.getUser());
            sentencia.setString(2, user.getEmail());
            sentencia.setString(3, user.getNombre());
            sentencia.setString(4, user.getContrasenia());
            sentencia.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return false;
    }

    /**
     * Funcion para aniadir un usuario a la bbdd
     * @param usuario a aniadir
     * @return true/false
     */
    public boolean aniadirUsuario(UsuarioEntity usuario){
        if (usuario == null) {
            return false;
        }
        String sql = "INSERT INTO usuarios (user, email, nombre, password) Values (?,?,?,?)";
        return actualizarDatosUsuario(sql, usuario);
    }

    /**
     * Funcion para obtener un usuario por el nombre de usuario
     * @param user nombre de usuario
     * @return usuario buscado
     */
    public UsuarioEntity obtenerUsuarioPorUser(String nombreUsuario, String contrasenia){
        String sql = "Select * from usuarios " + "where user ='" + nombreUsuario + 
                    "' and password = '" + contrasenia + "'";
        ArrayList<UsuarioEntity> usuarios = new ArrayList<>(leerSentenciaUser(sql));
    
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
    }

    /**
     * Metodo para obtener los datos de un usuario por el email
     * @param email email del usuario buscado
     * @return usuario buscado
     */
    public UsuarioEntity obtenerUsuariosPorEmail(String email,  String contrasenia){
        String sql = "SELECT * FROM usuarios WHERE email = '" + email + 
                    "' and password = '" + contrasenia + "'";
        ArrayList<UsuarioEntity> usuarios = new ArrayList<>(leerSentenciaUser(sql));
        if (usuarios.isEmpty()) {
            return null;
        }
        return usuarios.get(0);
    }

    /**
     * Funcion que te devuleve todos los usuarios de la base de datos
     * @return lista de usuarios
     */
    public HashSet<UsuarioEntity> obtenerTodosLosUsuarios(){
        String sql = "SELECT * FROM usuarios";
        return leerSentenciaUser(sql);
    }

    /**
     * Metodo para borrar un usuario
     * @param user usuario a borrar
     * @return true/false
     */
    public boolean borrarUsuario(UsuarioEntity user) {
        String sql = "DELETE FROM usuarios WHERE user = ?";
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            sentencia.setString(1, user.getUser());
            sentencia.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Funcion que verifica si existe el emai en la bbdd
     * @param email a verificar
     * @return true/false
     */
    public boolean verificarEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Funcion que verifica si existe el usuario en la bbdd
     * @param nombreUsuario a verificar
     * @return true/false
     */
    public boolean verificarUsuario(String nombreUsuario) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE user = ?";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

