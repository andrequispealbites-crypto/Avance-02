package models;

import java.util.LinkedList;
import java.util.Queue;

public class ColaTurnos {

    private Queue<Turno> cola;

    public ColaTurnos() {

        cola = new LinkedList<>();
    }

    public void agregarTurno(
            Turno turno) {

        cola.offer(turno);
    }

    public Turno obtenerSiguiente() {

        return cola.poll();
    }

    public boolean estaVacia() {

        return cola.isEmpty();
    }

    public int cantidad() {

        return cola.size();
    }
}