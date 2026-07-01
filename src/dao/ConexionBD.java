package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;

    private Connection conexion;

    private static final String URL =
            "jdbc:mysql://localhost:3306/banco";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "";

    private ConexionBD() {

        try {

            conexion =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            System.out.println(
                    "Conexión exitosa");

        } catch(SQLException e) {

            e.printStackTrace();
        }
    }

    public static ConexionBD getInstancia() {

        if(instancia == null) {

            instancia =
                    new ConexionBD();
        }

        return instancia;
    }

    public Connection getConexion() {

        return conexion;
    }
}