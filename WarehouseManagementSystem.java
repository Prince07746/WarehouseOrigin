package Warehouse4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarehouseManagementSystem {
    private static List<User> users = new ArrayList<>();
    private static Inventory inventory = new Inventory();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);
    private static double totalMoneyMade = 0.0;
    private static User currentUser; // Keep track of the logged-in user

    public static void main(String[] args) {
        initializeDemoUsers();
        cart.readFromFile();
        inventory.initializeProducts();
        WarehouseManagementSystem system = new WarehouseManagementSystem();

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void login() {
        System.out.println("Enter your username:");
        String enteredUsername = scanner.nextLine();
        System.out.println("Enter your password:");
        String enteredPassword = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                System.out.println("Login successful! Welcome, " + user.getUsername() + " (" + user.getType() + ")");
                currentUser = user; // Set the logged-in user
                if (user.getType().equalsIgnoreCase("Manager")) {
                    managementMenu();
                } else {
                    userMenu();
                }
                return;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
    }

    public static void registerUser() {
        System.out.println("Enter username for registration:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter user type (Manager/User):");
        String type = scanner.nextLine();

        if (!type.equalsIgnoreCase("Manager") && !type.equalsIgnoreCase("User")) {
            System.out.println("Invalid user type. Registration failed.");
            return;
        }

        // Initialize balance for the new user (default to 0.0 or any specific amount)
        double balance = 0.0; // Adjust as needed
        users.add(new User(username, password, type, balance));
        System.out.println("Registration successful. You can now login.");
    }

    private void userMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. Remove Item from Cart");
            System.out.println("4. View Remaining Balance");
            System.out.println("5. Purchase Items");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inventory.viewProducts();
                    break;
                case 2:
                    addItemToCart();
                    break;
                case 3:
                    removeItemFromCart();
                    break;
                case 4:
                    viewRemainingBalance(); // New option to view balance
                    break;
                case 5:
                    finalizePurchase();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void managementMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Management Menu ---");
            System.out.println("1. View Items");
            System.out.println("2. Add New Item");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inventory.viewProducts();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void initializeDemoUsers() {
        users.add(new User("Feza", "Melissa", "Manager", 1000.0));
        users.add(new User("Mundes", "Daniel", "User", 2000.0));
        users.add(new User("Yak", "Chuol", "User", 1500.0));
    }

    private static void addNewProduct() {
        System.out.print("Enter Product Type: ");
        String productType = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Selling Price: ");
        int sellingPrice = scanner.nextInt();
        System.out.print("Enter Expiring Year: ");
        int expiringYear = scanner.nextInt();
        System.out.print("Enter Purchasing Price: ");
        int purchasingPrice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Manufacturer: ");
        String manufacturer = scanner.nextLine();

        Product newProduct = new Product(productType, name, model, sellingPrice, expiringYear, purchasingPrice, manufacturer);
        inventory.addNewProduct(newProduct);
    }

    private static void addItemToCart() {
        System.out.println("Enter Product ID to add to cart:");
        int id = scanner.nextInt();
        Product product = inventory.getProductById(id);
        if (product != null) {
            cart.addItem(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void removeItemFromCart() {
        System.out.println("Enter Product ID to remove from cart:");
        int id = scanner.nextInt();
        Product product = inventory.getProductById(id);
        if (product != null) {
            cart.removeItem(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private void finalizePurchase() {
        double totalCost = cart.calculateTotalCost();
        if (totalCost > currentUser.getBalance()) {
            System.out.println("Insufficient balance. Your balance is: " + currentUser.getBalance());
            return;
        }

        totalMoneyMade += totalCost; // Update total money made
        currentUser.setBalance(currentUser.getBalance() - totalCost); // Deduct the cost from the user's balance
        System.out.println("Purchase successful! Total cost: " + totalCost);
        cart.clear(); // Clear the cart after purchase
    }

    private void viewRemainingBalance() {
        System.out.println("Your remaining balance is: " + currentUser.getBalance());
    }
}