package store.model;

import java.text.NumberFormat;

public class OrderedProduct {
    private String name;
    private int price;
    private int quantity;
    private int totalPrice;
    private int freeItem;

    public OrderedProduct(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price*quantity;
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

    public int getFreeItem() {
        return freeItem;
    }

    @Override
    public String toString() {
            NumberFormat numberFormat = NumberFormat.getInstance();
            String formattedPrice = numberFormat.format(price);
            return "-" + name + " " + formattedPrice + "원 " + quantity + "개 "+totalPrice;
    }
}
