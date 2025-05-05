package backend.es.nexphernandez.adict.games.model;
import java.util.Objects;
/**
 * @author nexphernanez
 * @version 1.0.0
 */
public class RolEntity {
    private int id;
    private String rol;

    /**
     * Constructor vacio
     */
    public RolEntity() {
    }

    /**
     * Constructor con el atributo rol
     * @param rol
     */
    public RolEntity(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RolEntity)) {
            return false;
        }
        RolEntity rolEntity = (RolEntity) o;
        return Objects.equals(id, rolEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getRol();
    }
    
}
