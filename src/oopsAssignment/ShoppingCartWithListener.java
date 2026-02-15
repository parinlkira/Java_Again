package oopsAssignment;

// Listener interface for shopping cart events
interface CartListener {
    void onItemAdded(String itemName, double price);
    void onItemRemoved(String itemName);
    void onCartCleared();
}

public class ShoppingCartWithListener {
    private java.util.List<String> items;
    private java.util.List<Double> prices;
    private CartListener listener;

    // Constructor
    public ShoppingCartWithListener() {
        items = new java.util.ArrayList<>();
        prices = new java.util.ArrayList<>();
        listener = new CartEventListener();
    }

    // Inner class implementing the CartListener interface
    private class CartEventListener implements CartListener {
        @Override
        public void onItemAdded(String itemName, double price) {
            System.out.println("🛒 Event: Item '" + itemName + "' added to cart for $" + String.format("%.2f", price));
            System.out.println("   Current cart total: $" + String.format("%.2f", getTotal()));
        }

        @Override
        public void onItemRemoved(String itemName) {
            System.out.println("🗑️  Event: Item '" + itemName + "' removed from cart");
        }

        @Override
        public void onCartCleared() {
            System.out.println("🧹 Event: Shopping cart cleared");
        }
    }

    // Method to add item to cart
    public void addItem(String itemName, double price) {
        items.add(itemName);
        prices.add(price);
        listener.onItemAdded(itemName, price);
    }

    // Method to remove item from cart
    public void removeItem(String itemName) {
        int index = items.indexOf(itemName);
        if (index != -1) {
            items.remove(index);
            prices.remove(index);
            listener.onItemRemoved(itemName);
        } else {
            System.out.println("❌ Item '" + itemName + "' not found in cart");
        }
    }

    // Method to clear cart
    public void clearCart() {
        items.clear();
        prices.clear();
        listener.onCartCleared();
    }

    // Method to get total price
    public double getTotal() {
        double total = 0.0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }

    // Method to display cart contents
    public void displayCart() {
        System.out.println("\n📋 Shopping Cart Contents:");
        if (items.isEmpty()) {
            System.out.println("   Cart is empty");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + items.get(i) + " - $" + String.format("%.2f", prices.get(i)));
            }
            System.out.println("   Total: $" + String.format("%.2f", getTotal()));
        }
        System.out.println();
    }

    // Method to get cart size
    public int getSize() {
        return items.size();
    }
}