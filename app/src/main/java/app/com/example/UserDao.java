package app.com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public String getHashedPassword(String nombre) throws SQLException {
        String query = "SELECT contras FROM usuarios WHERE nombre = ?";
        String hashedPassword = null;

        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                hashedPassword = rs.getString("contras");
            }
        }

        return hashedPassword;
    }
    
    public void saveUser(String nombre, String correo, String hashedPassword) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, correo, contras) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(3, hashedPassword);
            stmt.executeUpdate();
        }
    }
    
    public boolean nombreUsado(String nombre) throws SQLException {
        String query = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";
        
        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}


