package services;

import models.Transaction;
import structures.Queue;
import java.util.*;
import java.io.*;
import utils.FileHandler;

public class LendingService {
    private Queue<Transaction> queue = new Queue<>();
    private List<Transaction> allTransactions = new ArrayList<>();
    private final String FILE = "data/transactions.txt";

    public LendingService() {
        loadTransactionsFromFile();
    }

    public void borrowBook(Scanner scanner) {
        System.out.print("ISBN: "); String isbn = scanner.nextLine();
        System.out.print("Borrower ID: "); String id = scanner.nextLine();
        System.out.print("Borrow Date (YYYY-MM-DD): "); String date = scanner.nextLine();

        Transaction t = new Transaction(isbn, id, date);
        allTransactions.add(t);
        queue.enqueue(t);
        saveTransactionsToFile();
    }

    public void returnBook(Scanner scanner) {
        System.out.print("Borrower ID: "); String id = scanner.nextLine();
        System.out.print("ISBN: "); String isbn = scanner.nextLine();
        System.out.print("Return Date: "); String date = scanner.nextLine();

        for (Transaction t : allTransactions) {
            if (t.borrowerId.equals(id) && t.isbn.equals(isbn) && !t.isReturned) {
                t.returnBook(date);
                System.out.println("Book returned successfully.");
                saveTransactionsToFile();
                return;
            }
        }
        System.out.println("No matching active transaction found.");
    }

    private void loadTransactionsFromFile() {
        try {
            List<String> lines = FileHandler.readFile(FILE);
            for (String line : lines) {
                String[] parts = line.split(",", -1);
                if (parts.length == 5) {
                    Transaction t = new Transaction(parts[0], parts[1], parts[2]);
                    if (Boolean.parseBoolean(parts[4])) {
                        t.returnBook(parts[3]);
                    }
                    allTransactions.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("No transaction file found.");
        }
    }

    private void saveTransactionsToFile() {
        List<String> lines = new ArrayList<>();
        for (Transaction t : allTransactions) {
            lines.add(t.toString());
        }
        try {
            FileHandler.writeFile(FILE, lines);
        } catch (IOException e) {
            System.out.println("Failed to save transactions.");
        }
    }

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }
}
