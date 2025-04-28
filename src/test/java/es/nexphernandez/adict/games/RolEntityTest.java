package es.nexphernandez.adict.games;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import backend.es.nexphernandez.adict.games.model.RolEntity;

public class RolEntityTest {
    @Test
    void testConstructorVacio() {
        RolEntity rol = new RolEntity();
        assertNotNull(rol, "El constructor vacío debería crear un objeto no nulo");
        assertEquals(null, rol.getRol(), "El atributo 'rol' debería ser null por defecto");
    }

    @Test
    void testGettersAndSetters() {
        RolEntity rol = new RolEntity();
        rol.setRol("Usuario");

        assertEquals("Usuario", rol.getRol(), "El getter para 'rol' debería devolver el valor correcto");
    }

    @Test
    void testEqualsConDistintoTipo() {
        RolEntity rol = new RolEntity("Admin");
        String otroObjeto = "Esto no es un RolEntity";
        assertFalse(rol.equals(otroObjeto), "Un rol no debería ser igual a un objeto de otro tipo");
    }

    @Test
    void testToString() {
        RolEntity rol = new RolEntity("Admin");
        assertEquals("Admin", rol.toString(), "El método toString debería devolver el valor del atributo 'rol'");
    }
}
