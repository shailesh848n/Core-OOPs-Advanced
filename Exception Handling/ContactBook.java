
import java.io.*;
import java.util.*;
 

class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
}

public class ContactBook {
 
    static List<String> contacts = new ArrayList<>();
    static final String FILE_NAME = "contacts.txt";
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
 
        while (running) {
            System.out.println("\n1.Add  2.Search  3.Delete  4.Save  5.Exit");
            System.out.print("Choice: ");
 
            try {
                int choice = Integer.parseInt(sc.nextLine()); 
 
                switch (choice) {
                    case 1 -> addContact(sc);
                    case 2 -> searchContact(sc);
                    case 3 -> deleteContact(sc);
                    case 4 -> saveToFile();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number, not text.");
            } catch (ContactNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File Error: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } finally {
                System.out.println("(Action attempted)");
            }
        }
 
        sc.close();
        System.out.println("Program ended.");
    }
 
    static void addContact(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
 
        if (name.trim().isEmpty() || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Name/Phone cannot be empty.");
        }
 
        contacts.add(name + "-" + phone);
        System.out.println("Contact added.");
    }
 
    static void searchContact(Scanner sc) throws ContactNotFoundException {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
 
        for (String c : contacts) {
            if (c.toLowerCase().startsWith(name.toLowerCase() + "-")) {
                System.out.println("Found: " + c);
                return;
            }
        }
        throw new ContactNotFoundException(name + " not found in contact book.");
    }
 
    static void deleteContact(Scanner sc) throws ContactNotFoundException {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();
 
        boolean removed = contacts.removeIf(c -> c.toLowerCase().startsWith(name.toLowerCase() + "-"));
 
        if (!removed) {
            throw new ContactNotFoundException(name + " not found, cannot delete.");
        }
        System.out.println("Contact deleted.");
    }
 
    static void saveToFile() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String c : contacts) {
                pw.println(c);
            }
        }
        System.out.println("Saved to " + FILE_NAME);
    }
}
