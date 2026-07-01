package models;

public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private Rol rol;

    public Usuario(int idUsuario, String nombreUsuario, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean esAdministrador() {
        return Rol.ADMINISTRADOR.equals(rol);
    }

    @Override
    public String toString() {
        return nombreUsuario + " (" + rol + ")";
    }
}
