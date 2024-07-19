package app.com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Contador {
    private int segundos;
    private String nombreUsuario; // Identificador del usuario

    public Contador(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.segundos = obtenerSegundosDesdeBD();
        iniciarActualizacionContinua();
    }

    // Método para iniciar la actualización continua del contador en la base de datos
    private void iniciarActualizacionContinua() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Tarea que se ejecuta cada segundo para actualizar el contador en la BD
        Runnable tarea = () -> {
            segundos++;
            try {
                actualizarSegundosEnBD(segundos);
                //System.out.println("Segundos actualizados en la BD para " + nombreUsuario + ": " + segundos); // Mensaje de depuración
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };

        // Ejecutar la tarea cada segundo
        scheduler.scheduleAtFixedRate(tarea, 0, 1, TimeUnit.SECONDS);
    }

    // Método para obtener los segundos desde la base de datos al inicializar
    private int obtenerSegundosDesdeBD() {
        int segundosDesdeBD = 0;

        String query = "SELECT segundos FROM usuarios WHERE nombre = ?";

        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                segundosDesdeBD = rs.getInt("segundos");
                //System.out.println("Segundos obtenidos de la BD para " + nombreUsuario + ": " + segundosDesdeBD); // Mensaje de depuración
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return segundosDesdeBD;
    }

    // Método para actualizar los segundos en la base de datos
    private void actualizarSegundosEnBD(int segundos) throws SQLException {
        String query = "UPDATE usuarios SET segundos = ? WHERE nombre = ?";

        try (Connection conn = ConexionBBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, segundos);
            stmt.setString(2, nombreUsuario);
            stmt.executeUpdate();
        }
    }

    public int getSegundos() {
        return segundos;
    }
}



