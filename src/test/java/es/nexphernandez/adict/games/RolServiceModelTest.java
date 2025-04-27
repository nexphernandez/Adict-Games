package es.nexphernandez.adict.games;

import backend.es.nexphernandez.adict.games.model.RolEntity;
import backend.es.nexphernandez.adict.games.model.servicios.RolServiceModel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RolServiceModelTest {

    private RolServiceModel rolServiceModel;
    private Connection connection;
    private RolEntity rolEntity;

    @BeforeAll
    void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/adictGamesTest.db");
        rolServiceModel = new RolServiceModel("src/main/resources/adictGamesTest.db");

        rolEntity = new RolEntity("Administrador");
    }

    @BeforeEach
    void resetDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM rol");
        }
    }

    @AfterAll
    void closeDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM rol");
        }
        connection.close();
    }

    @Test
    void testConstructorVacio() {
        RolServiceModel servicio = new RolServiceModel();
        assertNotNull(servicio, "El servicio no debería ser nulo");
    }

    @Test
    void testAniadirRol() {
        boolean aniadido = rolServiceModel.aniadirRol(rolEntity);
        assertTrue(aniadido, "El rol debería añadirse correctamente");

        HashSet<RolEntity> roles = rolServiceModel.obtenerTodosLosRoles();
        assertEquals(1, roles.size(), "Debería haber un rol en la base de datos");
        assertTrue(roles.contains(rolEntity), "El rol añadido debería estar en la base de datos");
    }

    @Test
    void testObtenerTodosLosRoles() {
        rolServiceModel.aniadirRol(rolEntity);

        HashSet<RolEntity> roles = rolServiceModel.obtenerTodosLosRoles();
        assertNotNull(roles, "La lista de roles no debería ser nula");
        assertEquals(1, roles.size(), "Debería haber un rol en la base de datos");
        assertTrue(roles.contains(rolEntity), "El rol añadido debería estar en la lista de roles");
    }
}