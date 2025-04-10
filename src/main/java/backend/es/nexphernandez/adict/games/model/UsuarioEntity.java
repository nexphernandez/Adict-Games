package backend.es.nexphernandez.adict.games.model;
/**
 * @author nexphernandez
 * @version 1.0.0
 */

import java.util.HashSet;
import java.util.Objects;

public class UsuarioEntity {
    
    private String user;
    private String email;
    private String nombre;
    private String contrasenia;
    private String rol;
    private HashSet<JuegoEntity> juegosComprados;

    /**
     * Constructor vacio
     */
    public UsuarioEntity(){
        juegosComprados = new HashSet<>();
    }

    /**
     * Cosntructor con los atributos de la clase usuario
     * @param user del usuario
     * @param email del usuario
     * @param nombre del usuario
     * @param contrasenia del usuario
     */
    public UsuarioEntity(String user, String email,String nombre,String contrasenia){
        this.user = user;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.rol = "Usuario";
        this.juegosComprados = new HashSet<>();
    }

    /**
     * Cosntructor con los atributos de la clase usuario
     * @param user del usuario
     * @param email del usuario
     * @param nombre del usuario
     * @param contrasenia del usuario
     * @param rol del usuario
     */
    public UsuarioEntity(String user, String email,String nombre,String contrasenia, String rol){
        this.user = user;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.rol = rol;
        this.juegosComprados = new HashSet<>();
    }
    
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    } 

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public HashSet<JuegoEntity> getJuegosComprados() {
        return this.juegosComprados;
    }

    public void setJuegosComprados(HashSet<JuegoEntity> juegosComprados) {
        this.juegosComprados = juegosComprados;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity usuarioEntity = (UsuarioEntity) o;
        return Objects.equals(email, usuarioEntity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return getUser() 
            + "/n" + getEmail() 
            + "/n" + getNombre() 
            + "/n"  + getContrasenia() +
            (getJuegosComprados().isEmpty()? "":getJuegosComprados());
    }
    

}
