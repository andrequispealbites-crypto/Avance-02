package services;

import models.Auditoria;
import models.Usuario;

public class AuditoriaService {

    public void registrarAccion(Usuario usuario, String accion) {
        String responsable = usuario == null ? "Sistema" : usuario.toString();
        Auditoria.registrar(responsable + " -> " + accion);
    }
}
