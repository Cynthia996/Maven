package org.pb.contactos.jdbc.recursosConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Conexiondb {
    private static String url= "jdbc:mysql://localhost:3306/contactosdb?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "cynfede2229";
    private static Connection connection;


    public static Connection getInstance() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
