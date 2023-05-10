import java.sql.Connection;
import java.util.Scanner;

public class PropertyManagerModule {
    private Connection connection;

    public PropertyManagerModule(Connection connection) {
        this.connection = connection;
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║                 Admin Module                     ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.println("║Please select an option:                          ║");
            System.out.println( "║1. Tenant Module                                  ║");
            System.out.println( "║2. Landlord Module                                ║");
            System.out.println( "║3. Go back to main menu                           ║");
            System.out.println( "║4. Export Data                                    ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
            

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    TenantModule tenantModule = new TenantModule(connection);
                    tenantModule.run();
                    break;
                case 2:
                    LandlordModule landlordModule = new LandlordModule(connection);
                    landlordModule.run();
                    break;
                case 3:
                    System.out.println("Exiting Property Management App...");
                    break;
                case 4:
                    System.out.println("EXPORT PROPERTY DATA");
                    PropertiesExporter.main(null);                   
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }
}
