package app.com.example;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String contras = request.getParameter("contras");

        try {
            // Recuperar la contraseña hasheada de la base de datos
            UserDao userDao = new UserDao();
            String hashedPassword = userDao.getHashedPassword(nombre);

            if (hashedPassword != null && BCrypt.checkpw(contras, hashedPassword)) {
                HttpSession session = request.getSession();
                boolean autenticado = session.getAttribute("nombre") != null;

                if (autenticado) {
                    response.sendRedirect("yaEstasLogeado.jsp");
                } else {
                    // Almacenar el nombre del usuario en la sesión
                    session.setAttribute("nombre", nombre);
                    
                    // Iniciar el contador y guardarlo en la sesión
                    Contador contador = new Contador(nombre);
                    session.setAttribute("contador", contador);
                    response.sendRedirect("accesoCorrecto.jsp");
                }
            } else {
                response.sendRedirect("accesoIncorrecto.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error al iniciar sesión.");
        }
    }
}

