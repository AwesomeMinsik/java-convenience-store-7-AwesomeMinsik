package store.model;

import java.text.NumberFormat;

public class OrderedProduct {
    private String name;
    private int price;
    private int quantity;
    private int totalPrice;
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

    public void setQuantity(int orderedQuantity){
        this.quantity=orderedQuantity;
    }

    @Override
    public String toString() {
            NumberFormat numberFormat = NumberFormat.getInstance();
            String formattedPrice = numberFormat.format(totalPrice);
            return  name +"\t\t\t\t"+ quantity + "\t\t\t"+ formattedPrice + "원 ";
    }
}
