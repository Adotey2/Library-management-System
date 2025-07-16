package structures;

import models.Book;

import java.util.*;

public class CategoryTree {
    private TreeNode root;

    public CategoryTree() {
        this.root = new TreeNode("Library");
    }

    public void insert(Book book) {
        insertCategory(book.getCategory()).addBook(book);
    }

    public TreeNode insertCategory(String category) {
        for (TreeNode node : root.children) {
            if (node.category.equalsIgnoreCase(category)) {
                return node;
            }
        }
        TreeNode newCategory = new TreeNode(category);
        root.children.add(newCategory);
        return newCategory;
    }

    public List<Book> getAllBooks() {
        List<Book> all = new ArrayList<>();
        for (TreeNode node : root.children) {
            all.addAll(node.books);
        }
        return all;
    }

    public List<Book> getBooksByCategory(String category) {
        for (TreeNode node : root.children) {
            if (node.category.equalsIgnoreCase(category)) {
                return node.books;
            }
        }
        return new ArrayList<>();
    }

    public void displayCategories() {
        for (TreeNode node : root.children) {
            System.out.println("- " + node.category);
        }
    }

    public void remove(Book book) {
        Iterator<TreeNode> it = root.children.iterator();
        while (it.hasNext()) {
            TreeNode node = it.next();
            if (node.category.equalsIgnoreCase(book.getCategory())) {
                node.books.removeIf(b -> b.getIsbn().equals(book.getIsbn()));
                if (node.books.isEmpty()) {
                    it.remove(); // remove category if empty
                }
                break;
            }
        }
    }

    public static class TreeNode {
        String category;
        List<Book> books;
        List<TreeNode> children;

        public TreeNode(String category) {
            this.category = category;
            this.books = new ArrayList<>();
            this.children = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }
    }
    public void printInventoryDistribution() {
        System.out.println("Inventory Distribution by Category:");
        for (TreeNode node : root.children) {
            System.out.println(node.category + ": " + node.books.size() + " book(s)");
        }
    }
    
}
