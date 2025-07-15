package services;

import models.Borrower;
import java.util.*;
import java.io.*;
import utils.FileHandler;

public class BorrowerService {
    private HashMap<String, Borrower> borrowers = new HashMap<>();
    private final String FILE = "data/borrowers.txt";

    public BorrowerService() {
        loadBorrowersFromFile();
    }

    public void registerBorrower(Scanner scanner) {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("ID: "); String id = scanner.nextLine();
        System.out.print("Contact: "); String contact = scanner.nextLine();

        Borrower b = new Borrower(name, id, contact);
        borrowers.put(id, b);
        saveBorrowersToFile();
    }

    public Borrower getBorrower(String id) {
        return borrowers.get(id);
    }

    private void loadBorrowersFromFile() {
        try {
            List<String> lines = FileHandler.readFile(FILE);
            for (String line : lines) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 5) {
                    Borrower b = new Borrower(parts[0], parts[1], parts[2]);
                    if (!parts[3].isEmpty()) {
                        b.borrowedBooks.addAll(Arrays.asList(parts[3].split("\\|")));
                    }
                    b.fine = Double.parseDouble(parts[4]);
                    borrowers.put(b.id, b);
                }
            }
        } catch (IOException e) {
            System.out.println("No borrower file found.");
        }
    }

    private void saveBorrowersToFile() {
        List<String> lines = new ArrayList<>();
        for (Borrower b : borrowers.values()) {
            lines.add(b.toString());
        }
        try {
            FileHandler.writeFile(FILE, lines);
        } catch (IOException e) {
            System.out.println("Failed to save borrowers.");
        }
    }

    public void saveOnExit() {
        saveBorrowersToFile();
    }

    public HashMap<String, Borrower> getAllBorrowers() {
        return borrowers;
    }
}