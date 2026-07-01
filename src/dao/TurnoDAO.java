package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class TurnoDAO {

    public void guardar(
            Turno turno) {

        try {

            Connection con =
                    ConexionBD
                            .getInstancia()
                            .getConexion();

            String sql =
                    "INSERT INTO turno "
                            + "(idTurno,tipo,estado,prioridad)"
                            + " VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    turno.getIdTurno());

            ps.setString(
                    2,
                    turno.getTipo().name());

            ps.setString(
                    3,
                    turno.getEstado().name());

            ps.setString(
                    4,
                    turno.getPrioridad().name());

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public List<Turno> listar() {

        List<Turno> turnos =
                new ArrayList<>();

        try {

            Connection con =
                    ConexionBD
                            .getInstancia()
                            .getConexion();

            String sql =
                    "SELECT * FROM turno";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Turno turno =
                        new Turno(
                                rs.getInt("idTurno"),
                                TipoTurno.valueOf(
                                        rs.getString("tipo")),
                                Prioridad.valueOf(
                                        rs.getString("prioridad")));

                turnos.add(turno);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return turnos;
    }
}