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
        System.out.println( "║1. Add Tenant                                     ║");
        System.out.println( "║2. View Tenants                                   ║");
        System.out.println( "║3. View My Lease                                  ║");
        System.out.println( "║4. Get Lease                                      ║");
        System.out.println( "║5. Go back to main menu                           ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // Get user input for the menu option
        int choice = getUserChoice();

        // Process the user's choice
        switch (choice) {
            case 1:
                addTenant();
                TenantModule tenantModule = new TenantModule(connection);
                tenantModule.run();
                break;
            case 2:
                // View Properties
                viewTenants();
                TenantModule tenantModule0 = new TenantModule(connection);
                tenantModule0.run();
                break;
            case 3:
                // View My Lease
                viewMyLease();
                TenantModule tenantModule1 = new TenantModule(connection);
                tenantModule1.run();
                break;
            case 4:
                getLease();
                TenantModule tenantModule2 = new TenantModule(connection);
                tenantModule2.run();
                break;
            case 5:
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
    
    private void addTenant() {
        Scanner scanner = new Scanner(System.in);
        try {
            // Prompt the user to enter tenant details
            System.out.print("Enter tenant name : ");
            String name = scanner.nextLine();
            System.out.print("Enter tenant email : ");
            String email = scanner.nextLine();
            System.out.print("Enter tenant password : ");
            String password = scanner.nextLine();

            // Create a prepared statement to insert the tenant into the table
            String query = "INSERT INTO tenants (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            
            // Execute the query
            statement.executeUpdate();

            System.out.println("Tenant added successfully!");

        } catch (SQLException e) {
            System.out.println("An error occurred while adding the tenant: " + e.getMessage());
        }
    }
    private void viewTenants() {
        try {
            // Create a statement object to execute SQL queries
            Statement statement = connection.createStatement();

            // Execute a query to get all tenants
            String query = "SELECT * FROM tenants";
            ResultSet resultSet = statement.executeQuery(query);

            // Print the results of the query
            System.out.println("Tenant List:");
            System.out.println("+----+------------------+------------------------+");
            System.out.println("| ID | Name             | Email                  |");
            System.out.println("+----+------------------+------------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                //String password = resultSet.getString("password");

                // Print the tenant details in a row
                System.out.format("| %2d | %-16s | %-16s |\n", id, name, email);
            }

            System.out.println("+----+------------------+------------------------+");

            // Close the statement and result set
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving tenants: " + e.getMessage());
        }
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
    private void getLease() {
        try {
            // Display available properties
            viewProperties();

            // Prompt the user to enter the ID of the property they want to lease
            System.out.print("Enter the ID of the property you want to lease: ");
            int propertyId = scanner.nextInt();

            // Check if the property is available for lease
            String checkAvailabilityQuery = "SELECT is_available FROM properties WHERE id = ?";
            PreparedStatement checkAvailabilityStatement = connection.prepareStatement(checkAvailabilityQuery);
            checkAvailabilityStatement.setInt(1, propertyId);
            ResultSet availabilityResult = checkAvailabilityStatement.executeQuery();

            if (availabilityResult.next()) {
                int isAvailable = availabilityResult.getInt("is_available");

                if (isAvailable == 1) {
                    // The property is available, prompt for lease details
                    System.out.print("Enter your tenant ID: ");
                    int tenantId = scanner.nextInt();
                    scanner.nextLine(); // Consume the remaining newline character
                    System.out.print("Enter the start date (yyyy-MM-dd): ");
                    String startDateString = scanner.nextLine();
                    Date startDate = Date.valueOf(startDateString);
                    System.out.print("Enter the end date (yyyy-MM-dd): ");
                    String endDateString = scanner.nextLine();
                    Date endDate = Date.valueOf(endDateString);
                    System.out.print("Enter the monthly rent amount: ");
                    double rentAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the remaining newline character

                    // Insert the lease into the leases table
                    String insertLeaseQuery = "INSERT INTO leases (property_id, tenant_id, start_date, end_date, rent) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertLeaseStatement = connection.prepareStatement(insertLeaseQuery);
                    insertLeaseStatement.setInt(1, propertyId);
                    insertLeaseStatement.setInt(2, tenantId);
                    insertLeaseStatement.setDate(3, startDate);
                    insertLeaseStatement.setDate(4, endDate);
                    insertLeaseStatement.setDouble(5, rentAmount);
                    insertLeaseStatement.executeUpdate();

                    // Update the property status to unavailable
                    String updatePropertyQuery = "UPDATE properties SET is_available = 0 WHERE id = ?";
                    PreparedStatement updatePropertyStatement = connection.prepareStatement(updatePropertyQuery);
                    updatePropertyStatement.setInt(1, propertyId);
                    updatePropertyStatement.executeUpdate();

                    System.out.println("Lease successfully created!");

                } else {
                    // The property is not available for lease
                    System.out.println("This property is not available for lease.");
                }
            } else {
                // Invalid property ID
                System.out.println("Invalid property ID.");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while processing the lease: " + e.getMessage());
        }
    }

}