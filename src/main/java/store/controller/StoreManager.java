package store.controller;

import store.model.OrderedProduct;
import store.model.ProductManager;
import store.service.PromotionService;
import store.model.Product;
import store.util.parser.InputParser;
import store.view.InputView;
import store.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public abstract class StoreManager {
    public static OrderResult getOrder(Map<Integer, Product> productList) {
        Map<Integer, Product> products = OutputView.printProductList(productList);
        Map<String, Integer> orderList = InputView.inputOrder(products);
        Map<Integer, Product> productByOrder = ProductManager.getOrderedProduct(products, orderList);
        List<OrderedProduct> orderQuantities = InputParser.getRequestProduct(orderList, productByOrder);
        return new OrderResult(productByOrder, orderQuantities);
    }

    public static void payments(Map<Integer, Product> productByOrder, List<OrderedProduct> orderQuantities) {
        Map<Product, String> ProductItem = getProductByOrderList(productByOrder);

        Map<String, OrderedProduct> orderedItem = getOrderItemList(orderQuantities);

        PromotionService.apply(ProductItem, orderedItem);

    }

    private static Map<String, OrderedProduct> getOrderItemList(List<OrderedProduct> orderQuantities) {
        Map<String, OrderedProduct> orderedItem = new LinkedHashMap<>();
        for (OrderedProduct orderedProduct : orderQuantities) {
            orderedItem.put(orderedProduct.getName(), orderedProduct);
        }
        return orderedItem;
    }

    private static Map<Product, String> getProductByOrderList(Map<Integer, Product> productByOrder) {
        Map<Product, String> ProductItem = new LinkedHashMap<>();
        for (Product product : productByOrder.values()) {
            ProductItem.put(product, product.getName());
        }
        return ProductItem;
    }

    public record OrderResult(
            Map<Integer, Product> productByOrder,
            List<OrderedProduct> orderQuantities
    ) {
    }
}