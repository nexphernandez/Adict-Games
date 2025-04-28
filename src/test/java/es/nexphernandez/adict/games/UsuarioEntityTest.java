package es.nexphernandez.adict.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import backend.es.nexphernandez.adict.games.model.JuegoEntity;
import backend.es.nexphernandez.adict.games.model.RolEntity;
import backend.es.nexphernandez.adict.games.model.UsuarioEntity;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioEntityTest {
    private Connection connection;
    UsuarioEntity usuario;

    @BeforeAll
    void setupDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/adictGamesTest.db");
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
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        assertNotNull(usuarioEntity);
    }

    @Test
    void testConstructor() {
        UsuarioEntity usuarioEntity = new UsuarioEntity("ad", "sfgs", "fss", "sfs", null);
        assertNotNull(usuarioEntity);
    }

    @Test
    void testEquals() {
        UsuarioEntity usuario1 = new UsuarioEntity("Juan1", "juan@example.com", "Juan", "secreta123");
        UsuarioEntity usuario2 = new UsuarioEntity("Juanito", "juan@example.com", "Juan", "otro123");

        assertTrue(usuario1.equals(usuario2), "Dos usuarios con el mismo email deberían ser iguales");
    }

    @Test
    void testNotEquals() {
        UsuarioEntity usuario1 = new UsuarioEntity("Juan1", "juan@example.com", "Juan", "secreta123");
        UsuarioEntity usuario2 = new UsuarioEntity("Carlos", "carlos@example.com", "Carlos", "password123");

        assertFalse(usuario1.equals(usuario2), "Dos usuarios con diferentes emails no deberían ser iguales");
    }

    @Test
    void testEqualsConNull() {
        UsuarioEntity usuario = new UsuarioEntity("Juan1", "juan@example.com", "Juan", "secreta123");
        assertFalse(usuario.equals(null), "Un usuario no debería ser igual a null");
    }

    @Test
    void testEqualsConMismoObjeto() {
        UsuarioEntity usuario = new UsuarioEntity("Juan1", "juan@example.com", "Juan", "secreta123");
        assertTrue(usuario.equals(usuario), "Un usuario debería ser igual a sí mismo");
    }

    @Test
    void testEqualsConDistintoTipo() {
        UsuarioEntity usuario = new UsuarioEntity("Juan1", "juan@example.com", "Juan", "secreta123");
        String otroObjeto = "Esto no es un UsuarioEntity";
        assertFalse(usuario.equals(otroObjeto), "Un usuario no debería ser igual a un objeto de otro tipo");
    }

    @Test
    void testHashCode() {
        UsuarioEntity usuario1 = new UsuarioEntity("Juan1", "juan@example.com", "Juan", "secreta123");
        UsuarioEntity usuario2 = new UsuarioEntity("Juanito", "juan@example.com", "Juan", "otro123");

        assertEquals(usuario1.hashCode(), usuario2.hashCode());
    }

    @Test
    void testToString() {
        String nombre = "Juan";
        String contrasenia = "secreta123";
        String email = "juan@example.com";
        String user = "juanito";

        RolEntity rol = new RolEntity("Admin");
        HashSet<JuegoEntity> juegosComprados = new HashSet<>();
        juegosComprados.add(new JuegoEntity("Zelda", "urlImagen", "codigo123", null));
        juegosComprados.add(new JuegoEntity("Mario", "urlImagen", "codigo456", null));

        UsuarioEntity usuario = new UsuarioEntity(user, email, nombre, contrasenia);
        usuario.setRol(rol);
        usuario.setJuegosComprados(juegosComprados);

        String expectedToString = user + "/n" + email + "/n" + nombre + "/n" + contrasenia +
                 juegosComprados.toString();

        assertEquals(expectedToString, usuario.toString(),
                "El método toString debería devolver el formato esperado con todos los atributos");
    }

    @Test
    void testGettersAndSetters() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUser("nex");
        usuario.setEmail("nex@example.com");
        usuario.setNombre("Nex Fernandez");
        usuario.setContrasenia("contrasenia123");

        assertEquals("nex", usuario.getUser(), "El getter para 'user' debería devolver el valor correcto");
        assertEquals("nex@example.com", usuario.getEmail(),
                "El getter para 'email' debería devolver el valor correcto");
        assertEquals("Nex Fernandez", usuario.getNombre(),
                "El getter para 'nombre' debería devolver el valor correcto");
        assertEquals("contrasenia123", usuario.getContrasenia(),
                "El getter para 'contrasenia' debería devolver el valor correcto");
    }

    @Test
    void testRol() {
        RolEntity rol = new RolEntity("Admin");
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setRol(rol);

        assertEquals(rol, usuario.getRol(), "El getter para 'rol' debería devolver el rol asignado");
    }

    @Test
    void testJuegosComprados() {
        HashSet<JuegoEntity> juegos = new HashSet<>();
        juegos.add(new JuegoEntity("Zelda", "urlImagen", "codigo123", null));
        juegos.add(new JuegoEntity("Mario", "urlImagen", "codigo456", null));

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setJuegosComprados(juegos);

        assertEquals(2, usuario.getJuegosComprados().size(), "El usuario debería tener 2 juegos comprados");
    }

}
