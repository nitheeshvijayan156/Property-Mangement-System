import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LandlordModule {
    private Connection connection;

    public LandlordModule(Connection connection) {
        this.connection = connection;
    }

    public void run() {

        // Get user input for the menu option
        int choice = getUserChoice();

        // Process the user's choice
        switch (choice) {
            case 1:
                // View Properties
                viewProperties();
                LandlordModule landlordModule = new LandlordModule(connection);
                landlordModule.run();
                break;
            case 2:
                // Add Property
                addProperty();
                LandlordModule landlordModule1 = new LandlordModule(connection);
                landlordModule1.run();
                break;
            case 3:
                // Update Property
                updateProperty();
                LandlordModule landlordModule2 = new LandlordModule(connection);
                landlordModule2.run();
                break;
            case 4:
                // Delete Property
                deleteProperty();
                LandlordModule landlordModule3 = new LandlordModule(connection);
                landlordModule3.run();
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

    public int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                 Landlord Module                  ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║Please select an option:                          ║");
            System.out.println( "║1. View Properties                                ║");
            System.out.println( "║2. Add Property                                   ║");
            System.out.println( "║3. Update Property                                ║");
            System.out.println( "║4. Delete Property                                ║");
            System.out.println( "║5. Go back to main menu                           ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 5) {
                    break;
                } else {
                    System.out.println("Invalid input, please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
        return choice;
    }
    
    

    public void viewProperties() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM properties");
            
            System.out.println("Available Properties:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                int numBedrooms = resultSet.getInt("num_bedrooms");
                double rent = resultSet.getDouble("rent");
                
                 System.out.printf("%d. %s - %d bedrooms - $%.2f/month%n", id, address, numBedrooms, rent);
            }
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving properties: " + e.getMessage());
        }
    }    

    public void addProperty() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter information about the new property
        System.out.println("Enter the property address:");
        String address = scanner.nextLine();
        
        System.out.println("Enter the number of bedrooms:");
        int numBedrooms = scanner.nextInt();
        
        System.out.println("Enter the monthly rent:");
        double rent = scanner.nextDouble();
        
        // Insert the new property into the database
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO properties (address, num_bedrooms, rent) VALUES (?, ?, ?)");
            statement.setString(1, address);
            statement.setInt(2, numBedrooms);
            statement.setDouble(3, rent);
            statement.executeUpdate();
            statement.close();
            
            System.out.println("Property added successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the property: " + e.getMessage());
        }
    }    

    public void updateProperty() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the ID of the property to update
        System.out.println("Enter the ID of the property to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        
        // Check if the property exists
        try {
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM properties WHERE id = ?");
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            
            if (!resultSet.next()) {
                System.out.println("Property not found.");
                return;
            }
            
            // Prompt the user to enter the new property information
            System.out.println("Enter the new property address (or press Enter to skip):");
            String address = scanner.nextLine();
            
            System.out.println("Enter the new number of bedrooms (or 0 to skip):");
            int numBedrooms = scanner.nextInt();
            
            System.out.println("Enter the new monthly rent (or 0.0 to skip):");
            double rent = scanner.nextDouble();
            
            // Update the property in the database
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE properties SET address = ?, num_bedrooms = ?, rent = ? WHERE id = ?");
            updateStatement.setString(1, address.isEmpty() ? resultSet.getString("address") : address);
            updateStatement.setInt(2, numBedrooms == 0 ? resultSet.getInt("num_bedrooms") : numBedrooms);
            updateStatement.setDouble(3, rent == 0.0 ? resultSet.getDouble("rent") : rent);
            updateStatement.setInt(4, id);
            updateStatement.executeUpdate();
            updateStatement.close();
            
            System.out.println("Property updated successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while updating the property: " + e.getMessage());
        }
    }    

    public void deleteProperty() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the ID of the property to delete
        System.out.println("Enter the ID of the property to delete:");
        int id = scanner.nextInt();
        
        // Check if the property exists
        try {
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM properties WHERE id = ?");
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            
            if (!resultSet.next()) {
                System.out.println("Property not found.");
                return;
            }
            
            // Delete the property from the database
            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM properties WHERE id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
            deleteStatement.close();
            
            System.out.println("Property deleted successfully.");
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the property: " + e.getMessage());
        }
    }    
}
