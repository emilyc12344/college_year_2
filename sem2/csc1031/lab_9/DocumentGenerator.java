package lab_9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Reader{
    Scanner scanner = new Scanner(System.in);
}

abstract class Document implements Reader {
    protected List<String> content = new ArrayList<>();

    // Templaete method
    public final void generateDocument() {
        createHeader();
        createBody();
        createFooter();
        printDocument();
    }

    private void createHeader() {
        System.out.print("Enter company name: ");
        String companyName = scanner.nextLine();
        if (companyName.isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty.");
        }

        System.out.print("Enter date (DD/MM/YYYY): ");
        String date = scanner.nextLine();
        if (date.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty.");
        }

        // Add lines to the document
        content.add("Company: " + companyName);
        content.add("Date: " + date);
    }

    abstract void createBody();

    void createFooter() {
        content.add("=========================");
    }

    void printDocument() {
        System.out.println("\n=== Printing Document ===");
        for (String line : content) {
            System.out.println(line);
        }
    }
}

class Invoice extends Document {
    @Override
    void createBody() {
        content.add(0, "=== INVOICE ===");
        System.out.print("Enter total amount: ");
        String input = scanner.nextLine();
        double totalAmount;
        try {
            totalAmount = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Total amount must be numeric.");
        }
        if (totalAmount <= 0) {
            throw new IllegalArgumentException("Total amount must be positive.");
        }

        content.add("Total Due: €" + totalAmount);
    }

    @Override
    void createFooter() {
        content.add("Prepared by: AutoDoc System");
        content.add("Document Type: INVOICE");
        super.createFooter();
    }
}

class Report extends Document {
    @Override
    void createBody() {
        content.add(0, "=== REPORT ===");
        System.out.print("Enter report summary: ");
        String summary = scanner.nextLine();
        if (summary.isEmpty()) {
            System.out.println("Warning: Summary is empty.");
        }
        content.add("Report Summary: " + summary);
    }

    @Override
    void createFooter() {
        content.add("Reviewed by: Management Department");
        super.createFooter();
    }
}

class Receipt extends Document {
    @Override
    void createBody() {
        content.add(0, "=== RECEIPT ===");
        System.out.print("Enter amount paid: ");
        double amountPaid = scanner.nextDouble();
        if (amountPaid <= 0) {
            throw new IllegalArgumentException("Amount paid must be positive.");
        }

        System.out.print("Enter number of items: ");
        int itemsCount = scanner.nextInt();
        if (itemsCount <= 0) {
            throw new IllegalArgumentException("Items count must be positive.");
        } else if (itemsCount == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        double pricePer = amountPaid / itemsCount;

        content.add("Total Paid: €" + amountPaid);
        content.add("Items Purchased: " + itemsCount);
        content.add("Price per Item: €" + pricePer);
    }

    @Override
    void createFooter() {
        content.add("Prepared by: AutoDoc System");
        content.add("Document Type: RECEIPT");
        super.createFooter();
    }
}

public class DocumentGenerator implements Reader{
    public static void main(String[] args) {
        try {
            System.out.println("Choose document type: (INV) Invoice, (REP) Report, (REC) Receipt");
            String choice = scanner.nextLine();
            Document document;
            switch (choice) {
                case "INV":
                    document = new Invoice();
                    break;
                case "REP":
                    document = new Report();
                    break;
                case "REC":
                    document = new Receipt();
                    break;
                default:
                    System.out.println("Invalid choice. Exiting.");
                    return;
            }

            document.generateDocument();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}