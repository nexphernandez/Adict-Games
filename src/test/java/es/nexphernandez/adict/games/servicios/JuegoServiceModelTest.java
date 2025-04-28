package es.nexphernandez.adict.games.servicios;

import backend.es.nexphernandez.adict.games.model.GeneroEntity;
import backend.es.nexphernandez.adict.games.model.JuegoEntity;
import backend.es.nexphernandez.adict.games.model.servicios.JuegoServiceModel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JuegoServiceModelTest {

    private JuegoServiceModel juegoServiceModel;
    private Connection connection;
    private JuegoEntity juegoEntity;

    @BeforeAll
    void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/adictGamesTest.db");
        juegoServiceModel = new JuegoServiceModel("src/main/resources/adictGamesTest.db");
        HashSet<GeneroEntity> generos =  new HashSet<>();
        GeneroEntity generoEntity = new GeneroEntity("Aventura");
        generos.add(generoEntity);
        juegoEntity = new JuegoEntity("Zelda", null, "fnjkfnj", generos);
    
    }

    @BeforeEach
    void resetDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM juegos");
        }
    }

    @AfterAll
    void closeDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM juegos");
        }
        connection.close();
    }

    @Test
    void testConstructorVacio(){
        JuegoServiceModel juegoServiceModel = new JuegoServiceModel();
        assertNotNull(juegoServiceModel);
    }
    @Test
    void testAniadirJuego() {
        boolean aniadido = juegoServiceModel.aniadirJuego(juegoEntity);
        assertTrue(aniadido, "El juego debería añadirse correctamente");
    }

    @Test
    void testObtenerJuegosPorNombre() {

    }

    @Test
    void testBorrarJuego() {
        juegoServiceModel.aniadirJuego(juegoEntity);
        boolean borrado = juegoServiceModel.borrarJuego(juegoEntity);
        assertTrue(borrado, "El juego debería borrarse correctamente");
    }
}