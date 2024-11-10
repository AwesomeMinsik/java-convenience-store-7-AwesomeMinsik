package store.service;

import store.model.OrderedProduct;
import store.model.Product;
import store.model.ProductManager;

import java.util.Map;
public abstract class PromotionService {

    public static void apply(Map<Product,String> productByOrder, Map<String,OrderedProduct> orderedItem) {
        for (Map.Entry<Product, String> entry : productByOrder.entrySet()) {
            Product product = entry.getKey();
            OrderedProduct orderedProduct = orderedItem.get(product.getName());
            ProductManager.deductStockProduct(product, orderedProduct);

            if (product.getPromotion().getName().isEmpty()) {
                ProductManager.deductStockProduct(product, orderedProduct);
            }
            else
                ProductManager.deductStockProduct(product, orderedProduct);
        }

    }
}
