import java.util.ArrayList;
import java.util.List;

public class Fridge {
    // add attributes
    private List<String> foodItems;
    private int balance;

    // add constructors
    public Fridge(int initialiseBalance) { // initialise both food and fridge balance
        // check if negative
        if (initialiseBalance < 0) {
            System.out.println("Error");
            this.balance = 0;
        } else {
            this.balance = initialiseBalance;
        }
        this.foodItems = new ArrayList<>();
    }

    // add methods
    public void addFood(String item, int cost) {
        // check validity
        if (item == null || item.trim().isEmpty() || cost < 0) {
            System.out.println("Error");
            return; // stops if invalid
        }

        // check if we have enough money
        if (cost > balance) {
            System.out.println("Error");
            return; // stops if too broke
        }

        // all checks pass
        foodItems.add(item);
        balance = balance - cost;

        System.out.println("Item " + item + " has been added to the fridge.");
    }

    public void getFood(String item) {
        // check validity
        if (item == null || item.trim().isEmpty()) {
            System.out.println("Error");
            return;
        }

        // check if item in fridge
        if (foodItems.contains(item)) {
            foodItems.remove(item); // remove item from fridge
            System.out.println("Item " + item + " has been removed from the fridge.");
        } else {
            System.out.println("Error");
        }
    }

    public void checkStatus() {
        System.out.println("Food items:");
        if (foodItems.isEmpty()) { // checks if theres anything in fridge
            System.out.println("(none)");
        } else {
            for (String food : foodItems) { // iterates through food in fridge
                System.out.println(food);
            }
        }
        System.out.println("Balance: €" + balance);
    }
}
