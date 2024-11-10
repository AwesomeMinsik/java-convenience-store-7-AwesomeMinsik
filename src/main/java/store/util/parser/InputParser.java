package store.util.parser;

import store.factory.OrderItemFactory;
import store.model.OrderedProduct;
import store.model.Product;
import store.util.validate.StringValidator;

import java.util.*;

public class InputParser {
    public static Map<String, Integer> parseOrder(String order) {
        return parseProduct(order);
    }
    public static List<OrderedProduct> getRequestProduct(Map<String, Integer> orderList, Map<Integer, Product> productByOrder) {
        List<OrderedProduct> orderedProducts = new ArrayList<>();

        Map<String, Product> productNameMap = new HashMap<>();
        for (Product product : productByOrder.values()) {
            productNameMap.put(product.getName(), product);
        }

        for (Map.Entry<String, Integer> entry : orderList.entrySet()) {
            String productName = entry.getKey();
            Integer quantity = entry.getValue();

            if (productNameMap.containsKey(productName)) {
                Product product = productNameMap.get(productName);
                orderedProducts.add(OrderItemFactory.createProductObject(product, quantity));
            }
        }

        return orderedProducts;
    }
    private static Map<String, Integer> parseProduct(String productStr) {
        Map<String, Integer> productMap = new LinkedHashMap<>();
        String[] split = productStr.split(",");
        for (String string : split) {
            String cleaned = string.replace("[", "").replace("]", "");
            String[] parts = cleaned.split("-");
            if (parts.length == 2) {
                String productName = parts[0].trim();
                int quantity = StringValidator.validateNumber(parts[1].trim());
                productMap.put(productName, quantity);
            }
        }
        return productMap;
    }
}
