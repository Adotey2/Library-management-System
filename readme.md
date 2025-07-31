# Library Management System

## Data File Structures

### Books Structure

```
title,author,isbn,category,year,publisher,shelfLocation
```

### Borrowers Structure

```
name,id,contact,borrowedBooks,fine
```

### Transactions Structure

```
isbn,borrowerId,borrowDate,returnDate,isReturned
```

---

# System Overview

## 1. Adding Books

**Process**:

* Create `Book` object with title, author, ISBN, category
* Insert book into `CategoryTree` under correct category
* Save book data to `books.txt` file

**Files Used**: `CategoryTree.java`, `TreeNode.java`, `Book.java`
**Files Changed**: `Data/books.txt`

**Data Structures**:

* **CategoryTree**: Organizes books by subject categories

  ```java
  // Example: Adding a new book to the system
  CategoryTree library = new CategoryTree();
  Book newBook = new Book("Things Fall Apart", "Chinua Achebe", "9780385474542", "Fiction", 1958);
  library.insert(newBook); // Automatically places book in the Fiction category
  ```
* **TreeNode**: Stores all books within one category

  ```java
  // TreeNode stores books and maintains category structure
  public TreeNode(String category) {
      this.category = category;
      this.books = new ArrayList<>();  // This collection holds all books in this category
  }
  ```

---

## 2. Finding Books

**Process**:

* Search books by category using `CategoryTree`
* Sort results by title or year using `Sorter` class

**Files Used**: `CategoryTree.java`, `Sorter.java`
**Files Changed**: None

**Data Structures**:

* **CategoryTree**: Quick category-based search

  ```java
  List<Book> fictionBooks = library.getBooksByCategory("Fiction");
  ```
* **Merge Sort**: Sorts large book lists efficiently

  ```java
  // Sort books by publication year
  List<Book> chronologicalBooks = Sorter.mergeSortByYear(allBooks);

  // Sort books alphabetically by title
  List<Book> alphabeticalBooks = Sorter.mergeSortByTitle(allBooks);
  ```

---

## 3. Borrowing Books

**Process**:

* Check if book is available
* Create transaction linking book to borrower
* Update borrower record with borrowed book
* Set return deadline

**Files Used**: `Queue.java`, `Transaction.java`, `Borrower.java`
**Files Changed**: `Data/transactions.txt`, `Data/borrowers.txt`

**Data Structures**:

* **Queue**: Processes borrowing requests in order

  ```java
  Queue<BorrowRequest> borrowQueue = new Queue<>();
  borrowQueue.enqueue(new BorrowRequest(bookId, borrowerId));

  while (!borrowQueue.isEmpty()) {
      BorrowRequest request = borrowQueue.dequeue();
      // Process the borrowing request
  }
  ```

---

## 4. Returning Books

**Process**:

* Find transaction record
* Calculate overdue days
* Add fines if book is late
* Mark transaction as complete

**Files Used**: `LinkedList.java`, `Transaction.java`
**Files Changed**: `Data/transactions.txt`, `Data/borrowers.txt`

**Data Structures**:

* **LinkedList**: Tracks overdue books and fines

  ```java
  // Example of tracking overdue books with LinkedList
  LinkedList<Transaction> overdueBooks = new LinkedList<>();
  // Add overdue transactions to the list
  // Process each overdue item
  overdueBooks.printAll(); // Display all overdue items
  ```

---

## 5. Reports

**Process**:

* Count books in each category
* Generate inventory statistics

**Files Used**: `CategoryTree.java`
**Files Changed**: None

**Data Structures**:

* **CategoryTree**: For structured inventory analysis

  ```java
  // Generate inventory distribution report
  library.printInventoryDistribution();
  ```

---

## Data Structures Explained

### CategoryTree

* Organizes books into categories like Fiction, Science, etc.
* Fast access to books by category
* Better than searching through all books one by one

### TreeNode

* Holds all books in one category
* Stores category name and book list together

### Merge Sort

* Divides book list in half repeatedly
* Sorts each half separately
* Combines sorted halves back together
* Works well for large numbers of books

### Queue

* First request gets processed first
* Ensures fair order for borrowing requests
* Prevents newer requests from skipping ahead

### LinkedList

* Grows and shrinks as needed
* Good for collections that change size often
* Used for tracking fines and overdue books

---

## Why These Structures

* **CategoryTree**: Fast category searches instead of checking every book
* **Merge Sort**: Reliable sorting performance for any size collection
* **Queue**: Fair processing order for user requests
* **LinkedList**: Flexible size for unpredictable data like fines
* **File Storage**: Simple data saving without complex databases

Each structure solves a specific problem in the library system efficiently.
