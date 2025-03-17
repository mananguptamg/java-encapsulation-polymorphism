import java.util.*;

abstract class Product{
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double calculateDiscount();

    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
    }
}

class Electronics extends Product implements Taxable{
    private double discountRate = 0.10; // 10% discount
    private double taxRate = 0.15; // 15% tax

    public Electronics(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * discountRate;
    }

    @Override
    public double calculateTax() {
        return getPrice() * taxRate;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: " + (taxRate * 100) + "%";
    }
}

class Clothing extends Product implements Taxable{
    private double discountRate = 0.20; // 20% discount
    private double taxRate = 0.08; // 8% tax

    public Clothing(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * discountRate;
    }

    @Override
    public double calculateTax() {
        return getPrice() * taxRate;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Rate: " + (taxRate * 100) + "%";
    }
}

class Groceries extends Product{
    private double discountRate = 0.05; // 5% discount

    public Groceries(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * discountRate;
    }
}

interface Taxable {
    double calculateTax();
    String getTaxDetails();
}
public class ECommercePlatform {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        Electronics laptop = new Electronics(101, "Laptop", 50000);
        Clothing tshirt = new Clothing(102, "T-Shirt", 500);
        Groceries apples = new Groceries(103, "Apples", 30);

        products.add(laptop);
        products.add(tshirt);
        products.add(apples);

        for (Product product : products) {
            product.displayDetails();
            double discount = product.calculateDiscount();
            double tax = (product instanceof Taxable) ? ((Taxable) product).calculateTax() : 0;
            double finalPrice = product.getPrice() + tax - discount;
            System.out.println("Discount: " + discount);
            System.out.println("Tax: " + tax);
            System.out.println("Final Price: " + finalPrice);
            System.out.println();
        }
    }
}

//SampleOutput
//Product ID: 101
//Name: Laptop
//Price: 50000.0
//Discount: 5000.0
//Tax: 7500.0
//Final Price: 52500.0
//
//Product ID: 102
//Name: T-Shirt
//Price: 500.0
//Discount: 100.0
//Tax: 40.0
//Final Price: 440.0
//
//Product ID: 103
//Name: Apples
//Price: 30.0
//Discount: 1.5
//Tax: 0.0
//Final Price: 28.5