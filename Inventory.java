package Warehouse4;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products = new ArrayList<>();

    public void initializeProducts() {
        // Add some demo products
        products.add(new Product("Notebook", "Dell Inspiron", "Inspiron 15", 700, 2026, 500, "Dell"));
        products.add(new Product("Smartphone", "Samsung Galaxy S21", "Galaxy S21", 1200, 2025, 800, "Samsung"));
        products.add(new Product("Tablet", "Apple iPad", "iPad Pro", 1000, 2024, 700, "Apple"));
        products.add(new Product("Tablet", "XMM", "New Model", 2000, 2028, 1800, "Notepors"));
        products.add(new Product("Tablet", "XMM", "New Model", 2000, 2028, 1800, "Notepors"));
    }

    public void addNewProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully.");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n--- Product List ---");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}