package es.nexphernandez.adict.games.servicios;

import backend.es.nexphernandez.adict.games.model.UsuarioEntity;
import backend.es.nexphernandez.adict.games.model.servicios.UsuarioServiceModel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioServiceModelTest {

    private UsuarioServiceModel usuarioService;
    private Connection connection;
    UsuarioEntity usuario;

    @BeforeAll
    void setupDatabase() throws Exception {

        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/adictGamesTest.db");
        usuarioService = new UsuarioServiceModel("src/main/resources/adictGamesTest.db");

        usuario = new UsuarioEntity("nex", "nex@example.com", "Nex Fernandez", "contrasenia123");

    }

    @BeforeEach
    void resetDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM usuarios");
        }
    }

    @AfterAll
    void closeDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM usuarios");
        }
        connection.close();
    }

    @Test
    void testConstructorVacio() {
        UsuarioServiceModel servicio = new UsuarioServiceModel();
        assertNotNull(servicio, "El servicio no debería ser nulo");
    }

    @Test
    void testAniadirUsuario() {
        boolean insertado = usuarioService.aniadirUsuario(usuario);
        assertTrue(insertado, "El usuario debería insertarse correctamente");
    }

    @Test
    void testAniadirUsuarioNull() {
        UsuarioEntity usuarioNull = null;
        boolean insertado = usuarioService.aniadirUsuario(usuarioNull);
        assertFalse(insertado, "El usuario no debería insertarse");
    }

    @Test
    void testObtenerUsuarioPorUser() {
        boolean insertado = usuarioService.aniadirUsuario(usuario);
        assertTrue(insertado, "El usuario debería insertarse correctamente");

        UsuarioEntity usuarioRecuperado = usuarioService.obtenerUsuarioPorUser("nex", "contrasenia123");
        assertNotNull(usuarioRecuperado, "El usuario debería recuperarse");
        assertEquals("nex@example.com", usuarioRecuperado.getEmail(), "El email debería coincidir");
        assertEquals("Nex Fernandez", usuarioRecuperado.getNombre(), "El nombre debería coincidir");
    }

    @Test
    void testObtenerUsuarioPorUserEmpty() {
        UsuarioEntity usuarioRecuperado = usuarioService.obtenerUsuarioPorUser("pep", "1585");
        assertNull(usuarioRecuperado, "El usuario no debería recuperarse");
    }

    @Test
    void testObtenerUsuariosPorEmail() {
        boolean insertado = usuarioService.aniadirUsuario(usuario);
        assertTrue(insertado, "El usuario debería insertarse correctamente");

        UsuarioEntity usuarioRecuperado = usuarioService.obtenerUsuariosPorEmail("nex@example.com", "contrasenia123");

        assertNotNull(usuarioRecuperado, "El usuario debería recuperarse correctamente");
        assertEquals(usuario.getEmail(), usuarioRecuperado.getEmail(), "El email debería coincidir");
        assertEquals(usuario.getNombre(), usuarioRecuperado.getNombre(), "El nombre debería coincidir");
    }

    @Test
    void testVerificarEmail() {
        boolean insertado = usuarioService.aniadirUsuario(usuario);
        assertTrue(insertado, "El usuario debería insertarse correctamente");

        boolean emailExiste = usuarioService.verificarEmail("nex@example.com");
        assertTrue(emailExiste, "El email debería existir");

        boolean emailNoExiste = usuarioService.verificarEmail("noexiste@example.com");
        assertFalse(emailNoExiste, "El email no debería existir");
    }

    @Test
    void testVerificarUsuario() {
        // Insertamos un usuario en la base de datos
        boolean insertado = usuarioService.aniadirUsuario(usuario);
        assertTrue(insertado, "El usuario debería insertarse correctamente");

        // Verificamos si el usuario existe
        boolean usuarioExiste = usuarioService.verificarUsuario("nex");
        assertTrue(usuarioExiste, "El usuario debería existir");

        // Verificamos un usuario que no existe
        boolean usuarioNoExiste = usuarioService.verificarUsuario("usuarioInexistente");
        assertFalse(usuarioNoExiste, "El usuario no debería existir");
    }

    @Test
    void testBorrarUsuario() {
        boolean insertado = usuarioService.aniadirUsuario(usuario);
        assertTrue(insertado, "El usuario debería insertarse correctamente");

        boolean borrado = usuarioService.borrarUsuario(usuario);
        assertTrue(borrado, "El usuario debería borrarse correctamente");

        UsuarioEntity usuarioRecuperado = usuarioService.obtenerUsuarioPorUser("nex", "contrasenia123");
        assertNull(usuarioRecuperado, "El usuario no debería recuperarse después de ser borrado");
    }

    @Test
    void testObtenerUsuarioPorEmailEmpty() {
        UsuarioEntity usuarioRecuperado = usuarioService.obtenerUsuariosPorEmail("pepgmail.com@", "1585");
        assertNull(usuarioRecuperado, "El usuario no debería recuperarse");
    }
}
