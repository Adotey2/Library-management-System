package models;
public class Book {
    String title, author, isbn, category, publisher, shelfLocation;
    int year;

    public Book(String title, String author, String isbn, String category, int year, String publisher, String shelfLocation) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.year = year;
        this.publisher = publisher;
        this.shelfLocation = shelfLocation;
    }

    public String getCategory() {
        return category;
    }
    
    public String toString() {
        return title + "," + author + "," + isbn + "," + category + "," + year + "," + publisher + "," + shelfLocation;
    }
}
