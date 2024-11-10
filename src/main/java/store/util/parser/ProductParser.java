package store.util.parser;

import store.factory.ProductFactory;
import store.factory.PromotionFactory;
import store.model.Product;
import store.policy.PromotionPolicy;

import java.util.*;

import static store.util.files.PromotionLoader.getPromotionList;

public abstract class ProductParser {
    public static Map<Integer, Product> getProductList(List<String> parts) {

        Map<Integer, Product> products = new LinkedHashMap<>();
        int key=0;
        for (String line : parts) {
            key++;
            parseListToProduct(line, products,key);
        }
        return products;
    }

    private static void parseListToProduct(String line, Map<Integer, Product> products, int key) {
        List<String> parts = Arrays.stream(line.split(","))
                .map(String::trim)
                .toList();
        products.put(key,ProductFactory.createProductObject(parts, getPromotionList()));
    }


    public static PromotionPolicy getPromotionType(String promotion, List<PromotionPolicy> promotionPolicies) {
        for (PromotionPolicy promotionPolicy : promotionPolicies) {
            if (promotionPolicy.getName().equalsIgnoreCase(promotion))
                return promotionPolicy;
        }
        return null;
    }

    public static PromotionPolicy parseStringToPromotionType(String line) {
        String[] split = line.split(",");
        return PromotionFactory.createPromotion(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[0], split[0]);
    }
}
