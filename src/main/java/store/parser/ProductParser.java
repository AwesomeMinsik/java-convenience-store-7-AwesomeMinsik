package store.parser;

import store.factory.ProductFactory;
import store.model.Product;
import store.policy.PromotionPolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static store.policy.PromotionPolicy.*;

public abstract class ProductParser {
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
        products.add(ProductFactory.createProductObject(parts));
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

    public static List<String> parseProductToString(List<Product> products) {
        List<String> productStrings = new ArrayList<>();
        for (Product product : products) {
            productStrings.add(String.join(",",
                            product.getName(),
                            String.valueOf(product.getPrice()),
                            String.valueOf(product.getQuantity()),
                            toString(product.getPromotion())));
        }
        return productStrings;
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
