package app.com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/*
 * public class ContadoresServlet extends HttpServlet {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Override protected void doGet(HttpServletRequest request,
 * HttpServletResponse response) throws ServletException, IOException {
 * HttpSession session = request.getSession(); String nombre = (String)
 * session.getAttribute("nombre");
 * 
 * if (nombre == null) { response.sendRedirect("login.jsp"); return; }
 * 
 * UserDao userDao = new UserDao(); try { Contador contador =
 * userDao.getContadores(nombre); if (contador == null) { contador =
 * ContadorGlobal.getContador(); userDao.updateContadores(nombre, contador); }
 * else { contador.setContador1(contador.getContador1() +
 * ContadorGlobal.getContador().getContador1());
 * contador.setContador2(contador.getContador2() +
 * ContadorGlobal.getContador().getContador2());
 * contador.setContador3(contador.getContador3() +
 * ContadorGlobal.getContador().getContador3());
 * userDao.updateContadores(nombre, contador); }
 * session.setAttribute("contador", contador); } catch (SQLException e) {
 * e.printStackTrace(); response.sendRedirect("error.jsp"); }
 * 
 * request.getRequestDispatcher("paginaPrincipal.jsp").forward(request,
 * response); } }
 */


