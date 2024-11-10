package store.controller;

import store.model.OrderedProduct;
import store.model.ProductManager;
import store.service.PromotionService;
import store.util.files.FileManager;
import store.model.Product;
import store.util.parser.InputParser;
import store.view.InputView;
import store.view.OutputView;

import java.util.*;

public abstract class StoreManager {
    public static OrderResult getOrder() {
        Map<Integer, Product> products = OutputView.printProductList(FileManager.getProductList());
        Map<String, Integer> orderList = InputView.inputOrder();
        Map<Integer, Product> productByOrder = ProductManager.getOrderedProduct(products, orderList);
        List<OrderedProduct> orderQuantities = InputParser.getRequestProduct(orderList, productByOrder);
        return new OrderResult(productByOrder, orderQuantities);
    }

    public static void payments(Map<Integer, Product> productByOrder, List<OrderedProduct> orderQuantities) {
        Map<Product, String> ProductItem = new LinkedHashMap<>();
        for (Product product : productByOrder.values()) {
            ProductItem.put(product, product.getName());
        }
        Map<String, OrderedProduct> orderedItem = new LinkedHashMap<>();
        for (OrderedProduct orderedProduct : orderQuantities) {
            orderedItem.put(orderedProduct.getName(), orderedProduct);
        }
        PromotionService.apply(ProductItem, orderedItem);
    }

    public record OrderResult(
            Map<Integer, Product> productByOrder,
            List<OrderedProduct> orderQuantities
    ) {}
}