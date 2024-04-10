/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin_BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/ropa_tumbada?allowPublicKeyRetrieval=true&useSSL=false";
    private String USER = "";
    private String PASSWORD = "";
    private boolean stateConn = false;
    private Connection connection;
    
    public Conexion(String user, String pass) throws SQLException, ClassNotFoundException {
        this.USER = user;
        this.PASSWORD = pass;
        this.openConnection();
    }
    
    public boolean getStateConn() {
        return this.stateConn;
    }
    
    public Connection getConnection() {
        return this.connection;
    }
    
    public void openConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga el controlador JDBC
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            this.stateConn = true;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
    
    // Método para cerrar la conexión
    public void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}