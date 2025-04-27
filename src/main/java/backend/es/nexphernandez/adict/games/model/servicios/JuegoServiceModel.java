package backend.es.nexphernandez.adict.games.model.servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        generoServiceModel = new GeneroServiceModel();
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
                String urlImangenStr = resultado.getString("urlImangen");
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

    private HashSet<GeneroEntity> obtenerGenerosPorJuego(int juegoId) {
        String sql = "SELECT g.id, g.nombre FROM generos AS g " +
                     "INNER JOIN juego_genero AS jg ON g.id = jg.genero_id " +
                     "WHERE jg.juego_id = ?";
        HashSet<GeneroEntity> generos = new HashSet<>();
        try (PreparedStatement stmt = conectar().prepareStatement(sql)) {
            stmt.setInt(1, juegoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                generos.add(new GeneroEntity(id, nombre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generos;
    }

    /**
     * Funcion que actualiza los datos segun la secuencia introducida en la tabla
     * juego
     * 
     * @param sql         sentencia a realizar
     * @param JuegoEntity juego con los datos para actualizar
     * @return true/false
     */
    public boolean actualizarDatosJuego(String sql, JuegoEntity juego) {
        try {
            PreparedStatement sentencia = conectar().prepareStatement(sql);
            sentencia.setString(1, juego.getNombre());
            sentencia.setString(2, juego.getUrlImangen());
            sentencia.setString(3, juego.getCodigo());
            HashSet<GeneroEntity> generos = juego.getGeneros();
            String sqlGenero = "Update nombre from generos as g " +
                    "inner join juego_genero as jg " +
                    "on g.id = jg.juego_id " +
                    "inner join juegos as j " +
                    "on jg.genero_id = j.id" +
                    " Where j.codigo = " + juego.getCodigo();
            for (GeneroEntity generoBuscado : generos) {
                generoServiceModel.actualizarDatosGenero(sqlGenero, generoBuscado);
            }
            sentencia.setString(4, juego.getNombre());
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
     * Funcion para aniadir un juego a la bbdd
     * 
     * @param juego a aniadir
     * @return true/false
     */
    public boolean aniadirJuego(JuegoEntity juego) {
        if (juego == null) {
            return false;
        }
        String sql = "INSERT INTO juegos (nombre, urlImagen, codigo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, juego.getNombre());
            stmt.setString(2, juego.getUrlImangen());
            stmt.setString(3, juego.getCodigo());
            stmt.executeUpdate();

            // Obtener el ID del juego insertado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int juegoId = rs.getInt(1);

                // Insertar géneros en la tabla relacionada
                for (GeneroEntity genero : juego.getGeneros()) {
                    String sqlGenero = "INSERT INTO juego_genero (juego_id, genero_id) VALUES (?, ?)";
                    try (PreparedStatement stmtGenero = conectar().prepareStatement(sqlGenero)) {
                        stmtGenero.setInt(1, juegoId);
                        stmtGenero.setInt(2, genero.getId());
                        stmtGenero.executeUpdate();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
        String sql = "DELETE FROM juegos where nombre ='" + juego + "'";
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
