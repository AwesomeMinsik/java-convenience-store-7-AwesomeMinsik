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

    public static void deductStockProduct(Product product, OrderedProduct orderedProduct) {

            if (product.getPromotion().getBuy() == orderedProduct.getQuantity())
                isEqualQuantityPromotionBuy(product, orderedProduct);
    }

    private static void isEqualQuantityPromotionBuy(Product product, OrderedProduct orderedProduct) {
        int freeItem;
        boolean isYes=false;
        freeItem = orderedProduct.getQuantity() / product.getPromotion().getBuy();
        if (freeItem > 0) {
            System.out.println("현재 " + product.getName() + "은(는) " + freeItem + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
            isYes = inputIsYes(product, orderedProduct);

        }
        if (isYes) {
            System.out.println(product);
            System.out.println("orderedProduct = " + orderedProduct);
            System.out.println("freeItem = " + freeItem);
            product.deductStockByQuantity(orderedProduct.getQuantity() + freeItem);
            System.out.println(product);
        } else product.deductStockByQuantity(orderedProduct.getQuantity());
    }

    public static boolean inputIsYes(Product product, OrderedProduct orderedProduct) {
        if (product.getPromotion().getBuy() == orderedProduct.getQuantity()) {
            String input = InputView.applyMembershipDiscount();
            return input.equalsIgnoreCase("y");
        }
        return false;
    }
}