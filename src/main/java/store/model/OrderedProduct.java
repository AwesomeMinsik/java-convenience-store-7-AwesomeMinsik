package store.model;

import java.text.NumberFormat;

public class OrderedProduct {
    //[콜라-1],[감자칩-10]
    private String name;
    private int price;
    private int quantity;
    private int totalPrice;

    public OrderedProduct(String name, int price, int quantity, int totalPrice) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
            NumberFormat numberFormat = NumberFormat.getInstance();
            String formattedPrice = numberFormat.format(price);
            return "- " + name + " " + formattedPrice + "원 " + quantity + "개"+totalPrice;
    }
}
