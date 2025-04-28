package es.nexphernandez.adict.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import backend.es.nexphernandez.adict.games.model.GeneroEntity;
import backend.es.nexphernandez.adict.games.model.RolEntity;

public class GeneroEntityTest {
    @Test
    void testToString() {
        RolEntity rol = new RolEntity("Admin");
        assertEquals("Admin", rol.toString(), "El método toString debería devolver el valor del atributo 'rol'");
    }

    @Test
    void testGettersAndSetters() {
        GeneroEntity genero = new GeneroEntity();
        genero.setId(2);
        genero.setGenero("Deportes");

        assertEquals(2, genero.getId(), "El getter para 'id' debería devolver el valor correcto");
        assertEquals("Deportes", genero.getGenero(), "El getter para 'genero' debería devolver el valor correcto");
    }

    @Test
    void testEqualsConMismoObjeto() {
        GeneroEntity genero = new GeneroEntity(1, "Aventura");
        assertTrue(genero.equals(genero), "Un género debería ser igual a sí mismo");
    }

    @Test
    void testEqualsConDistintoTipo() {
        GeneroEntity genero = new GeneroEntity(1, "Aventura");
        String otroObjeto = "Esto no es un GeneroEntity";
        assertFalse(genero.equals(otroObjeto), "Un género no debería ser igual a un objeto de otro tipo");
    }
}
