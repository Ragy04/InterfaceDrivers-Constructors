package Conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionSql {
    private static String servidor = "jdbc:mysql://localhost/formula";
    private static String user = "root";
    private static String pass = "";
    private static String driver = "com.mysql.cj.jdbc.Driver"; // Asegúrate de usar el driver correcto
    private Connection conexion;

    public ConexionSql() {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(servidor, user, pass);
            JOptionPane.showMessageDialog(null, "La conexion fue exitosa");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de clase no encontrada: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexion: " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}

