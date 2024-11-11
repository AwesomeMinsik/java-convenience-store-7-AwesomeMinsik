package store.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductManager {
    public static Map<Integer, Product> getOrderedProduct(Map<Integer, Product> products, Map<String, Integer> orderedProductList) {
        int key = 0;
        Map<Integer, Product> matchedProducts = new HashMap<>();
        findByProductName(products, orderedProductList, matchedProducts, key);
        return matchedProducts;
    }

    private static void findByProductName(Map<Integer, Product> products, Map<String, Integer> orderedProductList, Map<Integer, Product> matchedProducts, int key) {
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            if (orderedProductList.containsKey(product.getName())) {
                matchedProducts.put(key++, product);
            }
        }
    }
}