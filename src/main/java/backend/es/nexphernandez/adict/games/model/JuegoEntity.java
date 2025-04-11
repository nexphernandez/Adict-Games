package backend.es.nexphernandez.adict.games.model;
import java.util.HashSet;
import java.util.Objects;
/**
 * @author nexphernandez
 * @version 1.0.0
 */
public class JuegoEntity {
    private String nombre;
    private String urlImangen;
    private String codigo;
    private HashSet<GeneroEntity> generos;

    /**
     * Constructor vacio
     */
    public JuegoEntity() {
        this.generos = new HashSet<>();
    }

    /**
     * Constructor con los atributos de la clase
     * @param nombre del juego
     * @param urlImagen del juego
     * @param codigo del juego
     */

    public JuegoEntity(String nombre, String urlImangen,String codigo, HashSet<GeneroEntity> generos) {
        this.nombre = nombre;
        this.urlImangen = urlImangen;
        this.codigo = codigo;
        this.generos = generos;
    }
    

    public HashSet<GeneroEntity> getGeneros() {
        return this.generos;
    }

    public void setGeneros(HashSet<GeneroEntity> generos) {
        this.generos = generos;
    }

    public String getUrlImangen() {
        return this.urlImangen;
    }

    public void setUrlImangen(String urlImangen) {
        this.urlImangen = urlImangen;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JuegoEntity)) {
            return false;
        }
        JuegoEntity juegoEntity = (JuegoEntity) o;
        return Objects.equals(codigo, juegoEntity.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }


    @Override
    public String toString() {
        return  getNombre() + "," +
             getUrlImangen() + "," +
             getCodigo() + "," +
             getGeneros();
    }
    
    
}
