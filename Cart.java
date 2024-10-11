package Warehouse4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items = new ArrayList<>();
    private static final String FILE_NAME = "cart_items.txt"; // Name of the file to store cart items

    public void addItem(Product product) {
        items.add(product);
        System.out.println("Item added to cart: " + product.getName());
    }

    public void removeItem(Product product) {
        if (items.remove(product)) {
            System.out.println("Item removed from cart: " + product.getName());
        } else {
            System.out.println("Item not found in the cart.");
        }
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : items) {
            totalCost += product.getSellingPrice();
        }
        return totalCost;
    }

    public void clear() {
        items.clear();
    }

    public List<Product> getItems() {
        return items;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : items) {
                writer.write(product.getName() + "," + product.getSellingPrice());
                writer.newLine();
            }
            System.out.println("Cart saved to file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving cart to file: " + e.getMessage());
        }
    }

    public void readFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved cart found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) { // Updated to match the number of expected attributes
                    String productType = parts[0]; // New parameter: product type
                    String name = parts[1];         // Name
                    String model = parts[2];        // Model
                    int sellingPrice = Integer.parseInt(parts[3]); // Selling price
                    int expiringYear = Integer.parseInt(parts[4]); // Expiring year
                    int purchasingPrice = Integer.parseInt(parts[5]); // Purchasing price
                    String manufacturer = parts[6]; // Manufacturer

                    // Create a new Product instance with the appropriate parameters
                    Product product = new Product(productType, name, model, sellingPrice, expiringYear, purchasingPrice, manufacturer);
                    items.add(product);
                }
            }
            System.out.println("Cart loaded from file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error reading cart from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing product price: " + e.getMessage());
        }
    }

}
