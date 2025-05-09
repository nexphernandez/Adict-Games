package backend.es.nexphernandez.adict.games.model.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import backend.es.nexphernandez.adict.games.model.GeneroEntity;
import backend.es.nexphernandez.adict.games.model.JuegoEntity;
import backend.es.nexphernandez.adict.games.model.abstractas.Conexion;

/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class JuegoServiceModel extends Conexion {
    GeneroServiceModel generoServiceModel;

    /**
     * Constructor vacio
     */
    public JuegoServiceModel() {
        generoServiceModel = new GeneroServiceModel();
    }

    /**
     * Constructor con el path de la bbdd
     * 
     * @param unaRutaBD path de la bbdd
     */
    public JuegoServiceModel(String unaRutaBD) {
        super(unaRutaBD);
        generoServiceModel = new GeneroServiceModel(unaRutaBD);
    }

    /**
     * Funcion que aplica una sentencia en la tabla juego
     * 
     * @param sql sentencia a aplicar
     * @return lista de juegos
     */
    public HashSet<JuegoEntity> leerSentenciaJuego(String sql) {
        HashSet<JuegoEntity> juegos = new HashSet<>();
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                int idJuego = resultado.getInt("id");
                String nombreStr = resultado.getString("nombre");
                String urlImangenStr = resultado.getString("urlImagen");
                String codigoStr = resultado.getString("codigo");
                HashSet<GeneroEntity> generos = obtenerGenerosPorJuego(idJuego);
                JuegoEntity juego = new JuegoEntity(nombreStr, urlImangenStr, codigoStr, generos);
                juegos.add(juego);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return juegos;
    }

    /**
     * Funcion para obtener los generos de un juego sabiendo el id
     * 
     * @param juegoId id del juego a buscar
     * @return lista de geneneros del juego
     */
    private HashSet<GeneroEntity> obtenerGenerosPorJuego(int juegoId) {
        String sql = "Select * from juego_genero where juego_id ='" + juegoId + "'";
        HashSet<GeneroEntity> generos = new HashSet<>();
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("genero_id");
                generos.addAll(generoServiceModel.buscarGeneroporId(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generos;
    }

    /**
     * Funcion para aniadir un juego a la bbdd
     * 
     * @param juego a aniadir
     * @return true/false
     */
    public boolean aniadirJuego(JuegoEntity juego) {
        if (juego == null) {
            return false;
        }
        HashSet<GeneroEntity> generos = generoServiceModel.obtenerTodosLosGeneros();
        for (GeneroEntity genero : juego.getGeneros()) {
            if (!generos.contains(genero)) {
                generoServiceModel.aniadirGenero(genero);
            }
        }
        String sql = "INSERT INTO juegos (nombre, urlImagen, codigo) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO juego_genero (juego_id, genero_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setString(1, juego.getNombre());
            stmt.setString(2, juego.getUrlImangen());
            stmt.setString(3, juego.getCodigo());
            stmt.executeUpdate();
            int id = obtenerIdPorNombre(juego.getNombre());
            for (GeneroEntity genero : juego.getGeneros()) {
                PreparedStatement sentencia = conectar().prepareStatement(sql2);
                sentencia.setInt(1, id);
                sentencia.setInt(2, genero.getId());
                sentencia.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }

    }

    public int obtenerIdPorNombre(String nombre){
        String sql = "select id from juegos where nombre='" + nombre + "'";
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            ResultSet resultSet = sentencia.executeQuery();
            int id = resultSet.getInt("id");
            sentencia.execute();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return -1;
    }

    /**
     * Funcion para obtener un juego por el nombre
     * 
     * @param juego nombre de juego
     * @return juego buscado
     */
    public JuegoEntity obtenerJuegosPorNombre(String nombre) {
        String sql = "SELECT * FROM juegos WHERE nombre = '" + nombre + "'";
        ArrayList<JuegoEntity> juegos = new ArrayList<>(leerSentenciaJuego(sql));
        if (juegos.isEmpty()) {
            return null;
        }
        return juegos.get(0);
    }

    /**
     * Metodo para borrar un juego
     * 
     * @param juego juego a borrar
     * @return true/false
     */
    public boolean borrarJuego(JuegoEntity juego) {
        String sql = "DELETE FROM juegos where codigo ='" + juego.getCodigo() + "'";
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            return sentencia.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return false;
    }

    public HashSet<JuegoEntity> obtenerTodosLosJuegos(){
        String sql = "Select * from juegos";
        return leerSentenciaJuego(sql);
    }

}
