package es.nexphernandez.adict.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import backend.es.nexphernandez.adict.games.model.GeneroEntity;
import backend.es.nexphernandez.adict.games.model.JuegoEntity;

public class JuegoEntityTest {

    @Test
    void testConstructorVacio() {
        JuegoEntity juego = new JuegoEntity();
        assertNotNull(juego, "El constructor vacío debería crear un objeto no nulo");
        assertNotNull(juego.getGeneros(), "El conjunto de géneros debería inicializarse como un HashSet vacío");
    }

    @Test
    void testConstructorConParametros() {
        HashSet<GeneroEntity> generos = new HashSet<>();
        generos.add(new GeneroEntity(1, "Aventura"));
        generos.add(new GeneroEntity(2, "Acción"));

        JuegoEntity juego = new JuegoEntity("Zelda", "urlImagen", "codigo123", generos);

        assertEquals("Zelda", juego.getNombre(), "El nombre debería coincidir con el valor proporcionado");
        assertEquals("urlImagen", juego.getUrlImangen(),
                "La URL de la imagen debería coincidir con el valor proporcionado");
        assertEquals("codigo123", juego.getCodigo(), "El código debería coincidir con el valor proporcionado");
        assertEquals(2, juego.getGeneros().size(), "El conjunto de géneros debería contener 2 elementos");
    }

    @Test
    void testGettersAndSetters() {
        JuegoEntity juego = new JuegoEntity();
        juego.setNombre("Mario");
        juego.setUrlImangen("urlMario");
        juego.setCodigo("codigoMario");

        assertEquals("Mario", juego.getNombre(), "El getter para 'nombre' debería devolver el valor correcto");
        assertEquals("urlMario", juego.getUrlImangen(),
                "El getter para 'urlImagen' debería devolver el valor correcto");
        assertEquals("codigoMario", juego.getCodigo(), "El getter para 'codigo' debería devolver el valor correcto");
    }

    @Test
    void testEquals() {
        JuegoEntity juego1 = new JuegoEntity("Zelda", "urlImagen", "codigo123", null);
        JuegoEntity juego2 = new JuegoEntity("Mario", "otraUrl", "codigo123", null);

        assertTrue(juego1.equals(juego2), "Dos juegos con el mismo código deberían ser iguales");
    }

    @Test
    void testNotEquals() {
        JuegoEntity juego1 = new JuegoEntity("Zelda", "urlImagen", "codigo123", null);
        JuegoEntity juego2 = new JuegoEntity("Mario", "otraUrl", "codigo456", null);

        assertFalse(juego1.equals(juego2), "Dos juegos con códigos diferentes no deberían ser iguales");
    }

    @Test
    void testEqualsConMismoObjeto() {
        JuegoEntity juego = new JuegoEntity("Zelda", "urlImagen", "codigo123", null);
        assertTrue(juego.equals(juego), "Un juego debería ser igual a sí mismo");
    }

    @Test
    void testEqualsConDistintoTipo() {
        JuegoEntity juego = new JuegoEntity("Zelda", "urlImagen", "codigo123", null);
        String otroObjeto = "Esto no es un JuegoEntity";
        assertFalse(juego.equals(otroObjeto), "Un juego no debería ser igual a un objeto de otro tipo");
    }

    @Test
    void testHashCode() {
        JuegoEntity juego1 = new JuegoEntity("Zelda", "urlImagen", "codigo123", null);
        JuegoEntity juego2 = new JuegoEntity("Mario", "otraUrl", "codigo123", null);

        assertEquals(juego1.hashCode(), juego2.hashCode(),
                "Dos juegos con el mismo código deberían tener el mismo hashCode");
    }

    @Test
    void testToString() {
        HashSet<GeneroEntity> generos = new HashSet<>();
        generos.add(new GeneroEntity(1, "Aventura"));
        generos.add(new GeneroEntity(2, "Acción"));

        JuegoEntity juego = new JuegoEntity("Zelda", "urlImagen", "codigo123", generos);

        String expectedToString = "Zelda,urlImagen,codigo123," + generos.toString();
        assertEquals(expectedToString, juego.toString(), "El método toString debería devolver el formato esperado");
    }

    @Test
    void testGeneros() {
        HashSet<GeneroEntity> generos = new HashSet<>();
        generos.add(new GeneroEntity(1, "Aventura"));
        generos.add(new GeneroEntity(2, "Acción"));

        JuegoEntity juego = new JuegoEntity();
        juego.setGeneros(generos);

        assertEquals(2, juego.getGeneros().size(), "El conjunto de géneros debería contener 2 elementos");
        assertTrue(juego.getGeneros().stream().anyMatch(g -> g.getGenero().equals("Aventura")),
                "El género 'Aventura' debería estar presente");
        assertTrue(juego.getGeneros().stream().anyMatch(g -> g.getGenero().equals("Acción")),
                "El género 'Acción' debería estar presente");
    }
}