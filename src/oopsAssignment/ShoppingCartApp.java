package oopsAssignment;

public class ShoppingCartApp {
    public static void main(String[] args) {
        System.out.println("🛍️  Welcome to the Online Shopping Cart Demo!");
        System.out.println("==========================================\n");

        // Create a shopping cart with listener
        ShoppingCartWithListener cart = new ShoppingCartWithListener();

        // Display initial empty cart
        cart.displayCart();

        // Add some items to demonstrate the listener
        System.out.println("Adding items to cart:");
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        cart.addItem("Keyboard", 79.99);
        cart.addItem("Monitor", 299.99);

        // Display cart after adding items
        cart.displayCart();

        // Remove an item to test removal listener
        System.out.println("Removing an item:");
        cart.removeItem("Mouse");

        // Display cart after removal
        cart.displayCart();

        // Try to remove an item that doesn't exist
        System.out.println("Trying to remove non-existent item:");
        cart.removeItem("Headphones");

        // Add more items
        System.out.println("Adding more items:");
        cart.addItem("Headphones", 149.99);
        cart.addItem("USB Drive", 19.99);

        // Display final cart
        cart.displayCart();

        // Clear the cart to test clear listener
        System.out.println("Clearing the cart:");
        cart.clearCart();

        // Display empty cart after clearing
        cart.displayCart();

        System.out.println("🎉 Shopping cart demo completed!");
    }
}
