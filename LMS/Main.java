import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public void displayBook() {
        System.out.println("\nBook ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (isAvailable ? "Available" : "Issued"));
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }


    public void addBook(String bookId, String title, String author) {
        Book newBook = new Book(bookId, title, author);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }


    public void searchByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }


    public void searchById(String bookId) {
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                book.displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }


    public void issueBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }


    public void returnBook(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book is already available.");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }


    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\nList of Books:");
            for (Book book : books) {
                book.displayBook();
            }
        }
    }


    public void deleteBook(String bookId) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId().equalsIgnoreCase(bookId)) {
                iterator.remove();
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }
}

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add New Book");
            System.out.println("2. Search for a Book by Title");
            System.out.println("3. Search for a Book by ID");
            System.out.println("4. Issue a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. List All Books");
            System.out.println("7. Delete a Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    searchBookByTitle();
                    break;
                case 3:
                    searchBookById();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    listAllBooks();
                    break;
                case 7:
                    deleteBook();
                    break;
                case 8:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8);
        scanner.close();
    }

    private static void addNewBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        library.addBook(bookId, title, author);
    }

    private static void searchBookByTitle() {
        System.out.print("Enter Book Title to search: ");
        String title = scanner.nextLine();
        library.searchByTitle(title);
    }

    private static void searchBookById() {
        System.out.print("Enter Book ID to search: ");
        String bookId = scanner.nextLine();
        library.searchById(bookId);
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String bookId = scanner.nextLine();
        library.issueBook(bookId);
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String bookId = scanner.nextLine();
        library.returnBook(bookId);
    }

    private static void listAllBooks() {
        library.displayAllBooks();
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();
        library.deleteBook(bookId);
    }
}
