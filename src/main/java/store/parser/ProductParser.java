package store.parser;

import store.model.Product;
import store.policy.PromotionPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static store.policy.PromotionPolicy.*;

public class ProductParser {
    public static List<Product> getProductList(List<String> parts) {
        List<Product> products = new ArrayList<>();
        for (String line : parts) {
            parseListToProduct(line, products);
        }
        return products;
    }

    private static void parseListToProduct(String line, List<Product> products) {
        List<String> parts = Arrays.stream(line.split(","))
                .map(String::trim)
                .toList();

        String name = parts.get(0);
        int price = Integer.parseInt(parts.get(1));
        int quantity = Integer.parseInt(parts.get(2));
        String promotionStr = parts.get(3);

        PromotionPolicy promotion = fromString(promotionStr);

        products.add(new Product(name, price, quantity, promotion));
    }

    public static List<String> convertProductToString(List<Product> products) {
        List<String> productStrings = new ArrayList<>();
        for (Product product : products) {
            String name = product.getName();
            int price = product.getPrice();
            int quantity = product.getQuantity();
            String promotion = toString(product.getPromotion());
            productStrings.add(String.join(",", name, String.valueOf(price), String.valueOf(quantity), promotion));
        }
        return productStrings;
    }

    public static PromotionPolicy fromString(String promotion) {
        if (promotion.equalsIgnoreCase("null")) {
            return NONE;
        }
        return switch (promotion) {
            case "MD추천상품" -> MD_RECOMMENDED;
            case "탄산2+1" -> BUY2GET1;
            case "반짝할인" -> LIMITED_DISCOUNT;
            default -> NONE;
        };
    }

    public static String toString(PromotionPolicy promotionPolicy) {
        if (promotionPolicy == NONE) {
            return "null";
        }

        return switch (promotionPolicy) {
            case MD_RECOMMENDED -> "MD추천상품";
            case BUY2GET1 -> "탄산2+1";
            case LIMITED_DISCOUNT -> "반짝할인";
            default -> "null";
        };
    }
}
