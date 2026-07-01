package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.DocumentoIdentidad;

public class ClienteDAO {

    public void guardar(
            Cliente cliente) {

        try {

            Connection con =
                    ConexionBD
                            .getInstancia()
                            .getConexion();

            String sql =
                    "INSERT INTO cliente "
                            + "(codigo,nombre,dni)"
                            + " VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    cliente.getCodigo());

            ps.setString(
                    2,
                    cliente.getNombre());

            ps.setString(
                    3,
                    cliente.getDocumento()
                            .getNumero());

            ps.executeUpdate();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public List<Cliente> listar() {

        List<Cliente> clientes =
                new ArrayList<>();

        try {

            Connection con =
                    ConexionBD
                            .getInstancia()
                            .getConexion();

            String sql =
                    "SELECT * FROM cliente";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                DocumentoIdentidad doc =
                        new DocumentoIdentidad(
                                rs.getString("dni"),
                                "DNI");

                Cliente cliente =
                        new Cliente(
                                rs.getInt("codigo"),
                                rs.getString("nombre"),
                                doc);

                clientes.add(cliente);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return clientes;
    }
}