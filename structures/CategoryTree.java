package structures;

import models.Book;

public class CategoryTree {
    private TreeNode root;

    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private TreeNode insertRec(TreeNode node, Book book) {
        if (node == null) {
            node = new TreeNode(book.getCategory());
            node.books.add(book);
            return node;
        }
        int cmp = book.getCategory().compareToIgnoreCase(node.category);
        if (cmp == 0) node.books.add(book);
        else if (cmp < 0) node.left = insertRec(node.left, book);
        else node.right = insertRec(node.right, book);
        return node;
    }

    public void listBooks() {
        listRec(root);
    }

    private void listRec(TreeNode node) {
        if (node != null) {
            listRec(node.left);
            for (Book b : node.books) System.out.println(b);
            listRec(node.right);
        }
    }

    public void printInventoryDistribution() {
        System.out.println("Inventory Distribution by Category:");
        printDistribution(root);
    }

    private void printDistribution(TreeNode node) {
        if (node != null) {
            printDistribution(node.left);
            System.out.println(node.category + ": " + node.books.size() + " book(s)");
            printDistribution(node.right);
        }
    }
}