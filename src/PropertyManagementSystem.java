import java.sql.*;

public class PropertyManagementSystem {

    public static void main(String[] args) {
        // Establish a connection to the MySQL database
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
            System.exit(1);
        }

        // Initialize the app with the connection
        PropertyManagementApp app = new PropertyManagementApp(connection);

        // Start the app
        app.start();
    }
}
