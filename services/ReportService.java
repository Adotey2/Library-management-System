package services;

import models.Transaction;
import models.Borrower;
import structures.CategoryTree;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class ReportService {

    public static void mostBorrowedBooksLastMonth(List<Transaction> transactions) {
        System.out.println("Most Borrowed Books (Last 30 Days):");
        HashMap<String, Integer> bookFreq = new HashMap<>();
        LocalDate now = LocalDate.now();

        for (Transaction t : transactions) {
            LocalDate borrowDate = LocalDate.parse(t.borrowDate);
            if (ChronoUnit.DAYS.between(borrowDate, now) <= 30) {
                bookFreq.put(t.isbn, bookFreq.getOrDefault(t.isbn, 0) + 1);
            }
        }

        bookFreq.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .limit(5)
            .forEach(entry -> System.out.println("ISBN: " + entry.getKey() + ", Times: " + entry.getValue()));
    }

    public static void topFinedBorrowers(HashMap<String, Borrower> borrowers) {
        System.out.println("Top Borrowers With Outstanding Fines:");
        borrowers.values().stream()
            .sorted((a, b) -> Double.compare(b.fine, a.fine))
            .limit(5)
            .forEach(b -> System.out.println(b.name + " | Fine: " + b.fine));
    }

    public static void algorithmPerformanceNotes() {
        System.out.println("\nAlgorithm Performance Notes:");
        System.out.println("- CategoryTree Insert/List (BST): O(log n) average, Ω(log n)");
        System.out.println("- Queue Enqueue/Dequeue: O(1), Ω(1)");
        System.out.println("- HashMap Lookup: O(1) average, Ω(1)");
        System.out.println("- Recursive Borrower Search: O(n), Ω(1)");
        System.out.println("- Overdue Min-Heap Extraction: O(log n), Ω(1)");
    }

    public static void flagOverdues(List<Transaction> transactions, HashMap<String, Borrower> borrowers) {
        LocalDate now = LocalDate.now();
        PriorityQueue<Transaction> overdueQueue = new PriorityQueue<>((a, b) -> a.borrowDate.compareTo(b.borrowDate));

        for (Transaction t : transactions) {
            if (!t.isReturned) {
                LocalDate borrowDate = LocalDate.parse(t.borrowDate);
                long days = ChronoUnit.DAYS.between(borrowDate, now);
                if (days > 14) overdueQueue.add(t);
            }
        }

        System.out.println("\nOverdue Books (14+ days):");
        while (!overdueQueue.isEmpty()) {
            Transaction t = overdueQueue.poll();
            System.out.println("ISBN: " + t.isbn + " | Borrower: " + t.borrowerId + " | Borrow Date: " + t.borrowDate);
            Borrower b = borrowers.get(t.borrowerId);
            if (b != null) b.fine += 2.0; // flat fine rate
        }
    }
}
