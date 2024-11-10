package store.model;

import store.policy.PromotionPolicy;
import java.text.NumberFormat;
public class Product {
    private String name;
    private int price;
    private int quantity;
    private PromotionPolicy promotion;

    public Product(String name, int price, int quantity, PromotionPolicy promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = (promotion != null) ? promotion : new PromotionPolicy("", 0, 0, "","");
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

    public void deductStockByQuantity(int orderedQuantity) {
        this.quantity= quantity-orderedQuantity;
    }

    public PromotionPolicy getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedPrice = numberFormat.format(price);
        String quantityText = (quantity == 0) ? "재고 없음" : quantity + "개";
        String promotionName = (promotion != null) ? promotion.getName() : "";
        return "- " + name + " " + formattedPrice + "원 " + quantityText + " " + promotionName;
    }

}
