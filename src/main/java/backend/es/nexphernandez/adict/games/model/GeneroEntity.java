package backend.es.nexphernandez.adict.games.model;
import java.util.Objects;
/**
 * @author nexphernanez
 * @version 1.0.0
 */
public class GeneroEntity {
    private int id;
    private String genero;

    /**
     * Constructor vacio
     */
    public GeneroEntity() {
    }

    /**
     * Constructor con el atributo genero
     * @param genero
     */
    public GeneroEntity(int id,String genero) {
        this.id = id;
        this.genero = genero;
    }

    /**
     * Constructor con el atributo genero
     * @param genero
     */
    public GeneroEntity(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
        if (!(o instanceof GeneroEntity)) {
            return false;
        }
        GeneroEntity generoEntity = (GeneroEntity) o;
        return Objects.equals(id, generoEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getGenero();
    }
    
}
