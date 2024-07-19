package app.com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;



public class DarDeBaja extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nombre=request.getParameter("nombre");
		String contras=request.getParameter("contras");
		
		try {			
            // Recuperar la contraseña hasheada de la base de datos
            UserDao userDao = new UserDao();
            String hashedPassword = userDao.getHashedPassword(nombre);

            if (hashedPassword != null && BCrypt.checkpw(contras, hashedPassword)) {
            	
            	String consulta="DELETE FROM usuarios WHERE nombre = ? AND contras = ?";
            	Connection conex = ConexionBBDD.getConnection();
            	PreparedStatement ps = conex.prepareStatement(consulta);
                ps.setString(1, nombre);
                ps.setString(2, hashedPassword);
                ps.executeUpdate();
                
                boolean autenticado = true;
            	if (autenticado) {
            		// Almacenar el nombre del usuario en la sesión
                    HttpSession session = request.getSession();
                    session.setAttribute("nombre", nombre);
                    
                    cerrarSesion(request);
                    response.sendRedirect("usuarioEliminado.jsp");
            	}
            	
            } else {
            	response.sendRedirect("usuarioEliminadoFallido.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error al iniciar sesión.");
        }
	}
	
	private void cerrarSesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Obtener la sesión actual si existe
        if (session != null) {
            session.invalidate(); // Invalidar la sesión actual
        }
    }
}
