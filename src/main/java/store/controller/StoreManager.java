package store.controller;

import store.factory.OrderItemFactory;
import store.model.OrderedProduct;
import store.model.ProductManager;
import store.util.files.FileManager;
import store.model.Product;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class StoreManager {
    public static void getOrder() {
        List<OrderedProduct> orderedProducts = new ArrayList<>();

        List<Product> products = OutputView.printProductList(FileManager.getProductList());
        List<Map<String, Integer>> orderList = InputView.inputOrder();
        List<Product> productByOrder = ProductManager.findProductByOrderInput(products, orderList);

        List<Integer> orderQuantities = getOrderQuantities(orderList);
        for (int i = 0; i < productByOrder.size(); i++) {
            createOrder(orderedProducts, productByOrder.get(i), orderQuantities.get(i));
        }

        String membershipStatus = InputView.applyMembershipDiscount();
        OutputView.printBillLetter();
        payments(orderedProducts,membershipStatus);

    }
    private static void createOrder(List<OrderedProduct> orderedProducts, Product product, Integer quantity) {
        orderedProducts.add(OrderItemFactory.createProductObject(product, quantity));
    }

    public static List<Integer> getOrderQuantities(List<Map<String, Integer>> orderList) {
        return orderList.stream()
                .flatMap(order -> order.values().stream())
                .collect(Collectors.toList());
    }

    public static void payments(List<OrderedProduct> productByOrderInput, String membershipStatus) {
        if (membershipStatus.equalsIgnoreCase("Y")) {
            for (OrderedProduct product : productByOrderInput) {
                System.out.println(product.getName()+"\t\t"+product.getQuantity()+"\t\t"+product.getTotalPrice());
            }
        }
        if (membershipStatus.equalsIgnoreCase("N")) {
        }
    }
}