package store.service;

import store.controller.BillLetter;
import store.model.OrderedProduct;
import store.model.Product;
import store.view.InputView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PromotionService {

    private static Set<String> promotedProducts = new HashSet<>();

    public static void apply(Map<Product, String> productByOrder, Map<String, OrderedProduct> orderedItem) {
        int promotinalTotalPrice = 0;
        int nonPromotionalTotal = 0;

        for (Map.Entry<Product, String> entry : productByOrder.entrySet()) {
            Product product = entry.getKey();
            OrderedProduct orderedProduct = orderedItem.get(product.getName());
            int orderedProductQuantity = orderedProduct.getQuantity();
            int dealQuantity = 0;

            if (!product.getPromotion().getName().isEmpty())
                dealQuantity = getMaxPromotionQuantity(product);

            if (dealQuantity > 0) {
                int realQuantity = Math.min(dealQuantity, orderedProductQuantity);
                if (!product.getPromotion().getName().isEmpty()) {
                    promotinalTotalPrice = getTotalWithDeductStock(orderedProduct, product, realQuantity, promotinalTotalPrice);
                }
                promotedProducts.add(product.getName());
                orderedProductQuantity -= realQuantity;
            }

            if (orderedProductQuantity > 0 && !promotedProducts.contains(product.getName())) {
                if (product.getPromotion().getName().isEmpty()) {
                    product.deductStockByQuantity(orderedProductQuantity);
                    nonPromotionalTotal+=orderedProduct.getTotalPrice();
                }
            }
        }
        int discountedTotal = getDiscountedTotal(nonPromotionalTotal);
        System.out.println("discountedTotal = " + discountedTotal);
        int allTotal = promotinalTotalPrice + nonPromotionalTotal;

        BillLetter.getPaymentsResult(orderedItem, allTotal);
    }

    private static int getDiscountedTotal(int nonPromotionalTotal) {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        if (InputView.applyMembershipDiscount().equalsIgnoreCase("y")) {
            int discountedTotal = (int) (nonPromotionalTotal * 0.7);
            if (nonPromotionalTotal - discountedTotal > 8000) {
                return nonPromotionalTotal - 8000;
            }
            return discountedTotal;
        }
        return nonPromotionalTotal;
    }

    private static int getTotalWithDeductStock(OrderedProduct orderedProduct, Product product, int realQuantity, int total) {
        int freeItem = 0;
        freeItem = orderedProduct.getQuantity() / product.getPromotion().getBuy();
        if (orderedProduct.getQuantity() % (product.getPromotion().getBuy() + 1) == 0) {
            product.deductStockByQuantity(realQuantity);
            total += orderedProduct.getTotalPrice();
        }
        if (!(orderedProduct.getQuantity() % (product.getPromotion().getBuy() + 1) == 0)) {
            product.deductStockByQuantity(realQuantity + freeItem);
            orderedProduct.setQuantity(realQuantity + freeItem);
            total += orderedProduct.getTotalPrice();
        }
        return total;
    }

    private static int getMaxPromotionQuantity(Product product) {
        int maxPromotion = (product.getQuantity() / (product.getPromotion().getBuy() + 1)) * (product.getPromotion().getBuy() + 1);
        return Math.min(maxPromotion, product.getQuantity());
    }
}