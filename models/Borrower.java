package models;

import java.util.ArrayList;

public class Borrower {
    public String name, id, contact;
    public ArrayList<String> borrowedBooks = new ArrayList<>();
    public double fine;

    public Borrower(String name, String id, String contact) {
        this.name = name;
        this.id = id;
        this.contact = contact;
        this.fine = 0.0;
    }

    public String toString() {
        return name + "," + id + "," + contact + "," + String.join("|", borrowedBooks) + "," + fine;
    }
}
