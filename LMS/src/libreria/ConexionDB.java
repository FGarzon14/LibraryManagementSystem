package libreria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // Variables de instancia para conexión
    private final String host;
    private final String user;
    private final String password;

    // Constructor para inicializar variables
    public ConexionDB() {
        this.host = "jdbc:mysql://localhost:3306/libreria";
        this.user = "admin";
        this.password = "1234";
    }

    // Método para obtener una conexión a la base de datos
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(host, user, password);
    }

    // Método main para iniciar la conexión
    public static void main(String[] args) {
        ConexionDB conexionDB = new ConexionDB();
        try (Connection connection = conexionDB.getConnection()) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}