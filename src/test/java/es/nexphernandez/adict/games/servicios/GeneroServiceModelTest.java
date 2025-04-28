package es.nexphernandez.adict.games.servicios;

import backend.es.nexphernandez.adict.games.model.GeneroEntity;
import backend.es.nexphernandez.adict.games.model.servicios.GeneroServiceModel;

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
class GeneroServiceModelTest {

    private GeneroServiceModel generoServiceModel;
    private Connection connection;
    private GeneroEntity generoEntity;

    @BeforeAll
    void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/adictGamesTest.db");
        generoServiceModel = new GeneroServiceModel("src/main/resources/adictGamesTest.db");

        generoEntity = new GeneroEntity("Acción");
    }

    @BeforeEach
    void resetDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM generos");
        }
    }

    @AfterAll
    void closeDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM generos");
        }
        connection.close();
    }

    @Test
    void testConstructorVacio() {
        GeneroServiceModel servicio = new GeneroServiceModel();
        assertNotNull(servicio, "El servicio no debería ser nulo");
    }

    @Test
    void testAniadirGenero() {
        boolean aniadido = generoServiceModel.aniadirGenero(generoEntity);
        assertTrue(aniadido, "El género debería añadirse correctamente");

        // Verificamos que el género se haya añadido
        HashSet<GeneroEntity> generos = generoServiceModel.obtenerTodosLosGeneros();
        assertNotNull(generos, "La lista de géneros no debería ser nula");
        assertEquals(1, generos.size(), "Debería haber un género en la base de datos");
        assertTrue(generos.contains(generoEntity), "El género añadido debería estar en la base de datos");
    }

    @Test
    void testObtenerTodosLosGeneros() {
        // Añadimos un género
        generoServiceModel.aniadirGenero(generoEntity);

        // Obtenemos todos los géneros
        HashSet<GeneroEntity> generos = generoServiceModel.obtenerTodosLosGeneros();
        assertNotNull(generos, "La lista de géneros no debería ser nula");
        assertEquals(1, generos.size(), "Debería haber un género en la base de datos");
        assertTrue(generos.contains(generoEntity), "El género añadido debería estar en la lista de géneros");
    }

    @Test
    void testBorrarGenero() {
        // Añadimos un género
        generoServiceModel.aniadirGenero(generoEntity);

        // Borramos el género
        boolean borrado = generoServiceModel.borrarGenero(generoEntity);
        assertTrue(borrado, "El género debería borrarse correctamente");

        // Verificamos que la lista de géneros esté vacía
        HashSet<GeneroEntity> generos = generoServiceModel.obtenerTodosLosGeneros();
        assertTrue(generos.isEmpty(), "No debería haber géneros en la base de datos después de borrar");
    }
}