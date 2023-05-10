import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertiesExporter {
    public static void main(String[] args) {
        // Establish a database connection using DatabaseManager class
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM properties")) {

            FileWriter writer = new FileWriter("properties.csv");
            writer.write("id,address,rent,is_available,num_bedrooms,status\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                double rent = resultSet.getDouble("rent");
                boolean isAvailable = resultSet.getBoolean("is_available");
                int numBedrooms = resultSet.getInt("num_bedrooms");
                String status = resultSet.getString("status");

                writer.write(id + "," + address + "," + rent + "," + isAvailable + "," + numBedrooms + "," + status + "\n");
            }

            writer.close();
            System.out.println("Data exported to properties.csv");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
