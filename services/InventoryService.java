package services;
import models.Book;                 
import structures.CategoryTree;
import java.util.*;
import java.io.*;
import utils.FileHandler;;

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

    public void listBooks() {
        tree.listBooks();
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
}