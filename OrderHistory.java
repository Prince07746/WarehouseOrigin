package Warehouse4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<String> orderHistory = new ArrayList<>();

    // Add a product to the user's order history
    public void addOrder(Product product) {
        orderHistory.add(product.toString());  // Add product details to history
    }

    // Display the user's order history
    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders in history.");
        } else {
            System.out.println("\n--- Order History ---");
            for (String order : orderHistory) {
                System.out.println(order);
            }
        }
    }

    // Save the user's order history to a file
    public void saveToFile(String username) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(username + "_orderHistory.txt", true)))) {
            for (String order : orderHistory) {
                out.println(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read the user's order history from a file
    public void readFromFile(String username) {
        try (BufferedReader in = new BufferedReader(new FileReader(username + "_orderHistory.txt"))) {
            String order;
            while ((order = in.readLine()) != null) {
                orderHistory.add(order);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Order history file not found for user: " + username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
