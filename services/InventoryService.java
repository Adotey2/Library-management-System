package services;

import models.Book;
import structures.CategoryTree;
import structures.Sorter;
import java.util.*;
import java.io.*;
import utils.FileHandler;

public class InventoryService {
    private CategoryTree tree = new CategoryTree();
    private List<Book> allBooks = new ArrayList<>();
    private final String FILE = "data/books.txt";

    public InventoryService() {
        loadBooksFromFile();
    }

    public void addBook(Scanner scanner) {
        System.out.print("Title: "); String title = scanner.nextLine();
        System.out.print("Author: "); String author = scanner.nextLine();
        System.out.print("ISBN: "); String isbn = scanner.nextLine();
        System.out.print("Category: "); String category = scanner.nextLine();
        System.out.print("Year: "); int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Publisher: "); String publisher = scanner.nextLine();
        System.out.print("Shelf Location: "); String shelf = scanner.nextLine();

        Book book = new Book(title, author, isbn, category, year, publisher, shelf);
        allBooks.add(book);
        tree.insert(book);
        saveBooksToFile();
    }

    public void removeBookByISBN(Scanner scanner) {
        System.out.print("Enter ISBN to remove: ");
        String isbn = scanner.nextLine();
        boolean removed = false;

        Iterator<Book> iterator = allBooks.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.isbn.equalsIgnoreCase(isbn)) {
                iterator.remove();
                tree.remove(b); // Tree update (requires implementation in CategoryTree)
                removed = true;
                break;
            }
        }

        if (removed) {
            saveBooksToFile();
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void listBooks() {
        tree.getAllBooks();
    }

    public void searchBooks(Scanner scanner) {
        System.out.println("Search by: 1. Title 2. Author 3. ISBN");
        int opt = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter search term: ");
        String term = scanner.nextLine();
        boolean found = false;
        for (Book b : allBooks) {
            if ((opt == 1 && b.title.equalsIgnoreCase(term)) ||
                (opt == 2 && b.author.equalsIgnoreCase(term)) ||
                (opt == 3 && b.isbn.equalsIgnoreCase(term))) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No matching book found.");
    }

    public void sortBooks(Scanner scanner) {
        System.out.println("Sort by: 1. Title 2. Year");
        int opt = scanner.nextInt(); scanner.nextLine();
        List<Book> sorted;
        if (opt == 1) sorted = Sorter.mergeSortByTitle(new ArrayList<>(allBooks));
        else sorted = Sorter.mergeSortByYear(new ArrayList<>(allBooks));
        sorted.forEach(System.out::println);
    }

    public void filterByCategory(Scanner scanner) {
        System.out.print("Enter category to filter: ");
        String category = scanner.nextLine();
        tree.getBooksByCategory(category);
    }

    private void loadBooksFromFile() {
        try {
            List<String> lines = FileHandler.readFile(FILE);
            for (String line : lines) {
                String[] parts = line.split(",", -1);
                if (parts.length == 7) {
                    Book book = new Book(parts[0], parts[1], parts[2], parts[3],
                                         Integer.parseInt(parts[4]), parts[5], parts[6]);
                    allBooks.add(book);
                    tree.insert(book);
                }
            }
        } catch (IOException e) {
            System.out.println("No books file found, starting fresh.");
        }
    }

    private void saveBooksToFile() {
        List<String> lines = new ArrayList<>();
        for (Book b : allBooks) {
            lines.add(b.toString());
        }
        try {
            FileHandler.writeFile(FILE, lines);
        } catch (IOException e) {
            System.out.println("Failed to save books.");
        }
    }

    public CategoryTree getTree() {
        return tree;
    }
}
