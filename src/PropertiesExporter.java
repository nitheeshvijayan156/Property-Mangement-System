import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertiesExporter {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pms";
        String user = "root";
        String password = "divya@01";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM properties")) {
            
            FileWriter writer = new FileWriter("properties.csv");
            writer.write("id,address,rent,is_available,num_bedrooms,status\n");

            while (rs.next()) {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                double rent = rs.getDouble("rent");
                boolean isAvailable = rs.getBoolean("is_available");
                int numBedrooms = rs.getInt("num_bedrooms");
                String status = rs.getString("status");
                
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
