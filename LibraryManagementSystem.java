import java.util.*;

abstract class LibraryItem{
    private String itemId;
    private String title;
    private String author;

    LibraryItem(String itemId, String title, String author){
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void getItemDetails() {
        System.out.println("ID: " + itemId + ", Title: " + title + ", Author: " + author);
    }

    public abstract int getLoanDuration();
}

interface Reservable {
    void reserveItem();
    boolean checkAvailability();
}

// Subclass: Book
class Book extends LibraryItem implements Reservable {
    public Book(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 14; // Books can be borrowed for 14 days
    }

    @Override
    public void reserveItem() {
        System.out.println("Book reserved: " + getTitle());
    }

    @Override
    public boolean checkAvailability() {
        return true; // Assuming books are always available for now
    }
}

// Subclass: Magazine
class Magazine extends LibraryItem {
    public Magazine(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 7; // Magazines can be borrowed for 7 days
    }
}

// Subclass: DVD
class DVD extends LibraryItem implements Reservable {
    public DVD(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 3; // DVDs can be borrowed for 3 days
    }

    @Override
    public void reserveItem() {
        System.out.println("DVD reserved: " + getTitle());
    }

    @Override
    public boolean checkAvailability() {
        return false; // Assuming DVDs are currently unavailable
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        List<LibraryItem> items = new ArrayList<>();

        Book book = new Book("101", "Java", "James Gosling");
        Magazine magazine = new Magazine("202", "India Tomorrow", "John");
        DVD dvd = new DVD("303", "Inception", "Christopher Nolan");

        items.add(book);
        items.add(magazine);
        items.add(dvd);

        for (LibraryItem item : items) {
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");

            if (item instanceof Reservable) {
                ((Reservable) item).reserveItem();
                System.out.println("Available: " + ((Reservable) item).checkAvailability());
            }
            System.out.println();
        }
    }
}

//SampleOutput
//ID: 101, Title: Java, Author: James Gosling
//Loan Duration: 14 days
//Book reserved: Java
//Available: true
//
//ID: 202, Title: India Tomorrow, Author: John
//Loan Duration: 7 days
//
//ID: 303, Title: Inception, Author: Christopher Nolan
//Loan Duration: 3 days
//DVD reserved: Inception
//Available: false
