import services.InventoryService;
import services.BorrowerService;
import services.LendingService;
import services.ReportService;

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
            System.out.println("7. Reports & Analysis");
            System.out.println("8. Search Book");
            System.out.println("9. Sort Books");
            System.out.println("10. Filter Books by Category");
            System.out.println("11. Remove Book");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> inventory.addBook(scanner);
                case 2 -> inventory.listBooks();
                case 3 -> borrowerService.registerBorrower(scanner);
                case 4 -> lendingService.borrowBook(scanner);
                case 5 -> lendingService.returnBook(scanner);
                case 6 -> {
                    borrowerService.saveOnExit();
                    System.exit(0);
                }
                case 7 -> {
                    ReportService.mostBorrowedBooksLastMonth(lendingService.getAllTransactions());
                    ReportService.topFinedBorrowers(borrowerService.getAllBorrowers());
                    inventory.getTree().printInventoryDistribution();
                    ReportService.flagOverdues(lendingService.getAllTransactions(), borrowerService.getAllBorrowers());
                    borrowerService.saveOnExit();
                    ReportService.algorithmPerformanceNotes();
                }
                case 8 -> inventory.searchBooks(scanner);
                case 9 -> inventory.sortBooks(scanner);
                case 10 -> inventory.filterByCategory(scanner);
                case 11 -> inventory.removeBookByISBN(scanner);

                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
