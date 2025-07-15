package structures;
import models.Book;
import java.util.ArrayList;

public class TreeNode {
    public String category;
    public ArrayList<Book> books;
    public TreeNode left, right;

    public TreeNode(String category) {
        this.category = category;
        this.books = new ArrayList<>();
        this.left = this.right = null;
    }
}