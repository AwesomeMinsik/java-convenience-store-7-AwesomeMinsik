package store.model;

import store.factory.OrderItemFactory;
import store.view.InputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static void setProductPriceByQuantity(Map<String, Integer> orderedProduct, Product product) {
        int totalValue = product.getPrice() * orderedProduct.get(product.getName());
        product.setTotalPrice(totalValue);
    }

    private static void createOrderProduct(List<OrderedProduct> orderedProducts, Product product, Integer quantity) {
        orderedProducts.add(OrderItemFactory.createProductObject(product, quantity));
    }


}