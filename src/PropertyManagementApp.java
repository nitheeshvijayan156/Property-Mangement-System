import java.sql.*;
import java.util.Scanner;

public class PropertyManagementApp {
    private Connection connection;

    public PropertyManagementApp(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        // Display a welcome message and menu options
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      Property Management System      ║");
        System.out.println("║                                      ║");
        System.out.println("║ Please select an option:             ║");
        System.out.println("║ 1. Landlord                          ║");
        System.out.println("║ 2. Tenant                            ║");
        System.out.println("║ 3. Property Manager                  ║");
        System.out.println("║ 4. Exit                              ║");
        System.out.println("╚══════════════════════════════════════╝");
        

        // Get user input for the menu option
        int choice = getUserChoice();

        // Process the user's choice
        switch (choice) {
            case 1:
                // Call the Landlord module
                LandlordModule landlordModule = new LandlordModule(connection);
                landlordModule.run();
                break;
            case 2:
                // Call the Tenant module
                TenantModule tenantModule = new TenantModule(connection);
                tenantModule.run();
                break;
            case 3:
                // Call the Property Manager module
                PropertyManagerModule propertyManagerModule = new PropertyManagerModule(connection);
                propertyManagerModule.run();
                break;
            case 4:
                // Exit the app
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                // Invalid choice, prompt the user to try again
                System.out.println("Invalid choice, please try again.");
                start();
                break;
        }
    }

    private int getUserChoice() {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (true) {
            try {
                input = scanner.nextLine();
                if (input == null || input.trim().equals("")) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    continue;
                }
                choice = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
            }
        }
        return choice;
    }
}
