package backend.es.nexphernandez.adict.games.model;
import java.util.Objects;
/**
 * @author nexphernanez
 * @version 1.0.0
 */
public class GeneroEntitty {
    private String genero;

    /**
     * Constructor vacio
     */
    public GeneroEntitty() {
    }

    /**
     * Constructor con el atributo genero
     * @param genero
     */
    public GeneroEntitty(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GeneroEntitty)) {
            return false;
        }
        GeneroEntitty generoEntitty = (GeneroEntitty) o;
        return Objects.equals(genero, generoEntitty.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genero);
    }

    @Override
    public String toString() {
        return "{" +
            " genero='" + getGenero() + "'" +
            "}";
    }
    
}
