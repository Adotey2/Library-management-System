import services.InventoryService;
import services.BorrowerService;
import services.LendingService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryService inventory = new InventoryService();
        BorrowerService borrowerService = new BorrowerService();
        LendingService lendingService = new LendingService();

        while (true) {
            System.out.println("\n=== Ebenezer Library System ===");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Register Borrower");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> inventory.addBook(scanner);
                case 2 -> inventory.listBooks();
                case 3 -> borrowerService.registerBorrower(scanner);
                case 4 -> lendingService.borrowBook(scanner);
                case 5 -> lendingService.returnBook(scanner);
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
