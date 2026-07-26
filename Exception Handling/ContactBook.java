
import java.io.*;
import java.util.*;
 
// Custom checked exception -> file me pehle missing thi, ab add kar di
// "extends Exception" isse CHECKED exception banata hai
class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
 
/*
 * ===== EXCEPTION HANDLING CONCEPTS COVERED =====
 * 1. try-catch-finally
 * 2. Multiple catch blocks (specific -> general order)
 * 3. throw   -> khud exception create karke phekna
 * 4. throws  -> method signature me batana ki yeh exception de sakta hai
 * 5. Checked exception   -> ContactNotFoundException (custom), IOException (built-in)
 * 6. Unchecked exception -> IllegalArgumentException, NumberFormatException
 * 7. finally block       -> hamesha chalta hai, cleanup ke liye
 */
public class ContactBook {
 
    // Simplicity ke liye ArrayList<String> use kiya: "Name-Phone" format
    static List<String> contacts = new ArrayList<>();
    static final String FILE_NAME = "contacts.txt";
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
 
        while (running) {
            System.out.println("\n1.Add  2.Search  3.Delete  4.Save  5.Exit");
            System.out.print("Choice: ");
 
            try {
                int choice = Integer.parseInt(sc.nextLine()); // NumberFormatException yahan aa sakti hai
 
                switch (choice) {
                    case 1 -> addContact(sc);
                    case 2 -> searchContact(sc);
                    case 3 -> deleteContact(sc);
                    case 4 -> saveToFile();
                    case 5 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            }
            // ---- Specific exceptions pehle ----
            catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number, not text.");
            } catch (ContactNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File Error: " + e.getMessage());
            }
            // ---- Sabse last me generic Exception (safety net) ----
            catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            } finally {
                // Yeh block HAMESHA chalega, chahe exception aaye ya na aaye
                System.out.println("(Action attempted)");
            }
        }
 
        sc.close();
        System.out.println("Program ended.");
    }
 
    // ---------------- ADD ----------------
    static void addContact(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
 
        // "throw" -> khud check karke exception create ki
        if (name.trim().isEmpty() || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Name/Phone cannot be empty.");
        }
 
        contacts.add(name + "-" + phone);
        System.out.println("Contact added.");
    }
 
    // ---------------- SEARCH ----------------
    // "throws" -> yeh method ContactNotFoundException de sakta hai, caller ko handle karna hoga
    static void searchContact(Scanner sc) throws ContactNotFoundException {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
 
        for (String c : contacts) {
            if (c.toLowerCase().startsWith(name.toLowerCase() + "-")) {
                System.out.println("Found: " + c);
                return;
            }
        }
        // Kuch nahi mila -> custom exception throw kar do
        throw new ContactNotFoundException(name + " not found in contact book.");
    }
 
    // ---------------- DELETE ----------------
    static void deleteContact(Scanner sc) throws ContactNotFoundException {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();
 
        boolean removed = contacts.removeIf(c -> c.toLowerCase().startsWith(name.toLowerCase() + "-"));
 
        if (!removed) {
            throw new ContactNotFoundException(name + " not found, cannot delete.");
        }
        System.out.println("Contact deleted.");
    }
 
    // ---------------- SAVE TO FILE ----------------
    static void saveToFile() throws IOException {
        // try-with-resources -> writer khud-ba-khud close ho jayega
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String c : contacts) {
                pw.println(c);
            }
        }
        System.out.println("Saved to " + FILE_NAME);
    }
}
 