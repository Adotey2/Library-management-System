package models;
public class Transaction {
    public String isbn, borrowerId, borrowDate, returnDate;
    public boolean isReturned;

    public Transaction(String isbn, String borrowerId, String borrowDate) {
        this.isbn = isbn;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = "";
        this.isReturned = false;
    }

    public void returnBook(String returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
    }

    public String toString() {
        return isbn + "," + borrowerId + "," + borrowDate + "," + returnDate + "," + isReturned;
    }
}
