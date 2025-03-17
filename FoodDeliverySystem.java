import java.util.*;

abstract class FoodItem{
    String itemName;
    double price;
    int quantity;

    FoodItem(String itemName, double price, int quantity){
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void getItemDetails() {
        System.out.println("Item: " + itemName + ", Price: " + price + ", Quantity: " + quantity);
    }

    public abstract double calculateTotalPrice();
}
interface Discountable {
    void applyDiscount(double discountRate);
    double getDiscountDetails();
}

class VegItem extends FoodItem implements Discountable {
    private double discount;

    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
        this.discount = 0.0;
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity() - discount;
    }

    @Override
    public void applyDiscount(double discountRate) {
        this.discount = getPrice() * getQuantity() * discountRate / 100;
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}

class NonVegItem extends FoodItem implements Discountable {
    private double discount;
    private static final double ADDITIONAL_CHARGE = 50.0;

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
        this.discount = 0.0;
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity() + ADDITIONAL_CHARGE - discount;
    }

    @Override
    public void applyDiscount(double discountRate) {
        this.discount = getPrice() * getQuantity() * discountRate / 100;
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}


public class FoodDeliverySystem {
    public static void main(String[] args) {
        List<FoodItem> order = new ArrayList<>();

        VegItem maharajaBurger = new VegItem("Maharaja Burger", 120, 2);
        NonVegItem chickenBurger = new NonVegItem("Chicken Burger", 250, 1);

        maharajaBurger.applyDiscount(10); // 10% discount
        chickenBurger.applyDiscount(5); // 5% discount

        order.add(maharajaBurger);
        order.add(chickenBurger);

        double totalOrderPrice = 0;

        for (FoodItem item : order) {
            item.getItemDetails();
            System.out.println("Total Price: " + item.calculateTotalPrice());
            if (item instanceof Discountable) {
                System.out.println("Discount Applied: " + ((Discountable) item).getDiscountDetails());
            }
            System.out.println();
            totalOrderPrice += item.calculateTotalPrice();
        }

        System.out.println("Final Order Total: " + totalOrderPrice);
    }
}