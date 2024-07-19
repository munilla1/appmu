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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contras = request.getParameter("contras");

        UserDao userDao = new UserDao();
        
        try {
            if (userDao.nombreUsado(nombre)) {
                response.sendRedirect("nombreEnUso.jsp");
                return;
            }
            
            // Crear un objeto BCryptPasswordEncoder
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            // Generar el hash de la contraseña
            String hashedPassword = passwordEncoder.encode(contras);

            // Guardar el usuario y la contraseña hasheada en la base de datos
            userDao.saveUser(nombre, correo, hashedPassword);
            
            response.sendRedirect("datosGuardados.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("usuarioNoGuardado.jsp");
        }
    }
}

