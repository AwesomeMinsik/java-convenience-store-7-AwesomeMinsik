package store.model;

import store.policy.PromotionType;

import java.text.NumberFormat;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private PromotionType promotion;

    public Product(String name, int price, int quantity, PromotionType promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PromotionType getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionType promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedPrice = numberFormat.format(price);
        return "- " + name + " " + formattedPrice + "원 " + quantity + "개 " + promotion;
    }
}
