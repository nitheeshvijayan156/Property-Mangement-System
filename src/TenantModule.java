import java.sql.*;
import java.util.Scanner;

public class TenantModule {
    private Connection connection;
    Scanner scanner = new Scanner(System.in);

    public TenantModule(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        // Display a welcome message and menu options
        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                 Tenant Module                    ║");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║Please select an option:                          ║");
        System.out.println( "║1. View Properties                                ║");
        System.out.println( "║2. View My Lease                                  ║");
        System.out.println( "║3. Go back to main menu                           ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // Get user input for the menu option
        int choice = getUserChoice();

        // Process the user's choice
        switch (choice) {
            case 1:
                // View Properties
                viewProperties();
                TenantModule tenantModule = new TenantModule(connection);
                tenantModule.run();
                break;
            case 2:
                // View My Lease
                viewMyLease();
                TenantModule tenantModule1 = new TenantModule(connection);
                tenantModule1.run();
                break;
            case 3:
                // Go back to main menu
                System.out.println("Going back to main menu...");
                PropertyManagementApp propertyManagementApp = new PropertyManagementApp(connection);
                propertyManagementApp.start();
                break;
            default:
                // Invalid choice, prompt the user to try again
                System.out.println("Invalid choice, please try again.");
                run();
                break;
        }
    }

    private int getUserChoice() {

        int choice;
    
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            // If the user input is not an integer, set choice to -1 to indicate an error
            choice = -1;
        }
    
        return choice;
    }

    private void viewProperties() {
        try {
            // Create a statement object to execute SQL queries
            Statement statement = connection.createStatement();
    
            // Execute a query to get all available properties
            String query = "SELECT * FROM properties WHERE status = 'available'";
            ResultSet resultSet = statement.executeQuery(query);
    
            // Print the results of the query
            System.out.println("Available Properties:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                double rent = resultSet.getDouble("rent");
                int bedrooms = resultSet.getInt("num_bedrooms");
                // int bathrooms = resultSet.getInt("bathrooms");
                System.out.printf("ID: %d | Address: %s | Rent: $%.2f | Bedrooms: %d |\n", id, address, rent, bedrooms);
            }
    
            // Close the statement and result set
            statement.close();
            resultSet.close();
    
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving properties: " + e.getMessage());
        }
    }
    private void viewMyLease() {
        try {
            // Get the logged in user's ID
            System.out.println("Enter tenant id");
            int userId = scanner.nextInt();
    
            // Query to fetch the lease information for the logged in user
            String query = "SELECT * FROM leases WHERE tenant_id = ?";
    
            // Create a prepared statement with the query
            PreparedStatement statement = connection.prepareStatement(query);
    
            // Set the parameter values for the prepared statement
            statement.setInt(1, userId);
    
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
    
            // Print the header for the lease table
            System.out.println("+-------------+---------------+------+------------+------------+-----------+");
            System.out.println("| Lease ID    | Property ID | Tenant ID | Rent | Start Date | End Date    |");
            System.out.println("+-------------+---------------+------+------------+------------+-----------+");
    
            // Loop through the result set and print the lease details
            while (resultSet.next()) {
                int leaseId = resultSet.getInt("id");
                int propertyId = resultSet.getInt("property_id");
                int tenantid =resultSet.getInt("tenant_id");
                double rent = resultSet.getDouble("rent");
                String startDate = resultSet.getString("start_date");
                String endDate = resultSet.getString("end_date");
                //String status = getStatus(startDate, endDate);
    
                // Print the lease details in a row
                System.out.format("| %11d | %11d| %11d | %4.2f | %10s | %10s |\n", leaseId, propertyId, tenantid, rent, startDate, endDate);
            }
    
            // Print the footer for the lease table
            System.out.println("+-------------+---------------+------+------------+------------+-----------+");
    
            // Close the statement and result set
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error fetching leases: " + e.getMessage());
        }
    }
    
    
}
