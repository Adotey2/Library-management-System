books: //title,author,isbn,category,year,publisher,shelfLocation
borrowers: //name,id,contact,borrowedBooks,fine
transactions; isbn,borrowerId,borrowDate,returnDate,isReturned

# Comprehensive Library Management System Overview

## 1. Book Management Process

### Adding a New Book
1. **Process**: When a librarian adds a new book:
   - Book data is collected (title, author, ISBN, etc.)
   - A `Book` model object is created
   - The book is inserted into the appropriate category
   - Book details are written to `books.txt`

2. **Data Structures Used**:
   - **CategoryTree**: The main organizational structure
     ```java
     // Example: Adding a new book to the system
     CategoryTree library = new CategoryTree();
     Book newBook = new Book("Things Fall Apart", "Chinua Achebe", "9780385474542", "Fiction", 1958);
     library.insert(newBook); // Automatically places book in the Fiction category
     ```
   - **TreeNode**: Stores books within their category
     ```java
     // TreeNode stores books and maintains category structure
     public TreeNode(String category) {
         this.category = category;
         this.books = new ArrayList<>();  // This collection holds all books in this category
     }
     ```

### Book Categorization
1. **Process**: Books are automatically organized by category:
   - When inserting a book, the system checks if its category exists
   - If not, a new category node is created
   - The book is added to the appropriate category node

2. **Data Structures Used**:
   - **CategoryTree** maintains a hierarchy of categories with the "Library" as the root
   - **TreeNode** handles both binary tree operations and category management

## 2. Book Discovery Process

### Searching and Sorting Books
1. **Process**: When a user searches for books:
   - Books can be retrieved by category
   - Results can be sorted by different criteria

2. **Data Structures Used**:
   - **CategoryTree** for category-based retrieval:
     ```java
     List<Book> fictionBooks = library.getBooksByCategory("Fiction");
     ```
   - **Sorter** for organizing results using merge sort:
     ```java
     // Sort books by publication year
     List<Book> chronologicalBooks = Sorter.mergeSortByYear(allBooks);
     
     // Sort books alphabetically by title
     List<Book> alphabeticalBooks = Sorter.mergeSortByTitle(allBooks);
     ```
     
3. **How Merge Sort Works**:
   - Divides the book list into halves recursively
   - Sorts each half independently
   - Merges sorted halves back together
   - Achieves O(n log n) performance, efficient for large collections

## 3. Borrowing Process

1. **Process**: When a user borrows a book:
   - System confirms book availability
   - Creates transaction record linking book (ISBN) to borrower (ID)
   - Updates borrower record with borrowed book
   - Records transaction with current date and return deadline

2. **Files Updated**:
   - `transactions.txt`: New entry with ISBN, borrower ID, borrow date
   - `borrowers.txt`: Updated to include borrowed book ISBN

3. **Data Structures Used**:
   - **Queue**: For processing multiple borrowing requests sequentially
     ```java
     Queue<BorrowRequest> borrowQueue = new Queue<>();
     borrowQueue.enqueue(new BorrowRequest(bookId, borrowerId));
     
     while (!borrowQueue.isEmpty()) {
         BorrowRequest request = borrowQueue.dequeue();
         // Process the borrowing request
     }
     ```

## 4. Return and Fine Management Process

1. **Process**: When a book is returned:
   - System finds the transaction record
   - Calculates days overdue (if any)
   - Assesses fines based on overdue days
   - Updates borrower's fine balance
   - Marks transaction as returned

2. **Files Updated**:
   - `transactions.txt`: Updated with return date and status
   - `borrowers.txt`: Updated fine amount and removed book from borrowed list

3. **Data Structures Used**:
   - **LinkedList**: For maintaining dynamic collections like fine records
     ```java
     // Example of tracking overdue books with LinkedList
     LinkedList<Transaction> overdueBooks = new LinkedList<>();
     // Add overdue transactions to the list
     // Process each overdue item
     overdueBooks.printAll(); // Display all overdue items
     ```

## 5. Inventory Management Process

1. **Process**: For library inventory reports:
   - System traverses the category tree
   - Counts books by category
   - Generates distribution statistics

2. **Data Structures Used**:
   - **CategoryTree** for hierarchical organization:
     ```java
     // Generate inventory distribution report
     library.printInventoryDistribution();
     ```

This library system demonstrates effective use of appropriate data structures for different operations:
- Trees for hierarchical organization
- Sorting algorithms for efficient searching
- Queues for sequential processing
- Linked lists for dynamic collections

The combination of these structures with the file-based persistence layer creates a complete library management solution.