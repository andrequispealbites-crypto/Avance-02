package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.Empleado;

public class EmpleadoDAO {

    public void guardar(
            Empleado empleado) {

        try {

            Connection con =
                    ConexionBD
                            .getInstancia()
                            .getConexion();

            String sql =
                    "INSERT INTO empleado "
                            + "(codigo,nombre)"
                            + " VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    empleado.getCodigo());

            ps.setString(
                    2,
                    empleado.getNombre());

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}